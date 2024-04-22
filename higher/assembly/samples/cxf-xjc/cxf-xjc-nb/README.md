# Sample: cxf-xjc-nb

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

### Problem ( from [StackOverflow](https://stackoverflow.com/questions/78334725/) )

+ How to configure the Maven [cxf-xjc-plugin][1] to include XJC plugins from [HiSrc BasicJAXB Plugins][3]?
+ How can the name of the generated files be changes to a custom name?
    + When using the Maven `cxf-xjc-plugin` to generate Java classes out of XSD schema definitions and configuring it with two `<xsdOption>` blocks to create Java files of the different XML schemas into two different Java packages. The generated Java classes and factories have the same name (e.g. `ObjectFactory.java`). 

### Solution

This [download (zip)][4] is a stand-alone Maven project to demonstrate the configuration of the Maven [cxf-xjc-plugin][1] from [Apache CXF][2] together with the XJC [HiSrc BasicJAXB Plugins][3]. Specifically,

+ it configures the [HiSrc BasicJAXB FluentApiPlugin][5] to show how two `ObjectFactory` instances from different Java packages can be conveniently used in the same code block.
+ it includes [two XML schemas (A and B) with binding files][41] and a global binding file to customize the JAXB/XJC generated class names.

> **Note:** After extracting the download to your local folder, you can run the tests and/or the demo using:

~~~
mvn -Ptest clean test
mvn -Pexec compile exec:java
~~~

Both XML schemas (A and B) define similar business models. Each models a `notebook`. The A schema models a *paper* notebook while the B schema models an *electronic* notebook. Both models have the same element names:

**Business Model**
~~~
notebook
    owner
    pageSpec
~~~

By default, XJC generates a JAXB class for each of the above, in separate Java packages:

**Without Customization: A**
~~~
org.example.nb.A.Notebook
org.example.nb.A.Owner
org.example.nb.A.PageSpec
org.example.nb.A.ObjectFactory
~~~

**Without Customization: B**
~~~
org.example.nb.B.Notebook
org.example.nb.B.Owner
org.example.nb.B.PageSpec
org.example.nb.B.ObjectFactory
~~~

Although the class names are the same, each class is qualified by its own Java package. And on the XML side, each document declares it's own namespace. When *unmarshalling*, JAXB uses the namespace from the XML documents ([nbA.xml][57] and [nbB.xml][58]) to determine the correct business model.

The problem is that references to the two models, in the same Java code block, require use of fully qualified class names.

*To solve that problem*, each XML schema is customized using a JAXB binding file invoking `jaxb:class` declarations to rename the generate Java classes.

The classes from the A schema are *customized* to include the suffix: `Analog`

**Extract from [nbA.xjb][42]**
~~~
<jaxb:bindings schemaLocation="nbA.xsd" node="/xs:schema">

    <jaxb:bindings node="//xs:element[@name='notebook']/xs:complexType">
        <anx:annotate>@jakarta.xml.bind.annotation.XmlRootElement(name = "notebook")</anx:annotate>
        <jaxb:class name="NotebookAnalog" />
    </jaxb:bindings>

    <jaxb:bindings node="//xs:complexType[@name='Owner']">
        <jaxb:class name="OwnerAnalog" />
    </jaxb:bindings>

    <jaxb:bindings node="//xs:complexType[@name='PageSpec']">
        <jaxb:class name="PageSpecAnalog" />
    </jaxb:bindings>

</jaxb:bindings>
~~~

The classes from the B schema are *customized* to include the suffix: `Digital`

