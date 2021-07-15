package com.bosuyun.platform.data.driver.query.sql;

import com.bosuyun.platform.common.context.ReqContext;
import com.bosuyun.platform.common.misc.Paging;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by liuyuancheng on 2021/5/20  <br/>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)

public class SelectStatement extends SqlStatement {

    public SelectStatement(ReqContext context) {
        this.setTableSchema(context.getTableSchema());
        this.setTableName(context.getTableName());
    }

    private List<String> fields;

    private List<String> distinct = new ArrayList<>();

    private SelectStatement subQuery;

    private Integer offset;

    private Integer limit;

    private Map<String, Paging.Direction> orderBy;

    private List<String> groupBy;

    private WhereClause whereClause;

    @Override
    public String toSql() {
        getSql().setLength(0);
        getSql().append("SELECT ");
        if (Objects.nonNull(fields) && fields.size() > 0) {
            getSql().append(String.join(",", fields));
        } else {
            getSql().append("*");
        }
        getSql().append(" ");
        getSql().append("FROM ");
        if (Objects.nonNull(getTableSchema())) {
            getSql().append(getTableSchema()).append(".");
        }
        getSql().append(getTableName());
        if (Objects.nonNull(whereClause) && !whereClause.getClauseItems().isEmpty()) {
            getSql().append(" WHERE ").append(whereClause.toSqlSegment());
        }
        // 排序
        if (Objects.nonNull(orderBy) && !orderBy.isEmpty()) {
            getSql().append(" ORDER BY");
            var orderIterator = orderBy.entrySet().iterator();
            while (orderIterator.hasNext()){
                var item = orderIterator.next();
                getSql().append(" ").append(item.getKey()).append(" ").append(item.getValue());
                if(orderIterator.hasNext()){
                    getSql().append(",");
                }
            }
        }
        // 分页
        if (Objects.nonNull(offset) && Objects.nonNull(limit)) {
            getSql().append(" LIMIT ").append(limit).append(" OFFSET ").append(offset);
        }
        return getSql().toString();
    }
}
