FROM openjdk:26-oraclelinux9
LABEL authors="valkyrie"
ADD target/Api-Gateway.jar Api-Gateway.jar
ENTRYPOINT ["java", "-jar", "/Api-Gateway.jar"]