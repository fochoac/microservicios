FROM openjdk:8-jre-alpine

RUN mkdir /app

WORKDIR /app

ADD ./target/unir-agendamiento-cita-web-1.0.jar /app

EXPOSE 8083


CMD ["java", "-jar", "unir-agendamiento-cita-web-1.0.jar"]