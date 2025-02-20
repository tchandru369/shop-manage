FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY src/main/resources/JasperFile/Invoice_Table_Based.jasper /app/JasperFile/Invoice_Table_Based.jasper
COPY src/main/resources/BillingReports /app/BillingReports
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]