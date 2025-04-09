FROM openjdk:17-slim as BUILD
EXPOSE 8080
ADD target/management-0.0.1-SNAPSHOT.jar management-0.0.1-SNAPSHOT.jar 
ENTRYPOINT ["java", "-jar", "/management-0.0.1-SNAPSHOT.jar"]