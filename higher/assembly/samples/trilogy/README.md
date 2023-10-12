# Sample: Trilogy

This is a Maven project to demonstrate the unmarshalling of XML files using an XML Schema to generate the **JAXB** classes. When _local scoping_ is set to _toplevel_ as a _global binding_, this demo shows how to avoid Java class name duplication by configuring distinct names in the bindings file. Further, it includes examples of XJC plugins to add custom `hashCode`, `equals`, `toString` and implementations to each generated JAXB class.

## Execution

This is a stand-alone Maven project. You can run the test using:

~~~
mvn -Ptest clean test
mvn -Pexec compile exec:java
~~~

The [output][2] shows the test results.

### Problem ( from [StackOverflow](https://stackoverflow.com/questions/77276719/) )

+ How to avoid JAXB generation of duplicate classes from an XSD.

### Solution

To avoid JAXB generation of duplicate classes from an XSD, you can define distinct names for the generated classes as either a local or external binding.

~~~
<jaxb:bindings node="XPATH">
    <jaxb:class name="YourDistinctName"/>
</jaxb:bindings>
~~~

One cause of Java class name duplication is the use of _toplevel_ scoping in the global bindings.

> **Note:** The _localScoping_ attribute can have the value of either `nested` or `toplevel`. This attribute describes the JAXB binding of nested XML schema component to either a nested schema-derived JAXB class or a toplevel schema-derived JAXB class. To avoid naming collisions between nested components, the default value for this attribute is nested. A developer can customize _localScoping_ to _toplevel_ when schema components nest too deeply or an application would prefer to not work with nested classes or when using JPA (JPA section 2.1, entities must be top-level classes).

For example, download this demo ([zip][1]) and consider its XML schema [trilogy.xsd][9] that models a `Trilogy` of books. It defines a TOC on the root `trilogy` element and another TOC for each `book`. The schema defines each TOC as an anonymous type and each TOC serves a different purpose. The first TOC describes the three book titles but the second TOC is a list of chapters in each book. Both have the same element name `<toc>...</toc>`, as shown in this XML instance, [trilogy.xml][11], but need distinct Java class definitions.

When the schema is customized to use `<jaxb:globalBindings localScoping=”toplevel”/>`, then JAXB attempts to generate the two types based on the one element name which leads to a name conflict.

To resolve the Java class name duplication, the demo uses a tool from the [HiSrc BasicJAXB][37] project to scan the XSD and output these suggested bindings: [trilogy.xjb][8]. For instance, here's the binding for the book TOC:

~~~
<jaxb:bindings node="//xs:element[@name='trilogy']/xs:complexType/xs:sequence/xs:element[@name='book']/xs:complexType/xs:sequence/xs:element[@name='toc']/xs:complexType">
    <jaxb:class name="TrilogyBookTocType"/>
</jaxb:bindings>
~~~

The binding selects the target node using an XPATH and declares the Java class name to be `TrilogyBookTocType`.

The demo provides a bash script to run the tool but it can be run manually like this...

~~~
java -cp \
    $HOME/.m2/repository/org/patrodyne/jvnet/hisrc-basicjaxb-tools/2.1.1/hisrc-basicjaxb-tools-2.1.1.jar \
    org.jvnet.basicjaxb.util.CreateToplevelXJBindings \
    --nested src/main/resources/trilogy.xsd
~~~

The tool sends it output to `stdout` where you can copy/paste it to your binding file or schema.

#### Execution

The demo is a stand-alone Maven project. You can run the test using:

~~~
mvn -Ptest clean test
mvn -Pexec compile exec:java
~~~

##### Approach

The [hisrc-higherjaxb-maven-plugin][38] is configured to generate **JAXB** classes using the provided [trilogy.xsd][9] schema and binding file [trilogy.xjb][8]. The schema provides the namespace `"http://org.example/trilogy"` which **JAXB** uses to create the Java `package` name using its own naming convention.

As an option, a more advanced implementation of Java's built-in `Object` methods are generated using these **XJC** [hisrc-basicjaxb-plugins][37]. In particular, the sample project uses the `toString` plugin to display *human-readable* representations of the unmarshaled `Trilogy` objects.

**hisrc-basicjaxb-plugins**
~~~
<args>
    <arg>-no-header</arg>
    <arg>-XhashCode</arg>
    <arg>-Xequals</arg>
    <arg>-XtoString</arg>
</args>
~~~

> *Note:* When the **XJC** [hisrc-basicjaxb-plugins][37] are used, the [hisrc-basicjaxb-runtime][39] dependency is required on the run-time class path.

##### Testing

The JUnit test class, [TrilogyTest.java][15], scans for the sample files and invokes the method `checkSample(File sample)` to provide each file to the tester. For this project, a `JAXBContext` is created and each file in the [samples][11] path is *unmarshaled* to a `Trilogy` object. When successful, each object is *marshaled* for logging and your [review][2].

##### Demonstration

A Java standard engine application with a `main(...)` method is at [`org.example.trilogy.Main`][10]. This application is executed using:

~~~
mvn -Pexec compile exec:java -Dexec.args="src/test/samples/trilogy.xml"
~~~

<!-- References -->

[1]: https://github.com/patrodyne/hisrc-basicjaxb/releases/download/2.1.1/hisrc-basicjaxb-sample-trilogy-2.1.1-mvn-src.zip
[2]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/trilogy/OUTPUT.txt
[3]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/trilogy/project-pom.xml
[4]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/trilogy/bin/CreateToplevelXJBindings.cmd
[5]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/trilogy/bin/CreateToplevelXJBindings.sh
[6]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/trilogy/bin/run.sh
[7]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/trilogy/bin/run.cmd
[8]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/trilogy/src/main/resources/trilogy.xjb
[9]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/trilogy/src/main/resources/trilogy.xsd
[10]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/trilogy/src/main/java/org/example/trilogy/Main.java
[11]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/trilogy/src/test/samples/trilogy.xml
[12]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/trilogy/src/test/resources/jvmsystem.arguments
[13]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/trilogy/src/test/resources/jvmsystem.properties
[14]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/trilogy/src/test/resources/simplelogger.properties
[15]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/trilogy/src/test/java/org/example/trilogy/TrilogyTest.java
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
