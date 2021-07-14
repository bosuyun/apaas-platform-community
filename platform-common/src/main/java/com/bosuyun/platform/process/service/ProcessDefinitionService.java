package com.bosuyun.platform.process.service;

import com.bosuyun.platform.process.msic.ProcessDefinition;
import org.camunda.bpm.engine.RepositoryService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class ProcessDefinitionService {

    @Inject
    RepositoryService repositoryService;

    public List<ProcessDefinition> getProcessDefinitions() {
        return repositoryService.createProcessDefinitionQuery().latestVersion().list().stream()
                .map(entry -> new ProcessDefinition(entry.getId(), entry.getName(), entry.getDescription()))
                .collect(Collectors.toList());
    }

}
