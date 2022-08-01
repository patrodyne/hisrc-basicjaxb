#!/bin/bash
# Read JVM System Properties from configuration file.
if [ -z "${JVM_SYS_PROPS}" ]; then
	JVM_SYS_PROP_FILE="${BASEDIR}/src/test/resources/jvmsystem.properties"
	if [ -r "${JVM_SYS_PROP_FILE}" ] ; then
		while read -r JVM_SYS_PROP
		do
		  if [[ ! "${JVM_SYS_PROP}" =~ ^#.*  ]]; then
			  JVM_SYS_PROPS="${JVM_SYS_PROPS} -D${JVM_SYS_PROP}"
		  fi
		done < "${JVM_SYS_PROP_FILE}"
	fi
fi
