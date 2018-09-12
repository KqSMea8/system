#!/bin/sh

HOME=$1
PORT=$2
APP=$3
TARGET=$4
TOMCAT=$HOME'/tomcat-'$PORT
echo $TARGET
if [ -z $TARGET ]; then
	TARGET="web"
fi

echo '部署开始'
ps aux | grep tomcat-${PORT} | awk '{print $2}' >> tmp
cat tmp | head -2 | xargs kill -9
cat tmp | head -1 | xargs kill -9
rm tmp
	
cd ${TOMCAT}'/webapps/'
rm ROOT.war
rm -rf ROOT

if [ ${TARGET} = "web" ]; then
	cp ${APP}'/'${TARGET}'/target/ROOT.war' ${TOMCAT}'/webapps/'
elif [ ${TARGET} = "fb-job" ]; then
    cp ${APP}'/'${TARGET}'/target/scashier-job.war' ${TOMCAT}'/webapps/ROOT.war'
else
	cp ${APP}'/'${TARGET}'/target/scashier-inner.war' ${TOMCAT}'/webapps/ROOT.war'
fi

cd ${TOMCAT}
./bin/catalina.sh start
echo '部署完毕[ 端口:'$PORT',项目:'$APP']'
sleep 5
