#!/bin/bash
#
# Fix long lines in JTextArea not rendering
#   -Dsun.java2d.xrender=false \
#   -Dsun.java2d.opengl=true \
# See https://bugs.openjdk.java.net/browse/JDK-8262010
#
# Gnome scaling
# export GDK_SCALE=2
#
# Hint: When sub-projects are present, use ../execmain.sh, ../../execmain.sh, etc.
#       from the sub-project to invoke this script.

MAINCLASS="${1:-org.example.swing.SchemasTool}"

mvn test-compile exec:java \
	-Dorg.jboss.logging.provider=slf4j \
	-Dsun.java2d.uiScale.enabled=true \
	-Dsun.java2d.uiScale=200% \
	-Dexec.classpathScope="test" \
	-Dexec.mainClass="${MAINCLASS}"
