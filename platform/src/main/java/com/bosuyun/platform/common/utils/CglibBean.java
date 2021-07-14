package com.bosuyun.platform.common.utils;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;

import java.util.Map;

/**
 * Created by liuyuancheng on 2021/3/16  <br/>
 */
public class CglibBean {
    /**
     * 实体Object
     */
    private Object object = null;

    /**
     * 属性map
     */
    private BeanMap beanMap = null;

    public CglibBean() {
        super();
    }

    public CglibBean(Map<String, Class<?>> propertyMap) {
        this.object = generateBean(propertyMap);
        this.beanMap = BeanMap.create(this.object);
    }

    /**
     * 给bean属性赋值
     *
     * @param property 属性名
     * @param value    值
     */
    public void setValue(String property, Object value) {
        beanMap.put(property, value);
    }

    public void setValues(Map<String,Object> properties){
        beanMap.putAll(properties);
    }

    /**
     * 通过属性名得到属性值
     *
     * @param property 属性名
     * @return 值
     */
    public Object getValue(String property) {
        return beanMap.get(property);
    }

    /**
     * 得到该实体bean对象
     *
     * @return
     */
    public Object getObject() {
        return this.object;
    }

    private Object generateBean(Map<String, Class<?>> propertyMap) {
        if (propertyMap == null || propertyMap.isEmpty()) {
            return null;
        }
        BeanGenerator generator = new BeanGenerator();
        for (Map.Entry<String, Class<?>> entry : propertyMap.entrySet()) {
            generator.addProperty(entry.getKey(), entry.getValue());
        }
        return generator.create();
    }

}
