# Author: zfitcode
FROM daocloud.io/library/java:openjdk-8-jre-alpine

#作者
MAINTAINER zfitcode <zfitcode@163.com>

#系统编码
ENV LANG=C.UTF-8 LC_ALL=C.UTF-8

VOLUME /tmp

ADD target/chat-1.0.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]

EXPOSE 8099
