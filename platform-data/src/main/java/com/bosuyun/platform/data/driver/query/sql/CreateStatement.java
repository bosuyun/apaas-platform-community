package com.bosuyun.platform.data.driver.query.sql;

import com.bosuyun.platform.data.exception.DataDriverException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by liuyuancheng on 2021/5/20  <br/>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public class CreateStatement extends SqlStatement {

    /**
     * 注释
     */
    private String comment;

    /**
     * 索引名称及命令内容
     */
    private Map<String, String> indexes;

    /**
     * postgres 支持的索引类型
     * 索引不应该使用在较小的表上。
     * 索引不应该使用在有频繁的大批量的更新或插入操作的表上。
     * 索引不应该使用在含有大量的 NULL 值的列上。
     * 索引不应该使用在频繁操作的列上。
     */
    private final List<String> indexTypes = Arrays.asList("btree", "hash", "gin", "gist",
            "sp-gist", "brin", "bloom", "rum", "zombodb", "bitmap");

    /**
     * 创建索引
     *
     * @param indexName
     * @param fields
     * @param indexType
     */
    public CreateStatement addFieldIndex(String indexName, String indexType, List<String> fields) {
        if (fields.isEmpty()) {
            throw new DataDriverException("请指定索引列");
        }
        if (this.getIndexes().size() >= 2) {
            throw new DataDriverException("每次创建一条索引");
        }
        if (!indexTypes.contains(indexType)) {
            log.warn("支持的索引类型 {}", indexTypes);
            throw new DataDriverException("不支持的索引类型: " + indexType);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE ");
        sb.append("INDEX ").append(" IF NOT EXISTS ").append(indexName).append(" ON ")
                .append(getTableName())
                .append(" USING ")
                .append(Objects.isNull(indexType) ? "btree" : indexType)
                .append(" (")
                .append(String.join(",", fields))
                .append(")");
        this.getIndexes().put(indexName, sb.toString());
        return this;
    }

    /**
     * 创建模式（用于区分不同的应用工作区，不同的工作区可以创建相同的应用）
     *
     * @param TableSchema
     */
    public CreateStatement createSchema(String TableSchema, String comment) {
        this.setTableSchema(TableSchema);
        this.setComment(comment);
        return this;
    }

    @Override
    public String toSql() {
        if (Objects.nonNull(getIndexes()) && !getIndexes().isEmpty()) {
            return this.getIndexes().values().toArray(String[]::new)[0];
        } else if (Objects.nonNull(getTableSchema()) && !this.getTableSchema().isEmpty()) {
            return String.format("CREATE SCHEMA %s;CREATE COMMENT ON SCHEMA %s IS '%s';",
                    super.getTableSchema(), super.getTableSchema(), this.getComment());
        }
        return "";
    }

}
