package com.bosuyun.platform.process.task;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * Created by liuyuancheng on 2021/7/13  <br/>
 */
@Slf4j
public class PServiceTask extends PJavaTask implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        log.error("sdf");
        System.out.println("PServiceTask");
    }

}
