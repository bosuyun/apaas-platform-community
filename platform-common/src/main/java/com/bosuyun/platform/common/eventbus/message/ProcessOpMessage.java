package com.bosuyun.platform.common.eventbus.message;

/**
 * 流程操作
 *
 * Created by liuyuancheng on 2021/6/19  <br/>
 */
public class ProcessOpMessage extends EventMessage {

    {
        setMessageType(this.getClass().getSimpleName());
    }

}
