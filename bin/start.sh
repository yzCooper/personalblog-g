###启动

#!/bin/sh

memory=$1
env=$2
hostname=$3
if [ ! -n "$memory" ];
then
	echo '必须传入，分配内存，如：./stop.sh 1'
	exit 1
elif [ ! -n "$env" ];
then
echo '必须传入，配置文件后缀，如：./stop.sh 1 env'
	exit 1
elif [ ! -n "$hostname" ];
then
echo '必须传入，主机名称，如：./stop.sh 1 env core1'
	exit 1
else
	nohup java -server -Xms"$memory" -Xms"$memory" -XX:PermSize=512M -XX:MaxPermSize=1g -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:+PrintGCDetails -XX:CMSInitiatingOccupancyFraction=80 -XX:+HeapDumpOnOutOfMemoryError -Xloggc:logs/gc.log -jar ./eagleface-web-1.0-SNAPSHOT.jar --spring.profiles.active="$env" --eureka.instance.hostname="$hostname" >>/dev/null 2>&1 &
fi


