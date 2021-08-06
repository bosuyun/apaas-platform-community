package com.bosuyun.platform;

import com.bosuyun.platform.common.context.ReqContext;

import javax.inject.Singleton;

/**
 * Created by liuyuancheng on 2021/2/14  <br/>
 */
@Singleton
public class TestContext {

    private final ReqContext context = new ReqContext()
            .setTableName("data_testapp")
            .setApplicationId(1L)
            .setSchemaId(1L)
            .setTenantId(111L)
            .setDatasourceId(1L);

    public ReqContext getContext() {
        return context;
    }

    public ReqContext getContextPostgres(){
        return context.setDatasourceId(1L);
    }
}
