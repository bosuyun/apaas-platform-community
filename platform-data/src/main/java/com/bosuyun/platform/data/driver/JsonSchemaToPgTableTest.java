package com.bosuyun.platform.data.driver;

import com.bosuyun.platform.common.misc.DataNode;
import com.bosuyun.platform.common.utils.JsonSchemaUtils;
import com.bosuyun.platform.common.utils.JsonUtils;
import com.bosuyun.platform.TestContext;
import io.quarkus.test.junit.QuarkusTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
@Slf4j
class JsonSchemaToPgTableTest {

    @Inject
    TestContext testContext;

    @Test
    void createTable() {
        testContext.getContextPostgres().setTableName("testggg").setSpaceId(111L);
        var t= "{\n" +
                "  \"$schema\": \"http://json-schema.org/draft-07/schema#\",\n" +
                "  \"type\": \"object\",\n" +
                "  \"properties\": {\n" +
                "    \"name\": {\n" +
                "      \"type\": \"string\",\n" +
                "      \"default\":\"testDefaultValue\",\n" +
                "      \"description\":\"testDesc\"\n" +
                "    },\n" +
                "    \"salary\": {\n" +
                "      \"type\": \"number\",\n" +
                "      \"title\": \"1.11\",\n" +
                "      \"default\":\"testDefaultValue\"\n" +
                "    },\n" +
                "    \"age\": {\n" +
                "      \"type\": \"integer\",\n" +
                "      \"default\":\"11\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"additionalProperties\": false,\n" +
                "  \"required\": [\n" +
                "    \"name\",\n" +
                "    \"salary\",\n" +
                "    \"age\"\n" +
                "  ]\n" +
                "}";
        var s = new JsonSchemaToPgTable().createTable(testContext.getContextPostgres(), JsonUtils.toJsonNode(t));
        System.out.println(s);
    }

    @Test
    void alterTable() {
        testContext.getContextPostgres().setTableName("testggg").setSpaceId(111L);
        var t = JsonSchemaUtils.gen(new DataNode() {{
            put("name", "zhangsan");
            put("age", 33);
            put("salary", 2.2222);
            put("sex",1);
        }});
        log.debug("t: {}",t);
        var s = new JsonSchemaToPgTable().alterTable(testContext.getContextPostgres(), t);
        log.debug("s: {}",s);
    }
}