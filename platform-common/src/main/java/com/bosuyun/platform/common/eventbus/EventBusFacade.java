package com.bosuyun.platform.common.eventbus;

import com.bosuyun.platform.common.exception.BizzException;
import com.bosuyun.platform.common.eventbus.message.codecs.DataOpMessageCodec;
import com.bosuyun.platform.common.eventbus.event.IEvent;
import com.bosuyun.platform.common.eventbus.message.EventMessage;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.mutiny.core.eventbus.EventBus;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Created by liuyuancheng on 2021/6/18  <br/>
 */
@Slf4j
@NoArgsConstructor
@ApplicationScoped
public class EventBusFacade {

    @Inject
    EventBus eventBus;

    public void launchDataOpEvent(EventMessage message) {
        if (!message.hasReqContext()) {
            throw new BizzException("必须传入ReqContext");
        }
        eventBus.publish(IEvent.DATA_OP_EVENT, message, new DeliveryOptions()
                .setCodecName(DataOpMessageCodec.class.getSimpleName()));
    }

    public void launchProcessEvent(EventMessage message) {
        if (!message.hasReqContext()) {
            throw new BizzException("必须传入ReqContext");
        }
        eventBus.publish(IEvent.PROCESS_OP_EVENT, message);
    }

}
