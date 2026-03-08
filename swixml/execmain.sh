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

MAINCLASS="${1:-org.swixml.examples.BindingExamplesApplication}"

mvn test-compile exec:java \
	-Dorg.jboss.logging.provider=slf4j \
	-Dsun.java2d.uiScale.enabled=true \
	-Dsun.java2d.uiScale=200% \
	-Dexec.classpathScope="test" \
	-Dexec.mainClass="${MAINCLASS}"

# Examples
#
# org.swixml.legacy.NewTag
# org.swixml.legacy.MenuBarWithConstraints
# org.swixml.legacy.Layout
# org.swixml.legacy.CustomTags
# org.swixml.legacy.GridBag
# org.swixml.legacy.Cards
# org.swixml.legacy.XInclude
# org.swixml.legacy.InitClass
# org.swixml.legacy.Actions
# org.swixml.legacy.HelloWorld
# org.swixml.legacy.Accelerator
# org.swixml.legacy.HelloWorldNoAction
# org.swixml.legacy.Localization
#
# org.swixml.examples.task.BackgroundTaskExample
# org.swixml.examples.layout.LayoutExample
# org.swixml.examples.border.BorderExample
# org.swixml.examples.treecard.TreeCard2Main
# org.swixml.examples.treecard.TreeCard1
# org.swixml.examples.table.TableExample
# org.swixml.examples.text.TextExample
# org.swixml.examples.slider.SliderExample
# org.swixml.examples.spinner.SpinnerExample
# org.swixml.examples.laf.ListInstalledLAF
# org.swixml.examples.tree.TreeExample
# org.swixml.examples.toaster.ToasterExample
# org.swixml.examples.combo.ComboExample
# org.swixml.examples.BindingExamplesApplication
# org.swixml.examples.button.ButtonExample
# org.swixml.examples.dialog.LoginExample
# org.swixml.examples.script.ScriptExample
