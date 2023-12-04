@echo off
@rem Run a Maven goal to execute the Demo class.
@mvn -Pexec clean test-compile exec:java ^
	-Dexec.classpathScope=test ^
	-Dexec.mainClass=org.example.platce.Demo
