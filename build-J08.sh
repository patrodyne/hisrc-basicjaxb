#!/bin/sh
#
# Usage: build-J08.sh [option(s)] [goal(s)]
# Example: build-J08.sh clean install
#
# Profile Id: none - default, install common jars to local repository.
# Profile Id: samples - package sample plus default projects.
# Profile Id: tests - package test plus default projects.
# Profile Id: dist - package distribution plus default.
# Profile Id: all - package the above.
# Profile Id: sonatype-oss-release - upload default artifacts to central repository.
# Profile Id: only-eclipse - Eclipse/M2E life cycle mappings.
#
# How to build and test:
#
#   1) build-J08.sh -DskipTests=true clean install
#   2) build-J08.sh -DskipTests=true -Pall clean package
#   3) build-J08.sh -DskipTests=false -Pall test
#   Notes:
#     Step #1 installs the shared libraries to your local maven repository.
#     Step #2 packages the shared, test and sample projects.
#     Step #3 unit test the shared, test and sample projects.
#
JAVA08_HOME="/usr/lib/jvm/java-8-openjdk-amd64"
if [ ! -d "${JAVA08_HOME}" ]; then
  echo "Please configure JDK 8 home path."
  exit 1
fi
export JAVA_HOME="${JAVA08_HOME}"

BASEDIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
source ${BASEDIR}/build-INC.sh

if [ $# -eq 0 ]; then
  ${BASEDIR}/build.sh
else
  mvn --fail-at-end ${JVM_SYS_PROPS} "$@"
fi

# mvn ${JVM_SYS_PROPS} install
# mvn ${JVM_SYS_PROPS} -Psamples package
# mvn ${JVM_SYS_PROPS} -Ptests package
# mvn ${JVM_SYS_PROPS} -Psamples,tests package
# mvn ${JVM_SYS_PROPS} -Pdist package
# mvn ${JVM_SYS_PROPS} -Pall package
