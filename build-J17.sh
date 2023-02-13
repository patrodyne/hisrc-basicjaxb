#!/bin/bash
#
# Usage: build-J17.sh [option(s)] [goal(s)]
# Example: build-J17.sh clean install
#
# Profile Id: none - default, install common jars to local repository.
# Profile Id: assembly - assemble (zip) explorer, samples projects.
# Profile Id: all - package the above plus templates and tutorials.
#
# How to build and test:
#
#   1) build-J17.sh -DskipTests=true clean install
#   2) build-J17.sh -DskipTests=true -Pall clean package
#   3) build-J17.sh -DskipTests=false -Pall test
#   Notes:
#     Step #1 installs the shared libraries to your local maven repository.
#     Step #2 packages the shared, test and sample projects.
#     Step #3 unit test the shared, test and sample projects.
#
JAVA17_HOME="/usr/lib/jvm/java-17-openjdk-amd64"
if [ ! -d "${JAVA17_HOME}" ]; then
  echo "Please configure JDK 17 home path."
	echo "Debian:"
	echo "  apt-cache search openjdk"
	echo "  sudo apt-get install openjdk-17-jdk"
	echo "  sudo apt-get install openjdk-17-source"
	echo "  sudo update-java-alternatives --set java-1.17.0-openjdk-amd64"
  exit 1
fi
export JAVA_HOME="${JAVA17_HOME}"

BASEDIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
source ${BASEDIR}/build-INC.sh

if [ $# -eq 0 ]; then
  ${BASEDIR}/build.sh
else
  source ${BASEDIR}/build-CFG.sh
  mvn ${JVM_SYS_PROPS} "$@"
fi

# mvn ${JVM_SYS_PROPS} -DskipTests=true clean install
# mvn ${JVM_SYS_PROPS} -Passembly package
