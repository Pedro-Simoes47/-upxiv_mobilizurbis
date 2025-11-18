# Etapa 1: Construção (Build)
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Execução (Run)
# MUDANÇA AQUI: Usando Eclipse Temurin (mais leve e atual)
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
# Copia o arquivo .jar gerado na etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta 8080
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]