package com.bosuyun.platform.data.driver.executor;

import com.bosuyun.platform.common.misc.DataNodeList;
import com.bosuyun.platform.common.misc.DataNode;
import com.bosuyun.platform.data.driver.ConnectionPool;
import com.bosuyun.platform.data.msic.DataDriverException;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import java.util.Objects;

/**
 * 事务管理器
 * <p>
 * Created by liuyuancheng on 2021/5/21  <br/>
 */
@Slf4j
@Singleton
public class SqlExecuteManager {

    @Inject
    EntityManager em;

    private ConnectionPool connectionPool;

    private Transaction transaction;

    public SqlExecuteManager begin() {
        transaction = connectionPool.getDbConnection().beginAndAwait();
        return this;
    }

    /**
     * 执行SQL查询
     *
     * @param sqlStatement
     * @return
     */
    public DataNodeList execute(String sqlStatement) {
        if (Objects.isNull(connectionPool)) {
            throw new DataDriverException("connectionPool is not initialized");
        }
        if(StringUtils.isBlank(sqlStatement)){
            throw new DataDriverException("Sql statement cannot be blank string.");
        }
        log.debug("Executing SQL statement : {}", sqlStatement);
        RowSet<Row> rowSet = connectionPool.getDbConnection().query(sqlStatement).executeAndAwait();
        var datas = rowSet.iterator();
        var columns = rowSet.columnsNames();
        var ret = new DataNodeList();
        while (datas.hasNext()) {
            ret.add(new DataNode(columns, datas.next()));
        }
        log.debug("execute return {} rows, with column {}.", ret.size(), columns);
        return ret;
    }

    /**
     * 判断是否存在查询结果
     *
     * @return
     */
    public Boolean exists(String sqlStatement) {
        log.debug("SQL exists : {}", sqlStatement);
        RowSet<Row> rowSet = connectionPool.getDbConnection().query(sqlStatement).execute()
                .await().indefinitely();
        return rowSet.rowCount() >= 1;
    }


    public SqlExecuteManager executeWithTx(String statement) {
        if (Objects.isNull(transaction)) {
            this.begin();
        }
        this.execute(statement);
        return this;
    }

    public SqlExecuteManager commit() {
        transaction.commit();
        return this;
    }
}
