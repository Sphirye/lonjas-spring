#!/usr/bin/env bash

docker stop lonjas-spring
docker rm lonjas-spring
docker rmi lonjas-spring

cd /root/lonjas-spring/project
bash -x gradlew buildDocker --no-daemon --stacktrace -Dprod -Pprofile=prod -x test -Dkotlin.compiler.execution.strategy=in-process -Dkotlin.incremental=false

docker logs -f lonjas-spring