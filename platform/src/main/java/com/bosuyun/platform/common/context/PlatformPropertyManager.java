package com.bosuyun.platform.common.context;

import com.bosuyun.platform.common.entity.PlatformProperty;
import com.bosuyun.platform.common.exception.PlatformPropertyException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;

/**
 * Created by liuyuancheng on 2021/6/29  <br/>
 */
@ApplicationScoped
@Slf4j
public class PlatformPropertyManager {

    public static Integer asInteger(String key) {
        return Integer.parseInt(get(key));
    }

    public static Double asDouble(String key) {
        return Double.parseDouble(get(key));
    }

    public static String get(String key) {
        var platformPropertyOptional = PlatformProperty.findByKey(key);
        if (platformPropertyOptional.isPresent()) {
            var platformProperty = platformPropertyOptional.get();
            if (StringUtils.isBlank(platformProperty.getValue())) {
                return platformProperty.getValue();
            } else {
                return platformProperty.getDefaultValue();
            }
        } else {
            throw new PlatformPropertyException("Platform property named \"" + key + "\" is not exists.");
        }
    }

    /**
     * 更改配置项
     *
     * @param key
     * @param value
     */
    public static void forcePut(String key, Object value) {
        var platformPropertyOptional = PlatformProperty.findByKey(key);
        if (platformPropertyOptional.isPresent()) {
            PlatformProperty.update("value=?1 where key=?2", value, key);
        } else {
            add(key, value.toString(), value.toString(),"Forced put property.");
        }
    }

    public static void put(String key, Object value) {
        var platformPropertyOptional = PlatformProperty.findByKey(key);
        if (platformPropertyOptional.isPresent()) {
            PlatformProperty.update("value=?1 where key=?2", value.toString(), key);
        } else {
            throw new PlatformPropertyException("Platform property named " + key + " is exists.");
        }
    }


    /**
     * 添加新的配置项
     *
     * @param key
     * @param value
     * @param defaultValue
     */
    public static void add(String key, String value, String defaultValue,String comment) {
        PlatformProperty platformProperty = new PlatformProperty();
        platformProperty.setKey(key);
        platformProperty.setValue(value);
        platformProperty.setDefaultValue(defaultValue);
        platformProperty.setComment(comment);
        platformProperty.persist();
    }

}
