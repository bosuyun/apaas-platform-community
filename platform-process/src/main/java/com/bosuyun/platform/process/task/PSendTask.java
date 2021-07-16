package com.bosuyun.platform.process.task;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * Call java to process a task of sending some message.
 *
 * Created by liuyuancheng on 2021/7/13  <br/>
 */
@Slf4j
public class PSendTask extends PJavaTask implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("PSendTask");
    }

}
