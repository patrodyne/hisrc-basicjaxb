# HiSrc BasicJAXB

A library of XJC plugins and tools to extend JAXB.

## Description

This repository is a fork of [JAXB2 Basics][2]. The original project was developed by the admirable
Alexey Valikov (a.k.a Highsource). This repository contains Java projects to build Maven artifact(s)
related to the Java Architecture for XML Binding (JAXB) framework. It is one of a family of repositories
forked from Highsource that provide tools for JAXB and JPA processing. Names have been changed to reflect
the familial connection between the repositories and to fix a conformance issue with the original 'maven-jaxb2-plugin' name. 

### List of repositories in this family

| Patrodyne                   | Highsource                  | Purpose                                                |
| --------------------------- | --------------------------- | ------------------------------------------------------ |
| [hisrc-basicjaxb-annox][11] | [annox][21]                 | Parse XML Schema to find Java annotation declarations. |
| [hisrc-basicjaxb][12]       | [jaxb2-basics][22]          | A library of XJC plugins and tools to extend JAXB.     |
| [hisrc-higherjaxb][13]      | [maven-jaxb2-plugin][23]    | Maven plugin to generated Java source from XML Schema. |
| [hisrc-hyperjaxb-annox][15] | [jaxb2-annotate-plugin][25] | XJC plugin to add arbitrary Java annotations to JAXB.  |
| [hisrc-hyperjaxb][14]       | [hyperjaxb3][24]            | Maven and XJC plugins to add JPA annotations to JAXB.  |

### Graph of repositories relationships

![Patrodyne-Highsource Graph][1]

### Goals

The initial goals of this fork are:

* Produce a mostly warning and error free build under Java 8 and Java 11.
* Provide new build scripts to facilitate installation, deployment and release.
* Release fresh artifacts to Maven Central in the 'org.patrodyne.jvnet' group.

### Status

In progress,

* Obsolete build scripts have been removed.
* New build scripts have been added.
* POMs have been refactored with renamed artifacts.
* POMs have been updated to reduce warnings and errors.
* Changes to Java sources is in progress.
* Verification of unit and integration tests is in progress.

<!-- References -->

  [1]: https://raw.githubusercontent.com/patrodyne/hisrc-hyperjaxb/master/etc/hisrc-repositories.svg
  [11]: https://github.com/patrodyne/hisrc-hyperjaxb-annox
  [12]: https://github.com/patrodyne/hisrc-hyperjaxb
  [13]: https://github.com/patrodyne/hisrc-higherjaxb
  [14]: https://github.com/patrodyne/hisrc-hyperjaxb-annox
  [15]: https://github.com/patrodyne/hisrc-hyperjaxb
  [21]: https://github.com/highsource/annox
  [22]: https://github.com/highsource/jaxb2-basics
  [23]: https://github.com/highsource/maven-jaxb2-plugin
  [24]: https://github.com/highsource/hyperjaxb3
  [25]: https://github.com/highsource/jaxb2-annotate-plugin
