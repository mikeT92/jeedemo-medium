/*
 * jeedemo-medium-business-model:Task.java
 * Copyright (c) Michael Theis 2017
 */
package edu.hm.cs.fwp.jeedemo.business.tasks.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * {@code Entity} acting as a representation of a simple task.
 * 
 * @author mikeT92
 * @version 1.0
 * @since 03.03.2018
 */
@Entity
@Table(name = "T_TASK")
public class Task {

	@Id
	@Column(name = "TASK_ID")
	private UUID id;

	@NotNull
	@Size(max = 80)
	@Column(name = "SUBJECT")
	private String subject;

	@NotNull
	@Size(max = 1024)
	@Column(name = "DESCRIPTION")
	private String description;

	@NotNull
	@Column(name = "LIFECYCLE_STATE")
	@Enumerated(EnumType.ORDINAL)
	private LifeCycleState lifeCycleState;

	@Column(name = "SUBMITTED_AT")
	private LocalDateTime submittedAt;

	@Size(max = 12)
	@Column(name = "SUBMITTED_BY")
	private String submittedBy;

	@Column(name = "DUE_DATE")
	private LocalDateTime dueDate;

	@Column(name = "COMPLETED_AT")
	private LocalDateTime completedAt;

	@Size(max = 12)
	@Column(name = "COMPLETED_BY")
	private String completedBy;

	@Version
	@Column(name = "VERSION")
	private long version;

	public Task() {
		this.id = UUID.randomUUID();
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LifeCycleState getLifeCycleState() {
		return lifeCycleState;
	}

	public void setLifeCycleState(LifeCycleState lifeCycleState) {
		this.lifeCycleState = lifeCycleState;
	}

	public LocalDateTime getSubmittedAt() {
		return submittedAt;
	}

	public void setSubmittedAt(LocalDateTime submittedAt) {
		this.submittedAt = submittedAt;
	}

	public String getSubmittedBy() {
		return submittedBy;
	}

	public void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDateTime getCompletedAt() {
		return completedAt;
	}

	public void setCompletedAt(LocalDateTime completedAt) {
		this.completedAt = completedAt;
	}

	public String getCompletedBy() {
		return completedBy;
	}

	public void setCompletedBy(String completedBy) {
		this.completedBy = completedBy;
	}

	public UUID getId() {
		return id;
	}

	public long getVersion() {
		return version;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Task other = (Task) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%s { id : %s }", getClass().getDeclaredFields(), this.id);
	}
}
