package com.bosuyun.platform.applica;

import com.bosuyun.platform.common.misc.DataNode;
import com.bosuyun.platform.data.DataSchemaFacade;
import com.bosuyun.platform.TestContext;
import com.bosuyun.platform.data.schema.DataSchemaFactory;
import io.quarkus.test.junit.QuarkusTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

/**
 * Created by liuyuancheng on 2020/12/17  <br/>
 */
@QuarkusTest
@Slf4j
class ElementFormServiceTest {

    @Inject
    DataAppService dataAppFacade;

    @Inject
    TestContext testContext;

    @Inject
    DataSchemaFacade dataSchemaService;

    @Test
    void writeDataToApp() {
        // 设置dataSchema
        var schema = DataSchemaFactory.newTree()
                .addField("name", DataSchemaFactory.string())
                .addField("age", DataSchemaFactory.integer())
                .addField("desc", DataSchemaFactory.string())
                .setRequired("desc");
        dataSchemaService.updateSchema(1L, schema);
        // 向APP写入数据
        DataNode dataNode = new DataNode();
        dataNode.put("name", "xiaosan");
        dataNode.put("age", 30);
        dataNode.put("desc","我是中国人，我来自江苏");
        dataNode.put("xxx","222");
        dataAppFacade.writeData(testContext.getContext(), dataNode);
        Assertions.assertTrue(true);
    }



}
