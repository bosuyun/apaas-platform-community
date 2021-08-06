package com.bosuyun.platform.process.eventbus;

import com.bosuyun.platform.common.exception.BizzException;
import com.bosuyun.platform.data.eventbus.event.DataOperationListener;
import com.bosuyun.platform.data.eventbus.message.EventMessage;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.util.concurrent.Executors;

import static com.bosuyun.platform.common.constants.PlatformConstants.PLATFORM_ID;

/**
 * using for domain event.
 * <p>
 * Created by liuyuancheng on 2021/6/18  <br/>
 */
@Slf4j
@NoArgsConstructor
@Singleton
public class EventBusFacade {

    private static final EventBus eventBus = new EventBus(PLATFORM_ID);

    private static final AsyncEventBus asyncEventBus = new AsyncEventBus(PLATFORM_ID, Executors.newFixedThreadPool(4));

    /**
     * 初始化
     */
    @PostConstruct
    public void init(){
        eventBus.register(new DataOperationListener());
    }

    public void launchDataOpEvent(EventMessage message) {
        if (!message.hasReqContext()) {
            throw new BizzException("必须传入ReqContext");
        }
        eventBus.post(message);
    }

    public void launchProcessEvent(EventMessage message) {
        if (!message.hasReqContext()) {
            throw new BizzException("必须传入ReqContext");
        }
        eventBus.post(message);
    }

}
