/*
 * jeedemo-medium-integration:TaskRepositoryBean.java
 * Copyright (c) Michael Theis 2017
 */
package edu.hm.cs.fwp.jeedemo.integration.tasks.persistence;

import java.util.UUID;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.hm.cs.fwp.jeedemo.business.tasks.entity.Task;

/**
 * {@code Repository} that handles persistence of {@code Task} entities.
 * 
 * @author mikeT92
 * @version 1.0
 * @since 03.03.2018
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class TaskRepositoryBean {

	@PersistenceContext
	private EntityManager entityManager;

	public void addTask(Task newTask) {
		this.entityManager.persist(newTask);
	}

	public void setTask(Task modifiedTask) {
		this.entityManager.merge(modifiedTask);
	}

	public void removeTask(Task task) {
		this.entityManager.remove(task);
	}

	public Task getTaskById(UUID taskId) {
		return this.entityManager.find(Task.class, taskId);
	}
}
