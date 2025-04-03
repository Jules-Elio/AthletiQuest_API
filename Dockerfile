FROM amazoncorretto:23-alpine-jdk

LABEL authors="Sclus"

VOLUME /tmp

COPY ./target/athletiquest-api-0.0.1-SNAPSHOT.jar athletiquest-api.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -jar athletiquest-api.jar"]