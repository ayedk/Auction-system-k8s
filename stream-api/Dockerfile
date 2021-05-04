FROM openjdk:11.0.10-oraclelinux7
WORKDIR /stream-api
RUN mkdir  lib config log bin
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ./lib/streamService-0.0.1.jar
COPY  ./jmx_prometheus_javaagent-0.15.0.jar ./lib/
WORKDIR /stream-api/bin
COPY  ./start.sh .
COPY  ./config.yaml .
ENTRYPOINT ["sh","start.sh"]