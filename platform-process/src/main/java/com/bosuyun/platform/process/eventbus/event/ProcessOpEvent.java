package com.bosuyun.platform.process.eventbus.event;

import com.bosuyun.platform.common.eventbus.message.codecs.ProcessOpMessageCodec;
import com.bosuyun.platform.data.eventbus.message.ProcessOpMessage;
import io.quarkus.vertx.ConsumeEvent;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;

/**
 * Created by liuyuancheng on 2021/6/19  <br/>
 */
@Slf4j
@ApplicationScoped
public class ProcessOpEvent implements IEvent {

    @ConsumeEvent(value = IEvent.PROCESS_OP_EVENT, codec = ProcessOpMessageCodec.class)
    void consume(ProcessOpMessage message) {
        // Something blocking
        log.warn("consumer {} received message :{}", IEvent.PROCESS_OP_EVENT, message);
    }

}
