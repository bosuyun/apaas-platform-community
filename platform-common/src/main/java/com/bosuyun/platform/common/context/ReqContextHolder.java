package com.bosuyun.platform.common.context;

/**
 * 存储在
 *
 * Created by liuyuancheng on 2020/12/14  <br/>
 */
public class ReqContextHolder {

    private static ThreadLocal<ReqContext> context;

    public static ReqContext getContext(){
        return new ReqContext();
    }

}
