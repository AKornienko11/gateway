FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Было: FROM openjdk:17-jdk-slim
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=builder /app/target/gateway-api-0.0.1-SNAPSHOT.jar gateway-api.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "gateway-api.jar"]