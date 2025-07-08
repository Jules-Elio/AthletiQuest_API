FROM amazoncorretto:24-alpine-jdk

RUN addgroup -S spring && adduser -S spring -G spring

USER spring:spring

LABEL authors="Sclus"

VOLUME /tmp

COPY ./target/*.jar athletiquest-api.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -jar athletiquest-api.jar"]
