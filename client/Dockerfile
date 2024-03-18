FROM openjdk:17-oracle

WORKDIR /client-app

COPY /target/client-0.0.1-SNAPSHOT.jar client-app

ENV SOCKET_SERVER_HOST=localhost
ENV SOCKET_SERVER_PORT=8000

CMD ["java", "-jar", "client-app"]


