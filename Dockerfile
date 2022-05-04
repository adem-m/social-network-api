ARG MAVEN_TAG=3.6.3-openjdk-17-slim
ARG MAVEN_DIGEST=sha256:ead687c670f30898fc28e6c7b9dab652360d522b8912ba96eca0f08592a73eec

ARG OPENJDK_TAG=18.0.1-jdk
ARG OPENJDK_DIGEST=sha256:a7cd96ef84bfc74c2be885f525de1c31a81eb183f9c7035e9221ad5d42aeda57

FROM maven:${MAVEN_TAG}@${MAVEN_DIGEST} AS maven

WORKDIR /usr/src/app
COPY . .
RUN mvn clean package -DskipTests=true

FROM openjdk:${OPENJDK_TAG}@${OPENJDK_DIGEST} AS java

WORKDIR /usr/src/app
COPY --from=maven /usr/src/app/target/social-network-api-1.0-SNAPSHOT.jar .

ENTRYPOINT ["java","-jar","social-network-api-1.0-SNAPSHOT.jar"]