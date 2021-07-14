package com.bosuyun.platform.common.utils;

import com.google.common.base.CaseFormat;

/**
 * 字符串工具类
 *
 * Created by liuyuancheng on 2020/9/10  <br/>
 */
public class StringCaseUtils {

    /**
     * 首字母转大写
     *
     * @param s
     * @return
     */
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return Character.toUpperCase(s.charAt(0)) + s.substring(1);
        }
    }

    /**
     * 转为驼峰命名
     *
     * @param s
     * @return
     */
    public static String toCamel(String s) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, s);
    }

    /**
     * 转为下划线
     *
     * @param s
     * @return
     */
    public static String toUnderScore(String s) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, s);
    }

    /**
     * 首字母转小写
     *
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return Character.toLowerCase(s.charAt(0)) + s.substring(1);
        }
    }

}
