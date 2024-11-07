# Use the official Gradle image with JDK 23 as the base image for building
FROM gradle:jdk21 AS builder

# Set the working directory
WORKDIR /app

# Copy only necessary files for dependency resolution first
COPY gradlew .
COPY gradle/ gradle/
COPY build.gradle.kts .
COPY settings.gradle.kts .

# Copy the rest of the application code
COPY /src/ src/

# Give execute permissions to gradlew
RUN chmod +x gradlew

# Build the application, skipping tests for faster builds
RUN ./gradlew clean build -x test

# Use a smaller base image for running the application
FROM eclipse-temurin:21.0.5_11-jdk-noble

# Set the working directory in the new image
WORKDIR /app

# Copy the built jar from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the port that your application runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]