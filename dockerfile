#FROM openjdk:8
#EXPOSE 8080
#ARG JAR_FILE=/target/movieticketsystem-0.0.1-SNAPSHOT.jar
#WORKDIR /SpringBoot-Applications
#COPY ${JAR_FILE} Online-MovieThek.jar
#ENTRYPOINT ["java","-jar","Online-MovieThek.jar"]