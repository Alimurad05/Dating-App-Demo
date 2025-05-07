FROM maven:3.8-openjdk-17 AS build
WORKDIR /app

# Copy the pom.xml file to download dependencies
COPY pom.xml .
# Download dependencies (separate step for caching)
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Create the final image
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the built jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]