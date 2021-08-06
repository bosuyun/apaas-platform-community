package com.bosuyun.platform.data.driver;

import com.bosuyun.platform.common.misc.SystemFieldConstants;
import com.bosuyun.platform.data.driver.executor.QueryExecutor;
import com.fasterxml.jackson.databind.JsonNode;
import com.bosuyun.platform.common.context.ReqContext;
import com.bosuyun.platform.data.driver.executor.PostgresQueryExecutor;
import com.bosuyun.platform.data.exception.DataDriverException;
import com.bosuyun.platform.common.misc.DataNode;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * JSON Schema转为Postgresql
 * <p>
 * Created by liuyuancheng on 2021/5/27  <br/>
 */
@Slf4j
public class JsonSchemaToPgTable {

    public StringBuilder sql = new StringBuilder();

    /**
     * jsonSchema的类型向pgSql的类型映射
     */
    private final static Map<String, String> pgTypeMap = new HashMap<>() {{
        put("integer", "integer");
        put("string", "text");
        put("number", "float8");
        //todo 需要扩展jsonSchema 增加money类型;在精确计算的场景中使用numeric确保精度
        put("money", "money");
        put("object", "jsonb");  // 复杂类型（使用jsonb检索）
    }};

    private final static Map<String, String> typeDefaultValueMap = new HashMap<>() {{
        put("integer", "0");
        put("string", "''");
        put("number", "0");
        //todo 需要扩展jsonSchema 增加money类型;在精确计算的场景中使用numeric确保精度
        put("money", "0");
    }};

    private final static Map<String, String> reversedPgTypeMap = pgTypeMap.entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

    /**
     * 创建表SQL
     *
     * @param context
     * @param jsonSchema
     * @return
     */
    public String createTable(@NonNull final ReqContext context, @NonNull final JsonNode jsonSchema) {
        sql.delete(0, sql.length());
        sql.append("CREATE TABLE ").append(context.getTableNameChain())
                .append("(")
                .append(SystemFieldConstants.DB_SYSTEM_FIELD_ID).append(" serial4 PRIMARY KEY NOT NULL,");
        sql.append("deleted boolean NOT NULL DEFAULT FALSE,");
        //todo 测试标记 （允许特定用户登录账号，但添加的所有的记录均为测试数据）
        sql.append("for_test boolean NOT NULL DEFAULT FALSE,");
        sql.append(SystemFieldConstants.DB_SYSTEM_FIELD_DELETED_AT).append(" timestamptz,");
        sql.append(SystemFieldConstants.DB_SYSTEM_FIELD_UPDATED_AT).append(" timestamptz,");
        sql.append(SystemFieldConstants.DB_SYSTEM_FIELD_CREATED_AT).append(" timestamptz NOT NULL DEFAULT NOW(),");
        sql.append(SystemFieldConstants.DB_SYSTEM_FIELD_DISCARDED).append(" jsonb NOT NULL DEFAULT '[]',");
        var objectProperties = jsonSchema.get("properties");
        var fieldNames = objectProperties.fieldNames();
        while (fieldNames.hasNext()) {
            var fieldName = fieldNames.next();
            var jsonSchemaType = StringUtils.unwrap(objectProperties.get(fieldName).get("type").asText(), "\"");
            sql.append(fieldName).append(" ").append(pgTypeMap.get(jsonSchemaType)).append(" NOT NULL ");
            sql.append("DEFAULT ").append(typeDefaultValueMap.get(jsonSchemaType)); // 默认值
            // 默认值，在业务层实现
            sql.append(",");
        }
        sql.append(");");
        // 构造表注释内容
        DataNode tableComment = new DataNode();
        tableComment.append("updateTime", LocalDateTime.now());
        tableComment.append("requestContext", context);
        tableComment.append("jsonSchema", jsonSchema);
        sql.append("COMMENT ON TABLE ").append(context.getTableName()).append(" IS \n'")
                .append(tableComment.toPrettyJson())
                .append("'");
        return this.toString();
    }

    /**
     * 生成表结构修改指令(对比字段名称、字段类型)
     *
     * @param context
     * @param jsonSchema
     * @return
     */
    public String alterTable(@NonNull final ReqContext context, @NonNull final JsonNode jsonSchema) {
        sql.delete(0, sql.length());
        QueryExecutor queryExecutor = new PostgresQueryExecutor(context);
        var pgTableStructure = queryExecutor.selectColumns(context.getTableNameChain(), false);
        System.out.println(pgTableStructure);
        var existsFields = pgTableStructure.keySet();
        var fieldNames = jsonSchema.get("properties").fieldNames();
        while (fieldNames.hasNext()) {
            var fieldName = fieldNames.next();
            var jsonSchemaType = StringUtils.unwrap(jsonSchema.get("properties").get(fieldName).get("type").asText(), "\"");
            var pgFieldType = pgTypeMap.get(jsonSchemaType);
            // 字段已存在则跳过
            if (existsFields.contains(fieldName)) {
                if (!pgFieldType.equals(pgTableStructure.asDataNode(fieldName).asText("dataType"))) {
                    throw new DataDriverException(String.format("Don't support change data type, you are modifying field '%s' from '%s' to '%s'.",
                            fieldName, pgTableStructure.asDataNode(fieldName).asText("dataType"), pgFieldType));
                }
                log.debug("field {} already exists in table {}, will be ignore.", fieldName, context.getTableNameChain());
                continue;
            }
            sql.append("ALTER TABLE ").append(context.getTableNameChain()).append(" ");
            sql.append("ADD ").append(fieldName).append(" ").append(pgFieldType);
            sql.append(";");
        }
        return this.toString();
    }

    @Override
    public String toString() {
        return sql.toString();
    }
}
