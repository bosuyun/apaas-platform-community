package com.bosuyun.platform.data.msic;

import com.bosuyun.platform.data.driver.executor.PostgresQueryExecutor;
import com.bosuyun.platform.data.driver.executor.QueryExecutor;
import com.bosuyun.platform.common.entity.Datasource;
import com.bosuyun.platform.common.context.ReqContext;
import com.bosuyun.platform.data.exception.DatasourceException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * Created by liuyuancheng on 2021/1/18  <br/>
 */
@Slf4j
public abstract class AbstractQueryFacade {


    @Setter
    private Datasource dataSource;

    private ReqContext context;

    /**
     * 绑定当前要操作的DataSource
     * @param context
     */
    public void initialize(ReqContext context) {
        log.debug("binding dataSource: {}", context);
        dataSource = Datasource.findByIdFromCache(context.getDatasourceId());
        this.context = context;
    }

    public Datasource getDataSource() {
        if (Objects.isNull(dataSource) || Objects.isNull(context)) {
            throw new DatasourceException("You should bind datasource before launch a query executor.");
        }
        return dataSource;
    }

    /**
     * 获取查询执行器
     *
     * @return
     */
    protected QueryExecutor getQueryExecutor() {
        if (!getDataSource().getAvailable()) {
            throw new DatasourceException(String.format("数据源不可用, Id: %s", context.getDatasourceId()));
        }
        switch (getDataSource().getDriver()) {
            case POSTGRES:
                return PostgresQueryExecutor.context(context);
            default:
                throw new DatasourceException(String.format("不支持的datasource driver :%s", dataSource.getDriver()));
        }
    }
}
