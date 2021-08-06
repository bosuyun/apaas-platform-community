package com.bosuyun.platform.data.eventbus;

import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

/**
 * 数据变化
 * <p>
 * Created by liuyuancheng on 2021/6/18  <br/>
 */
@Slf4j
public class DataOperationListener {

    @Subscribe
    public void doAction1(final String message) {
        log.warn("consumer {} received message :{}", message);
    }

}
