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

### Goals

The initial goals for this fork are:

* Produce a (mostly) warning and error free build under Java 8 and Java 11.
* Provide new build scripts to facilitate installation, deployment and release.
* Use the SLF4J API and SimpleLogger implementation everywhere.
* Release fresh artifacts to Maven Central in the `org.patrodyne.jvnet` group.

### Status

#### Completed

* Obsolete build scripts have been removed.
* New build scripts have been added.
* POMs have been refactored with renamed artifacts.
* POMs have been updated to reduce warnings and errors.

#### In Progress

* Changes to Java sources to resolve warnings/errors.
* Verification of unit and integration tests.
* Release to Maven Central TBD.

<!-- References -->

  [1]: https://raw.githubusercontent.com/patrodyne/hisrc-hyperjaxb/master/etc/hisrc-repositories.svg
  [2]: https://github.com/highsource
  [11]: https://github.com/patrodyne/hisrc-basicjaxb-annox
  [12]: https://github.com/patrodyne/hisrc-basicjaxb
  [13]: https://github.com/patrodyne/hisrc-higherjaxb
  [14]: https://github.com/patrodyne/hisrc-hyperjaxb-annox
  [15]: https://github.com/patrodyne/hisrc-hyperjaxb
  [21]: https://github.com/highsource/annox
  [22]: https://github.com/highsource/jaxb2-basics
  [23]: https://github.com/highsource/maven-jaxb2-plugin
  [24]: https://github.com/highsource/jaxb2-annotate-plugin
  [25]: https://github.com/highsource/hyperjaxb3
