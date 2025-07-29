FROM openjdk:26-oraclelinux9
LABEL authors="valkyrie"
ADD target/Job-service.jar Job-service.jar
ENTRYPOINT ["java", "-jar", "/Job-service.jar"]