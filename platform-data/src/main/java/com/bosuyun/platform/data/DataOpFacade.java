package com.bosuyun.platform.data;

import com.bosuyun.platform.common.misc.DataNode;
import com.bosuyun.platform.common.misc.DataNodeList;
import com.bosuyun.platform.common.misc.DataNodePage;
import com.bosuyun.platform.common.misc.Paging;
import com.bosuyun.platform.data.driver.query.sql.WhereClause;
import com.bosuyun.platform.data.eventbus.EventBusFacade;
import com.bosuyun.platform.data.eventbus.DataOperationMessage;
import com.bosuyun.platform.data.msic.AbstractQueryFacade;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static com.bosuyun.platform.data.driver.query.SqlOperatorFactory.id;

/**
 * 数据操作引擎
 * <p>
 * Created by liuyuancheng on 2021/2/14  <br/>
 */
@ApplicationScoped
@Slf4j
public class DataOpFacade extends AbstractQueryFacade {

    @Inject
    EventBusFacade eventBusFacade;

    /**
     * 插入一条数据
     *
     * @param dataNode
     * @return
     */
    public Long insertOne(final DataNode dataNode) {
        DataOperationMessage dataOpMessage = new DataOperationMessage().insert().setInputData(dataNode)
                .setReqContext(this.getQueryExecutor().getContext());
        Long returning = getQueryExecutor().insertOne(dataNode);
        if (Objects.nonNull(returning)) {
            eventBusFacade.launchDataOpEvent(dataOpMessage);
        }
        return returning;
    }

    /**
     * 插入多条数据
     *
     * @param dataNodes
     * @return
     */
    public List<Long> insertMany(final DataNodeList dataNodes) {
        DataOperationMessage dataOpMessage = new DataOperationMessage().insertMany().setInputData(dataNodes)
                .setReqContext(this.getQueryExecutor().getContext());
        var returning = getQueryExecutor().insertMany(dataNodes);
        if (Objects.nonNull(returning)) {
            eventBusFacade.launchDataOpEvent(dataOpMessage);
        }
        return returning;
    }

    /**
     * 更新一条数据（消息中会包含修改之前的内容）
     *
     * @param filter
     * @param dataNode
     * @return
     */
    public Long updateOne(final WhereClause filter, DataNode dataNode) {
        var formerData = this.selectOne(filter);
        var dataNodeForUpdate = formerData.getDiffData(dataNode);
        if (dataNodeForUpdate.isEmpty()) {
            log.debug("数据完全没有变化, 无需更新数据为 :{}", formerData);
            return formerData.asLong("id");
        }
        var returning = getQueryExecutor().updateOne(filter, dataNodeForUpdate);
        if (Objects.nonNull(returning) && returning > 0) {
            DataOperationMessage dataOpMessage = new DataOperationMessage().update()
                    .setInputData(dataNodeForUpdate)
                    .setUpdateFields(dataNode.getKeyList())
                    .setFormerData(formerData)
                    .setReqContext(this.getQueryExecutor().getContext());
           eventBusFacade.launchDataOpEvent(dataOpMessage);
        }
        return returning;
    }

    /**
     * 更新多条数据
     *
     * @param filter
     * @param dataNode
     * @return
     */
    public List<Long> updateMany(final WhereClause filter, final DataNode dataNode) {
        List<Long> matchedIds = getQueryExecutor().selectIdList(filter);
        List<Long> updatedIds = new LinkedList<>();
        if (Objects.isNull(matchedIds) || matchedIds.isEmpty()) {
            return null;
        }
        for (Long matchedId : matchedIds) {
            updatedIds.add(this.updateOne(id(matchedId), dataNode));
        }
        return updatedIds;
    }

    /**
     * 删除一条数据
     *
     * @param filter
     * @return
     */
    public Long deleteOne(WhereClause filter) {
        var dataNode = getQueryExecutor().selectOne(filter);
        var returning = getQueryExecutor().deleteOne(filter, false);
        if (Objects.nonNull(returning)) {
            DataOperationMessage dataOpMessage = new DataOperationMessage().delete().setInputData(dataNode)
                    .setReqContext(getQueryExecutor().getContext());
            eventBusFacade.launchDataOpEvent(dataOpMessage);
        }
        return returning;
    }

    public Long undoDelete(WhereClause filter) {
        var dataNode = getQueryExecutor().selectOne(filter);
        var returning = getQueryExecutor().deleteOne(filter, true);
        if (Objects.nonNull(returning)) {
            DataOperationMessage dataOpMessage = new DataOperationMessage().undoDelete().setInputData(dataNode).setReqContext(getQueryExecutor().getContext());
            eventBusFacade.launchDataOpEvent(dataOpMessage);
        }
        return returning;
    }

    /**
     * 删除多条数据
     *
     * @param filter
     * @return
     */
    public List<Long> deleteMany(WhereClause filter) {
        List<Long> matchedIds = getQueryExecutor().selectIdList(filter);
        List<Long> deletedIds = new LinkedList<>();
        if (Objects.isNull(matchedIds) || matchedIds.isEmpty()) {
            return null;
        }
        for (Long matchedId : matchedIds) {
            deletedIds.add(this.deleteOne(id(matchedId)));
        }
        return deletedIds;
    }

    /**
     * 获取索引列表
     *
     * @param filter
     * @return
     */
    public DataNode selectOne(final WhereClause filter) {
        return getQueryExecutor().selectOne(filter);
    }

    /**
     * 获取分页
     *
     * @param filter
     * @return
     */
    public DataNodePage selectPage(final WhereClause filter, final Paging paging) {
        return getQueryExecutor().setPaging(paging).selectPage(filter);
    }

    /**
     * 获取列表
     *
     * @param filter
     * @return
     */
    public DataNodeList selectList(final WhereClause filter) {
        return getQueryExecutor().selectList(filter);
    }

    /**
     * 计数
     *
     * @param filter
     * @return
     */
    public int count(final WhereClause filter) {
        return getQueryExecutor().count(filter);
    }

    /**
     * 获取索引列表
     *
     * @return
     */
    public DataNodeList getIndexes() {
        return getQueryExecutor().listIndexes();
    }

    /**
     * 创建索引(自动生成字段名)
     *
     * @param fields
     * @param indexType
     * @return
     */
    public DataNodeList createIndex(final List<String> fields, String indexType) {
        return getQueryExecutor().createIndex("idx_" + StringUtils.joinWith("_", fields), indexType, fields);
    }

    /**
     * 删除索引
     *
     * @param indexName
     */
    public void dropIndex(String indexName) {
        getQueryExecutor().dropIndex(indexName);
    }

}
