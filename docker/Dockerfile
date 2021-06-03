FROM openjdk:8-jdk-alpine

MAINTAINER ginger uniwjj@gmail.com

# 设置时区
ENV TIMEZONE=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TIMEZONE /etc/localtime \
    && echo $TIMEZONE > /etc/timezone

# 设置工作目录
WORKDIR "/app"

ARG JAR_FILE
COPY ${JAR_FILE} app.jar

ARG SPRING_PROFILE
ENV SPRING_PROFILE "${SPRING_PROFILE}"

# 管理端口
ARG MGMT_PORT
ENV MGMT_PORT "${MGMT_PORT}"
EXPOSE ${MGMT_PORT}

# jvm环境变量
ENV jvm_xms "128m"
ENV jvm_xmx "256m"

COPY config config
COPY run-app.sh run-app.sh
RUN chmod +x run-app.sh

# 启动服务
CMD ["sh", "run-app.sh"]

