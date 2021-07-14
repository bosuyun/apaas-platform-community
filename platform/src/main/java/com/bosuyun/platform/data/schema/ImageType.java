package com.bosuyun.platform.data.schema;

import com.bosuyun.platform.common.schema.ObjectType;
import com.bosuyun.platform.common.schema.SchemaNode;

/**
 * 基于基础JsonSchema类型扩展的
 *
 * Created by liuyuancheng on 2021/1/28  <br/>
 */
public class ImageType extends ObjectType {

    private static final String CUSTOM_PROPERTY_ALT = "alt";

    private static final String CUSTOM_PROPERTY_SRC = "src";

    public ImageType setSrc(String src) {
        this.setProperty(CUSTOM_PROPERTY_SRC, new SchemaNode()
                .setType(TYPE_STRING).setDefault(src))
                .setRequired(CUSTOM_PROPERTY_ALT);
        return this;
    }

    public String getAlt() {
        return this.get(CUSTOM_PROPERTY_ALT, String.class);
    }

    public String getSrc() {
        return this.get(CUSTOM_PROPERTY_SRC, String.class);
    }

    public ImageType setAlt(String alt) {
        this.setProperty(CUSTOM_PROPERTY_ALT, new SchemaNode().setType(TYPE_STRING).setDefault(alt));
        return this;
    }
}
