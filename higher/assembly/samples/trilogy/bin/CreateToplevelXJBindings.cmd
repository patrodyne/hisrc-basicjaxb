@echo off

SET "M2_REPO=%HOME%/.m2/repository"
SET "BASICJAXB_VERSION=2.1.1"
SET "CP=%M2_REPO%/org/patrodyne/jvnet/hisrc-basicjaxb-tools/%BASICJAXB_VERSION%/hisrc-basicjaxb-tools-%BASICJAXB_VERSION%.jar"
SET "EXEC=java -cp %CP% org.jvnet.basicjaxb.util.CreateToplevelXJBindings --nested"

%EXEC% "src/main/resources/trilogy.xsd"
