package com.bosuyun.platform.common.schema;

import lombok.NoArgsConstructor;

/**
 * 整型
 *
 * Created by liuyuancheng on 2021/2/4  <br/>
 */
@NoArgsConstructor
public class IntegerType extends SchemaNode {


    {
        setType(TYPE_INTEGER);
    }

    public IntegerType setMultipleOf(Integer multipleOf) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_NUMBER_MULTIPLE_OF, multipleOf);
        return this;
    }

    public Integer getMultipleOf() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_NUMBER_MULTIPLE_OF, Integer.class);
    }


}
