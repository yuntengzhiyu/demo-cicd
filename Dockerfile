# 第一阶段：构建
FROM maven:3.9-openjdk-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean test package

# 第二阶段：运行
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/demo-cicd-1.0.0.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar", "--server.port=8081"]
