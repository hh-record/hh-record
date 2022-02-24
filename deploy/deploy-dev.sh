#!/bin/sh
REPOSITORY=/home/ec2-user/app/record-backend/
PROJECT_NAME=record_backend

echo "> 현재 구동중인 애플리케이션 pid 확인"
CURRENT_PID=$(pgrep -f backend-0.0.1-SNAPSHOT.jar)

echo CURRENT_PID echo "> 현재 구동중인 애플리케이션 pid : $CURRENT_PID"
if [ -z $CURRENT_PID ]; then
        echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다"
else
        echo "> 현재 구동중인 애플리케이션을 종료합니다"
        echo "> kill -9 $CURRENT_PID"
        kill -9 $CURRENT_PID
        sleep 5
fi

echo "> 배포 시작"
nohup java -jar -Dspring.profiles.active=dev $REPOSITORY/jar/backend-0.0.1-SNAPSHOT.jar > ~/app_log/record-backend/nohup/backend.log 2>&1 &