#!/bin/bash

cd `dirname $0`
cd ..

if [ ! $# -eq 1 ]; then
  echo "ERROR: invalid param! Example: script/build-docker.sh 1.1.0"
  exit 1
fi

if [[ ! $1 =~ ^[0-9]+\.[0-9]+\.[0-9]+$ ]]; then
  echo "ERROR: version $1 is invalid!"
  exit 1
fi
echo "version is $1"

echo "start building app"
chmod +x script/build-app.sh
script/build-app.sh $1

echo "start building image"
cd target
docker build -t ginger/health-manage:$1 \
      --build-arg JAR_FILE=health-manage-*.jar \
      .

