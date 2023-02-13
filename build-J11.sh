#!/bin/bash
#
# Usage: build-J11.sh [option(s)] [goal(s)]
# Example: build-J11.sh clean install
#
# Profile Id: none - default, install common jars to local repository.
# Profile Id: assembly - assemble (zip) explorer, samples projects.
# Profile Id: tests - package test plus default projects.
# Profile Id: dist - package distribution plus default.
# Profile Id: all - package the above.
# Profile Id: sonatype-oss-release - upload default artifacts to central repository.
# Profile Id: only-eclipse - Eclipse/M2E life cycle mappings.
#
# How to build and test:
#
#   1) build-J11.sh -DskipTests=true clean install
#   2) build-J11.sh -DskipTests=true -Pall clean package
#   3) build-J11.sh -DskipTests=false -Pall test
#   Notes:
#     Step #1 installs the shared libraries to your local maven repository.
#     Step #2 packages the shared, test and sample projects.
#     Step #3 unit test the shared, test and sample projects.
#
JAVA11_HOME="/usr/lib/jvm/java-11-openjdk-amd64"
if [ ! -d "${JAVA11_HOME}" ]; then
  echo "Please configure JDK 11 home path."
  exit 1
fi
export JAVA_HOME="${JAVA11_HOME}"

BASEDIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
source ${BASEDIR}/build-INC.sh

if [ $# -eq 0 ]; then
  ${BASEDIR}/build.sh
else
  source ${BASEDIR}/build-CFG.sh
  mvn ${JVM_SYS_PROPS} "$@"
fi

# ./build-J11.sh ${JVM_SYS_PROPS} install
# ./build-J11.sh ${JVM_SYS_PROPS} -Passembly package
# ./build-J11.sh ${JVM_SYS_PROPS} -Ptests package
# ./build-J11.sh ${JVM_SYS_PROPS} -Passembly,tests package
# ./build-J11.sh ${JVM_SYS_PROPS} -Pdist package
# ./build-J11.sh ${JVM_SYS_PROPS} -Pall package
