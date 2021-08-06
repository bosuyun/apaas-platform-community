package com.bosuyun.platform.data.driver;

import com.bosuyun.platform.common.definition.DSDriverEnum;
import com.bosuyun.platform.common.entity.Datasource;
import com.bosuyun.platform.common.utils.AesUtils;
import com.bosuyun.platform.data.exception.DatasourceException;
import com.bosuyun.platform.data.exception.UnsupportedDriverTypeException;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 线程池
 * <p>
 * Created by liuyuancheng on 2021/1/27  <br/>
 */
@Data
@Slf4j
public abstract class PoolConnectionFactory {

    protected static final long MAX_CONNECTION_WAIT_MILLIS = 6000;

    private Exception lastException = null;

    @Inject
    private ConnectionPool poolConnection;

    abstract PoolConnectionFactory createConnection(final Datasource dataSource);

    abstract Map<String, String> getDataSourceProperties(final Datasource dataSource);

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
     * @param datasourceId
     * @return
     */
    @SuppressWarnings("unchecked")
    public static ConnectionPool get(final long datasourceId) {
        ConnectionPool poolConnection = myCache.getIfPresent(datasourceId);
        if (Objects.nonNull(poolConnection)) {
            return poolConnection;
        }
//        DataSourceFacade dataSourceFacade = CDI.current().select(DataSourceFacade.class).get();
        Datasource dataSource = Datasource.findByIdFromCache(datasourceId);
        log.info("dataSource Id {}, setting {}", datasourceId, dataSource);
        switch (dataSource.getDriver()) {
            case POSTGRES:
                set(datasourceId, new PostgreSqlPool().createConnection(dataSource).getPoolConnection());
                break;
            default:
                throw new DatasourceException("不支持的协议: " + dataSource.getDriver());
        }
        return get(datasourceId);
    }

    /**
     * 保存session
     *
     * @param datasourceId
     * @param poolConnection
     */
    public static void set(long datasourceId, final ConnectionPool poolConnection) {
        log.debug("缓存datasource Id: {} session: {}", datasourceId, poolConnection.getClass().getSimpleName());
        myCache.put(datasourceId, poolConnection);
    }


    static class PostgreSqlPool extends PoolConnectionFactory {

        @Override
        public PoolConnectionFactory createConnection(final Datasource dataSource) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(dataSource.getId().toString(), getDataSourceProperties(dataSource));
            // that worked so save the datasource and server id
            getPoolConnection().getEm().put(dataSource.getId(), emf.createEntityManager());
            return this;
        }

        @Override
        public Map<String, String> getDataSourceProperties(final Datasource dataSource) {
            Map<String, String> properties = new HashMap<>();
            properties.put("hibernate.hbm2ddl.auto", "none");
            properties.put("javax.persistence.jdbc.user", AesUtils.decrypt(dataSource.getUsername()));
            properties.put("javax.persistence.jdbc.password", AesUtils.decrypt(dataSource.getPassword()));
            if (dataSource.getDriver().equals(DSDriverEnum.POSTGRES)) {
                var url = StringUtils.join("jdbc:postgres://", dataSource.getHost(), ":", dataSource.getPort(), "/", dataSource.getDbname());
                properties.put("hibernate.dialect", DSDriverEnum.POSTGRES.getDialect());
                properties.put("javax.persistence.jdbc.driver", DSDriverEnum.POSTGRES.getDriver());
                properties.put("javax.persistence.jdbc.url", url);
                return properties;
            }
            throw new UnsupportedDriverTypeException();
        }

    }

}
