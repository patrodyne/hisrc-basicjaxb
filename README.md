# HiSrc BasicJAXB

A library of XJC plugins and tools to extend JAXB.

## Description

This repository is a fork of [JAXB2 Basics][22]. The original project was developed by the admirable
Alexey Valikov (a.k.a. [Highsource][2]). This repository contains Java projects to build Maven artifact(s)
related to the Java Architecture for XML Binding (JAXB) framework. It is one of a family of repositories
forked from [Highsource][2] that provide tools for JAXB and JPA processing. Repo and artifact names have
been changed to reflect the familial connection between the repositories and to fix a conformance issue
with the original `maven-jaxb2-plugin` name.

### List of repositories in this family

| Patrodyne                   | Highsource                  | Purpose                                                |
| --------------------------- | --------------------------- | ------------------------------------------------------ |
| [hisrc-basicjaxb-annox][11] | [annox][21]                 | Parse XML Schema to find Java annotation declarations. |
| [hisrc-basicjaxb][12]       | [jaxb2-basics][22]          | A library of XJC plugins and tools to extend JAXB.     |
| [hisrc-higherjaxb][13]      | [maven-jaxb2-plugin][23]    | Maven plugin to generated Java source from XML Schema. |
| [hisrc-hyperjaxb-annox][14] | [jaxb2-annotate-plugin][24] | XJC plugin to add arbitrary Java annotations to JAXB.  |
| [hisrc-hyperjaxb][15]       | [hyperjaxb3][25]            | Maven and XJC plugins to add JPA annotations to JAXB.  |

### Graph of repository relationships

![Patrodyne-Highsource Graph][1]

### Releases

#### GitHub Releases, Demonstrations

* [HiSrc BasicJAXB v2.0.0, Explorations][32]
* [HiSrc BasicJAXB v0.12.2, Explorations][31]

#### Maven Repositories

* Search
	* [MVN Repository](https://mvnrepository.com/artifact/org.patrodyne.jvnet?sort=popular)
	* [Central Repository](https://search.maven.org/search?q=g:org.patrodyne.jvnet)
* Index
	* [Central Repository](https://repo1.maven.org/maven2/org/patrodyne/jvnet/)

### Goals

New goals for the next release are:

* Clean up Java compiler _lint_ warnings.
* Replace deprecated XJC plugin strategies v1 with v2.
* Recheck Dependency Management for newer versions.
* Configure menu, log and Maven options from build-CFG.sh.
* Include JVM system arguments from 'src/test/resources/jvmsystem.arguments'.
* Moved 'explore' and 'samples' folder to new 'assembly' folder and restored int. testing.
* Set log levels for basic strategies in simplelogger.properties.
* Improved debug and trace logging JAXB plugin strategies.
* New explorations for copy and merge strategies.
* Compile sources and generate classes for Java release v11 using JDK 17.

### Status

#### In Progress

* New explorations for copy and merge strategies.

#### Completed

* Clean up Java compiler _lint_ warnings.
* Replace deprecated XJC plugin strategies v1 with v2.
* Recheck Dependency Management for newer versions.
* Configure menu, log and Maven options from build-CFG.sh.
* Include JVM system arguments from 'src/test/resources/jvmsystem.arguments'.
* Moved 'explore' and 'samples' folder to new 'assembly' folder and restored int. testing.
* Set log levels for basic strategies in simplelogger.properties.
* Improved debug and trace logging JAXB plugin strategies.
* Compile sources and generate classes for Java release v11 using JDK 17.

### Fork History

#### Version 2.0.0

* Update dependencies with newer versions *including* the Jakarta namespace.
* Replace 'eclipse-only' lifecyle profile with 'm2e' XML directive.
* Convert DOS line endings to Unix newlines.
* Update JUnit v4 to JUnit v5.
* Replace Spring DI with Jakarta CDI.
* Rename packages:
    * BasicJAXB Annotations
        * `OLD: org.jvnet.annox`
        * `NEW: org.jvnet.basicjaxb_annox`
    * BasicJAXB XJC Plugin
        * `OLD: org.jvnet.jaxb2_commons`
        * `NEW: org.jvnet.basicjaxb`
    * HigherJAXB Maven Plugin
        * `OLD: org.jvnet.mjiip`
        * `NEW: org.jvnet.higherjaxb`
    * HigherJAXB Maven Mojo
        * `OLD: org.jvnet.jaxb2.maven2`
        * `NEW: org.jvnet.higherjaxb.mojo`
    * HyperJAXB Persistence
        * `OLD: org.jvnet.hyperjaxb[23]`
        * `NEW: org.jvnet.hyperjaxb`
* Rename namespaces:
    * BasicJAXB XJC Annotations
        * `OLD: http://annox.dev.java.net`
        * `NEW: http://jvnet.org/basicjaxb/xjc/annox`
    * BasicJAXB XJC Plugin
        * `OLD: http://jaxb2-commons.dev.java.net/basic`
        * `NEW: http://jvnet.org/basicjaxb/xjc`
    * HyperJAXB Persistence
        * `OLD: http://hyperjaxb3.jvnet.org/ejb/schemas/customizations`
        * `NEW: http://jvnet.org/hyperjaxb/jpa`
* Update version to 2.0.0 due to Jakarta EE and other name changes.

#### Version 0.12.2

* Added demonstration explorations as GitHub releases.
* Updated dependencies with newer versions *excluding* the Jakarta namespace.

#### Version 0.12.1

* Obsolete build scripts have been removed.
* New build scripts have been added.
* POMs have been refactored with renamed artifacts.
* POMs have been updated to reduce warnings and errors.
* Configured SLF4J with SimpleLogger as the log framework.
* Updated Java sources to resolve warnings/errors.
* Verification of unit and integration tests.

<!-- References -->

  [1]: https://raw.githubusercontent.com/patrodyne/hisrc-hyperjaxb/master/etc/hisrc-repositories.svg
  [2]: https://github.com/highsource
  [11]: https://github.com/patrodyne/hisrc-basicjaxb-annox#readme
  [12]: https://github.com/patrodyne/hisrc-basicjaxb#readme
  [13]: https://github.com/patrodyne/hisrc-higherjaxb#readme
  [14]: https://github.com/patrodyne/hisrc-hyperjaxb-annox#readme
  [15]: https://github.com/patrodyne/hisrc-hyperjaxb#readme
  [21]: https://github.com/highsource/annox#readme
  [22]: https://github.com/highsource/jaxb2-basics#readme
  [23]: https://github.com/highsource/maven-jaxb2-plugin#readme
  [24]: https://github.com/highsource/jaxb2-annotate-plugin#readme
  [25]: https://github.com/highsource/hyperjaxb3#readme
  [31]: https://github.com/patrodyne/hisrc-basicjaxb/releases/tag/0.12.2
  [32]: https://github.com/patrodyne/hisrc-basicjaxb/releases/tag/2.0.0
