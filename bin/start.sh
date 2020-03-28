#!/bin/bash

if [ -z "$1" ]; then
    echo "ERROR: param not exits! Example: bin/start.sh 1.0.0"
    exit 1
fi

cd `dirname $0`
cd ..
ROOT_DIR=`pwd`
# jar格式：health-manage-1.0.0.jar
JAR=health-manage-$1.jar
LOGS_DIR=$ROOT_DIR/logs

cd $ROOT_DIR

# 检查jar包是否存在
if [ ! -f $ROOT_DIR/$JAR ]; then
    echo "ERROR: $ROOT_DIR/$JAR not exits!"
    exit 1
fi

# 检查进程是否启动
PIDS=`ps --no-heading -C java -f --width 1000 | grep "$JAR" | awk '{print $2}'`
if [ -n "$PIDS" ]; then
    echo "ERROR: The service already started!"
    echo "PID: $PIDS"
    exit 1
fi

# 创建日志文件夹
if [ ! -d $LOGS_DIR ]; then
   mkdir $LOGS_DIR
fi
STDOUT_FILE=$LOGS_DIR/stdout.log

# 配置启动参数
JAVA_OPTS=" -server -Xms64m -Xmx256m -verbose:gc -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=$LOGS_DIR/ -Xloggc:$LOGS_DIR/gc.log -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=5 -XX:GCLogFileSize=100M -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 "

echo -e "Starting the service ...\c"
echo "nohup $JAVA_HOME/bin/java -jar $ROOT_DIR/$JAR $JAVA_OPTS > $STDOUT_FILE 2>&1 &"
nohup $JAVA_HOME/bin/java -jar $ROOT_DIR/$JAR $JAVA_OPTS > $STDOUT_FILE 2>&1 &

sleep 3
PIDS=`ps --no-heading -C java -f --width 1000 | grep "$JAR" | awk '{print $2}'`
if [ -z "$PIDS" ]; then
    echo "ERROR: The service start error! Please check log!"
    exit 1
fi
echo "OK!"
echo "PID: $PIDS"
echo "STDOUT: $STDOUT_FILE"
