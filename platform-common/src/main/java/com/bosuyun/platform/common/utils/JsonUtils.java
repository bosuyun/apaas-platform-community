package com.bosuyun.platform.common.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import java.util.Map;

/**
 * Created by liuyuancheng on 2020/9/9  <br/>
 */
@Singleton
@Slf4j
public class JsonUtils {

    public static final ObjectMapper mapper = new ObjectMapper(new JsonFactory());

    static {
        mapper.registerModule(new JavaTimeModule());
        mapper.setDateFormat(new StdDateFormat());
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * 对象转为JsonNode
     *
     * @param object
     * @return
     */
    public static JsonNode objectToJsonNode(Object object) {
        return mapper.convertValue(object, JsonNode.class);
    }

    /**
     * 判断是否合法的JSON
     *
     * @param input
     * @return
     */
    public static Boolean isValidJson(final String input) {
        try {
            mapper.readTree(input);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * JSON2Yaml
     *
     * @param input
     * @return
     */
    public static String json2Yaml(String input) {
        try {
            return new YAMLMapper().writeValueAsString(mapper.readTree(input));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 字符串转JsonNode
     *
     * @param input
     * @return
     */
    public static JsonNode toJsonNode(String input) {
        try {
            return mapper.readTree(input);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * BSON转为JSON字符串
     *
     * @param input
     * @return
     */
    public static String toJsonString(Object input) {
        try {
            return mapper.writeValueAsString(input);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String toJsonString(Object input, boolean pretty) {
        if (!pretty) {
            return toJsonString(input);
        }
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(input);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static JsonNode mapToJsonNode(Map<String, Object> jsonNode) {
        return mapper.valueToTree(jsonNode);
    }

    /**
     * json字符串转换为bean
     *
     * @param input
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T fromJsonString(String input, Class<T> clazz) {
        try {
            return mapper.readValue(input, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


}
