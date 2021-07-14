package com.bosuyun.platform.common.utils;

import com.bosuyun.platform.common.misc.DataNode;
import com.bosuyun.platform.common.misc.PlainResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class JsonSchemaUtilsTest {

    @Test
    void genMap() {
        var t = JsonSchemaUtils.gen(new HashMap<>() {
            {
                put("name", "zhangsan");
                put("age", 13);
            }
        });
        System.out.println(t);
    }

    @Test
    void genBean() {
        var t1 = JsonSchemaUtils.gen(PlainResponse.builder().code(1).message("adf").data("").build());
        System.out.println(t1);
    }

    @Test
    void jsonSchemaValidate() {
        String schema = "{\"$schema\": \"http://json-schema.org/draft-07/schema#\", \"properties\": { \"id\": {\"type\": \"number\"}}}";
        String data = "{\"id\": 2}";
        Assertions.assertTrue(JsonSchemaUtils.jsonSchemaValidate(schema, data));
        data = "{\"id\": \"2\"}";
        Assertions.assertFalse(JsonSchemaUtils.jsonSchemaValidate(schema, data));
        Assertions.assertTrue(JsonSchemaUtils.getLastValidationError().toString().contains("string found, number expected"));
    }

    @Test
    void getLastValidationError() {
    }

    @Test
    void genFromMap() {
    }

    @Test
    void gen() {
        DataNode dataNode = new DataNode("age", 11).append("name", "zhangsan");
        System.out.println(JsonSchemaUtils.gen(dataNode));
    }

    @Test
    void genFromJsonNode() {
        String data = "{\"🙈\":1,\"🙉\":{\"🐒\":true,\"🐵\":[2,\"1234:5678::\"],\"🍌\":null},\"🙊\":null,\"months\":[\"JANUARY\",\"FEBRUARY\"]}";
        System.out.println(JsonSchemaUtils.genFromJson(data));
        data = "{\"name\":\"zhangsan\"}";
        System.out.println(JsonSchemaUtils.genFromJson(data));
    }
}