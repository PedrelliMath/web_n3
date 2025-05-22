FROM eclipse-temurin:24-alpine

RUN mkdir -p /opt/app

COPY target/api-0.0.1-SNAPSHOT.jar /opt/app/japp.jar

CMD ["java", "-jar", "/opt/app/japp.jar"]
