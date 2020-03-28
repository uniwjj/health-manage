FROM openjdk:8-jdk-alpine

MAINTAINER ginger uniwjj@gmail.com

# 设置时区
ENV TIMEZONE=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TIMEZONE /etc/localtime \
    && echo $TIMEZONE > /etc/timezone

# 设置工作目录
WORKDIR "/app"

ARG jar_file
COPY ${jar_file} app.jar

ARG spring_profile
ENV spring_profile "${spring_profile}"

# jvm环境变量
ENV jvm_xms "128m"
ENV jvm_xmx "256m"

COPY config config
COPY run-app.sh run-app.sh
RUN chmod +x run-app.sh

# 启动服务
CMD ["sh", "run-app.sh"]

