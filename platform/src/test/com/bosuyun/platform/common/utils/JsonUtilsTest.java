package com.bosuyun.platform.common.utils;

import com.fasterxml.jackson.databind.JsonNode;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by liuyuancheng on 2020/12/14  <br/>
 */
@QuarkusTest
class JsonUtilsTest {

    @Test
    void tstringToJsonNode() {
        JsonNode jsonNode = JsonUtils.toJsonNode("{\"name\":\"liu\"}");
        Assertions.assertEquals(jsonNode.get("name").asText(), "liu", "JsonUtilJson对象转换");
    }


    /**
     * 字符串转数组
     */
    @Test
    void tstringToJsonNodeArray() {
        JsonNode jsonNode = JsonUtils.toJsonNode("[23,33,456,6767]");
        Assertions.assertEquals(jsonNode.get(0).intValue(), 23, "JsonUtils 字符串转数组");
    }

    /**
     * Java类型转json字符串
     */
    @Test
    void toJsonString() {
        Map<String, Object> map = new HashMap<String, Object>() {{
            put("name", "liu");
            put("age", 30);
        }};
        System.out.println(JsonUtils.toJsonString(map));
    }
}
