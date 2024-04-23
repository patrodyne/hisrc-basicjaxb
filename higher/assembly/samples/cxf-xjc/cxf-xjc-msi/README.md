# Sample: cxf-xjc-msi

This is a Maven project to demonstrate using the Maven [cxf-xjc-plugin][1] from [Apache CXF][2] together with the XJC [HiSrc BasicJAXB Plugins][3].

## Execution

This is a stand-alone Maven project. You can run the test using either of these two commands where the second version includes a dump of the XJC classpath:

~~~
mvn -Ptest clean test
mvn -Ptest clean test -Dorg.slf4j.simpleLogger.log.org.apache.cxf.maven_plugin.XSDToJavaMojo=DEBUG 
~~~

The [output][21] shows the test results.

You can run the demo using:

~~~
mvn -Pexec compile exec:java
~~~

### Problem ( from [StackOverflow](https://stackoverflow.com/questions/17876332/) )

+ How to configure the [cxf-xjc-plugin][1] plugin to generate the *hashCode*, *equals* and *toString* methods from an XSD file?

### Solution

This [download (zip)][4] is a stand-alone Maven project to demonstrate the configuration of the Maven [cxf-xjc-plugin][1] from [Apache CXF][2] together with the XJC [HiSrc BasicJAXB Plugins][3]. Specifically, it configures the following XJC plugins:

+ [-XhashCode][5] - generate reflection-free *hashCode* methods.
+ [-Xequals][6] - generate reflection-free *equals* methods.
+ [-XtoString][7] - generate reflection-free *toString* methods.
+ [-xfluentAPI][8] - enable Fluent API, method chaining, for generated code.

> **Note:** After extracting the download to your local folder, you can run the tests and/or the demo using:

~~~
mvn -Ptest clean test
mvn -Pexec compile exec:java
~~~

**Business Model**
~~~
<?xml version="1.0" encoding="UTF-8"?>
<xs:schema
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    targetNamespace="http://www.example.com/messagedefinitions"
    xmlns="http://www.example.com/messagedefinitions"
>

    <xs:element name="Message">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="status" type="Status"/>
                <xs:element name="id" type="Identifier"/>
            </xs:sequence>
        </xs:complexType>
    </xs:element>

    <xs:simpleType name="Status">
        <xs:restriction base="xs:string">
            <xs:maxLength value="4"/>
        </xs:restriction>
    </xs:simpleType>

    <xs:simpleType name="Identifier">
        <xs:restriction base="xs:int"/>
    </xs:simpleType>

</xs:schema>
~~~

By default, XJC generates a JAXB class for each of the above elements using the `Object` *hashCode*, *equals* and *toString* methods. At this level, objects are compared by object id. To compare, evaluate the JAXB generated `Message` objects by value, the [HiSrc BasicJAXB Plugins][3] are configured as `<extensionArgs>` in the [cxf-xjc-plugin][1].

~~~
<extensionArgs>
    <extensionArg>-no-header</extensionArg>
    <extensionArg>-XhashCode</extensionArg>
    <extensionArg>-Xequals</extensionArg>
    <extensionArg>-XtoString</extensionArg>
    <extensionArg>-XfluentAPI</extensionArg>
    <extensionArg>-XfluentAPI-fluentMethodPrefix=use</extensionArg>
</extensionArgs>
~~~

For unit testing, the *-XfluentAPI* plugin, is also activated, to facilitate the setting of a test instance of the `Message` object.

[FluentAPITest][52]
~~~
final Object object = getUnmarshaller().unmarshal(sample);
if ( object instanceof Message )
{
    Message msi1 = (Message) object;
    Message msi2 = OF.createMessage()
        .useStatus("PASS")
        .useId(1001);

    getLogger().debug("MSI1: {}", msi1);
    getLogger().debug("MSI2: {}", msi2);

    assertEquals(msi1, msi2, "Unmarshaled and Fluent MSIs are equal.");
}
~~~

#### JAR Conflicts

The `cxf-xjc-plugin` above explicitly declares several dependencies (not shown) to resolve JAR conflicts between the CXF product and the HiSrc product. Because these products are produced by independent teams, some version conflicts are likely. The [download (zip)][4] contains a tool, named [ReadJarManifest.java][30] to identify and resolve conflicts. The conflicts have been resolved in the `download` sample.

**Generate `build.log`**
~~~
mvn -Dorg.slf4j.simpleLogger.log.org.apache.cxf.maven_plugin.XSDToJavaMojo=DEBUG -Ptest clean test >build.log
~~~

**ReadJarManifest.java**
~~~
Read JAR Manifest from a JAR file or a LOG file.

Usage:
    javac ReadJarManifest.java
    java ReadJarManifest [jarfile | stdin]

Example:
    java ReadJarManifest <build.log
~~~

> **Note:** The [ReadJarManifest.java][30] tool catches most issues but can also report *false conflicts*. In this example, the conflict percentage is falsely high because two non-conflicting jars have the same version number but different yet similar names.

~~~
Conflict%: 89
  file:/home/rick/.m2/repository/org/glassfish/jaxb/txw2/4.0.5/txw2-4.0.5.jar
  file:/home/rick/.m2/repository/org/glassfish/jaxb/xsom/4.0.5/xsom-4.0.5.jar
~~~

<!-- References -->

[1]: https://cxf.apache.org/cxf-xjc-plugin.html
[2]: https://cxf.apache.org/
[3]: https://github.com/patrodyne/hisrc-basicjaxb#
[4]: https://github.com/patrodyne/hisrc-basicjaxb/releases/download/2.2.1/hisrc-basicjaxb-sample-cxf-xjc-msi-2.2.1-mvn-src.zip
[5]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/plugins/src/main/java/org/jvnet/basicjaxb/plugin/hashcode/HashCodePlugin.java
[6]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/plugins/src/main/java/org/jvnet/basicjaxb/plugin/equals/EqualsPlugin.java
[7]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/plugins/src/main/java/org/jvnet/basicjaxb/plugin/tostring/ToStringPlugin.java
[8]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/plugins/src/main/java/org/jvnet/basicjaxb/plugin/fluentapi/FluentApiPlugin.java
[20]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-msi/project-pom.xml
[21]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-msi/OUTPUT.txt
[22]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-msi/README.md
[30]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-msi/bin/ReadJarManifest.java
[31]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-msi/bin/run.cmd
[32]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-msi/bin/run.sh
[40]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-msi/src/main/java/org/example/msi/Main.java
[41]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-msi/src/main/resources/msi.xjb
[42]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-msi/src/main/resources/msi.xsd
[50]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-msi/src/test/java/org/example/msi/AbstractMessageTest.java
[51]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-msi/src/test/java/org/example/msi/EqualsTest.java
[52]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-msi/src/test/java/org/example/msi/FluentAPITest.java
[53]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-msi/src/test/java/org/example/msi/ToStringTest.java
[54]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-msi/src/test/resources/jvmsystem.arguments
[55]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-msi/src/test/resources/jvmsystem.properties
[56]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-msi/src/test/resources/simplelogger.properties
[57]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-msi/src/test/samples/msi.xml


