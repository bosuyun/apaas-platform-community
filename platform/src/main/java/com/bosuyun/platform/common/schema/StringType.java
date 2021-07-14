package com.bosuyun.platform.common.schema;

import com.bosuyun.platform.common.exception.BizzException;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by liuyuancheng on 2021/2/4  <br/>
 */

@NoArgsConstructor
public class StringType extends SchemaNode {

    private static final List<String> formatTypes = Arrays.asList("time", "date-time", "date", "email",
            "ipv4", "hostname", "ipv6", "uri", "uri-reference", "iri", "iri-reference", "uri-template", "json-pointer",
            "relative-json-pointer", "regex");

    /**
     * http://www.iana.org/assignments/media-types/media-types.xhtml
     */
    private static final List<String> contentMediaTypes = Arrays.asList("text/html",
            "text/xml", "text/css",
            "text/javascript","text/markdown","text/javascript","text/uri-list","image/png","image/bmp"
    );

    private static final List<String> contentEncodings = Arrays.asList("base64","quoted-printable","binary","8bit","7bit");

    {
        setType(TYPE_STRING);
    }

    public StringType setMaxLength(int maxLength) {
        if (Objects.nonNull(this.getMinLength()) && maxLength < this.getMinLength()) {
            throw new BizzException("max需要大于minLength");
        }
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_STRING_MAX_LENGTH, maxLength);
        return this;
    }

    public Integer getMaxLength() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_STRING_MAX_LENGTH, Integer.class);
    }

    public Integer getMinLength() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_STRING_MIN_LENGTH, Integer.class);
    }

    public StringType setMinLength(int minLength) {
        if (minLength < 0) {
            throw new BizzException("minLength不能小于0");
        }
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_STRING_MIN_LENGTH, minLength);
        return this;
    }

    public StringType setFormat(String format) {
        if (!formatTypes.contains(format)) {
            throw new BizzException("不支持的format");
        }
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_STRING_FORMAT, format);
        return this;
    }

    public String getFormat() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_STRING_FORMAT, String.class);
    }

    public StringType setPattern(String pattern) {
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_STRING_PATTERN, pattern);
        return this;
    }

    public String getPattern() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_STRING_PATTERN, String.class);
    }

    public StringType setContentEncoding(String encoding) {
        if(!contentEncodings.contains(encoding)){
            throw new BizzException("编码错误");
        }
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_STRING_CONTENT_ENCODING, encoding);
        return this;
    }

    public String getContentEncoding() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_STRING_CONTENT_ENCODING, String.class);
    }

    public StringType setContentMediaType(String contentMediaType) {
        if(!contentMediaTypes.contains(contentMediaType)){
            throw new BizzException("contentMediaType错误");
        }
        this.put(JsonSchemaKey.JSON_SCHEMA_KEY_STRING_CONTENT_MEDIA_TYPE, contentMediaType);
        return this;
    }

    public String getContentMediaType() {
        return this.get(JsonSchemaKey.JSON_SCHEMA_KEY_STRING_CONTENT_MEDIA_TYPE, String.class);
    }
}
