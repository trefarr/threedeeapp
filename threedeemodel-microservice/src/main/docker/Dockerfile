FROM java:8
VOLUME /tmp
ADD threedeemodel-microservice-1.0.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 9000
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=6005", "-jar", "/app.jar"]
