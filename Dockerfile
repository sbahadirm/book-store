FROM postgres
ENV POSTGRES_PASSWORD 1231231234
ENV POSTGRES_DB postgres

FROM openjdk:11
ARG JAR_FILE=target/book-store-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} application.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "application.jar"]