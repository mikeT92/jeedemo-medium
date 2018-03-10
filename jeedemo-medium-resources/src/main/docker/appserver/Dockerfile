# GlassFish 5.0 on Docker setup for JEEDEMO
FROM docker-glassfish5:latest

# Maintainer
LABEL MAINTAINER Michael Theis<mtheis@hm.edu>

# copy all required libraries to lib folder of domain
COPY *.jar ${GLASSFISH_PATH}/glassfish/domains/${GLASSFISH_DOMAIN}/lib/

# set credentials for Glassfish admin user in default domain 
RUN \
	echo 'AS_ADMIN_PASSWORD='${ADMIN_PASSWORD}'\nEOF\n' >> /opt/pwdfile && \
# start domain for configuration
	${GLASSFISH_PATH}/bin/asadmin -e start-domain ${GLASSFISH_DOMAIN} && \
# create database resources (note: mysql assumed to be running in the same network!)
	${GLASSFISH_PATH}/bin/asadmin -e --user ${ADMIN_USER} --passwordfile=/opt/pwdfile create-jdbc-connection-pool --datasourceclassname com.mysql.jdbc.jdbc2.optional.MysqlDataSource --restype javax.sql.DataSource --wrapjdbcobjects true --isolationlevel read-committed --property portNumber=3306:password=fwpss2018:user=jeedemo:serverName=mysql-jeedemo:databaseName=jeedemo_db JEEDEMO_POOL && \
	${GLASSFISH_PATH}/bin/asadmin -e --user ${ADMIN_USER} --passwordfile=/opt/pwdfile create-jdbc-resource --connectionpoolid JEEDEMO_POOL jdbc/JEEDEMO_DATASOURCE && \
# enable default principal to role mapping
	${GLASSFISH_PATH}/bin/asadmin -e --user ${ADMIN_USER} --passwordfile=/opt/pwdfile set configs.config.server-config.security-service.activate-default-principal-to-role-mapping=true && \
# create custom file realm 
	${GLASSFISH_PATH}/bin/asadmin -e --user ${ADMIN_USER} --passwordfile=/opt/pwdfile create-auth-realm --classname com.sun.enterprise.security.auth.realm.file.FileRealm --property file=\$\{com.sun.aas.instanceRoot\}/config/jeedemoKeyFile:jaas-context=fileRealm JEEDEMO_REALM && \
# create file realm user for component tests
	echo 'AS_ADMIN_PASSWORD='${ADMIN_PASSWORD}'\n''AS_ADMIN_USERPASSWORD=arquillian''\nEOF\n' > /tmp/fwparqln-password && \
	${GLASSFISH_PATH}/bin/asadmin -e --user ${ADMIN_USER} --passwordfile /tmp/fwparqln-password create-file-user --groups JEEDEMO_USER:JEEDEMO_ADMIN --authrealmname JEEDEMO_REALM fwparqln && \
	rm /tmp/fwparqln-password && \
# create file realm users for demos
	echo 'AS_ADMIN_PASSWORD='${ADMIN_PASSWORD}'\n''AS_ADMIN_USERPASSWORD=fwpss2018''\nEOF\n' > /tmp/fwpdemo-password  && \
	${GLASSFISH_PATH}/bin/asadmin -e --user ${ADMIN_USER} --passwordfile /tmp/fwpdemo-password create-file-user --groups JEEDEMO_USER:JEEDEMO_ADMIN --authrealmname JEEDEMO_REALM fwpdemo && \
	rm /tmp/fwpdemo-password  && \
# stop domain after configuration
	${GLASSFISH_PATH}/bin/asadmin -e stop-domain ${GLASSFISH_DOMAIN}

