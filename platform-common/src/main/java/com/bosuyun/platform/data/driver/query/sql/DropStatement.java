package com.bosuyun.platform.data.driver.query.sql;

/**
 * Created by liuyuancheng on 2021/5/20  <br/>
 */
public class DropStatement extends SqlStatement {

    private String indexName;

    public DropStatement dropIndex(String indexName) {
        this.indexName = indexName;
        return this;
    }

    public DropStatement dropSchema(String schemaName) {
        this.setTableSchema(schemaName);
        return this;
    }

    @Override
    public String toSql() {
        if (!indexName.isEmpty()) {
            return String.format("DROP INDEX ON %s", super.getTableName());
        } else if (!this.getTableSchema().isEmpty()) {
            return String.format("DROP SCHEMA %s", super.getTableSchema());
        }
        return "";
    }

}
