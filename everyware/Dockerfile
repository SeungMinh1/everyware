FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

COPY everyware/keystore.p12 app.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java","-jar","/app.jar"]
