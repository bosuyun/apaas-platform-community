package com.bosuyun.platform.common.utils;


import com.google.common.io.Resources;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by liuyuancheng on 2020/9/15  <br/>
 */
public class ResourceUtils {
    public static String loadFile(String filename) {
        try {
            return Resources.toString(Resources.getResource(filename), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
