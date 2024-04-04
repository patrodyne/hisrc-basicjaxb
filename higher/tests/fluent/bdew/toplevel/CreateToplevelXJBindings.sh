#!/bin/sh

M2_REPO="${HOME}/.m2/repository"
BASICJAXB_VERSION="2.2.1"
CP="${M2_REPO}/org/patrodyne/jvnet/hisrc-basicjaxb-tools/${BASICJAXB_VERSION}/hisrc-basicjaxb-tools-${BASICJAXB_VERSION}.jar"
EXEC="java -cp ${CP} org.jvnet.basicjaxb.util.CreateToplevelXJBindings --nested"

cd "src/main/resources"

for XSD in *.xsd
	do
		${EXEC} "${XSD}"
	done

exit 0
