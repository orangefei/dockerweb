FROM openjdk:8-jdk-alpine

#构建参数
ARG JAR_FILE
ARG WORK_PATH="/opt/www"
# 环境变量
ENV JAVA_OPTS="" \
    JAR_FILE=${JAR_FILE}

#设置时区
RUN echo "http://mirrors.aliyun.com/alpine/v3.4/main/" > /etc/apk/repositories \
    && apk --no-cache add tzdata zeromq \
    && ln -snf /usr/share/zoneinfo/$TZ /etc/localtime \
    && echo '$TZ' > /etc/timezone

COPY target/$JAR_FILE $WORK_PATH/

WORKDIR $WORK_PATH

ENTRYPOINT exec java $JAVA_OPTS -jar $JAR_FILE
