#!/bin/bash
mvn clean package
mvn sonar:sonar \
  -Dsonar.host.url=http://173.249.51.238:9100 \
  -Dsonar.login=a888f60941d392090b31c1b8d8559d17cb86000c
docker build -t "heavyvinicio/unir-servicio-general-web" .
docker service create \
    --name unir-servicio-general-web \
    --publish 8081:8081 \
    --replicas=1 \
    --constraint 'node.role == manager' \
      heavyvinicio/unir-servicio-general-web

#docker-compose up
#docker run -it -p 8081:8081 heavyvinicio/unir-servicio-general-web
#PUSH
#export DOCKER_ID_USER="heavyvinicio"
#docker login
#CLAVE =YECO2010
#docker tag heavyvinicio/unir-servicio-general-web heavyvinicio/unir-servicio-general-web
#docker push heavyvinicio/unir-servicio-general-web

