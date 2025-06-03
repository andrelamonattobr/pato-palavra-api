# === Build Stage ===
FROM maven:3.9.9-eclipse-temurin-24 AS build

WORKDIR /app

# Copy pom and source code
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# === Runtime Stage ===
FROM openjdk:24-jdk-slim

WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/pato-palavra-0.0.1-SNAPSHOT.jar app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]