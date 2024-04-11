FROM maven:3.9.6-eclipse-temurin-21-alpine AS build

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:21-slim
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar demo.jar

EXPOSE 8080
CMD [ "./mvnw","spring-boot:run" ]