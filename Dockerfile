FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/management-0.0.1-SNAPSHOT.jar /app/management-0.0.1-SNAPSHOT.jar
COPY src/main/resources/JasperFile /app/JasperFile
RUN mkdir -p /app/BillingReports
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/management-0.0.1-SNAPSHOT.jar"]