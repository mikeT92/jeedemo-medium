# Autodeploy application on GlassFish 5.0
FROM jeedemo-medium-docker-glassfish5:latest

# Maintainer
LABEL MAINTAINER Michael Theis<mtheis@hm.edu>

COPY ./*.war /${GLASSFISH_HOME}/glassfish/domains/domain1/autodeploy
