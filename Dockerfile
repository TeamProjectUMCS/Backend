FROM eclipse-temurin:23-jdk-alpine AS build

WORKDIR /app

# Copy Maven configuration files
COPY pom.xml .
COPY mvnw .

# Download dependencies (this step is cached if pom.xml doesn't change)
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY . .

# Build the application
RUN ./mvnw package -DskipTests

# Runtime stage
FROM eclipse-temurin:23-jre-alpine

WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Start the application
CMD ["java", "-jar", "app.jar"]