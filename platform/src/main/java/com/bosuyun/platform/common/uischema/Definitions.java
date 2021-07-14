package com.bosuyun.platform.common.uischema;


import com.bosuyun.platform.common.misc.DataNode;

/**
 * Definitions建立当前页面公共的配置项，在其他组件中可以通过$ref来引用当前配置项中的内容。
 * 只能在顶级节点中定义
 * <p>
 * Created by liuyuancheng on 2021/1/7  <br/>
 */
public class Definitions extends DataNode {

    /**
     * 添加条目
     * @param name
     * @param schemaNode
     * @return
     */
    public Definitions addItem(String name, UiSchemaNode schemaNode) {
        this.append(name, schemaNode);
        return this;
    }
}
