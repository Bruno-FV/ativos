# Multi-stage build for a compact runtime image
FROM eclipse-temurin:17-jdk-jammy as build
WORKDIR /workspace
RUN apt-get update && apt-get install -y \
    curl \
    tar \
    gzip \
    && rm -rf /var/lib/apt/lists/*
COPY mvnw pom.xml ./
COPY .mvn .mvn
COPY src src
RUN sed -i 's/\r$//' mvnw
RUN chmod +x mvnw
RUN ./mvnw -B -DskipTests package

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]