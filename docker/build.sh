#!/bin/bash
echo "build starting ..."
cd ..
echo "mvn package starting ..."
mvn clean
mvn package -DskipTests=true -Dmaven.javadoc.skip=true
echo "mvn package finished ..."

cd zh-main
docker build -t zhaozhenghao1993/zh-admin:1.0.1 .
echo "build finished ..."
