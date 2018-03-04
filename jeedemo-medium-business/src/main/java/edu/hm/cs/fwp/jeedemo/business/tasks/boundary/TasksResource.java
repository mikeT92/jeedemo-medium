/*
 * jeedemo-medium-business:TasksResource.java
 * Copyright (c) Michael Theis 2017
 */
package edu.hm.cs.fwp.jeedemo.business.tasks.boundary;

import java.net.URI;
import java.util.UUID;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import edu.hm.cs.fwp.jeedemo.business.tasks.entity.Task;

/**
 * {@code REST endpoint} for task processing.
 * 
 * @author mikeT92
 * @version 1.0
 * @since 04.03.2018
 */
@Named
@RequestScoped
@Path("tasks")
public class TasksResource {

	@Inject
	TaskProcessorBean taskProcessor;

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response submitTask(Task newTask) {
		this.taskProcessor.submitTask(newTask);
		URI location = UriBuilder.fromResource(getClass()).build(newTask.getId());
		return Response.created(location).build();
	}

	@GET
	@Path("{taskId}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getTaskById(@PathParam("taskId") UUID taskId) {
		Task found = this.taskProcessor.retrieveTask(taskId);
		Response result = null;
		if (found != null) {
			result = Response.ok(found).build();
		} else {
			result = Response.status(404).build();
		}
		return result;
	}

}
