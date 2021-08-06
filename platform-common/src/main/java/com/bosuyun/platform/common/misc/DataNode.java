package com.bosuyun.platform.common.misc;

import com.bosuyun.platform.common.schema.ObjectType;
import com.bosuyun.platform.common.utils.BeanUtils;
import com.bosuyun.platform.common.utils.JsonSchemaUtils;
import com.bosuyun.platform.common.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 数据返回、查询条件等datasource层转换中枢
 * <p>
 * Created by liuyuancheng on 2021/3/23  <br/>
 */
@Slf4j
public class DataNode extends LinkedHashMap<String, Object> {

    /**
     * 对象转 map
     *
     * @param object
     */
    public DataNode(Object object){
        this.putAll(BeanUtils.beanToMap(object));
    }

    public DataNode(final String key, final Object value) {
        super();
        this.put(key, value);
    }

    public List<String> getKeyList() {
        var returning = new LinkedList<>(this.keySet());
        // 剔除系统字段
        returning.removeAll(SystemFieldConstants.getAll());
        return returning;
    }

    public DataNode() {
        super();
    }

    /**
     * 从字符串中解析
     *
     * @param data
     * @return
     */
    public static DataNode parse(String data) {
        return JsonUtils.fromJsonString(data, DataNode.class);
    }

    /**
     * 转换为JSON
     *
     * @return
     */
    public String toJson() {
        return JsonUtils.toJsonString(this);
    }

    /**
     * 转换并格式化为JSON串
     *
     * @return
     */
    public String toPrettyJson() {
        return JsonUtils.toJsonString(this, true);
    }

    public DataNode append(final String key, final Object object) {
        this.put(key, object);
        return this;
    }

    public DataNode asDataNode(final String key) {
        return (DataNode) this.get(key);
    }

    @SuppressWarnings("unchecked")
    public List<Object> asList(final String key) {
        return (List<Object>) this.get(key);
    }

    public Long asLong(final String key) {
        return Long.parseLong(this.get(key).toString());
    }

    public Integer asInt(final String key) {
        return Integer.parseInt(this.get(key).toString());
    }

    public Integer asIntPlus(final String key, final Integer plus) {
        return Integer.parseInt(this.get(key).toString()) + plus;
    }

    public String asString(String key) {
        return this.get(key).toString();
    }

    public String asText(String key) {
        return this.asString(key);
    }

    public <T> T asType(String key,Class<T> clazz){
        return clazz.cast(this.get(key));
    }

    /**
     * 获取第一条数据的key
     *
     * @return
     */
    public String getFirstEntry() {
        String[] keySet = this.keySet().toArray(String[]::new);
        if (keySet.length == 0) {
            return null;
        }
        return keySet[0];
    }

    public boolean nonEmpty() {
        return !isEmpty();
    }


    /**
     * 基于输入的Schema清洗字段
     *
     * @param schema
     * @return
     */
    public DataNode filterBySchema(ObjectType schema) {
        Set<String> schemaKeys = schema.getProperties().keySet();
        for (String dataKey : this.keySet()) {
            if (!schemaKeys.contains(dataKey)) {
                log.debug("Specific key \"{}\" does not exist in table schema, had been deleted.", dataKey);
                this.remove(dataKey);
            }
        }
        return this;
    }

    public String toJsonSchemaString(DataNode dataNode) {
        return JsonSchemaUtils.gen(dataNode).toString();
    }

    public Boolean valueEquals(DataNode comparedDataNode) {
        var myKeys = this.getKeyList();
        myKeys.retainAll(comparedDataNode.getKeyList());
        for (String myKey : myKeys) {
            if (!comparedDataNode.asString(myKey).equals(this.asString(myKey))) {
                return false;
            }
        }
        return true;
    }

    public DataNode getDiffData(DataNode inputData) {
        var returning = new DataNode();
        var myKeys = this.getKeyList();
        myKeys.retainAll(inputData.getKeyList());
        for (String myKey : myKeys) {
            if (!inputData.asString(myKey).equals(this.asString(myKey))) {
                returning.put(myKey, inputData.get(myKey));
            }
        }
        return returning;
    }

    @Override
    public String toString() {
        return this.toJson();
    }


}
