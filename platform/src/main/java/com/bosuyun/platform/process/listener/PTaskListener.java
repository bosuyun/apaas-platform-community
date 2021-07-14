package com.bosuyun.platform.process.listener;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

/**
 * Created by liuyuancheng on 2021/7/14  <br/>
 */
public class PTaskListener extends PListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {

    }
}
