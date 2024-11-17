# Use OpenJDK as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
ARG JAR_FILE=build/libs/user-managment-service.jar
COPY ${JAR_FILE} app.jar

# Expose the port that the app will run on
EXPOSE 8888

# Command to run the app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]