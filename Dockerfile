FROM openjdk:17-slim as BUILD

WORKDIR /app
COPY pom.xml /app/
COPY src /app/src/

RUN apt-get update && apt-get install -y maven

RUN mvn clean package -DskipTests

# Runtime stage with smaller JDK image
FROM openjdk:17-slim

COPY --from=BUILD /app/target/management-0.0.1-SNAPSHOT.jar application.jar
COPY src/main/resources /app/resources

ENTRYPOINT ["java", "-Djava.awt.headless=true", "-jar", "/application.jar"]