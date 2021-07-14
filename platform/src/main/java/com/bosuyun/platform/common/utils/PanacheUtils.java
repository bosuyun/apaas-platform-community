package com.bosuyun.platform.common.utils;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;

/**
 * Created by liuyuancheng on 2021/3/10  <br/>
 */
public class PanacheUtils {

    public static String queryConditionJoining(Map<String, Object> conditionMap) {
        if (conditionMap.isEmpty()) {
            return StringUtils.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        conditionMap.forEach((key, value) -> {
            if (Objects.nonNull(value) && StringUtils.isNotBlank(value.toString())) {
                sb.append(key).append(" = :").append(key).append(" and ");
            }
        });
        if (sb.length() == 0) {
            return StringUtils.EMPTY;
        }
        return sb.replace(sb.length() - 4, sb.length(), StringUtils.EMPTY).toString();
    }

    /**
     * 删除无效参数
     *
     * @param queryParams
     * @return
     */
    public static Map<String, Object> removeInvalidParams(Map<String, Object> queryParams) {
        return Maps.filterValues(queryParams, value -> Objects.nonNull(value)
                && StringUtils.isNotBlank(value.toString())
        );
    }

}
