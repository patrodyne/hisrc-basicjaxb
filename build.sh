#!/bin/bash
#
# build.sh: Display a menu of build actions for this POM project.
#
# See: https://github.com/patrodyne/hisrc-hyperjaxb/blob/master/etc/BUILD_TOOLS.md
#
# Reference: https://maven.apache.org/what-is-maven.html
#			 https://en.wikibooks.org/wiki/Bash_Shell_Scripting/Whiptail
#
# Hint: When sub-projects are present, use ../build.sh, ../../build.sh, etc.
#       from the sub-project to invoke this script.
#

BASEDIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
source ${BASEDIR}/build-CFG.sh
source ${BASEDIR}/build-INC.sh
export MAVEN_OPTS="${MAVEN_OPTS} ${JVM_SYS_PROPS}"
BUILDER="output mvn ${MAVEN_ARGS}"
FGTITLE="$(basename $(pwd))"
BGTITLE="$(date --rfc-3339=sec) $(pwd)"

menu_options()
{
	clear
	if [ "${FULL_MENU}" = true ]; then
		ACTION=$(whiptail --default-item="${ACTION}" --title "${FGTITLE}" --backtitle "${BGTITLE}" --menu "Select Action ..." --cancel-button "Close" 20 78 14 \
			"a)"	"Validate POM(s) correctness and that information is available" \
			"b)"	"Compare the effective POM with current POM" \
			"c)"	"Display plugins that have newer version available" \
			"d)"	"Display dependencies that have newer version available" \
			"e)"	"Display the dependency tree" \
			"f)"	"Resolve plugins and report dependencies" \
			"g)"	"Analyze dependencies and report on: (un)used and/or (un)declared" \
			"h)"	"Download source and javadoc jars to the local repository" \
			"i)"	"Clean and install the shared libraries to the local repository" \
			"j)"	"Clean and package all modules: libraries, assemblies, tests, etc." \
			"k)"	"Unit test all modules" \
			"l)"	"Integration test default, assembly modules" \
			"m)"	"Generate a site for each project" \
			"v)"	"Vim into current directory" \
			3>&2 2>&1 1>&3)
	else
		ACTION=$(whiptail --default-item="${ACTION}" --title "${FGTITLE}" --backtitle "${BGTITLE}" --menu "Select Action ..." --cancel-button "Close" 20 78 13 \
			"a)"	"Validate POM(s) correctness and that information is available" \
			"i)"	"Clean and install the shared libraries to the local repository" \
			"j)"	"Clean and package all modules: libraries, assemblies, tests, etc." \
			"k)"	"Unit test all modules" \
			"l)"	"Integration test default, assembly modules" \
			"v)"	"Vim into current directory" \
			3>&2 2>&1 1>&3)
	fi
}

menu_actions()
{
	if [ $? -eq 0 ]; then
		case "${ACTION}" in
			"a)")	${BUILDER} validate ;;
			"b)")	comparepom ;;
			"c)")	${BUILDER} versions:display-plugin-updates ;;
			"d)")	${BUILDER} versions:display-dependency-updates ;;
			"e)")	${BUILDER} dependency:tree ;;
			"f)")	${BUILDER} dependency:resolve-plugins ;;
			"g)")	${BUILDER} dependency:analyze ;;
			"h)")	${BUILDER} dependency:sources; mvn dependency:resolve -Dclassifier=javadoc ;;
			"i)")	${BUILDER} -DskipTests=true clean install ;;
			"j)")	${BUILDER} -DskipTests=true -Pall clean package ;;
			"k)")	${BUILDER} -DskipTests=false -Pall test ;;
			"l)")	${BUILDER} -DskipTests=false -Passembly clean integration-test ;;
			"m)")	${BUILDER} -DskipTests=false -Pall site ;;
			"v)")	vim . ;;
		esac	
		read -p "Press any key to continue..." anykey
		return 0
	else
		echo "Done"
		return 1
	fi
}

comparepom()
{
	# Check for GUI diff command.
	unset DIFFCMD
	if [ -n "$DISPLAY" ]; then
		if iscmd meld; then
			DIFFCMD="meld"
		elif iscmd gvimdiff; then
			DIFFCMD="gvimdiff -f"
		fi
	fi
	# Otherwise; check for TUI diff command.
	if [ -z "$DIFFCMD" ]; then
		if iscmd vimdiff; then
			DIFFCMD="vimdiff"
		elif iscmd sdiff; then
			DIFFCMD="output sdiff -w 160 -W -t --tabsize 2"
		elif iscmd diff; then
			DIFFCMD="output diff -w -c"
		fi
	fi
	# If diff command discovered then proceed.
	if [ -n "$DIFFCMD" ]; then
		TMPFILE="$(mktemp --tmpdir pom.XXXXXXXXXX)"
		${BUILDER} -Doutput="${TMPFILE}" help:effective-pom
		xmllint --xpath "/projects/*[1]" "${TMPFILE}" >"${TMPFILE}-P1" 2>/dev/null
		if [ -s "${TMPFILE}-P1" ]; then
			${DIFFCMD} "${TMPFILE}-P1" "pom.xml"
		else
			${DIFFCMD} "${TMPFILE}" "pom.xml"
		fi
		rm ${TMPFILE}*
	else
		echo "OOPS: Please configure a diff command."
	fi
}

iscmd()
{
	command -v $1 >/dev/null
}

confirmation()
{
	if (whiptail --title "Confirmation" --yesno "$1" 7 60) then
		return 0
	else
		return 1
	fi
}

message()
{
	whiptail --title "$1" --msgbox "$2" 7 60
}

information()
{
	message "Information" "$1"
}

warning()
{
	message "Warning" "$1"
}

error()
{
	message "Error" "$1"
}

output()
{
	if [ "${BUILD_LOG}" = true ]; then
		if [ -n "$DISPLAY" ]; then
			$@ 2>&1 | tee "build.log"
		else
			$@ 2>&1 | tee "build.log" | less
		fi
	else
		if [ -n "$DISPLAY" ]; then
			$@
		else
			$@ | less
		fi
	fi
}

required()
{
	if ! iscmd whiptail; then
		echo "Please install whiptail!"
		exit 1
	fi
	for CMD in mvn vim xmllint
	do
		if ! iscmd "${CMD}"; then
			error "Please install ${CMD}"
			exit 1
		fi
	done
}

# Display options and execute actions until user closes the menu.
required
while [ $? -eq 0 ]
do
	menu_options
	menu_actions
done

# vi:set tabstop=4 hardtabs=4 shiftwidth=4:
