FROM openjdk:17-slim as BUILD

WORKDIR /app
COPY pom.xml /app/
COPY src /app/src/

RUN apt-get update && apt-get install -y maven  # Install Maven manually
RUN mvn clean package -DskipTests

# Use OpenJDK 17 for the final image as well
FROM openjdk:17-slim

WORKDIR /app
COPY --from=BUILD /app/target/*.jar application.jar
ENTRYPOINT ["java", "-jar", "/application.jar"]