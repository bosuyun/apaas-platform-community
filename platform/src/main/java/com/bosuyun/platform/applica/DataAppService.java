package com.bosuyun.platform.applica;


import com.bosuyun.platform.common.exception.ApplicaException;
import com.bosuyun.platform.common.context.ReqContext;
import com.bosuyun.platform.common.misc.DataNode;
import com.bosuyun.platform.common.schema.SchemaNode;
import com.bosuyun.platform.common.utils.JsonSchemaUtils;
import com.bosuyun.platform.data.DataSchemaFacade;
import com.bosuyun.platform.data.DatasourceService;
import com.bosuyun.platform.data.DataOpFacade;
import io.quarkus.cache.CacheInvalidate;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


/**
 * 数据APP管理
 * <p>
 * Created by liuyuancheng on 2020/12/11  <br/>
 */
@ApplicationScoped
@Slf4j
public class DataAppService {

    private static final String CACHE_NAME = "DATA_APP";

    @Inject
    DataSchemaFacade dataSchemaFacade;

    @Inject
    DataOpFacade queryEngineFacade;

    @Inject
    DatasourceService datasourceFacade;

    /**
     * 向APP写入数据
     *
     * @param context
     * @param data
     * @param more    写入多个node
     */
    public void writeData(ReqContext context, @NonNull DataNode data, DataNode... more) {
        log.debug("写入数据 (只显示第一条): {}, context: {}", data, context);
        queryEngineFacade.initialize(context);
        SchemaNode schema = dataSchemaFacade.findById(context.getSchemaId()).getSchema();
        if (!JsonSchemaUtils.jsonSchemaValidate(schema.toString(), data.toJson())) {
            throw new ApplicaException("写入数据时，Json Schema验证失败：" + JsonSchemaUtils.getLastValidationError().toString());
        }
        queryEngineFacade.insertOne(data);
    }

    @CacheInvalidate(cacheName = CACHE_NAME)
    public void invalidate(Long appId) {
        log.debug("释放 {} 缓存 appId: {}", CACHE_NAME, appId);
    }
}
