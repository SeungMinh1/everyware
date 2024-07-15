FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ARG KEY_FILE=keystore.p12
COPY ${KEY_FILE} keystore.p12 
ENV TZ=Asia/Seoul
ENTRYPOINT ["java","-Dspring.profiles.active=${USE_PROFILE}", "-jar","/app.jar"]
