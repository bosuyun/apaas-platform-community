package com.bosuyun.platform.common.schema;

/**
 *
 * https://json-schema.org/understanding-json-schema/reference/generic.html
 *
 * Created by liuyuancheng on 2021/3/18  <br/>
 */
public class JsonSchemaKey {

    // 通用
    public static final String JSON_SCHEMA_KEY_DOLLAR_ID = "$id";
    public static final String JSON_SCHEMA_KEY_DOLLAR_SCHEMA = "$schema";
    public static final String JSON_SCHEMA_KEY_DOLLAR_REF = "$ref";
    /**
     * 草案7新增关键字.用于向jsonschema添加注释.它的值必须始终是一个字符串.
     */
    public static final String JSON_SCHEMA_KEY_DOLLAR_COMMENT = "$comment";
    public static final String JSON_SCHEMA_KEY_TITLE = "title";
    public static final String JSON_SCHEMA_KEY_DESCRIPTION = "description";
    public static final String JSON_SCHEMA_KEY_TYPE = "type";
    public static final String JSON_SCHEMA_KEY_ENUM = "enum";
    /**
     * 该字段用于将值限值为单个值
     */
    public static final String JSON_SCHEMA_KEY_CONST = "const";
    public static final String JSON_SCHEMA_KEY_DEFAULT = "default";
    public static final String JSON_SCHEMA_KEY_EXAMPLES = "examples";

    // string
    public static final String JSON_SCHEMA_KEY_STRING_CONTENT_MEDIA_TYPE = "contentMediaType";
    public static final String JSON_SCHEMA_KEY_STRING_CONTENT_ENCODING = "contentEncoding";
    public static final String JSON_SCHEMA_KEY_STRING_MIN_LENGTH = "minLength";
    public static final String JSON_SCHEMA_KEY_STRING_MAX_LENGTH = "maxLength";
    public static final String JSON_SCHEMA_KEY_STRING_PATTERN = "pattern";
    public static final String JSON_SCHEMA_KEY_STRING_FORMAT = "format";
    // number
    public static final String JSON_SCHEMA_KEY_NUMBER_MULTIPLE_OF = "multipleOf";
    public static final String JSON_SCHEMA_KEY_NUMBER_MINIMUM = "minimum";
    public static final String JSON_SCHEMA_KEY_NUMBER_MAXIMUM = "maximum";
    public static final String JSON_SCHEMA_KEY_NUMBER_EXCLUSIVE_MINIMUM = "exclusiveMinimum";
    public static final String JSON_SCHEMA_KEY_NUMBER_EXCLUSIVE_MAXIMUM = "exclusiveMaximum";

    // object
    public static final String JSON_SCHEMA_KEY_OBJECT_DEFINITIONS = "definitions";
    public static final String JSON_SCHEMA_KEY_OBJECT_PROPERTIES = "properties";
    public static final String JSON_SCHEMA_KEY_OBJECT_REQUIRED = "required";
    public static final String JSON_SCHEMA_KEY_OBJECT_ADDITIONAL_PROPERTIES = "additionalProperties";
    /**
     * 只验证属性的名称,而不校验它的值.
     */
    public static final String JSON_SCHEMA_KEY_OBJECT_PROPERTY_NAMES = "propertyNames";
    public static final String JSON_SCHEMA_KEY_OBJECT_PATTERN_PROPERTIES = "patternProperties";
    /**
     * 属性数量限制
     */
    public static final String JSON_SCHEMA_KEY_OBJECT_MIN_PROPERTIES = "minProperties";
    public static final String JSON_SCHEMA_KEY_OBJECT_MAX_PROPERTIES = "maxProperties";
    public static final String JSON_SCHEMA_KEY_OBJECT_DEPENDENCIES = "dependencies";

    // array
    public static final String JSON_SCHEMA_KEY_ARRAY_CONTAINS = "contains";
    public static final String JSON_SCHEMA_KEY_ARRAY_ITEMS = "items";
    /**
     * 额外项必须是数组
     */
    public static final String JSON_SCHEMA_KEY_ARRAY_ADDITIONAL_ITEMS = "additionalItems";
    public static final String JSON_SCHEMA_KEY_ARRAY_MIN_ITEMS = "minItems";
    public static final String JSON_SCHEMA_KEY_ARRAY_MAX_ITEMS = "maxItems";
    /**
     * 确保每一项都是唯一的
     */
    public static final String JSON_SCHEMA_KEY_ARRAY_UNIQUE_ITEMS = "uniqueItems";

    // 组合模式、子模式
    public static final String JSON_SCHEMA_KEY_SUB_ALL_OF = "allOf";
    public static final String JSON_SCHEMA_KEY_SUM_ANY_OF = "anyOf";
    public static final String JSON_SCHEMA_KEY_SUB_ONE_OF = "oneOf";
    public static final String JSON_SCHEMA_KEY_SUB_PATTERN_IF = "if";
    public static final String JSON_SCHEMA_KEY_SUB_PATTERN_THEN = "then";
    public static final String JSON_SCHEMA_KEY_SUB_PATTERN_ELSE = "else";
}
