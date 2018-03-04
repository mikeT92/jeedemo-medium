/*
 * jeedemo-medium-business:TaskProcessorBean.java
 * Copyright (c) Michael Theis 2017
 */
package edu.hm.cs.fwp.jeedemo.business.tasks.boundary;

import java.util.UUID;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import edu.hm.cs.fwp.jeedemo.business.tasks.entity.Task;
import edu.hm.cs.fwp.jeedemo.integration.tasks.persistence.TaskRepositoryBean;

/**
 * {@code Boundary} exposing all business methods to process a {@code Task}
 * according to its predefined workflow.
 * 
 * @author mikeT92
 * @version 1.0
 * @since 03.03.2018
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@RolesAllowed("JEEDEMO_USER")
public class TaskProcessorBean {

	@Inject
	TaskRepositoryBean taskRepository;

	public Task retrieveTask(UUID taskId) {
		return taskRepository.getTaskById(taskId);
	}

	public void submitTask(Task task) {
		this.taskRepository.addTask(task);
	}
}
