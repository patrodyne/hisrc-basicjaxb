#!/bin/sh
#
# Fix long lines in JTextArea not rendering
#   -Dsun.java2d.xrender=false \
#   -Dsun.java2d.opengl=true \
# See https://bugs.openjdk.java.net/browse/JDK-8262010
#
# Gnome scaling
# export GDK_SCALE=2

BASEDIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
source ${BASEDIR}/build-inc.sh

mvn test-compile exec:java \
	-Dsun.java2d.xrender=false \
	-Dexec.classpathScope="test" \
	-Dexec.mainClass="org.patrodyne.jvnet.basicjaxb.ex002.Explorer"
