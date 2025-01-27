# Sample: Iso20022

This is a Maven project to demonstrate how to annotate the *value* of an XML schema `simpleContent` type when multiple annotations are required.

On Mar 20, 2016, Alexy Valikov [replied][9]:

> This appeared to be more complex than I thought. I did not manage to attach customizations to the simple content-derived property with standard XJC means. So I wrote the customizations plugin which reads customizations from files, associated with classes or fields.

## Execution

This is a stand-alone Maven project. You can run the test using:

~~~
mvn -Ptest clean test
mvn -Pexec compile exec:java
~~~

The [output][2] shows the test results.

### Problem ( from [HiSrc BasicJAXB Annox Issue #1](https://github.com/patrodyne/hisrc-basicjaxb-annox/issues/1) )

+ How to annotate the value of an XML schema `simpleContent` type?
+ When multiple annotations are required?

### Solution

Configure the [hisrc-higherjaxb-maven-plugin][38] to execute XJC with these arguments:

~~~
...
<arg>-Xcustomizations</arg>
<arg>-Xcustomizations-directory=\${source.dir}/main/resources</arg>
<arg>-Xcustomizations-verbose=true</arg>
<arg>-Xcustomizations-debug=true</arg>
<arg>-Xannotate</arg>
<arg>-Xannotate-defaultFieldTarget=getter</arg>
...
~~~

Use the XJC [CustomizationsPlugin][40] to read [ActiveCurrencyAndAmount.value.xml][11]:

~~~
<cus:customizations
    xmlns:anx="http://jvnet.org/basicjaxb/xjc/annox"
    xmlns:cus="http://jvnet.org/basicjaxb/xjc/customizations"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://jvnet.org/basicjaxb/xjc/annox ../../../../../../../doc/annox.xsd
                        http://jvnet.org/basicjaxb/xjc/customizations ../../../../../../../doc/customizations.xsd"
>
    <anx:annotate xmlns:anx="http://jvnet.org/basicjaxb/xjc/annox" target="field">
        @jakarta.validation.constraints.Pattern(regexp = "[-+]?[0-9]{1,16}[.][0-9]{2}")
    </anx:annotate>
    <anx:annotate xmlns:anx="http://jvnet.org/basicjaxb/xjc/annox" target="field">
        @com.fasterxml.jackson.annotation.JsonProperty(required = true)
    </anx:annotate>
</cus:customizations>
~~~

This XML uses two namespaces `cus` and `anx` conforming to their schemas [customizations.xsd][8] and [annox.xsd][7], respectively. The root `cus:customizations` supports the declaration of multiple `anx:annotate` elements. *This is the key to the solution.*

> **Note:** The XML may also use `anx:annotate` as the root when only a single annotation is required. The `anx` (a.k.a. `annox`) namespace originates in the [HiSrc BasicJAXB Annox][41] project which implements the Glassfish JAXB-RI `RuntimeAnnotationReader` to enhance the `JAXBContext` to get JAXB annotations from XML resources instead of Java in-line annotations. This solution does not require the `JAXBContext` enhancement.

The XJC [CustomizationsPlugin][40] parses the [annox.xsd][7] using the `hisrc-basicjaxb-annox-parser` dependency provided by the `hisrc-hyperjaxb-annox-plugin`.
After parsing the annotations, it creates `CPluginCustomization` instances and adds them to `CClassInfo` from the `com.sun.tools.xjc.model` package.

Finally, the [HiSrc HyperJAXB Plugin][42] is used to process the additional `CPluginCustomization` instances and add the desired JAXB annotations in the generated Java sources.

~~~
...
<arg>-Xannotate</arg>
<arg>-Xannotate-defaultFieldTarget=getter</arg>
...
~~~

> **Note:** the `defaultFieldTarget` is included to emphasize the historic default value (i.e. `getter`). The [ActiveCurrencyAndAmount.value.xml][11] overrides the default target to use `field` instead. It is common to place JPA annotations on the `getter` but to place JAXB (and other?) annotations on the field.

#### Execution

The demo is a stand-alone Maven project. You can run the test using:

~~~
mvn -Ptest clean test
mvn -Pexec compile exec:java
~~~

##### Approach

