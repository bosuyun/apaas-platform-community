package com.bosuyun.platform.data.driver.executor;

import com.bosuyun.platform.common.context.ReqContext;
import com.bosuyun.platform.common.misc.DataNode;
import com.bosuyun.platform.common.misc.DataNodeList;
import com.bosuyun.platform.common.misc.DataNodePage;
import com.bosuyun.platform.data.driver.PoolConnectionFactory;
import com.bosuyun.platform.common.misc.Paging;
import com.bosuyun.platform.common.misc.Sorting;
import com.bosuyun.platform.data.driver.query.sql.SqlStatement;
import com.bosuyun.platform.data.driver.query.sql.WhereClause;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;

/**
 * 查询执行器
 * <p>
 * Created by liuyuancheng on 2020/12/25  <br/>
 */
@Data
@Accessors(chain = true)
public abstract class QueryExecutor {

    /**
     * 连接池的容器
     */
    private SqlExecuteManager sqlExecuteManager;

    /**
     * 请求上下文，存储数据源、应用等信息
     */
    private ReqContext context;

    /**
     * 指定要返回的字段列表
     */
    private List<String> fields;

    /**
     * 分页
     */
    private Paging paging = new Paging();

    /**
     * 排序方式
     */
    private Sorting sorting = new Sorting();

    /**
     * 开启软删除 null
     * 如果开启软删除，则屏蔽被软删除的数据
     */
    private Boolean enableSoftDelete = true;

    /**
     * 是否开启暴力模式（删除已被软删除的数据，直接替换现有数据）
     */
    private Boolean enableForce = false;

    public QueryExecutor setFields(String... fields) {
        this.fields = Arrays.asList(fields);
        return this;
    }

    /**
     * 设置上下文
     *
     * @param context
     */
    public QueryExecutor setContext(final ReqContext context) {
        this.context = context;
        sqlExecuteManager = new SqlExecuteManager(PoolConnectionFactory.get(context.getDsId()));
        return this;
    }

    /**
     * 查询一条
     *
     * @param filter
     * @return
     */
    public abstract DataNode selectOne(final WhereClause filter);

    /**
     * 分页查询
     *
     * @param filter
     * @return
     */
    public abstract DataNodePage selectPage(final WhereClause filter);

    /**
     * 查询一个列表，限制每次查询最多返回000条
     * 支持传入null
     *
     * @param filter
     * @return
     */
    public abstract DataNodeList selectList(final WhereClause filter);

    /**
     * 查询ID列表
     *
     * @param filter
     * @return
     */
    public abstract List<Long> selectIdList(final WhereClause filter);

    /**
     * 统计数量
     *
     * @param filter
     * @return
     */
    public abstract Integer count(final WhereClause filter);

    /**
     * 插入一条
     *
     * @param dataNode
     * @return
     */
    public abstract Long insertOne(final DataNode dataNode);

    /**
     * 批量插入
     *
     * @param dataNodes
     * @return
     */
    public abstract List<Long> insertMany(final DataNodeList dataNodes);

    /**
     * 删除一条
     *
     * @param filter
     * @return
     */
    public abstract Long deleteOne(final WhereClause filter, Boolean undo);

    /**
     * 删除多条
     *
     * @param filter
     */
    public abstract Long deleteMany(final WhereClause filter);

    /**
     * 编辑文档
     *
     * @param filter
     * @return
     */
    public abstract Long updateOne(final WhereClause filter, final DataNode DataNode);

    /**
     * 编辑多个文档
     *
     * @param filter
     */
    public abstract Long updateMany(final WhereClause filter, final DataNode DataNode);

    public abstract DataNodeList listIndexes();

    /**
     * 创建索引(一次传入多个字段，可创建组合索引)
     *
     * @param fields
     * @param indexType
     * @return
     */
    public abstract DataNodeList createIndex(String indexName, String indexType, List<String> fields);

    public abstract DataNodeList createSchema(final String schemaName);

    public abstract DataNodeList dropSchema(final String schemaName);

    /**
     * 删除索引
     *
     * @param indexName
     */
    public abstract void dropIndex(final String indexName);

    /**
     * 查询性能评估
     */
    public abstract void explain(SqlStatement sqlStatement);

    /**
     * 直接编写SQL，限定执行语句
     */
    public abstract DataNodeList pureSql(String sql);

    /**
     * 获取所有的列
     *
     * @param tableNameChain
     * @return
     */
    public abstract DataNode selectColumns(String tableNameChain, Boolean showAll);

    /**
     * 获取所有的表
     *
     * @param schemaName
     * @return
     */
    public abstract DataNodeList selectTables(String schemaName);
}
