#!/bin/bash

cd `dirname $0`
cd ..

if [ -z "$1" ]; then
    echo "ERROR: param not exits! Example: script/build-app.sh test"
    exit 1
fi

mvn clean package -Dmaven.test.skip=true -DskipDockerfile
