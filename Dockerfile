# Use a base image with minimal OS (Alpine Linux) and OpenJDK 17
FROM adoptopenjdk:17-jdk-hotspot as builder

# Set the working directory inside the container
WORKDIR /app

# Copy only the necessary files needed for dependency resolution
COPY pom.xml /app/
COPY src /app/src

# Build the application
RUN mvn clean install -DskipTests

# Use a new image with only the JRE and the application artifacts
FROM adoptopenjdk:17-jre-hotspot

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/target/minsait.jar /app/app.jar

# Expose the port your application will run on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
