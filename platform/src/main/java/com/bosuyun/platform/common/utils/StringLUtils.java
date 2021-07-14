package com.bosuyun.platform.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * String工具类
 *
 * Created by liuyuancheng on 2021/6/17  <br/>
 */
public class StringLUtils {

    /**
     * /22/3/5476/式的字符串，转化为数组
     *
     * @param slashedId
     * @return
     */
    public static List<Long> slashedIdToList(String slashedId) {
        return Arrays.stream(slashedId.split(",")).map(Long::parseLong)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * /公司/部门/二级部门/  式的字符串，转化为数组
     *
     * @param slashedString
     * @return
     */
    public static List<String> slashedStringToList(String slashedString) {
        return Arrays.stream(slashedString.split("/"))
                .filter(e -> Objects.nonNull(e) && StringUtils.isNotBlank(e))
                .collect(Collectors.toList());
    }

}
