package com.bosuyun.platform.process.task;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * Business rule task means some java logical or DMN decision table.
 * <p>
 * Created by liuyuancheng on 2021/7/13  <br/>
 */
@Slf4j
public class PBusinessRuleTask extends PJavaTask implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        log.debug("PBusinessRuleTask");
    }

}
