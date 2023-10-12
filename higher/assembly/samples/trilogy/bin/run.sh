#!/bin/bash

if grep -q "<packaging>jar</packaging>" pom.xml; then
	BASEDIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
	source ${BASEDIR}/build-cfg.sh
	source ${BASEDIR}/build-inc.sh
	export MAVEN_OPTS="${MAVEN_OPTS} ${JVM_SYS_PROPS}"

	mvn ${MAVEN_ARGS} -Pexec clean compile exec:java
else
    echo "Not a JAR project!"
fi

