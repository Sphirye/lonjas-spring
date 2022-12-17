#!/usr/bin/env bash

docker network create lonjas
docker run --name lonjasdb -d --network lonjas -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_DATABASE=lonjasdb -v /root/lonjas-spring/mysql:/var/lib/mysql --restart always mariadb --character-set-server=utf8 --collation-server=utf8_general_ci