FROM openjdk:17-jdk

COPY target/task-manager.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "task-manager.jar"]