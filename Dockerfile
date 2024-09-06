FROM openjdk:17-jdk

COPY ./build/libs/*SNAPSHOT.jar server.jar

ENTRYPOINT [ "java", "-jar", "/server.jar" ]