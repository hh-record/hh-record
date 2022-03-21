#!/bin/sh

DOCKER_IMAGE=ghcr.io/hh-record/hh-record

cat ~/docker_test/deploy/GIT-TOKEN.txt | docker login ghcr.io -u hh-record --password-stdin

docker stop hh-record && docker rm -fv hh-record
docker run -d -p 8888:8888 --name hh-record --restart always $DOCKER_IMAGE:latest