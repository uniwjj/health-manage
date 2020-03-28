#!/bin/bash

java_opts="-server \
-Xms$jvm_xms \
-Xmx$jvm_xmx \
-Dspring.profiles.active=$spring_profile \
-verbose:gc \
-XX:+PrintGCDetails \
-XX:+PrintGCDateStamps \
-XX:+PrintGCTimeStamps \
-XX:+HeapDumpOnOutOfMemoryError \
-XX:HeapDumpPath=logs \
-Xloggc:logs/gc.log \
-XX:+UseGCLogFileRotation \
-XX:NumberOfGCLogFiles=5 \
-XX:GCLogFileSize=100M \
-XX:+UseConcMarkSweepGC \
-XX:+CMSParallelRemarkEnabled \
-XX:+UseCMSCompactAtFullCollection \
-XX:+UseCMSInitiatingOccupancyOnly \
-XX:CMSInitiatingOccupancyFraction=70"

java_opts="$java_opts \
  -Dspring.datasource.jdbc-url=$jdbc_url \
  -Dspring.datasource.username=$db_username \
  -Dspring.datasource.password=$db_password"

java $java_opts -jar app.jar
