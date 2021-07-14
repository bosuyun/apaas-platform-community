package com.bosuyun.platform.data.driver;

import com.bosuyun.platform.common.definition.DSDriverTypeEnum;
import io.vertx.mutiny.pgclient.PgPool;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by liuyuancheng on 2021/1/27  <br/>
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@Slf4j
public class ConnectionPool {

    /**
     * 驱动类型
     */
    private DSDriverTypeEnum driverType;

    /**
     * SQLConnection
     */
    private PgPool dbConnection;


}
