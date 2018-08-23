FROM openjdk:8-jre-alpine

RUN mkdir /app

WORKDIR /app

ADD ./target/unir-gestion-agendas-api-gateway-1.0.jar /app

EXPOSE 8082


CMD ["java", "-jar", "unir-gestion-agendas-api-gateway-1.0.jar"]