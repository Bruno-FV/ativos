# Multi-stage build for a compact runtime image
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /api

COPY mvnw pom.xml ./
COPY .mvn .mvn
COPY src src
RUN sed -i 's/\r$//' mvnw
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:17-jre-jammy
WORKDIR /api
COPY --from=build /api/target/*.jar api.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","api.jar"]