#!/bin/bash

# jar格式：health-manage-1.0.0.jar
JAR=health-manage-\([0-9]+\.\)+jar

PIDS=`ps --no-heading -C java -f --width 1000 | grep -E "$JAR" | awk '{print $2}'`
# 判断字符串长度是否为0
if [ -z "$PIDS" ]; then
    echo "ERROR: The service does not started!"
    exit 1
fi

echo -e "Stopping the service ...\c"
for PID in $PIDS; do
    kill $PID > /dev/null 2>&1
done

COUNT=0
TOTAL=0
while [ $COUNT -lt 1 ]; do
    echo -e ".\c"
    sleep 1
    COUNT=1
    TOTAL=$(expr $TOTAL + 1)
    for PID in $PIDS; do
        PID_EXIST=`ps --no-heading -p $PID`
        if [ -n "$PID_EXIST" ]; then
            COUNT=0
            break
        fi
    done
    if [ $COUNT -lt 1 ] && [ $TOTAL -gt 30 ]; then
        for PID in $PIDS; do
            kill -9 $PID > /dev/null 2>&1
        done
        COUNT=0
        TOTAL=0
        break
    fi
done
echo "OK!"
echo "PID: $PIDS"
