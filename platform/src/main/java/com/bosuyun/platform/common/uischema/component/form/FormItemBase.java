package com.bosuyun.platform.common.uischema.component.form;

import com.bosuyun.platform.common.uischema.UiSchemaNode;

/**
 * 定义字段（多个field构成 from schema）
 * 与https://baidu.github.io/amis/ 对应
 * 用_符号标识不需要在data engine持久化的字段
 * <p>
 * Created by liuyuancheng on 2020/12/16  <br/>
 */
public abstract class FormItemBase extends UiSchemaNode {

    /**
     * "isNumeric,minimum:10" example
     */
    protected static final String VALIDATIONS = "validations";

    protected static final String VALIDATION_ERRORS = "validationErrors";

    protected static final String LABEL = "label";

    protected static final String NAME = "name";

    protected static final String DESCRIPTION = "description";

    protected static final String REQUIRED = "required";

    protected static final String LABEL_REMARK = "labelRemark";

    protected static final String MODE = "mode";

    protected static final String MODE_INLINE = "inline";

    protected static final String SIZE = "size";

    /**
     * 表单验证
     */
    public static class Validations extends UiSchemaNode {
    }

    /**
     * 表单验证错误提示
     */
    public static class validationErrors extends UiSchemaNode {

    }



}
