FROM maven
COPY . .
RUN mvn install

FROM openjdk
COPY --from=0 /target/pokemon-app-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "pokemon-app-0.0.1-SNAPSHOT.jar"]