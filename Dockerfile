FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY src/main/resources/crypto-moon-450715-c2-cf045efcb240.json /app/crypto-moon-450715-c2-cf045efcb240.json
ENV GOOGLE_APPLICATION_CREDENTIALS=/app/crypto-moon-450715-c2-cf045efcb240.json
COPY target/management-0.0.1-SNAPSHOT.jar /app/management-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/management-0.0.1-SNAPSHOT.jar"]