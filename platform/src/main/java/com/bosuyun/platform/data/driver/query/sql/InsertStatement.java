package com.bosuyun.platform.data.driver.query.sql;

import com.bosuyun.platform.common.context.ReqContext;
import com.bosuyun.platform.common.exception.ApplicaException;
import com.bosuyun.platform.common.misc.DataNode;
import com.bosuyun.platform.common.utils.JsonSchemaUtils;
import com.bosuyun.platform.data.msic.DataDriverException;
import com.bosuyun.platform.data.msic.DataSchemaTree;
import com.bosuyun.platform.common.misc.DataNodeList;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 构造插入语句
 * <p>
 * Created by liuyuancheng on 2021/5/20  <br/>
 */
@EqualsAndHashCode(callSuper = true)
public class InsertStatement extends SqlStatement {

    public InsertStatement(ReqContext context) {
        this.setTableSchema(context.getTableSchema());
        this.setTableName(context.getTableName());
        this.setSchemaId(context.getSchemaId());
    }

    /**
     * dataSchema 结构
     */
    private DataSchemaTree dataSchemaTree;

    private final DataNodeList values = new DataNodeList();

    public InsertStatement putDataNode(DataNode dataNode) {
        if(Objects.isNull(dataSchemaTree)){
            this.dataSchemaTree = DataSchemaTree.getDataSchema(getSchemaId());
        }
        // 过滤多余字段
        dataNode = dataNode.filterBySchema(this.dataSchemaTree);
        // 验证数据结构
        if (!JsonSchemaUtils.jsonSchemaValidate(this.dataSchemaTree.toString(), dataNode.toJson())) {
            throw new ApplicaException("DataSchema validation failed ：" + JsonSchemaUtils.getLastValidationError().toString());
        }
        dataNode.append("sid", InsertStatement.this.getSchemaId());
        dataNode.append("created_at", LocalDateTime.now());
        dataNode.append("updated_at", LocalDateTime.now());
        dataNode.append("deleted", false);
        if (this.values.size() > 1111) {
            throw new DataDriverException("一次最多插入1111条数据");
        }
        this.values.add(dataNode);
        return this;
    }

    public InsertStatement putDataNode(DataNodeList dataNodes) {
        for (DataNode dataNode : dataNodes) {
            this.putDataNode(dataNode);
        }
        return this;
    }

    @Override
    public String toSql() {
        this.resetSql();
        // 至少要有一条数据插入
        if (values.isEmpty()) {
            throw new DataDriverException("This operation required one dataNode item at least.");
        }
        for (DataNode value : values) {
            getSql().append("INSERT INTO ").append(this.getTableSchema()).append(".").append(this.getTableName());
            getSql().append("(");
            getSql().append(StringUtils.join(value.keySet(), ","));
            getSql().append(") ");
            getSql().append("VALUES (");
            getSql().append(value.values().stream().map(e -> String.format("'%s'", e)).collect(Collectors.joining(",")));
            getSql().append(") RETURNING id;");
        }
        return getSql().toString();
    }

}
