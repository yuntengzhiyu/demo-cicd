# 第一阶段：构建
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean test package

# 第二阶段：运行
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=builder /app/target/demo-cicd-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar", "--server.port=8081"]
