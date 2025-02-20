FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/management-0.0.1-SNAPSHOT.jar /app/management-0.0.1-SNAPSHOT.jar
COPY src/main/resources/JasperFile/Invoice_Table_Based.jasper /app/JasperFile/Invoice_Table_Based.jasper
COPY src/main/resources/BillingReports /app/BillingReports
EXPOSE 8080
ENTRYPOINT ["java","-jar","/management-0.0.1-SNAPSHOT.jar"]