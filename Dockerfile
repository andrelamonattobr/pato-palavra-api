# Use an official OpenJDK runtime as a parent image
FROM openjdk:24-jdk-slim as builder

# Set the working directory in the container
WORKDIR /app

# Copy the Maven wrapper and the project pom.xml
# These layers are cached separately to speed up subsequent builds if only code changes
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Download project dependencies
# This layer is also cached to speed up builds
RUN ./mvnw dependency:go-offline

# Copy the project source code
COPY src ./src

# Package the application
RUN ./mvnw package -DskipTests

# Use a smaller JRE image for the final application stage
FROM openjdk:24-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the packaged jar file from the builder stage
# Adjust the JAR file name if necessary (e.g., if your pom.xml specifies a different finalName)
COPY --from=builder /app/target/*.jar app.jar

# Expose the port the application runs on (default for Spring Boot is 8080)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
