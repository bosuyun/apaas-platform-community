package com.bosuyun.platform.common.schema;

import com.bosuyun.platform.common.exception.BizzException;

import java.util.*;

/**
 * 对象类型
 *
 * Created by liuyuancheng on 2021/2/4  <br/>
 */
public class ObjectType extends SchemaNode {

    {
        setType("object");
        /**
         * 验证json schema property名称
         * 字母或下划线开头，只能包含字母和数字，不能包含空格
         */
        setPropertyNames("^[a-z_][a-z0-9_]*$");
    }

    public ObjectType setRequired(String... required) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_OBJECT_REQUIRED, Arrays.asList(required));
        return this;
    }

    @SuppressWarnings("unchecked")
    public List<String> getRequired() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_OBJECT_REQUIRED, List.class);
    }

    public ObjectType setProperties(Map<String, SchemaNode> required) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_OBJECT_PROPERTIES, required);
        return this;
    }

    public ObjectType setProperty(String propertyName, SchemaNode dataNode){
        if(Objects.isNull(getProperties())||getProperties().isEmpty()){
            this.put(JsonSchemaKey.JSON_SCHEMA_KEY_OBJECT_PROPERTIES, new SchemaNode());
        }
        this.getProperties().put(propertyName,dataNode);
        return this;
    }

    public ObjectType removeProperty(String propertyName){
        if(Objects.isNull(getProperties())||getProperties().isEmpty()){
            this.put(JsonSchemaKey.JSON_SCHEMA_KEY_OBJECT_PROPERTIES, new SchemaNode());
        }
        this.getProperties().remove(propertyName);
        return this;
    }

    @SuppressWarnings("unchecked")
    public Map<String, SchemaNode> getProperties() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_OBJECT_PROPERTIES, Map.class);
    }

    public SchemaNode getProperty(String propertyName){
        return getProperties().get(propertyName);
    }

    public ObjectType setDefinitions(Map<String, ObjectType> required) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_OBJECT_DEFINITIONS, required);
        return this;
    }

    @SuppressWarnings("unchecked")
    public Map<String, ObjectType> getDefinitions() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_OBJECT_DEFINITIONS, Map.class);
    }

    public ObjectType setDependencies(Map<String, SchemaNode> dependencies) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_OBJECT_DEPENDENCIES, dependencies);
        return this;
    }

    @SuppressWarnings("unchecked")
    public Map<String, SchemaNode> getDependencies() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_OBJECT_DEPENDENCIES, Map.class);
    }

    public Integer getMaxProperties() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_OBJECT_MAX_PROPERTIES, Integer.class);
    }

    public Integer getMinProperties() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_OBJECT_MIN_PROPERTIES, Integer.class);
    }

    public ObjectType setMinProperties(int minLength) {
        if (minLength < 0) {
            throw new BizzException("minLength不能小于0");
        }
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_OBJECT_MIN_PROPERTIES, minLength);
        return this;
    }

    public ObjectType setMaxLength(int maxLength) {
        if (Objects.nonNull(this.getMinProperties()) && maxLength < this.getMinProperties()) {
            throw new BizzException("max需要大于min");
        }
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_OBJECT_MAX_PROPERTIES, maxLength);
        return this;
    }

    public ObjectType setPropertyNames(String pattern) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_OBJECT_PROPERTY_NAMES, new HashMap<String,String>() {{
            put("pattern", pattern);
        }});
        return this;
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> getPropertyNames() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_OBJECT_PROPERTY_NAMES, HashMap.class);
    }

    public ObjectType setPatternProperties(String pattern) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_OBJECT_PATTERN_PROPERTIES, pattern);
        return this;
    }

    public String getPatternProperties() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_OBJECT_PATTERN_PROPERTIES, String.class);
    }

    public ObjectType setAdditionalProperties(Boolean bool) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_OBJECT_PATTERN_PROPERTIES, bool);
        return this;
    }

    public ObjectType setAdditionalProperties(SchemaNode schemaNode) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_OBJECT_ADDITIONAL_PROPERTIES, schemaNode);
        return this;
    }

    public Boolean getAdditionalProperties() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_OBJECT_ADDITIONAL_PROPERTIES, Boolean.class);
    }
}
