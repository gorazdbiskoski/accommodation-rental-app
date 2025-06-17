FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY ./target/accommodation_rental_backend-0.0.1-SNAPSHOT.jar accommodations-app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/accommodations-app.jar"]