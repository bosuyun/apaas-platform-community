package com.bosuyun.platform.common.exception;

/**
 * 顶级业务异常
 * <p>
 * Created by liuyuancheng on 2020/6/30  <br/>
 */
public class BizzException extends RuntimeException {
    public BizzException(String s) {
        super(s);
    }

    public BizzException(){
        super();
    }
}
