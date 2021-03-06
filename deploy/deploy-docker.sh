#!/bin/sh

DOCKER_IMAGE=ghcr.io/hh-record/hh-record

cat ~/docker_test/deploy/GIT-TOKEN.txt | docker login ghcr.io -u hh-record --password-stdin

docker stop hh-record && docker rm -fv hh-record && docker rmi -f $DOCKER_IMAGE:latest
docker run -d -p 9999:9999 --name hh-record --restart always $DOCKER_IMAGE:latest