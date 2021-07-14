package com.bosuyun.platform.process.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

/**
 * Created by liuyuancheng on 2021/7/14  <br/>
 */
public class PExecutionListener extends PListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) {
        System.out.println(execution.getVariables());
    }
}
