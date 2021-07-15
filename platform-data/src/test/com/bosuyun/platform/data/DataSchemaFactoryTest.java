package com.bosuyun.platform.data;

import com.bosuyun.platform.data.schema.DataSchemaFactory;
import io.quarkus.test.junit.QuarkusTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * Created by liuyuancheng on 2020/12/17  <br/>
 */
@QuarkusTest
@Slf4j
class DataSchemaFactoryTest {

    @Inject
    DataSchemaFacade dataSchemaFacade;


//    /**
//     * 创建数据结构
//     */
    @Test
    @Transactional
    void createSchema() {
        var t = DataSchemaFactory.newTree().addField("image", DataSchemaFactory.image()
                .setAlt("test")
                .setSrc("https://www.baidu.com"))
                .addField("test", DataSchemaFactory.number().setDefault("1"))
                .setRequired("test");
//        var test = dataSchemaFacade.create(new DataSchema()
//                .setAppId(1L)
//                .setRealmId(1L)
//                .setSchema(dataSchemaNode)
//        );
        log.info("create success: {}", t);
    }
//
//    @TestG
//    @Transactional
//    void updateSchema() {
//        DataSchemaTree dataSchemaNode = new DataSchemaTree();
//        dataSchemaNode.addField("image", DataSchemaFactory.image()
//                .setAlt("test")
//                .setSrc("https://www.baidu.com")
//        );
//        dataSchemaNode.addField("datetime", DataSchemaFactory.datetime());
//        var test = dataSchemaFacade.updateSchema(10, dataSchemaNode);
//        log.info("create success: {}", test);
//        Assertions.assertTrue(true);
//    }

}
