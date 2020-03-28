#!/bin/bash

docker run \
--name some-health-manage \
-p 1521:1521 \
-v ~/library/docker/health-manage/logs:/app/logs \
-e spring_profile="online" \
-e jdbc_url="jdbc:mysql://127.0.0.1:3306/health_manage?characterEncoding=UTF-8&autoReconnect=true&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=GMT%2B8" \
-e db_username="healthmanage" \
-e db_password="healthmanage" \
-d \
registry.cn-hangzhou.aliyuncs.com/ginger/health-manage:1.0.0

