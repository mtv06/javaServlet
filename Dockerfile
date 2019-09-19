FROM tomcat:9-jre11
EXPOSE 8080
RUN rm -fr /usr/local/tomcat/webapps/ROOT
COPY ./target/RestService.war /usr/local/tomcat/webapps/ROOT.war
CMD ["catalina.sh", "run"]