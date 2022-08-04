FROM openjdk:11-jdk-bullseye
VOLUME /tmp

EXPOSE 8080

COPY ./target/bhaskara-back-0.1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]