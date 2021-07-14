package com.bosuyun.platform.common.utils;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.beans.BeanMap;
import net.sf.cglib.core.Converter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * Created by liuyuancheng on 2020/8/14  <br/>
 */
@Slf4j
public class BeanUtils {

    /**
     * Bean复制（忽略null）
     *
     * @param source
     * @param target
     * @param <T>
     * @return
     */
    public static <T, S> T copyNonNullProperties(S source, T target) {
        final Map<String, Object> targetMap = beanToMap(target);
        return copyProperties(source, target, new Converter() {
            @Override
            public Object convert(Object value, Class target, Object context) {
                if (Objects.isNull(value)) {
                    String property = context.toString().replace("set", "");
                    return targetMap.get(StringCaseUtils.toLowerCaseFirstOne(property));
                }
                return value;
            }
        });
    }

    public static <T, S> T copyValidProperties(S source, T target) {
        final Map<String, Object> targetMap = beanToMap(target);
        return copyProperties(source, target, new Converter() {
            @Override
            public Object convert(Object value, Class target, Object context) {
                if (Objects.isNull(value) || org.apache.commons.lang3.StringUtils.isBlank(value.toString())) {
                    String property = context.toString().replace("set", "");
                    return targetMap.get(StringCaseUtils.toLowerCaseFirstOne(property));
                }
                return value;
            }
        });
    }


    /**
     * Don't Use Convertor
     *
     * @param source
     * @param target
     * @param <T>
     * @return
     */
    public static <T, S> T copyProperties(S source, T target) {
        return copyProperties(source, target, null);
    }

    /**
     * Use A Convertor
     *
     * @param source
     * @param target
     * @param converter
     * @param <T>
     * @return
     */
    public static <T, S> T copyProperties(S source, T target, Converter converter) {
        final BeanCopier beanCopier = BeanCopier.create(source.getClass(),
                target.getClass(),
                Objects.nonNull(converter)
        );
        System.out.println(source);
        beanCopier.copy(source, target, converter);
        System.out.println(target);
        return target;
    }


    /**
     * 将对象装换为map
     *
     * @param bean
     * @return
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 将map装换为javabean对象
     *
     * @param map
     * @param bean
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    /**
     * map转object
     *
     * @param propertyMap
     * @return
     */
    public static Object mapToCglibBean(Map<String, Object> propertyMap) {
        Map<String, Class<?>> propertyClassMap = new HashMap<>();
        propertyMap.forEach((key, value) -> {
            propertyClassMap.put(key, value.getClass());
        });
        CglibBean bean = new CglibBean(propertyClassMap);
        bean.setValues(propertyMap);
        return bean.getObject();
    }
}
