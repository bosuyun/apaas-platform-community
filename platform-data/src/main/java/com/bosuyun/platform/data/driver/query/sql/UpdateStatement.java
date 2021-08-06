package com.bosuyun.platform.data.driver.query.sql;

import com.bosuyun.platform.common.context.ReqContext;
import com.bosuyun.platform.common.misc.DataNode;
import com.bosuyun.platform.common.misc.SystemFieldConstants;
import com.bosuyun.platform.data.exception.DataDriverException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 更新
 * <p>
 * Created by liuyuancheng on 2021/5/20  <br/>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class UpdateStatement extends SqlStatement {

    public WhereClause whereClause;

    public Integer limit = 1;

    /**
     * set values, 要更新的字段和值
     */
    private DataNode dataNode;

    public UpdateStatement(ReqContext context) {
        this.setTableSchema(context.getTableSchema()).setTableName(context.getTableName());
    }

    @Override
    public String toSql() {
        this.resetSql();
        // 至少要有一条数据插入
        if (Objects.isNull(dataNode) || dataNode.isEmpty()) {
            throw new DataDriverException("Invalid data node.");
        }
        var tableNameChain = this.getTableSchema() + "." + this.getTableName();
        getSql().append("UPDATE ").append(tableNameChain).append(" ");
        getSql().append("SET ");
        dataNode.put(SystemFieldConstants.DB_SYSTEM_FIELD_UPDATED_AT, LocalDateTime.now());
        var dataNodeIterator = dataNode.entrySet().iterator();
        while (dataNodeIterator.hasNext()) {
            var dataNode = dataNodeIterator.next();
            getSql().append(dataNode.getKey()).append("='").append(dataNode.getValue()).append("'");
            if (dataNodeIterator.hasNext()) {
                getSql().append(",");
            } else {
                getSql().append(" ");
            }
        }
        getSql().append("WHERE id IN (")
                .append("SELECT id FROM ").append(tableNameChain).append(" ")
                .append(" WHERE ").append(getWhereClause().toSqlSegment()).append(" LIMIT ").append(getLimit())
                .append(") ")
                .append(" RETURNING id;");
        return getSql().toString();
    }

}
