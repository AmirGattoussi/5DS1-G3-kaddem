# Use an OpenJDK base image with a JDK
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file produced by the Maven build
COPY target/5ds1-g3-kaddem-1.0.0.jar /app/5ds1-g3-kaddem.jar

# Expose the port your Spring app runs on (default is 8080)
EXPOSE 8089

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/5ds1-g3-kaddem.jar"]

