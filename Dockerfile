FROM tomcat:9-jre8-alpine

COPY target/RestService.war /usr/local/tomcat/webapps/api.war

WORKDIR /usr/local/tomcat

EXPOSE 8080