# Etapa de build
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa de runtime
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

#docker build -t hsr-backend:latest . -> Comando para buildar a imagem
#docker run --rm -p 8080:8080 hsr-backend:latest-> Comando para rodar a imagem
#docker run -d -p 8080:8080 --name ativos-container ativos-main-api -> Comando para rodar a imagem em segundo plano com nome do container