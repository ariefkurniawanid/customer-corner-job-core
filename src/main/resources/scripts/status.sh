#!/bin/sh

APP_NAME=customer-corner-job-core

if [ ! -r pid ]; then
	echo "not running"
	exit
fi

pid=`cat pid`
echo "checking pid $pid"
ps -fp $pid | grep $APP_NAME > /dev/null
if [ $? -eq 0 ]; then
	echo "running"
else
	echo "not running"
fi
