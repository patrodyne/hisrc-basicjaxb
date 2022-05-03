#!/bin/sh
# export GDK_SCALE=2
mvn test-compile exec:java \
	-Dexec.classpathScope="test" \
	-Dexec.mainClass="org.patrodyne.jvnet.basicjaxb.ex001.Explorer"
