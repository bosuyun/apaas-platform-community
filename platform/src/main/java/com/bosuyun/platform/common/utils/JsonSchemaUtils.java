package com.bosuyun.platform.common.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.victools.jsonschema.generator.*;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersionDetector;
import com.networknt.schema.ValidationMessage;
import com.saasquatch.jsonschemainferrer.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;

import static com.bosuyun.platform.common.utils.JsonUtils.toJsonString;

/**
 * Created by liuyuancheng on 2021/3/16  <br/>
 */
@Slf4j
public class JsonSchemaUtils {

    private static final ThreadLocal<Set<ValidationMessage>> lastValidationErrors = new ThreadLocal<>();

    private static final JsonSchemaInferrer inferrer = JsonSchemaInferrer.newBuilder()
            .setSpecVersion(SpecVersion.DRAFT_07)
            // Requires commons-validator
            .addFormatInferrers(FormatInferrers.email(), FormatInferrers.ip(), FormatInferrers.dateTime())
            .setAdditionalPropertiesPolicy(AdditionalPropertiesPolicies.notAllowed())
            .setRequiredPolicy(RequiredPolicies.commonFields())
            .addEnumExtractors(EnumExtractors.validEnum(java.time.Month.class),
                    EnumExtractors.validEnum(java.time.DayOfWeek.class))
            .build();

    /**
     * json Schema验证
     *
     * @return
     */
    public static boolean jsonSchemaValidate(final JsonNode schema, final JsonNode data, JsonNode... dataList) {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersionDetector.detect(schema));
        JsonSchema jsonSchema = factory.getSchema(schema);
        Set<ValidationMessage> errors = jsonSchema.validate(data);
        if (errors.isEmpty()) {
            lastValidationErrors.remove();
            log.debug("Json Schema 验证通过");
            return true;
        } else {
            lastValidationErrors.set(errors);
            log.info("表单验证失败 {}", errors);
            return false;
        }
    }

    public static Set<ValidationMessage> getLastValidationError() {
        return lastValidationErrors.get();
    }

    public static boolean jsonSchemaValidate(String schema, String data, String... dataList) {
        JsonNode schemaJson = JsonUtils.toJsonNode(schema);
        JsonNode dataJson = JsonUtils.toJsonNode(data);
        return jsonSchemaValidate(schemaJson, dataJson);
    }

    /**
     * 生成json schema
     * //todo 间接使用json 字符串转换，性能较低；后期考虑使用Bean
     *
     * @param map
     * @return
     */
    public static JsonNode gen(final Map<String, Object> map) {
        return genFromJson(toJsonString(map));
    }

    /**
     * 生成 json schema
     * https://victools.github.io/jsonschema-generator/#generator-options
     *
     * @param object
     * @return
     */
    public static JsonNode gen(final Object object) {
        SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_7,
                OptionPreset.PLAIN_JSON
        );
//        configBuilder.forFields().withDefaultResolver(field -> field.getType().getErasedType() == String.class ? "" : null);
//        configBuilder.with(Option.NULLABLE_FIELDS_BY_DEFAULT);
        SchemaGeneratorConfig config = configBuilder.build();
        SchemaGenerator generator = new SchemaGenerator(config);
        return generator.generateSchema(object.getClass());
    }

    /**
     * 基于Json字符串推断
     * https://github.com/saasquatch/json-schema-inferrer
     *
     * @param input
     * @return
     */
    public static JsonNode genFromJson(String input) {
        return genFromJson(JsonUtils.toJsonNode(input));
    }

    public static JsonNode genFromJson(final JsonNode jsonNode) {
        return inferrer.inferForSample(jsonNode);
    }

}
