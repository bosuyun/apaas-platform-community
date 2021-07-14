package com.bosuyun.platform.data.driver.query.sql;

/**
 * Created by liuyuancheng on 2021/5/20  <br/>
 */
public abstract class SqlClause {

    protected StringBuffer sqlSegment = new StringBuffer();

    abstract String toSqlSegment();
}
