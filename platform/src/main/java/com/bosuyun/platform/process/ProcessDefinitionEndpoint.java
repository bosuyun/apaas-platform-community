package com.bosuyun.platform.process;


import com.bosuyun.platform.process.msic.ProcessDefinition;
import com.bosuyun.platform.process.service.ProcessDefinitionService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("process")
@RequestScoped
public class ProcessDefinitionEndpoint {
    
    @Inject
    ProcessDefinitionService processDefinitionService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProcessDefinition> getAllProcessDefinitions() {
        return processDefinitionService.getProcessDefinitions();
    }

}
