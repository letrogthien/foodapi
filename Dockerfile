FROM eclipse-temurin:21-jdk
 
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} foodapi.jar
ENTRYPOINT ["java","-jar","/foodapi.jar"] 