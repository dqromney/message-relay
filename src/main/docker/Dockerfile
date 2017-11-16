# Image: https://hub.docker.com/_/openjdk/
FROM openjdk:8-jre-alpine

MAINTAINER  David Q. Romney <dqromney@gmail.com>

# External configuration files directory
VOLUME /config

ADD ["@project.build.finalName@.jar", "@project.build.finalName@.jar"]

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/@project.build.finalName@.jar"]
