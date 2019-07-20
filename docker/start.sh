#!/bin/bash
echo "starting ..."
docker run -e PARAMS="--spring.datasource.druid.url=jdbc:mysql://192.168.0.104:3306/test-admin? \
useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=GMT%2B8 \
--spring.datasource.druid.username=root --spring.datasource.druid.username=password" \
-p 8081:8081 -v /tmp/app/zh-admin:/data/app/zh-admin --name zh-admin -d zhaozhenghao1993/zh-admin:1.0.0
echo "finished ..."