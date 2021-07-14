package com.bosuyun.platform.common.uischema;

import com.bosuyun.platform.common.definition.UiComponentType;
import com.bosuyun.platform.common.misc.DataNode;

/**
 * UiSchemaNode 是指每一个 amis 配置节点的类型，支持模板、Schema（配置）以及SchemaArray（配置数组）三种类型
 *
 * Created by liuyuancheng on 2021/1/7  <br/>
 */
public class UiSchemaNode extends DataNode {
    /**
     * Schema类型
     */
    protected static final String TYPE = "type";

    /**
     * 只能在顶级节点中定义
     */
    protected static final String DEFINITIONS = "definitions";

    protected static final String VISIBLE = "visible";

    protected static final String VISIBLE_ON = "visibleOn";

    protected static final String DISABLED = "disabled";

    protected static final String DISABLED_ON = "disabledOn";

    protected void setType(UiComponentType componentType) {
        this.put(TYPE, componentType.getComponentType());
    }
}
