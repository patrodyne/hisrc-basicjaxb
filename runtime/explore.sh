#!/bin/sh
# export GDK_SCALE=2
mvn compile exec:java \
	-Dexec.mainClass="org.patrodyne.jvnet.basicjaxb.explore.Explorer"
