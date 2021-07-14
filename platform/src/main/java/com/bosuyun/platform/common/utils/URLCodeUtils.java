package com.bosuyun.platform.common.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * URL 编码和解码
 *
 * Created by liuyuancheng on 2020/12/17  <br/>
 */
public class URLCodeUtils {

    /**
     * 编码
     * @param value
     * @return
     */
    public static String encodeValue(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

    /**
     * 解码
     * @param value
     * @return
     */
    public static String decodeValue(String value) {
        return URLDecoder.decode(value, StandardCharsets.UTF_8);
    }
}
