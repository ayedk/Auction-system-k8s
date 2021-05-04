#!/bin/sh
java -javaagent:./../lib/jmx_prometheus_javaagent-0.15.0.jar=8888:config.yaml -jar ./../lib/streamService-0.0.1.jar --spring.config.location=../config/application.properties
