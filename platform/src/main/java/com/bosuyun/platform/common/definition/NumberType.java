package com.bosuyun.platform.common.definition;

import java.math.BigDecimal;

/**
 * 数字类型
 *
 * Created by liuyuancheng on 2021/2/3  <br/>
 */
public enum NumberType {
    INTEGER(Integer.class),
    DOUBLE(Double.class),
    BIG_DECIMAL(BigDecimal.class);
    private Class<? extends Number> type;

    NumberType(Class<? extends Number> type) {
        this.type = type;
    }

    public Class<? extends Number> getType() {
        return type;
    }

    public void setType(Class<? extends Number> type) {
        this.type = type;
    }
}