**Extract from [nbB.xjb][44]**
~~~
<jaxb:bindings schemaLocation="nbB.xsd" node="/xs:schema">

    <jaxb:bindings node="//xs:element[@name='notebook']/xs:complexType">
        <anx:annotate>@jakarta.xml.bind.annotation.XmlRootElement(name = "notebook")</anx:annotate>
        <jaxb:class name="NotebookDigital" />
    </jaxb:bindings>

    <jaxb:bindings node="//xs:complexType[@name='Owner']">
        <jaxb:class name="OwnerDigital" />
    </jaxb:bindings>

    <jaxb:bindings node="//xs:complexType[@name='PageSpec']">
        <jaxb:class name="PageSpecDigital" />
    </jaxb:bindings>

</jaxb:bindings>
~~~

> Note: Why is there an `<anx:annotate ... >` extension on the `notebook` element? Because, XJC heuristics omit the normally present `@XmlRootElement` annotation when `jaxb:class` is used to change the `Notebook` class name. [HiSrc HyperJAXB Annox][7] is used to add the annotation back in.

The JAXB/XJC naming convention for `ObjectFactory` is less flexible. The JAXB reference implementation and the API both assume that this is a well known class name, qualified by its Java package name. For example, the JAXB RI registers the name as a [reserved class name][6].

The good news is that, an application only requires one instance of each `ObjectFactory`; thus, each can be declared with short names as static instances.

Example: [AbstractNotebookTest][50]
~~~
protected static final org.example.nb.A.ObjectFactory OFA = new org.example.nb.A.ObjectFactory();
protected static final org.example.nb.B.ObjectFactory OFB = new org.example.nb.B.ObjectFactory();
~~~

Then the `OFA` and `OFB` instances can be used conveniently, in the same code block, as demonstrated here...

[FluentAPITest][52]
~~~
final Object object = getUnmarshaller().unmarshal(sample);
if ( object instanceof NotebookAnalog )
{
    NotebookAnalog nba1 = (NotebookAnalog) object;
    NotebookAnalog nba2 = OFA.createNotebookAnalog()
        .useTitle("PaperBook")
        .useOwner(OFA.createOwnerAnalog()
            .useFirstname("Paul")
            .useLastname("McCartney"))
        .usePageSpec(OFA.createPageSpecAnalog()
            .useLinesPerPage(new BigInteger("66"))
            .usePageCount(new BigInteger("100")));

    getLogger().debug("NBA1: {}", nba1);
    getLogger().debug("NBA2: {}", nba2);

    assertEquals(nba1, nba2, "Unmarshaled and Fluent NBAs are equal.");
}
else if ( object instanceof NotebookDigital )
{
    NotebookDigital nbb1 = (NotebookDigital) object;
    NotebookDigital nbb2 = OFB.createNotebookDigital()
        .useTitle("EBook")
        .useOwner(OFB.createOwnerDigital()
            .useFirstname("John")
            .useLastname("Lennon"))
        .usePageSpec(OFB.createPageSpecDigital()
            .useKbPerPage(new BigInteger("512"))
            .usePageCount(new BigInteger("1024")));

    getLogger().debug("NBB1: {}", nbb1);
    getLogger().debug("NBB2: {}", nbb2);

    assertEquals(nbb1, nbb2, "Unmarshaled and Fluent NBBs are equal.");
}
~~~

**POM Configuration**

The POM is configured to use two <xsdOption> blocks for each schema: A and B.

