FROM openjdk:8-jre-alpine

EXPOSE 8080

COPY ./target/spring-boot-zero-hero-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "spring-boot-zero-hero-0.0.1-SNAPSHOT.jar"]
