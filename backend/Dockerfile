FROM maven:3.8.8-eclipse-temurin-17-alpine AS build

WORKDIR /app 

COPY pom.xml .
RUN mvn dependency:go-offline 

COPY src src     
RUN mvn clean install


FROM openjdk:17-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar candlelight.jar

EXPOSE 8082

ENTRYPOINT [ "java", "-jar", "/app/candlelight.jar" ]