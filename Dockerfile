# syntax=docker/dockerfile:1

FROM maven:3.8.4-jdk-11-slim AS base
WORKDIR /app
COPY event-service/pom.xml event-service/pom.xml
COPY parser-service/pom.xml parser-service/pom.xml
COPY telegram-bot/pom.xml telegram-bot/pom.xml
COPY pom.xml .
RUN mvn dependency:go-offline

COPY wait-for-it.sh ./
COPY entrypoint.sh ./

COPY event-service/src event-service/src
COPY parser-service/src parser-service/src
COPY telegram-bot/src telegram-bot/src


FROM base as dev_service
ENTRYPOINT ["./entrypoint.sh"]

FROM base as build
RUN mvn clean package

FROM openjdk:11-jre-slim as eventservice
EXPOSE 8081
COPY --from=build /app/event-service/target/event-service-*.jar /event-service.jar
CMD ["java", "-jar", "/event-service.jar"]