FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY target/CRUDAlumnos-1.0.jar app.jar
EXPOSE 8002
ENTRYPOINT [ "java", "-jar", "app.jar" ]