FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} accounts.jar
ENTRYPOINT ["java", "-jar" , "-Dserver.port=8081" , "/accounts.jar"]