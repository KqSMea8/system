#!/usr/bin/env bash

PORT=$1

ps -ef | grep finance-counter-${PORT} | awk '{print $2}' | xargs kill -9
rm -rf ../../finance-counter/tomcat-finance-counter-${PORT}/webapps/ROOT
pwd
cp -rf web/target/ROOT ../../finance-counter/tomcat-finance-counter-${PORT}/webapps/ROOT
cd ../../finance-counter/tomcat-finance-counter-${PORT}/bin/
pwd
sh catalina.sh start
