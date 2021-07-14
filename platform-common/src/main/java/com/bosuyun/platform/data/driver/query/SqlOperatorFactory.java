package com.bosuyun.platform.data.driver.query;

import com.bosuyun.platform.common.context.ReqContext;
import com.bosuyun.platform.data.driver.query.sql.*;

import java.util.List;

/**
 * SQL构造器
 * <p>
 * Created by liuyuancheng on 2021/1/5  <br/>
 */
public class SqlOperatorFactory {

    public static SelectStatement select(ReqContext context) {
        return new SelectStatement(context);
    }

    public static InsertStatement insert(ReqContext context) {
        return new InsertStatement(context);
    }

    public static UpdateStatement update(ReqContext context) {
        return new UpdateStatement(context);
    }

    public static AlterTableStatement alter(ReqContext context) {
        return new AlterTableStatement(context);
    }

    public static WhereClause emptyClause() {
        return new WhereClause();
    }

    public static WhereClause between(String field, Object leftValue, Object rightValue) {
        return new WhereClause().between(field, leftValue, rightValue);
    }

    public static WhereClause in(String field, List<Object> values) {
        return new WhereClause().in(field, values);
    }

    public static WhereClause nin(String field, List<Object> values) {
        return new WhereClause().nin(field, values);
    }

    public static WhereClause eq(String field, Object value) {
        return new WhereClause().eq(field, value);
    }

    public static WhereClause ne(String field, Object value) {
        return new WhereClause().ne(field, value);
    }

    public static WhereClause gte(String field, Object value) {
        return new WhereClause().gte(field, value);
    }

    public static WhereClause lte(String field, Object value) {
        return new WhereClause().lte(field, value);
    }

    public static WhereClause gt(String field, Object value) {
        return new WhereClause().gt(field, value);
    }

    public static WhereClause lt(String field, Object value) {
        return new WhereClause().lt(field, value);
    }

    public static WhereClause id(long value) {
        return new WhereClause().id(value);
    }

    public static WhereClause like(String field, String value, String leftMatch, String rightMatch) {
        return new WhereClause().like(field, value, leftMatch, rightMatch);
    }


}
