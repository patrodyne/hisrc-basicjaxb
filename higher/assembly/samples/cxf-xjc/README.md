# Sample: cxf-xjc

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

### Problem ( from [GitHub HiSrc BasicJAXB Issue #19](https://github.com/patrodyne/hisrc-basicjaxb/issues/19) )

+ How to configure the Maven [cxf-xjc-plugin][1] to include XJC plugins from [HiSrc BasicJAXB Plugins][3].
+ How to configure the [HiSrc BasicJAXB FluentApiPlugin][5] to activate with an optional parameter to set the *prefix* for the generated fluent methods.

### Solution

This [download (zip)][4] is a stand-alone Maven project to demonstrate the configuration of the Maven [cxf-xjc-plugin][1] from [Apache CXF][2] together with the XJC [HiSrc BasicJAXB Plugins][3]. Specifically, it configures the [HiSrc BasicJAXB FluentApiPlugin][5] to activate with an optional parameter to set the *prefix* for the generated fluent methods.

The [cxf-xjc-plugin][1] provides `extensionArgs` to activate XJC plugins from an extension library like [HiSrc BasicJAXB Plugins][3]. It is list of additional arguments passed to XJC. (ex: `-XfluentAPI`). These arguments are processed by the [com.sun.tools.xjc.Driver][6].

When XJC calls a plugin in the [HiSrc BasicJAXB Plugins][3] library an additional scan of the `extensionArgs` is executed to look for plugin specific parameters like this.

~~~
-X-XfluentAPI-fluentMethodPrefix=use
~~~

The additional scan is performed by [AbstractParameterizablePlugin][7] and is specific to `org.jvnet.basicjaxb.plugin`.

**Extract from pom.xml**
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
                </extensions>
                <xsdOptions>
                    <xsdOption>
                        <xsd>${basedir}/src/main/resources/po.xsd</xsd>
                        <bindingFile>${basedir}/src/main/resources/po.xjb</bindingFile>
                        <extensionArgs>
                            <extensionArg>-no-header</extensionArg>
                            <extensionArg>-XfixedValue</extensionArg>
                            <extensionArg>-XhashCode</extensionArg>
                            <extensionArg>-Xequals</extensionArg>
                            <extensionArg>-XtoString</extensionArg>
                            <extensionArg>-Xcopyable</extensionArg>
                            <extensionArg>-XfluentAPI</extensionArg>
                            <extensionArg>-XfluentAPI-fluentMethodPrefix=use</extensionArg>
                        </extensionArgs>
                    </xsdOption>
                </xsdOptions>
            </configuration>
        </execution>
    </executions>
    <!--
        Below, declare newer dependencies used by XJC extensions
        (i.e. hisrc-basicjaxb-plugins) that need to override
        older versions used by cxf-xjc-plugin.
        Hint: To review the cxf-xjc-plugin Manifest for jar conflicts use:
            mvn -Dorg.slf4j.simpleLogger.log.org.apache.cxf.maven_plugin.XSDToJavaMojo=DEBUG -Ptest clean test
    -->
    <dependencies>
        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>${commons-io.version}</version>
        </dependency>
        <dependency>
            <groupId>jakarta.xml.bind</groupId>
            <artifactId>jakarta.xml.bind-api</artifactId>
            <version>${jaxb-api.version}</version>
        </dependency>
        <dependency>
            <groupId>org.glassfish.jaxb</groupId>
            <artifactId>jaxb-runtime</artifactId>
            <version>${jaxb-imp.version}</version>
        </dependency>
        <dependency>
            <groupId>org.glassfish.jaxb</groupId>
            <artifactId>jaxb-xjc</artifactId>
            <version>${jaxb-imp.version}</version>
        </dependency>
        <dependency>
            <groupId>org.glassfish.jaxb</groupId>
            <artifactId>codemodel</artifactId>
            <version>${jaxb-imp.version}</version>
        </dependency>
        <dependency>
            <groupId>org.glassfish.jaxb</groupId>
            <artifactId>xsom</artifactId>
            <version>${jaxb-imp.version}</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>${slf4j.version}</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-simple</artifactId>
            <version>${slf4j.version}</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>jcl-over-slf4j</artifactId>
            <version>${slf4j.version}</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>jul-to-slf4j</artifactId>
            <version>${slf4j.version}</version>
        </dependency>
    </dependencies>
</plugin>
...
~~~

#### JAR Conflicts

The `cxf-xjc-plugin` above explicitly declares several dependencies to resolve JAR conflicts between the CXF product and the HiSrc product. Because these products are produced by independent teams, some version conflicts are likely. The [download (zip)][4] contains a tool, named [ReadJarManifest.java][32] to identify and resolve conflicts. The conflicts have been resolved in the `download` sample.

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

<!-- References -->

[1]: https://cxf.apache.org/cxf-xjc-plugin.html
[2]: https://cxf.apache.org/
[3]: https://github.com/patrodyne/hisrc-basicjaxb#
[4]: https://github.com/patrodyne/hisrc-basicjaxb/releases/download/2.1.1/hisrc-basicjaxb-sample-cxf-xjc-po-2.2.1-mvn-src.zip
[5]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/plugins/src/main/java/org/jvnet/basicjaxb/plugin/fluentapi/FluentApiPlugin.java
[6]: https://javadoc.io/doc/com.sun.xml.bind/jaxb-xjc/latest/com.sun.tools.xjc/com/sun/tools/xjc/Driver.html
[7]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/tools/src/main/java/org/jvnet/basicjaxb/plugin/AbstractParameterizablePlugin.java
[20]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/README.md
[21]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/OUTPUT.txt
[22]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/pom.xml
[23]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/project-pom.xml
[30]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/bin/run.sh
[31]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/bin/run.cmd
[32]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/bin/ReadJarManifest.java
[40]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/src/main/resources/po.xsd
[41]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/src/main/resources/po.xjb
[42]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/src/main/java/org/example/po/Main.java
[50]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/src/test/samples/po.xml
[51]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/src/test/resources/jvmsystem.arguments
[52]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/src/test/resources/jvmsystem.properties
[53]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/src/test/resources/simplelogger.properties
[54]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/src/test/java/org/example/po/EqualsTest.java
[55]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/src/test/java/org/example/po/CopyableTest.java
[56]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/src/test/java/org/example/po/ToStringTest.java
[57]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/cxf-xjc/src/test/java/org/example/po/FluentAPITest.java
