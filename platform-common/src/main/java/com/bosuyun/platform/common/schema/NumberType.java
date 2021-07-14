package com.bosuyun.platform.common.schema;

import lombok.NoArgsConstructor;

/**
 * 数字类型
 *
 * Created by liuyuancheng on 2021/2/4  <br/>
 */
@NoArgsConstructor
public class NumberType extends SchemaNode {

    {
        setType(TYPE_NUMBER);
    }

    public NumberType setMultipleOf(Double multipleOf) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_NUMBER_MULTIPLE_OF, multipleOf);
        return this;
    }

    public Double getMultipleOf() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_NUMBER_MULTIPLE_OF, Double.class);
    }

    public NumberType setMinimum(Double multipleOf) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_NUMBER_MINIMUM, multipleOf);
        return this;
    }

    public Double getMinimum() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_NUMBER_MINIMUM, Double.class);
    }

    public NumberType setMaximum(Double multipleOf) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_NUMBER_MAXIMUM, multipleOf);
        return this;
    }

    public Double getMaximum() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_NUMBER_MAXIMUM, Double.class);
    }

    public NumberType setExclusiveMaximum(Double multipleOf) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_NUMBER_EXCLUSIVE_MAXIMUM, multipleOf);
        return this;
    }

    public Double getExclusiveMaximum() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_NUMBER_EXCLUSIVE_MAXIMUM, Double.class);
    }

    public NumberType setExclusiveMinimum(Double multipleOf) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_NUMBER_EXCLUSIVE_MINIMUM, multipleOf);
        return this;
    }

    public Double getExclusiveMinimum() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_NUMBER_EXCLUSIVE_MINIMUM, Double.class);
    }
}
