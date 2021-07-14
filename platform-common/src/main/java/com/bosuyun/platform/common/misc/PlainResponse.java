package com.bosuyun.platform.common.misc;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * Created by liuyuancheng on 2020/8/5  <br/>
 */
@Data
@SuperBuilder
public class PlainResponse {
    /**
     * 小于1：错误   0：成功
     */
    public Integer code;

    public String message;
    /**
     * track or response data
     */
    public Object data;
}
