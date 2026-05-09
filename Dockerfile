FROM maven:3.8.3-openjdk-17
WORKDIR /flash-memorizer

EXPOSE 8080

ENTRYPOINT ["mvn", "spring-boot:run", "-Dspring-boot.run.fork=false"]
