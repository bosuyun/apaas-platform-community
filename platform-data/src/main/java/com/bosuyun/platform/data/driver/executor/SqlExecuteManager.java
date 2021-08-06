package com.bosuyun.platform.data.driver.executor;

import com.bosuyun.platform.common.misc.DataNodeList;
import com.bosuyun.platform.data.driver.ConnectionPool;
import com.bosuyun.platform.data.exception.DataDriverException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;

/**
 * SQL执行管理器
 * <p>
 * https://docs.oracle.com/javaee/6/api/javax/persistence/Query.html#getFirstResult()
 * <p>
 * Created by liuyuancheng on 2021/5/21  <br/>
 */
@Slf4j
@Singleton
public class SqlExecuteManager {

    @Inject
    private ConnectionPool connectionPool;

    /**
     * 执行SQL查询
     *
     * @param sqlStatement
     * @return
     */
    public DataNodeList execute(@NonNull final Long datasourceId, @NonNull final String sqlStatement) {
        if (Objects.isNull(connectionPool)) {
            throw new DataDriverException("connectionPool is not initialized");
        }
        if (StringUtils.isBlank(sqlStatement)) {
            throw new DataDriverException("Sql statement cannot be blank string.");
        }
        Query query = connectionPool.getEm().get(datasourceId).createNativeQuery(sqlStatement);
        List<?> resultList = query.getResultList();
        log.debug("Executing SQL statement : {}, returning {} rows.", sqlStatement, query.getMaxResults());
        return new DataNodeList(resultList);
    }

    /**
     * 判断是否存在查询结果
     *
     * @return
     */
    public Boolean exists(@NonNull final Long datasourceId, @NonNull final String sqlStatement) {
        log.debug("SQL exists : {}", sqlStatement);
        Query query = connectionPool.getEm().get(datasourceId).createNativeQuery(sqlStatement);
        return query.getMaxResults() > 0;
    }
}
