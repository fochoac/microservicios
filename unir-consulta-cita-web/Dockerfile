FROM openjdk:8-jre-alpine

RUN mkdir /app

WORKDIR /app

ADD ./target/unir-consulta-cita-web-1.0.jar /app

EXPOSE 8084


CMD ["java", "-jar", "unir-consulta-cita-web-1.0.jar"]