package com.bosuyun.platform.data.eventbus;

import com.bosuyun.platform.common.exception.BizzException;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.util.concurrent.Executors;

/**
 * using for domain event.
 * <p>
 * Created by liuyuancheng on 2021/6/18  <br/>
 */
@Slf4j
@Singleton
public class EventBusFacade {

    private static final String eventBusId = "platform-data";

    private static final EventBus eventBus = new EventBus(eventBusId);

    private static final AsyncEventBus asyncEventBus = new AsyncEventBus(eventBusId, Executors.newFixedThreadPool(2));

    /**
     * 初始化
     */
    @PostConstruct
    public void init(){
        eventBus.register(new DataOperationListener());
    }

    public void launchDataOpEvent(DataOperationMessage message) {
        if (!message.hasReqContext()) {
            throw new BizzException("必须传入ReqContext");
        }
        eventBus.post(message);
    }

    public void launchProcessEvent(DataOperationMessage message) {
        if (!message.hasReqContext()) {
            throw new BizzException("必须传入ReqContext");
        }
        eventBus.post(message);
    }

}
