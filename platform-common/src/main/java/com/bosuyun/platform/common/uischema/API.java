package com.bosuyun.platform.common.uischema;

import com.bosuyun.platform.common.misc.DataNode;
import io.vertx.core.http.HttpMethod;

/**
 * API 类型用于配置请求接口的格式，涉及请求方式、请求地址、请求数据体等等相关配置
 *
 * Created by liuyuancheng on 2021/1/7  <br/>
 */
public class API extends DataNode {

    protected static final String METHOD = "method";
    protected static final String URL = "url";
    protected static final String DATA_TYPE = "dataType";

    {
        put(METHOD, HttpMethod.POST.toString());
        put(URL, "");
        put(DATA_TYPE, "");
    }

}
