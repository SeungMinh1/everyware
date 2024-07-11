FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

COPY everyware/keystore.p12
ENV TZ=Asia/Seoul
ENTRYPOINT ["java","-jar","/app.jar"]
