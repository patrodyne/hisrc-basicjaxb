#!/bin/bash

# Base directory
if [ -z "${BASEDIR}" ]; then
	BASEDIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
fi

# Read JVM System Arguments from configuration file.
if [ -z "${JVM_SYS_ARGS}" ]; then
	JVM_SYS_ARGS_FILE="${BASEDIR}/src/test/resources/jvmsystem.arguments"
	if [ -r "${JVM_SYS_ARGS_FILE}" ] ; then
		while read -r JVM_SYS_ARG
		do
		  if [[ ! "${JVM_SYS_ARG}" =~ ^#.*  ]]; then
			  JVM_SYS_ARGS="${JVM_SYS_ARGS}${JVM_SYS_ARG} "
		  fi
		done < "${JVM_SYS_ARGS_FILE}"
		export JVM_SYS_ARGS
	fi
fi

# Read JVM System Properties from configuration file, add -D.
if [ -z "${JVM_SYS_PROPS}" ]; then
	JVM_SYS_PROP_FILE="${BASEDIR}/src/test/resources/jvmsystem.properties"
	if [ -r "${JVM_SYS_PROP_FILE}" ] ; then
		while read -r JVM_SYS_PROP
		do
		  if [[ ! "${JVM_SYS_PROP}" =~ ^#.*  ]]; then
			  JVM_SYS_PROPS="${JVM_SYS_PROPS}-D${JVM_SYS_PROP} "
		  fi
		done < "${JVM_SYS_PROP_FILE}"
		export JVM_SYS_PROPS
	fi
fi
