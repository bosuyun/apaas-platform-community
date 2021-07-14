package com.bosuyun.platform.common.schema;

import com.bosuyun.platform.common.exception.BizzException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.bosuyun.platform.common.utils.JsonUtils;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * https://www.jianshu.com/p/fc97c1bbea39 官方解读 JSON SCHEMA
 * <p>
 * Created by liuyuancheng on 2021/2/1  <br/>
 */
@NoArgsConstructor
public class SchemaNode extends LinkedHashMap<String, Object> {

    public static final String TYPE_ARRAY = "array";
    public static final String TYPE_NULL = "null";
    public static final String TYPE_OBJECT = "object";
    public static final String TYPE_NUMBER = "number";
    public static final String TYPE_INTEGER = "integer";
    public static final String TYPE_BOOLEAN = "boolean";
    public static final String TYPE_STRING = "string";

    /**
     * 在PostgreSQL中对应的数据类型
     */
    private static final List<String> schemaTypes = Arrays.asList(TYPE_ARRAY,
            TYPE_NULL, TYPE_OBJECT,
            TYPE_STRING, TYPE_NUMBER, TYPE_INTEGER,
            TYPE_BOOLEAN
    );

    private static final List<String> plainTypes = Arrays.asList(TYPE_NULL,
            TYPE_NUMBER, TYPE_BOOLEAN,
            TYPE_STRING, TYPE_INTEGER
    );

    /**
     * 是否为一般类型（非object）
     * @return
     */
    public Boolean isPlainType() {
        return plainTypes.contains(getType());
    }

    public <T> T get(String key, Class<T> clazz) {
        return clazz.cast(this.get(key));
    }

    public SchemaNode getRootNode(final String id, final String schemaVersion) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_DOLLAR_ID, id);
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_DOLLAR_SCHEMA, schemaVersion);
        return this;
    }

    public SchemaNode setType(String type) {
        if (!schemaTypes.contains(type)) {
            throw new BizzException("不支持的schema type");
        }
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_TYPE, type);
        return this;
    }

    public String getType() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_TYPE, String.class);
    }

    public SchemaNode setTitle(String title) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_TITLE, title);
        return this;
    }

    public String getTitle() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_TITLE, String.class);
    }

    public String getComment() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_DOLLAR_COMMENT, String.class);
    }

    public SchemaNode setComment(String comment) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_DOLLAR_COMMENT, comment);
        return this;
    }

    public String getDefault() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_DEFAULT, String.class);
    }

    public SchemaNode setDefault(String defaultV) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_DEFAULT, defaultV);
        return this;
    }

    public String getConst() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_CONST, String.class);
    }

    public SchemaNode setConst(String defaultV) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_CONST, defaultV);
        return this;
    }

    public SchemaNode setEnum(ObjectArrayNode enumNode) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_ENUM, enumNode);
        return this;
    }

    public ObjectArrayNode getEnum() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_ENUM, ObjectArrayNode.class);
    }

    public SchemaNode setExamples(ObjectArrayNode enumNode) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_EXAMPLES, enumNode);
        return this;
    }

    public ObjectArrayNode getExamples() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_EXAMPLES, ObjectArrayNode.class);
    }

    public SchemaNode setDescription(String description) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_DESCRIPTION, description);
        return this;
    }

    public String getDescription() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_DESCRIPTION, String.class);
    }


    public JsonNode toJsonNode() {
        return new ObjectNode(JsonUtils.mapper.getNodeFactory(), (HashMap) this);
    }

}
