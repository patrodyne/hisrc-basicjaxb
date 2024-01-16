#!/bin/bash
#
# Usage: build-J21.sh [option(s)] [goal(s)]
# Example: build-J21.sh clean install
#
# Profile Id: none - default, install common jars to local repository.
# Profile Id: assembly - assemble (zip) explorer, samples projects.
# Profile Id: all - package the above plus templates and tutorials.
#
# How to build and test:
#
#   1) build-J21.sh -DskipTests=true clean install
#   2) build-J21.sh -DskipTests=true -Pall clean package
#   3) build-J21.sh -DskipTests=false -Pall test
#   Notes:
#     Step #1 installs the shared libraries to your local maven repository.
#     Step #2 packages the shared, test and sample projects.
#     Step #3 unit test the shared, test and sample projects.
#
# How to set Java alternative:
#
#   update-java-alternatives --list
#   sudo update-java-alternatives --set /usr/lib/jvm/java-1.21.0-openjdk-amd64
#   ll /etc/alternatives/j* | grep -v "gz" | grep 21
#
JAVA21_HOME="/usr/lib/jvm/java-21-openjdk-amd64"
if [ ! -d "${JAVA21_HOME}" ]; then
  echo "Please configure JDK 21 home path."
	echo "Debian:"
	echo "  apt-cache search openjdk"
	echo "  sudo apt-get install openjdk-21-jdk"
	echo "  sudo apt-get install openjdk-21-source"
	echo "  sudo update-java-alternatives --set java-1.21.0-openjdk-amd64"
  exit 1
fi
export JAVA_HOME="${JAVA21_HOME}"

BASEDIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
source ${BASEDIR}/build-INC.sh

if [ $# -eq 0 ]; then
  ${BASEDIR}/build.sh
else
  source ${BASEDIR}/build-CFG.sh
  mvn ${JVM_SYS_PROPS} "$@"
fi

# ./build-J21.sh ${JVM_SYS_PROPS} -DskipTests=true clean install
# ./build-J21.sh ${JVM_SYS_PROPS} -Passembly package
