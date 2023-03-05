# syntax=docker/dockerfile:1

FROM eclipse-temurin:19 as base
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve
COPY src ./src

FROM base as development
CMD ["./mvnw", "spring-boot:run", "-Dspring-boot.run.jvmArguments='-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000'"]

FROM base as build
RUN ./mvnw package

FROM eclipse-temurin:19 as production
EXPOSE 8080
COPY --from=build /app/target/pokerplanner-*.jar /pokerplanner.jar
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/pokerplanner.jar"]