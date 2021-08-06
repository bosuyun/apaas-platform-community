package com.bosuyun.platform.data.driver.executor;

import com.bosuyun.platform.common.context.ReqContext;
import com.bosuyun.platform.common.misc.DataNode;
import com.bosuyun.platform.common.misc.DataNodeList;
import com.bosuyun.platform.common.misc.DataNodePage;
import com.bosuyun.platform.common.misc.SystemFieldConstants;
import com.bosuyun.platform.data.driver.query.sql.*;
import com.bosuyun.platform.data.exception.DataDriverException;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.bosuyun.platform.data.driver.query.SqlOperatorFactory.eq;

/**
 * https://github.com/timescale/timescaledb
 * https://www.postgresql.org/docs/13/datatype-json.html
 * https://www.postgresql.org/docs/13/functions-json.html
 * https://segmentfault.com/a/1190000019344353
 * http://www.postgres.cn/docs/13/datatype-json.html#JSON-INDEXING
 * <p>
 * Created by liuyuancheng on 2021/1/18  <br/>
 */
@Slf4j
public class PostgresQueryExecutor extends QueryExecutor {

    public PostgresQueryExecutor(ReqContext context) {
        super.setContext(context);
    }

    public static QueryExecutor context(ReqContext context) {
        return new PostgresQueryExecutor(context);
    }

    @Override
    public DataNode selectOne(WhereClause filter) {
        filter.addClause(eq(SystemFieldConstants.DB_SYSTEM_FIELD_DELETED, false));
        SqlStatement statement = new SelectStatement(getContext())
                .setWhereClause(filter)
                .setFields(getFields())
                .setLimit(1);
        var t = getSqlExecuteManager().execute(getContext().getDatasourceId(), statement.toSql());
        if (t.isEmpty()) {
            log.debug("数据库中未找到条件匹配的数据: {}", filter.toSqlSegment());
            return null;
        } else {
            return t.get(0);
        }
    }

    @Override
    public DataNodePage selectPage(WhereClause filter) {
        filter.addClause(eq(SystemFieldConstants.DB_SYSTEM_FIELD_DELETED, false));
        int offset = (getPaging().getNum() - 1) * getPaging().getSize();
        int count = this.count(filter);
        SqlStatement statement = new SelectStatement(getContext())
                .setWhereClause(filter)
                .setFields(getFields())
                .setOffset(offset)
                .setLimit(getPaging().getSize())
                .setOrderBy(getPaging().getOrderByMap());
        var t = getSqlExecuteManager().execute(getContext().getDatasourceId(), statement.toSql());
        if (t.isEmpty()) {
            log.debug("数据库中未找到条件匹配的数据: {}", filter.toSqlSegment());
            return null;
        } else {
            int pages = (int) Math.ceil((double) count / getPaging().getSize());
            return new DataNodePage(getPaging())
                    .setList(t)
                    .setTotal(count)
                    .setPages(pages)
                    .setHasNext(getPaging().getNum() < pages);
        }
    }

    @Override
    public DataNodeList selectList(WhereClause filter) {
        // 不展示软删除的数据
        filter.addClause(eq(SystemFieldConstants.DB_SYSTEM_FIELD_DELETED, false));
        int offset = getPaging().getNum() * getPaging().getSize();
        SqlStatement statement = new SelectStatement(getContext())
                .setWhereClause(filter)
                .setFields(getFields())
                .setOffset(offset)
                .setLimit(getPaging().getSize());
        return getSqlExecuteManager().execute(getContext().getDatasourceId(), statement.toSql());
    }

    @Override
    public List<Long> selectIdList(WhereClause filter) {
        filter.addClause(eq(SystemFieldConstants.DB_SYSTEM_FIELD_DELETED, false));
        int offset = getPaging().getNum() * getPaging().getSize();
        SqlStatement statement = new SelectStatement(getContext())
                .setWhereClause(filter)
                .setFields(Arrays.asList("id"))
                .setOffset(offset)
                .setLimit(getPaging().getSize());
        return getSqlExecuteManager().execute(getContext().getDatasourceId(), statement.toSql()).extractFieldList("id", Long.class);
    }

    @Override
    public Integer count(WhereClause filter) {
        SqlStatement statement = new SelectStatement(getContext())
                .setFields(Arrays.asList("COUNT(1) AS count"))
                .setLimit(1)
                .setWhereClause(filter);
        var t = getSqlExecuteManager().execute(getContext().getDatasourceId(), statement.toSql());
        return ((Long) t.get(0).get("count")).intValue();
    }

    @Override
    public Long insertOne(DataNode dataNode) {
        SqlStatement statement = new InsertStatement(getContext()).putDataNode(dataNode);
        DataNodeList dataNodes = getSqlExecuteManager().execute(getContext().getDatasourceId(), statement.toSql());
        return dataNodes.get(0).asLong("id");
    }

    @Override
    public List<Long> insertMany(DataNodeList dataNodes) {
        SqlStatement statement = new InsertStatement(getContext()).putDataNode(dataNodes);
        DataNodeList idList = getSqlExecuteManager().execute(getContext().getDatasourceId(), statement.toSql());
        return idList.stream().map(e -> Long.parseLong(e.asString("id"))).collect(Collectors.toList());
    }

