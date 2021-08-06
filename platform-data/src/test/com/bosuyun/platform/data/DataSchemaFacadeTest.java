package com.bosuyun.platform.data;

import com.bosuyun.platform.data.schema.DataSchemaFactory;

import org.junit.jupiter.api.Test;

import javax.inject.Inject;

/**
 * Created by liuyuancheng on 2021/3/26  <br/>
 */

class DataSchemaFacadeTest {

    @Inject
    DataSchemaFacade dataSchemaService;

    @Test
    void updateSchema() {
        var schema = DataSchemaFactory.newTree()
                .addField("name", DataSchemaFactory.string())
                .addField("age", DataSchemaFactory.integer());
        dataSchemaService.updateSchema(1L, schema);
    }

}
