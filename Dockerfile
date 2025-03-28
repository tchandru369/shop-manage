FROM openjdk:17-slim as BUILD

WORKDIR /app
COPY pom.xml /app/
COPY src /app/src/

RUN apt-get update && apt-get install -y maven  # Install Maven manually
RUN mvn clean package -DskipTests && ls -al /app/target 

# Use OpenJDK 17 for the final image as well
FROM openjdk:17-slim

COPY --from=BUILD /app/target/management-0.0.1-SNAPSHOT.jar application.jar
ENTRYPOINT ["java", "-jar", "/application.jar"]