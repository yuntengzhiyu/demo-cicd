FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY target/demo-cicd-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar", "--server.port=8081"]
