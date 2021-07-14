package com.bosuyun.platform.common.annotation;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 读取配置中心的配置值（数据库）
 *
 * Created by liuyuancheng on 2021/6/29 <br/>
 */
@Qualifier
@Retention(RUNTIME)
@Target({METHOD, FIELD, PARAMETER, TYPE})
public @interface PlatformProperty {
    /**
     * The key of the config property used to look up the configuration value.
     * If it is not specified, it will be derived automatically as {@code <class_name>.<injection_point_name>},
     * where {@code injection_point_name} is the field name or parameter name,
     * {@code class_name} is the fully qualified name of the class being injected to.
     * If one of the {@code class_name} or {@code injection_point_name} cannot be determined, the value has to be provided.
     *
     * @return Name (key) of the config property to inject
     */
    @Nonbinding
    String name() default "";

    /**
     * <p>The default value if the configured property value does not exist.
     *
     * <p>If the target Type is not String a proper {@link org.eclipse.microprofile.config.spi.Converter} will get applied.
     * That means that any default value string should follow the formatting rules of the registered Converters.
     *
     *
     * @return the default value as a string
     */
    @Nonbinding
    String defaultValue() default "";
}
