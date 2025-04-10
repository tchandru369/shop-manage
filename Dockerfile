FROM openjdk:17-slim as BUILD

WORKDIR /app
COPY src ./src

RUN mvn clean package -DskipTests

# Runtime stage with smaller JDK image
FROM openjdk:17-slim

COPY /app/target/management-0.0.1-SNAPSHOT.jar application.jar

ENTRYPOINT ["java", "-jar", "/application.jar"]