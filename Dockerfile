FROM openjdk:17-jdk-slim
COPY target/receipt-processor-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]