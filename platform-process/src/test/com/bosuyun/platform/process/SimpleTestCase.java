package com.bosuyun.platform.process;

import io.quarkus.test.junit.QuarkusTest;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

/**
 * @author Daniel Meyer
 * @author Martin Schimak
 */
@QuarkusTest
@Slf4j
public class SimpleTestCase {

    @Rule
    public ProcessEngineRule rule = new ProcessEngineRule();

    @Test
    @Deployment(resources = {"testProcess.bpmn"})
    public void shouldExecuteProcess() {
        // Given we create a new process instance
        ProcessInstance processInstance = BpmnAwareTests.runtimeService().startProcessInstanceByKey("testProcess");
        // Then it should be active
        BpmnAwareTests.assertThat(processInstance).isActive();
        // And it should be the only instance
        Assertions.assertEquals(BpmnAwareTests.processInstanceQuery().count(), 1);
        // And there should exist just a single task within that process instance
        Assertions.assertNotNull(BpmnAwareTests.assertThat(BpmnAwareTests.task(processInstance)));
        // When we complete that task
        BpmnAwareTests.complete(BpmnAwareTests.task(processInstance));
        // Then the process instance should be ended
        BpmnAwareTests.assertThat(processInstance).isEnded();
        System.out.println(processInstance.getProcessInstanceId());
    }

    @Test
    @Deployment(resources = {"stdDiagram.bpmn"})
    public void shouldExecuteProcessCondition() {
        // Given we create a new process instance
        ProcessInstance processInstance = BpmnAwareTests.runtimeService().startProcessInstanceByKey("stdDiagram");
        BpmnAwareTests.assertThat(processInstance).isEnded();
    }

}
