package com.bosuyun.platform.common.schema;

import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by liuyuancheng on 2021/2/4  <br/>
 */

@NoArgsConstructor
public class ArrayType extends SchemaNode {

    {
        setType(TYPE_ARRAY);
    }

    public ArrayType setItems(List<SchemaNode> items) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_ARRAY_ITEMS, items);
        return this;
    }

    @SuppressWarnings("unchecked")
    public List<SchemaNode> getItems() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_ARRAY_ITEMS, List.class);
    }

    public ArrayType setContains(SchemaNode contains) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_ARRAY_CONTAINS, contains);
        return this;
    }

    public SchemaNode getContains() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_ARRAY_CONTAINS, SchemaNode.class);
    }

    public ArrayType setAdditionalItems(Boolean additionalItems) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_ARRAY_ADDITIONAL_ITEMS, additionalItems);
        return this;
    }

    public ArrayType setAdditionalItems(SchemaNode contains) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_ARRAY_ADDITIONAL_ITEMS, contains);
        return this;
    }

    public SchemaNode getAdditionalItems() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_ARRAY_ADDITIONAL_ITEMS, SchemaNode.class);
    }

    public ArrayType setMinItems(Integer contains) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_ARRAY_MIN_ITEMS, contains);
        return this;
    }

    public Integer getMinItems() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_ARRAY_MIN_ITEMS, Integer.class);
    }

    public ArrayType setMaxItems(Integer contains) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_ARRAY_MAX_ITEMS, contains);
        return this;
    }

    public Integer getMaxItems() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_ARRAY_MAX_ITEMS, Integer.class);
    }

    public ArrayType setUniqueItems(Boolean uniqueItems) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_ARRAY_UNIQUE_ITEMS, uniqueItems);
        return this;
    }

    public Boolean getUniqueItems() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_ARRAY_UNIQUE_ITEMS, Boolean.class);
    }
}
