FROM openjdk:17-jdk

COPY ./build/libs/*SNAPSHOT.jar server.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "/server.jar" ]