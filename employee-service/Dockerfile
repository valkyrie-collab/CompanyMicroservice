FROM openjdk:26-oraclelinux9
LABEL authors="valkyrie"
ADD target/Employee-service.jar Employee-service.jar
ENTRYPOINT ["java", "-jar", "/Employee-service.jar"]