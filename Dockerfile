FROM openjdk:17-slim as BUILD

WORKDIR /app
COPY pom.xml /app/
COPY src /app/src/

RUN apt-get update && apt-get install -y maven  # Install Maven manually
RUN mvn clean install -DskipTests

# Use OpenJDK 17 for the final image as well
FROM openjdk:17-slim

WORKDIR /app
COPY /app/target/management-0.0.1-SNAPSHOT.jar application.jar
RUN ls -al /application.jar  # Ensure the file is copied correctly
#RUN chmod +x /application.jar   Ensure the file is executable
ENTRYPOINT ["java", "-jar", "/application.jar"]