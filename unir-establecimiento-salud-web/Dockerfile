FROM openjdk:8-jre-alpine

RUN mkdir /app

WORKDIR /app

ADD ./target/unir-establecimiento-salud-web-1.0.jar /app

EXPOSE 8383


CMD ["java", "-jar", "unir-establecimiento-salud-web-1.0.jar"]