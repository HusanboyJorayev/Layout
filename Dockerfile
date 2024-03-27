#FROM openjdk:17
#VOLUME /my-app
#ADD target/Layout-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

FROM openjdk:17-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ADD target/Layout-0.0.1-SNAPSHOT.jar layoutapp.jar
# Run the JAR file
ENTRYPOINT ["java","-jar","/layoutapp.jar"]
