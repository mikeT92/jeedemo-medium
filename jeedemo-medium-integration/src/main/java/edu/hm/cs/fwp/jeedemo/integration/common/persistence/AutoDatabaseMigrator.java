/*
 * jeedemo-medium-integration:AutoDatabaseMigrator.java
 * Copyright (c) Michael Theis 2018
 */
package edu.hm.cs.fwp.jeedemo.integration.common.persistence;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.sql.DataSource;

import org.flywaydb.core.Flyway;

/**
 * {@code Startup} bean performing a Flyway database migration on the
 * applications database schema.
 * 
 * @author mikeT92
 * @version 1.0
 * @since 16.03.2018
 */
@Singleton
@Startup
public class AutoDatabaseMigrator {

	private static final Logger logger = Logger.getLogger(AutoDatabaseMigrator.class.getName());

	@Resource(lookup = "jdbc/JEEDEMO_DATASOURCE")
	DataSource dataSource;

	/**
	 * Migrates the current database schema of the application
	 */
	@PostConstruct
	public void migrateDatabase() {
		logger.info("Performing database migration of database schema...");
		Flyway flyway = new Flyway();
		flyway.setDataSource(this.dataSource);
		flyway.migrate();
		logger.info("... database schema migrated successfully");
	}
}
