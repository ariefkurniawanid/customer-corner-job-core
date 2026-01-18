#!/bin/sh

CURRENT_JAVA_HOME=$JAVA_HOME
export CURRENT_JAVA_HOME
CURRENT_PATH=$PATH
export CURRENT_JAVA_HOME
export JAVA_HOME=/opt/java/jdk1.8.0_281
export PATH=$JAVA_HOME/bin:$PATH

if type -p java; then
    echo found java executable in PATH
    _java=java
elif [[ -n "$JAVA_HOME" ]] && [[ -x "$JAVA_HOME/bin/java" ]];  then
    echo found java executable in JAVA_HOME
    _java="$JAVA_HOME/bin/java"
else
    echo "no java"
fi

if [[ "$_java" ]]; then
    version=$("$_java" -version 2>&1 | awk -F '"' '/version/ {print $2}')
    echo version "$version"
    nohup java -Xms1024m -Xmx2048m -XX:+UseG1GC \
    -Dspring.config.location=file:conf/application.yaml \
    -jar customer-corner-job-core-0.0.1-SNAPSHOT.jar \
    --server.servlet.context-path=/ > /dev/null 2>&1 &
    echo $! > pid
fi

export JAVA_HOME=$CURRENT_JAVA_HOME
export PATH=$CURRENT_PATH