**Extract from [pom.xml][22]**
~~~
...
<!-- mvn cxf-xjc:help -Ddetail=true -->
<!-- mvn cxf-xjc::generate -->
<plugin>
    <groupId>org.apache.cxf</groupId>
    <artifactId>cxf-xjc-plugin</artifactId>
    <version>${cxf-xjc-plugin.version}</version>
    <executions>
        <execution>
            <id>generate-sources</id>
            <phase>generate-sources</phase>
            <goals>
                <goal>xsdtojava</goal>
            </goals>
            <configuration>
                <fork>false</fork>
                <sourceRoot>${basedir}/target/generated-sources/xjc/</sourceRoot>
                <extensions>
                    <extension>org.patrodyne.jvnet:hisrc-basicjaxb-plugins:2.2.1</extension>
                    <extension>org.patrodyne.jvnet:hisrc-hyperjaxb-annox-plugin:2.2.1</extension>
                </extensions>
                <xsdOptions>

                    <xsdOption>
                        <bindingFiles>
                            <bindingFile>${basedir}/src/main/resources/nb.xjb</bindingFile>
                            <bindingFile>${basedir}/src/main/resources/nbA.xjb</bindingFile>
                        </bindingFiles>
                        <packagename>org.example.nb.A</packagename>
                        <xsd>${basedir}/src/main/resources/nbA.xsd</xsd>
                        <extension>true</extension>
                        <extensionArgs>
                            ...
                            <extensionArg>-XfluentAPI</extensionArg>
                            <extensionArg>-XfluentAPI-fluentMethodPrefix=use</extensionArg>
                        </extensionArgs>
                    </xsdOption>

                    <xsdOption>
                        <bindingFiles>
                            <bindingFile>${basedir}/src/main/resources/nb.xjb</bindingFile>
                            <bindingFile>${basedir}/src/main/resources/nbB.xjb</bindingFile>
                        </bindingFiles>
                        <packagename>org.example.nb.B</packagename>
                        <xsd>${basedir}/src/main/resources/nbB.xsd</xsd>
                        <extension>true</extension>
                        <extensionArgs>
                            ...
                            <extensionArg>-XfluentAPI</extensionArg>
                            <extensionArg>-XfluentAPI-fluentMethodPrefix=use</extensionArg>
                        </extensionArgs>
                    </xsdOption>

                </xsdOptions>
            </configuration>
        </execution>
    </executions>
    ... (dependencies)
</plugin>
...
~~~

> **Note:** The [cxf-xjc-plugin][1] provides `extensionArgs` to activate XJC plugins from an extension library like [HiSrc BasicJAXB Plugins][3]. It is list of additional arguments passed to XJC. (ex: `-XfluentAPI`).

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
[4]: https://github.com/patrodyne/hisrc-basicjaxb/releases/download/2.2.1/hisrc-basicjaxb-sample-cxf-xjc-nb-2.2.1-mvn-src.zip
[5]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/plugins/src/main/java/org/jvnet/basicjaxb/plugin/fluentapi/FluentApiPlugin.java
[6]: https://github.com/eclipse-ee4j/jaxb-ri/blob/b7d1ff7a13cecfaadde733387216b5cad09cc5b5/jaxb-ri/xjc/src/main/java/com/sun/tools/xjc/reader/xmlschema/ClassSelector.java#L351
[7]: https://github.com/patrodyne/hisrc-hyperjaxb-annox#readme
[20]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-nb/README.md
[21]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-nb/OUTPUT.txt
[22]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-nb/project-pom.xml
[30]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-nb/bin/ReadJarManifest.java
[31]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-nb/bin/run.cmd
[32]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-nb/bin/run.sh
[40]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-nb/src/main/java/org/example/nb/Main.java
[41]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-nb/src/main/resources/
[42]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-nb/src/main/resources/nbA.xjb
[43]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-nb/src/main/resources/nbA.xsd
[44]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-nb/src/main/resources/nbB.xjb
[46]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-nb/src/main/resources/nbB.xsd
[46]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-nb/src/main/resources/nb.xjb
[50]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-nb/src/test/java/org/example/nb/AbstractNotebookTest.java
[51]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-nb/src/test/java/org/example/nb/EqualsTest.java
[52]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-nb/src/test/java/org/example/nb/FluentAPITest.java
[53]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-nb/src/test/java/org/example/nb/ToStringTest.java
[54]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-nb/src/test/resources/jvmsystem.arguments
[55]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-nb/src/test/resources/jvmsystem.properties
[56]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-nb/src/test/resources/simplelogger.properties
[57]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-nb/src/test/samples/nbA.xml
[58]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/cxf-xjc-nb/src/test/samples/nbB.xml
