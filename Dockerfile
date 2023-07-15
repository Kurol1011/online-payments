FROM openjdk:17-oracle
COPY target/onlinePay-0.0.1-SNAPSHOT.jar online-payments-app.jar
EXPOSE 8080
ENTRYPOINT [ "java","-jar","online-payments-app.jar" ]