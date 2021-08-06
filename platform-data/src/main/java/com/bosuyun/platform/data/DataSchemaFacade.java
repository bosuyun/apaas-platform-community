package com.bosuyun.platform.data;


import com.bosuyun.platform.data.exception.DataDriverException;
import com.bosuyun.platform.common.entity.DataSchema;
import com.bosuyun.platform.common.schema.ObjectType;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Objects;

/**
 * 数据APP管理
 * <p>
 * Created by liuyuancheng on 2020/12/11  <br/>
 */
@ApplicationScoped
@Slf4j
public class DataSchemaFacade {

    @Inject
    DataOpFacade dataFindFacade;


    private static final String CACHE_NAME = "DATA_SCHEMA";

    /**
     * 经缓存
     *
     * @param id
     * @return
     */
    public DataSchema findById(Long id) {
        return DataSchema.findById(id);
    }

    public ObjectType findSchemaNode(Long id) {
        DataSchema dataSchema = this.findById(id);
        if (Objects.isNull(dataSchema)) {
            throw new DataDriverException("Schema id " + id + " is not exists.");
        }
        return dataSchema.getSchema();
    }

    public void invalidate(Long id) {
    }

    /**
     * 创建或更新一条schema
     *
     * @param dataSchema
     * @return
     */
    public DataSchema upOrCreate(DataSchema dataSchema) {
        return null;
    }


    /**
     * 更新schema 创建新的版本
     *
     * @param id
     * @param schema
     * @return
     */
    @Transactional
    public DataSchema updateSchema(Long id, ObjectType schema) {
        DataSchema dataSchema = findById(id);
        DataSchema.update("schema=?1 where id=?2", schema, id);
        log.debug("更新dataSchema, {} => {}", id, schema);
        return dataSchema;
    }

}
