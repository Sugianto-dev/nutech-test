FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY . .
RUN chmod +x ./mvnw
RUN ./mvnw clean package
CMD ["java", "-jar", "target/nutech-0.0.1-SNAPSHOT.jar"]
