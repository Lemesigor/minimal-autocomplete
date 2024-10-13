FROM maven:3.9.9-eclipse-temurin-21

ADD . /usr/src/backend
WORKDIR /usr/src/backend

EXPOSE 4567
ENTRYPOINT ["mvn", "clean", "verify", "exec:java"]