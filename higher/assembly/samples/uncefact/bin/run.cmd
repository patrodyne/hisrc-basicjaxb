@echo off
@rem Run a Maven goal to execute the Main class.
@mvn -Pexec clean test-compile exec:java ^
	-Dexec.classpathScope=test ^
	-Dexec.mainClass=org.jvnet.basicjaxb.profile
