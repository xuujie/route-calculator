FROM openjdk:8-jdk

ENV DEMO_DIR /demo

RUN mkdir -p $DEMO_DIR

COPY build/libs/docker-0.0.1-SNAPSHOT.jar $DEMO_DIR

EXPOSE 8080

CMD ["java", "-jar", "/demo/docker-0.0.1-SNAPSHOT.jar"]

