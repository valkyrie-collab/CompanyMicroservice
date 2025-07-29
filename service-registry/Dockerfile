FROM openjdk:26-oraclelinux9
LABEL authors="valkyrie"
ADD target/Service-registry.jar Service-registry.jar
ENTRYPOINT ["java", "-jar", "/Service-registry.jar"]