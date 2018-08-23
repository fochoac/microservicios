FROM openjdk:8-jre-alpine

RUN mkdir /app

WORKDIR /app

ADD ./target/unir-servicio-seguridad-web-1.0.jar /app

EXPOSE 8081


CMD ["java", "-jar", "unir-servicio-seguridad-web-1.0.jar"]