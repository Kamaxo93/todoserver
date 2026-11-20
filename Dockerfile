# ── Etapa 1: BUILD (JDK completo, para compilar) ──
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .
RUN ./gradlew buildFatJar --no-daemon

# ── Etapa 2: RUNTIME (solo JRE, imagen final pequeña) ──
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/build/libs/todoserver-all.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]