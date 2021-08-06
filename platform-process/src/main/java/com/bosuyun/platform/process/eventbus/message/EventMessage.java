package com.bosuyun.platform.process.eventbus.message;

import com.bosuyun.platform.common.context.ReqContext;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by liuyuancheng on 2021/6/19  <br/>
 */
public abstract class EventMessage implements Serializable {

    /**
     * 动作
     */
    @Setter
    @Getter
    private String messageType;

    private ReqContext reqContext;

    public Boolean hasReqContext(){
        return Objects.nonNull(reqContext);
    }

    public EventMessage setReqContext(ReqContext reqContext) {
        this.reqContext = reqContext;
        return this;
    }

    public ReqContext getReqContext() {
        return reqContext;
    }

}
