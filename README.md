# HiSrc BasicJAXB

A library of XJC plugins and tools to extend JAXB.

## Description

This repository is a fork of [JAXB2 Basics][22]. The original project was
developed by the admirable Alexey Valikov (a.k.a. [Highsource][2]). This
repository contains Java projects to build Maven artifact(s) related to the
Java Architecture for XML Binding (JAXB) framework. It is one of a family of
repositories forked from [Highsource][2] that provide tools for JAXB and JPA
processing. Repo and artifact names have been changed to reflect the familial
connection between the repositories and to fix a conformance issue with the
original `maven-jaxb2-plugin` name.

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

* [HiSrc BasicJAXB v2.1.1, Explorations][34]
* [HiSrc BasicJAXB v2.1.0, Explorations][33]
* [HiSrc BasicJAXB v2.0.0, Explorations][32]
* [HiSrc BasicJAXB v0.12.2, Explorations][31]

#### Maven Repositories

* Search
	* [MVN Repository](https://mvnrepository.com/artifact/org.patrodyne.jvnet?sort=popular)
	* [Central Repository](https://central.sonatype.com/search?q=org.patrodyne.jvnet+hisrc-basicjaxb&sort=name)
* Index
	* [Central Repository](https://repo1.maven.org/maven2/org/patrodyne/jvnet/)

### Goals

New goals for the next release are:

* New explorations for copy and merge strategies.

### Status

#### In Progress

* Review in progress.

#### Completed

* Review in progress.

### Fork History

#### Version 2.1.1

* Standardized the plugin option name and usage for all XJC plugins.
* Standardized logging and error handling for all XJC plugins.
* Added XJC [DefaultValuePlugin][3] to set default values in the schema.
* Added XJC [FluentApiPlugin][4] with method chaining for fluent configuration.
* Added XJC [ValueConstructorPlugin][6] for generating constructors.
* Added [`annotates`][5] to the `InheritancePlugin` for Java annotations.
* Added sample, [xjc-basic][7], to bundle the XJC tool and `hisrc-basicjaxb`.
* Update Maven plugin and dependency versions.
* Resolved deprecated method(s).
* Reviewed [Other XJC Plugins](https://github.com/javaee/jaxb2-commons)
    * Decided not to add *Commons Lang Plugin*, redundant.
    * Decided not to add *Namespace Prefix XJC Plugin*,
        * See [annotate namespace prefix](https://stackoverflow.com/questions/76541552/76551823#76551823).
        * See [jaxb2-namespace-prefix](https://central.sonatype.com/artifact/org.jvnet.jaxb2_commons/jaxb2-namespace-prefix/2.0).


#### Version 2.1.0

* Clean up Java compiler _lint_ warnings.
* Replace deprecated XJC plugin strategies v1 with v2.
* Recheck Dependency Management for newer versions.
* Configure menu, log and Maven options from build-CFG.sh.
* Include JVM system arguments from `src/test/resources/jvmsystem.arguments`.
* Moved 'explore' and 'samples' folder to new 'assembly' folder and restored int. testing.
* Set log levels for basic strategies in simplelogger.properties.
* Improved debug and trace logging JAXB plugin strategies.
* Compile sources and generate classes for Java release v11 using JDK 17.
* Replaced `maven.compiler.source/target="11"` with `maven.compiler.release="11"`.
* Moved `org.jvnet.basicjaxb.lang.Foo2` to `org.jvnet.basicjaxb.lang.Foo`.
* Update JAXB dependencies from 3.x to 4.x.

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
  [3]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/plugins/src/main/java/org/jvnet/basicjaxb/plugin/defaultvalue/DefaultValuePlugin.java
  [4]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/plugins/src/main/java/org/jvnet/basicjaxb/plugin/fluentapi/FluentApiPlugin.java
  [5]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/plugins/doc/inheritance.xsd
  [6]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/plugins/src/main/java/org/jvnet/basicjaxb/plugin/valueconstructor/ValueConstructorPlugin.java
  [7]: https://github.com/patrodyne/hisrc-basicjaxb/releases/download/2.1.1/hisrc-basicjaxb-sample-xjc-basic-2.1.1-mvn-src.zip
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
  [33]: https://github.com/patrodyne/hisrc-basicjaxb/releases/tag/2.1.0
  [34]: https://github.com/patrodyne/hisrc-basicjaxb/releases/tag/2.1.1

