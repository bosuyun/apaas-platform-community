package com.bosuyun.platform.data.msic;

import com.bosuyun.platform.common.schema.JsonSchemaKey;
import com.bosuyun.platform.common.schema.ObjectType;
import com.bosuyun.platform.common.schema.SchemaNode;
import com.bosuyun.platform.common.utils.JsonUtils;
import com.bosuyun.platform.data.DataSchemaFacade;
import com.bosuyun.platform.data.exception.DataSchemaException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.inject.spi.CDI;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * 数据库中的存储结构
 * <p>
 * Created by liuyuancheng on 2021/1/7  <br/>
 */
@Slf4j
@NoArgsConstructor
public class DataSchemaTree extends ObjectType {

    public DataSchemaTree(ObjectType objectType) {
        this.putAll(objectType);
    }

    /**
     * 添加字段
     *
     * @param fieldName
     * @param field
     * @return
     */
    public DataSchemaTree addField(String fieldName, SchemaNode field) {
        fieldName = fieldName.toLowerCase();
        if (fieldName.startsWith("_")) {
            throw new DataSchemaException("Field name cannot start with _.");
        }
        if (Pattern.matches("/^w+$/", fieldName)) {
            throw new DataSchemaException(String.format("不合法的字段名:%s,只能包含下划线、数字、字母", fieldName));
        }
        this.setProperty(fieldName, field);
        return this;
    }

    public Set<String> getFields() {
        return this.keySet();
    }

    public DataSchemaTree removeField(String fieldName) {
        this.removeProperty(fieldName);
        return this;
    }

    /**
     * 获取数据的值并转为JSON字符串
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public String toValJson() {
        return "";
//        return toValDocument().toJson();
    }

    /**
     * 解析
     *
     * @param input
     * @return
     */
    public static DataSchemaTree parse(String input) {
        try {
            return new ObjectMapper().readValue(input, DataSchemaTree.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取表单定义
     *
     * @return
     */
    public String toSchemaJson() {
        return JsonUtils.toJsonString(this);
    }

    /**
     * 初始化
     *
     * @param id
     * @return
     */
    public DataSchemaTree initAsRoot(String id) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_DOLLAR_SCHEMA, "http://json-schema.org/draft-07/schema#");
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_DOLLAR_ID, "#" + id);
        return this;
    }

    public DataSchemaTree initAsRoot() {
        return this.initAsRoot(UUID.randomUUID().toString());
    }

    public static DataSchemaTree getDataSchema(@NonNull Long schemaId) {
        var returning = CDI.current().select(DataSchemaFacade.class).get().findSchemaNode(schemaId);
        return new DataSchemaTree(returning);
    }

    @Override
    public String toString() {
        return this.toSchemaJson();
    }
}
