package com.bosuyun.platform.process.service;

import io.quarkus.runtime.StartupEvent;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.*;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import java.io.Serializable;


/**
 * Camunda Service API
 *
 * https://docs.camunda.org/manual/7.15/user-guide/process-engine/process-engine-api/#services-api
 * https://github.com/camunda/camunda-engine-unittest
 */
@ApplicationScoped
@Slf4j
public class ProcessEngineService implements Serializable {

    private static final long serialVersionUID = -4428518317731241682L;

    private ProcessEngine engine;

    @ConfigProperty(name = "camunda.engine.name")
    String processEngineName;

    @ConfigProperty(name = "camunda.db.url")
    String camundaDbUrl;

    @ConfigProperty(name = "camunda.db.user")
    String camundaDbUser;

    @ConfigProperty(name = "camunda.db.pass")
    String camundaDbPass;

    @ConfigProperty(name = "camunda.db.driver")
    String camundaDbDriver;

    @PostConstruct
    void init(@Observes StartupEvent event) {
        engine = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration()
                .setProcessEngineName(processEngineName)
                .setJdbcDriver(camundaDbDriver)
                .setJdbcUrl(camundaDbUrl)
                .setJdbcUsername(camundaDbUser)
                .setJdbcPassword(camundaDbPass)
                .setTenantCheckEnabled(true)
                .setDatabaseSchemaUpdate("true")
                .buildProcessEngine();
    }

    public String getName() {
        return engine.getName();
    }

    @Produces
    RepositoryService getRepositoryService() {
        return engine.getRepositoryService();
    }

    @Produces
    RuntimeService getRuntimeService() {
        return engine.getRuntimeService();
    }

    @Produces
    DecisionService getDecisionService(){
        return engine.getDecisionService();
    }

}