FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ./target/ifi-waste-management-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]