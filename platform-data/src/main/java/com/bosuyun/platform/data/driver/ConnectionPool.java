package com.bosuyun.platform.data.driver;

import com.bosuyun.platform.common.definition.DSDriverEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by liuyuancheng on 2021/1/27  <br/>
 */
@Data
@Slf4j
@Singleton
public class ConnectionPool {

    private ConnectionPool(){}

    /**
     * 驱动类型
     */
    private DSDriverEnum driverType;

    /**
     * SQLConnection
     */
    private DriverManagerConnectionProviderImpl.PooledConnections dbConnection;

    /**
     * datasourceId -> entityManager
     */
    private ConcurrentHashMap<Long, EntityManager> em;

}
