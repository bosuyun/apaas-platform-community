package com.bosuyun.platform.process.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * Executing groovy script to process some business logical.
 * <p>
 * Created by liuyuancheng on 2021/7/13  <br/>
 */
public class PScriptTask extends PJavaTask implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("PScriptTask");
    }

}