    /**
     * 软删除
     *
     * @param filter
     * @return
     */
    @Override
    public Long deleteOne(WhereClause filter, Boolean undo) {
        if (undo) {
            filter.addClause(eq(SystemFieldConstants.DB_SYSTEM_FIELD_DELETED, true));
        }
        var dataNode = new DataNode(SystemFieldConstants.DB_SYSTEM_FIELD_DELETED_AT, LocalDateTime.now())
                .append(SystemFieldConstants.DB_SYSTEM_FIELD_DELETED, !undo);
        return this.updateOne(filter, dataNode);
    }

    @Override
    public Long deleteMany(WhereClause filter) {
        return null;
    }

    @Override
    public Long updateOne(WhereClause filter, DataNode dataNode) {
        SqlStatement statement = new UpdateStatement(getContext()).setDataNode(dataNode).setLimit(1).setWhereClause(filter);
        var returning = getSqlExecuteManager().execute(getContext().getDatasourceId(), statement.toSql());
        if (returning.isEmpty()) {
            return null;
        }
        return returning.get(0).asLong("id");
    }

    @Override
    public Long updateMany(WhereClause filter, DataNode DataNode) {
        return 0L;
    }

    @Override
    public DataNodeList listIndexes() {
        SqlStatement statement = new SelectStatement(getContext())
                .setWhereClause(eq("tablename", this.getContext().getTableName()))
                .setTableSchema(null)
                .setTableName("pg_indexes");
        return getSqlExecuteManager().execute(getContext().getDatasourceId(), statement.toSql());
    }

    @Override
    public DataNodeList createIndex(String indexName, String indexType, List<String> fields) {
        SqlStatement statement = new CreateStatement().addFieldIndex(indexName, indexType, fields);
        return getSqlExecuteManager().execute(getContext().getDatasourceId(), statement.toSql());
    }

    @Override
    public DataNodeList createSchema(String schemaName) {
        SqlStatement statement = new SelectStatement(getContext())
                .setWhereClause(eq("schema_name", schemaName))
                .setLimit(1)
                .setTableName("schemata")
                .setTableSchema("information_schema");
        var exists = getSqlExecuteManager().exists(getContext().getDatasourceId(), statement.toSql());
        if (exists) {
            log.warn("schema named {},is already exists in datasourceId {}.", schemaName, getContext().getDatasourceId());
            return null;
        }
        SqlStatement statement1 = new CreateStatement()
                .setTableSchema(schemaName);
        return getSqlExecuteManager().execute(getContext().getDatasourceId(), statement1.toSql());
    }

    @Override
    public DataNodeList dropSchema(String schemaName) {
        SqlStatement statement = new SelectStatement(getContext())
                .setWhereClause(eq("schema_name", schemaName))
                .setLimit(1)
                .setTableName("schemata")
                .setTableSchema("information_schema");
        var exists = getSqlExecuteManager().exists(getContext().getDatasourceId(), statement.toSql());
        if (!exists) {
            log.warn("schema named {},not exists in datasourceId {}.", schemaName, getContext().getDatasourceId());
            return null;
        }
        SqlStatement statement1 = new DropStatement()
                .setTableSchema(schemaName);
        return getSqlExecuteManager().execute(getContext().getDatasourceId(), statement1.toSql());
    }

    @Override
    public void dropIndex(String indexName) {
        SqlStatement statement = new DropStatement().dropIndex(indexName);
        getSqlExecuteManager().execute(getContext().getDatasourceId(), statement.toSql());
    }

    @Override
    public void explain(SqlStatement sqlStatement) {
        var sql = sqlStatement.getSql().insert(0, "EXPLAIN ANALYZE ");
        getSqlExecuteManager().execute(getContext().getDatasourceId(), sql.toString());
    }

    @Override
    public DataNode selectColumns(String tableNameChain, Boolean showSystemColumn) {
        var tableName = ReqContext.parseTableNameChain(tableNameChain);
        StringBuilder sqlSb = new StringBuilder();
        sqlSb.append("SELECT data_type,is_nullable,column_default,column_name,table_name,table_schema FROM information_schema.columns ");
        sqlSb.append("WHERE table_schema='").append(tableName.get(0)).append("' ");
        sqlSb.append("AND ").append("table_name='").append(tableName.get(1)).append("'");
        if (!showSystemColumn) {
            sqlSb.append(" AND column_name NOT IN (")
                    .append(SystemFieldConstants.getAll().stream().map(e -> "'" + e + "'").collect(Collectors.joining(",")))
                    .append(");");
        }
        DataNode ret = new DataNode();
        for (DataNode dataNode : getSqlExecuteManager().execute(getContext().getDatasourceId(), sqlSb.toString())) {
            ret.put(dataNode.asString("columnName"), dataNode);
            // float8 等价于 double precision
            if ("double precision".equals(dataNode.asString("dataType"))) {
                dataNode.put("dataType", "float8");
            }
        }
        return ret;
    }

    @Override
    public DataNodeList selectTables(String schemaName) {
        String sql = String.format("select table_schema,table_name from information_schema.tables " +
                "WHERE table_schema='%s' AND table_schema NOT IN ('pg_catalog','information_schema');", schemaName.trim());
        return getSqlExecuteManager().execute(getContext().getDatasourceId(), sql);
    }

    @Override
    public DataNodeList pureSql(String sql) {
        if (sql.contains("ALTER") || sql.contains("CREATE") || sql.contains("DELETED") || sql.contains("DROP")) {
            throw new DataDriverException("该操作仅支持SELECT");
        }
        return null;
    }
}
