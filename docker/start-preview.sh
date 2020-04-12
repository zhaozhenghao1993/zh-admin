#!/bin/bash
echo "starting ..."
docker run \
-e PARAMS="\
--spring.datasource.druid.url=jdbc:mysql://192.168.0.104:3306/test-admin?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=GMT%2B8 \
--spring.datasource.druid.username=root \
--spring.datasource.druid.password=root \
--zh-admin.file.log.folder=/data/app/zh-admin/log \
" \
-p 8080:8080 \
-v /tmp:/data/app \
--name zh-admin \
-d zhaozhenghao1993/zh-admin-preview:1.0.1
echo "finished ..."
