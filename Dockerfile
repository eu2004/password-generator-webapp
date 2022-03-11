#
# Build stage
#
# FROM maven:3.8.4-jdk-11-slim AS build
# WORKDIR /apps/passwordgenweb
# COPY src ./src
# COPY pom.xml .
# ... COPY C:/Users/emilian.utma/.m2/repository/ro/eu/passwallet/passwallet-service/1.3 $USER_HOME_DIR/.m2/repository/ro/eu/passwallet/passwallet-service/1.3
# RUN mvn -f pom.xml clean install -X

#
# Package stage
# 
FROM openjdk:11.0.4-jre-slim
# ...  COPY --from=build /apps/passwordgenweb/target/password-generator-webapp-1.1-SNAPSHOT.jar /usr/local/lib/password-generator-webapp-1.1.jar
COPY ./target/password-generator-webapp-1.1-SNAPSHOT.jar /usr/local/lib/password-generator-webapp-1.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/password-generator-webapp-1.1.jar"]