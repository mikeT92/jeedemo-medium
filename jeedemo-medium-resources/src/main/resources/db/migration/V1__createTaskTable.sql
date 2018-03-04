/* 
 * V1__createTaskTable.sql
 * Creates all database artifacts required by the tasks module of the JEEDEMO application
 */
/*
 * Table containing all tasks managed by the JEEDEMO application
 */
CREATE TABLE T_TASK (
	TASK_ID VARCHAR(32) PRIMARY KEY NOT NULL,
    SUBJECT VARCHAR(80) NOT NULL ,
    DESCRIPTION VARCHAR(1024) NOT NULL ,
    LIFECYCLE_STATE INT NOT NULL ,
    SUBMITTED_AT TIMESTAMP NULL, 
    SUBMITTED_BY VARCHAR(16),
    DUE_DATE TIMESTAMP NULL,
    COMPLETED_AT TIMESTAMP NULL,
    COMPLETED_BY VARCHAR(16) ,
    VERSION INT NOT NULL
);

CREATE UNIQUE INDEX X_TASK
	ON T_TASK
   (TASK_ID ASC);
