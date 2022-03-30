# syntax=docker/dockerfile:1

FROM maven:3.8.4-jdk-11-slim AS base
WORKDIR /app
COPY event-service/pom.xml event-service/pom.xml
COPY parser-service/pom.xml parser-service/pom.xml
COPY telegram-bot/pom.xml telegram-bot/pom.xml
COPY registry-service/pom.xml registry-service/pom.xml

COPY pom.xml .
RUN mvn dependency:go-offline

COPY wait-for-it.sh ./
COPY entrypoint.sh ./

COPY event-service/src event-service/src
COPY parser-service/src parser-service/src
COPY telegram-bot/src telegram-bot/src
COPY registry-service/src registry-service/src


FROM base as dev_service
ENTRYPOINT ["./entrypoint.sh"]

FROM base as build
RUN mvn clean package

FROM openjdk:11-jre-slim as event-service
EXPOSE 8081
COPY --from=build /app/event-service/target/event-service-*.jar /event-service.jar
CMD ["java", "-jar", "/event-service.jar"]

FROM openjdk:11-jre-slim as parser-service
COPY --from=build /app/parser-service/target/parser-service-*.jar /parser-service.jar
CMD ["java", "-jar", "/parser-service.jar"]

FROM openjdk:11-jre-slim as registry-service
COPY --from=build /app/registry-service/target/registry-service-*.jar /registry-service.jar
CMD ["java", "-jar", "/registry-service.jar"]

FROM openjdk:11-jre-slim as telegram-bot
COPY --from=build /app/telegram-bot/target/TelegramNotifierBot*.jar /telegram-bot.jar
CMD ["java", "-jar", "/telegram-bot.jar"]