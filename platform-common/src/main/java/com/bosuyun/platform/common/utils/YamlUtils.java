package com.bosuyun.platform.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

/**
 * Created by liuyuancheng on 2020/9/9  <br/>
 */
public class YamlUtils {

    /**
     * Yaml转JSON
     *
     * @param yaml
     * @return
     */
    public static String yaml2Json(String yaml) {
        ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
        try {
            Object obj = yamlReader.readValue(yaml, Object.class);
            ObjectMapper jsonWriter = new ObjectMapper();
            return jsonWriter.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

}