The [hisrc-higherjaxb-maven-plugin][38] is configured to generate **JAXB** classes using the provided [pain-013-001-10-part.xsd][13] schema and binding file [pain-013-001-10-part.xjb][12]. The schema provides the namespace `"urn:iso:std:iso:20022:tech:xsd:pain.013.001.10"` The **JAXB** bindings file maps this namespace to the Java package name `iso.std20022.tech.pain_013_001_10`.

As an option, a more advanced implementation of Java's built-in `Object` methods are generated using these **XJC** [hisrc-basicjaxb-plugins][37]. In particular, the sample [project][4] uses the `toString` plugin to display *human-readable* representations of the unmarshaled `Document` objects.

**hisrc-basicjaxb-plugins**
~~~
<args>
    ...
    <arg>-no-header</arg>
    <arg>-XhashCode</arg>
    <arg>-Xequals</arg>
    <arg>-XtoString</arg>
</args>
~~~

> *Note:* When the **XJC** [hisrc-basicjaxb-plugins][37] are used, the [hisrc-basicjaxb-runtime][39] dependency is required on the run-time class path.

##### Testing

The JUnit test class, [Iso20022Test.java][20], scans for the sample files and invokes the method `checkSample(File sample)` to provide each file to the tester. For this project, a `JAXBContext` is created and each file in the [samples][24] path is *unmarshaled* to a `Document` object. When successful, each object is *marshaled* for logging and your [review][2].

##### Demonstration

A Java standard engine application with a `main(...)` method is at [`iso.std20022.tech.Main`][10]. This application is executed using [pain01.xml][25]:

~~~
mvn -Pexec compile exec:java -Dexec.args="src/test/samples/pain01.xml"
~~~

<!-- References -->

[1]: https://github.com/patrodyne/hisrc-basicjaxb/releases/download/2.1.1/hisrc-basicjaxb-sample-iso20022-2.1.1-mvn-src.zip
[2]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/iso20022/OUTPUT.txt
[3]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/iso20022/README.md
[4]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/iso20022/project-pom.xml
[5]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/iso20022/bin/run.cmd
[6]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/iso20022/bin/run.sh
[7]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/iso20022/doc/annox.xsd
[8]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/iso20022/doc/customizations.xsd
[9]: https://github.com/highsource/jaxb2-annotate-plugin/issues/24#issuecomment-199042888
[10]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/iso20022/src/main/java/iso/std20022/tech/Main.java
[11]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/iso20022/src/main/resources/iso/std20022/tech/pain_013_001_10/ActiveCurrencyAndAmount.value.xml
[12]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/iso20022/src/main/resources/pain-013-001-10-part.xjb
[13]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/iso20022/src/main/resources/pain-013-001-10-part.xsd
[20]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/iso20022/src/test/java/iso/std20022/tech/Iso20022Test.java
[21]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/iso20022/src/test/resources/jvmsystem.arguments
[22]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/iso20022/src/test/resources/jvmsystem.properties
[23]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/iso20022/src/test/resources/simplelogger.properties
[24]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/iso20022/src/test/samples/
[25]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/iso20022/src/test/samples/pain01.xml
[30]: https://jakarta.ee/specifications/xml-binding/
[31]: https://jakarta.ee/specifications/xml-binding/4.0/
[32]: https://github.com/jakartaee/jaxb-api/tree/4.0.0-RELEASE
[33]: https://github.com/eclipse-ee4j/jaxb-ri/tree/4.0.2-RI-RELEASE/jaxb-ri
[34]: https://jakarta.ee/xml/ns/jaxb/bindingschema_3_0.xsd
[35]: https://projects.eclipse.org/projects/ee4j.jaxb-impl/releases/4.0.0
[36]: https://repo1.maven.org/maven2/com/sun/xml/bind/jaxb-ri/4.0.2/
[37]: https://github.com/patrodyne/hisrc-basicjaxb#readme
[38]: https://github.com/patrodyne/hisrc-higherjaxb#readme
[39]: https://central.sonatype.com/artifact/org.patrodyne.jvnet/hisrc-basicjaxb-runtime
[40]: https://github.com/patrodyne/hisrc-basicjaxb/blob/f456d58a2a97ebcca37d0bff8b243bd76a818ae2/plugins/src/main/java/org/jvnet/basicjaxb/plugin/customizations/CustomizationsPlugin.java
[41]: https://github.com/patrodyne/hisrc-basicjaxb-annox#readme
[42]: https://github.com/patrodyne/hisrc-hyperjaxb-annox#readme

