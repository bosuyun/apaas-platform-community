package com.bosuyun.platform.data.driver;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.bosuyun.platform.common.definition.DSDriverTypeEnum;
import com.bosuyun.platform.common.utils.AesUtils;
import com.bosuyun.platform.common.entity.Datasource;
import com.bosuyun.platform.data.msic.DatasourceException;
import io.vertx.mutiny.pgclient.PgPool;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * 线程池
 * <p>
 * Created by liuyuancheng on 2021/1/27  <br/>
 */
@Data
@Accessors(chain = true)
@Slf4j
public abstract class PoolConnectionFactory {

    protected static final long MAX_CONNECTION_WAIT_MILLIS = 6000;

    private Exception lastException = null;

    private ConnectionPool poolConnection = new ConnectionPool();

    abstract PoolConnectionFactory createConnection(final Datasource dataSource);

    abstract String getDataSourceUrl(final Datasource dataSource);

    /**
     * 数据库连接池-缓存
     */
    private static final Cache<Long, ConnectionPool> myCache = CacheBuilder
            .newBuilder()
            .initialCapacity(1)
            .maximumSize(100)
            .build();

    /**
     * 获取session
     *
     * @param dsId
     * @return
     */
    @SuppressWarnings("unchecked")
    public static ConnectionPool get(final long dsId) {
        ConnectionPool poolConnection = myCache.getIfPresent(dsId);
        if (Objects.nonNull(poolConnection)) {
            return poolConnection;
        }
//        DataSourceFacade dataSourceFacade = CDI.current().select(DataSourceFacade.class).get();
        Datasource dataSource = Datasource.findByIdFromCache(dsId);
        log.info("dataSource Id {}, setting {}", dsId, dataSource);
        switch (dataSource.getDriver()) {
            case POSTGRES:
                set(dsId, new PostgreSqlPool().createConnection(dataSource).getPoolConnection());
                break;
            default:
                throw new DatasourceException("不支持的协议: " + dataSource.getDriver());
        }
        return get(dsId);
    }

    /**
     * 保存session
     *
     * @param dsId
     * @param poolConnection
     */
    public static void set(long dsId, final ConnectionPool poolConnection) {
        log.debug("缓存datasource Id: {} session: {}", dsId, poolConnection.getClass().getSimpleName());
        myCache.put(dsId, poolConnection);
    }


    static class PostgreSqlPool extends PoolConnectionFactory {

        @Override
        public PoolConnectionFactory createConnection(final Datasource dataSource) {
            PgPool connection = PgPool.pool(getDataSourceUrl(dataSource));
            // that worked so save the datasource and server id
            this.getPoolConnection()
                    .setDbConnection(connection)
                    .setDriverType(DSDriverTypeEnum.POSTGRES);

            return this;
        }

        @Override
        public String getDataSourceUrl(final Datasource dataSource) {
            // jdbc:postgresql://localhost:5432/platform
            var connectionUri = StringUtils.join(
                    "postgres://", AesUtils.decrypt(dataSource.getUsername()), ":",
                    AesUtils.decrypt(dataSource.getPassword()), "@",
                    dataSource.getHost(), ":", dataSource.getPort(), "/", dataSource.getDbname()
            );
            return connectionUri;
        }

    }

}
