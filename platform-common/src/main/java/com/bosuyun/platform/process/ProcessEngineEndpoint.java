package com.bosuyun.platform.process;

import com.bosuyun.platform.process.service.ProcessEngineService;
import com.bosuyun.platform.process.msic.ProcessEngineState;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.io.ByteArrayInputStream;

@Path("engine")
@RequestScoped
@Slf4j
public class ProcessEngineEndpoint {

    @Inject
    ProcessEngineService processEngineService;

    @Inject
    RepositoryService repositoryService;

    @Inject
    RuntimeService runtimeService;

    @GET
    @Path("info")
    @Produces(MediaType.APPLICATION_JSON)
    public ProcessEngineState getEngineInfo() {
        ProcessEngineState state = new ProcessEngineState();
        state.setEngineName(processEngineService.getName());
        state.setState(new ProcessEngineState.State(repositoryService.createProcessDefinitionQuery().count(),
                runtimeService.createExecutionQuery().count()));
        return state;
    }

    @POST
    @Path("deployment")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public Response createDeployment(@HeaderParam("Deployment-Name") String name,
            @HeaderParam("Resource-Name") String resourceName, byte[] content) {
        log.info("Content: " + content);
        log.info("Name: " + name);
        try {
            repositoryService.createDeployment().name(name)
                .addInputStream(resourceName, new ByteArrayInputStream(content)).deploy();
        } catch (Exception ex) {
            return Response.serverError().build();
        }
        return Response.status(Status.CREATED).build();
    }

    @PUT
    @Path("deployment/{id}")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public Response updateDeployment(@PathParam("id") String deploymentId, @HeaderParam("Deployment-Name") String name,
    @HeaderParam("Resource-Name") String resourceName, byte[] content) {
        return Response.ok().build();
    }

}