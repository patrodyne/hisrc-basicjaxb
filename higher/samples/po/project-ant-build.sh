#!/bin/bash
#
# build.sh: Display a menu of build actions for this ANT project.
#
# Reference: https://ant.apache.org/manual/index.html
#			 https://en.wikibooks.org/wiki/Bash_Shell_Scripting/Whiptail

BASEDIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
BUILDER="output ant"
FGTITLE="$(basename $(pwd))"
BGTITLE="$(date --rfc-3339=sec) $(pwd)"

# export ANT_OPTS="-Dcom.sun.tools.internal.xjc.Options.findServices=true"
export ANT_OPTS="-Dcom.sun.tools.xjc.Options.findServices=true"

menu_options()
{
	clear
	ACTION=$(whiptail --default-item="${ACTION}" --title "${FGTITLE}" --backtitle "${BGTITLE}" --menu "Select Action ..." --cancel-button "Close" 20 78 12 \
		"a)"	"Diagnose ANT state and exit" \
		"b)"	"Print project targets" \
		"c)"	"Clean (remove) target sub-directory" \
		"d)"	"Generate additional source files" \
		"e)"	"Compile source file(s)" \
		"f)"	"Compile test source file(s)" \
		"g)"	"Execute unit/integration test(s)" \
		"h)"	"Package resources and classes to artifact(s)" \
		"i)"	"Install artifact(s)" \
		"v)"	"Vim into current directory" \
		3>&2 2>&1 1>&3)
}

menu_actions()
{
	if [ $? -eq 0 ]; then
		case "${ACTION}" in
			"a)")	${BUILDER} -diagnostics ;;
			"b)")	${BUILDER} -projecthelp ;;
			"c)")	${BUILDER} clean ;;
			"d)")	${BUILDER} generate-sources ;;
			"e)")	${BUILDER} compile ;;
			"f)")	${BUILDER} test-compile ;;
			"g)")	${BUILDER} test ;;
			"h)")	${BUILDER} package ;;
			"i)")	${BUILDER} install ;;
			"v)")	vim . ;;
		esac	
		read -p "Press any key to continue..." anykey
		return 0
	else
		echo "Done"
		return 1
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
	if [ -n "$DISPLAY" ]; then
		$@
	else
		$@ | less
	fi
	
}

required()
{
	if ! iscmd whiptail; then
		echo "Please install whiptail!"
		exit 1
	fi
	for CMD in mvn vim
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
