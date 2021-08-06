package com.bosuyun.platform.common.uischema;

import com.bosuyun.platform.common.misc.DataNode;

import javax.ws.rs.HttpMethod;

/**
 * API 类型用于配置请求接口的格式，涉及请求方式、请求地址、请求数据体等等相关配置
 * <p>
 * Created by liuyuancheng on 2021/1/7  <br/>
 */
public class API extends DataNode {

    protected static final String METHOD = "method";
    protected static final String URL = "url";
    protected static final String DATA_TYPE = "dataType";

    {
        put(METHOD, HttpMethod.POST);
        put(URL, "");
        put(DATA_TYPE, "");
    }

}
