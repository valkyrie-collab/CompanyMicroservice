FROM openjdk:26-oraclelinux9
LABEL authors="valkyrie"
ADD target/Authentication-service.jar Authentication-service.jar
ENTRYPOINT ["java", "-jar", "/Authentication-service.jar"]