# syntax=docker/dockerfile:1
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /workspace

COPY core-plataform ./core-plataform
RUN --mount=type=cache,target=/root/.m2 \
    cd core-plataform && mvn -q -DskipTests -Dspotless.check.skip=true install

COPY people-school-service ./people-school-service
WORKDIR /workspace/people-school-service
RUN --mount=type=cache,target=/root/.m2 \
    mvn -q -DskipTests -Dspotless.check.skip=true package

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /workspace/people-school-service/target/people-school-service-*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
