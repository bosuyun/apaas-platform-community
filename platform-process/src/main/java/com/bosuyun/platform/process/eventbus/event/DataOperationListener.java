package com.bosuyun.platform.process.eventbus.event;

import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

/**
 * 数据变化
 * <p>
 * Created by liuyuancheng on 2021/6/18  <br/>
 */
@Slf4j
public class DataOperationListener implements IEvent {

    @Subscribe
    public void doAction1(final String message) {
        log.warn("consumer {} received message :{}", IEvent.DATA_OP_EVENT, message);
    }

}
