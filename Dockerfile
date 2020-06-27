FROM openjdk:10.0.2-13-jdk-slim

EXPOSE 8080

RUN mkdir -p /opt/application/jar
WORKDIR /opt/application/jar
COPY build/libs/hotel-service-0.0.1-SNAPSHOT.jar /opt/application/jar/hotel-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT [ "java", \
    "-jar", \
    "/opt/application/jar/hotel-service-0.0.1-SNAPSHOT.jar" ]