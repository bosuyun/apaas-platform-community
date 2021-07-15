package com.bosuyun.platform.common.eventbus.event;

import com.bosuyun.platform.common.eventbus.message.codecs.DataOpMessageCodec;
import com.bosuyun.platform.common.eventbus.message.DataOpMessage;
import io.quarkus.vertx.ConsumeEvent;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;

/**
 * 数据变化
 * <p>
 * Created by liuyuancheng on 2021/6/18  <br/>
 */
@Slf4j
@ApplicationScoped
public class DataOpEvent implements IEvent {

    @ConsumeEvent(value = IEvent.DATA_OP_EVENT,codec = DataOpMessageCodec.class)
    void consume(DataOpMessage message) {
        log.warn("consumer {} received message :{}", IEvent.DATA_OP_EVENT, message);
    }

}
