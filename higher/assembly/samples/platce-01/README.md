# Sample: Plátce

This is a Maven project to demonstrate the unmarshalling of XML files with a similar wrapper to POJOs based on the content or the attribute _name_ in the an element named _method_. The goal is to avoid having to write different `Response` class for every _method_ element content. Assume, the XML schema is from different system and does not use an XML namespace.

**For example**, if the XML element _class_ has the three attributes (_name_, _version_, _revision_) and an _any_ element where the _any_ element can contain alternative (_method_) elements then the goal is to unmarshal each _method_ element using its _name_ attribute into a matching Java type. A _method_ element with _name_="platce-info" should bind to a Java type like `MethodXX1` and another with _name_="platce-data" should bind to a different type like `MethodXX2`.

Further, the alternative types `MethodXX1`, `MethodXX2`, etc. may share common elements like (_status_, _error_msg_) that can be inherited from a `MethodBase` type.

For this example, assume the XML element named _class_ is bound to a Java type named `VeraWSMethod`.

**Sample "response" instance:**
~~~
<?xml version="1.0" encoding="UTF-8"?>
<response id="085845">
    <class name="vera-pd" version="1.25" revision="1">
        <method name="platce-info">
            <status>0</status>
            <error_msg/>
            ... method content ...
        </method>
    </class>
</response>
~~~

### Execution

This sample is a stand-alone Maven project. You can run the test and/or application using:

~~~
mvn -Ptest clean test
mvn -Pexec compile exec:java -Dexec.args="src/test/samples/response01.xml"
~~~

The [output][22] shows the test results.

### Problem ( from [StackOverflow](https://stackoverflow.com/questions/77479159/) )

+ Unmarshalling multiple XMLs with similar wrapper. Is something like that possible?

### Solution

Yes, it is possible to unmarshal XML instances that include varying content distinguished by a given attribute having different values.

Here is a standalone Maven demonstration [project][21] and [zip][20] that uses these JAXB features to provide a solution:

+ `@XmlJavaTypeAdapter(MethodXmlAdapter.class)` - a JAXB annotation to specify a class to provide custom _unmarshaller_ and _marshaller_ methods.
+ `MethodXmlAdapter.class` - receives a [DOM][10] [`Element`][11] from JAXB when unmarshalling the _method_ element. The `Element` instance is used to read the distinguishing name as a DOM attribute. When marshaling, JAXB provides a `MethodXXN` instance to be marshaled, in the adapter, to back to a DOM `Element` representation.

The key points for unmarshalling is the distinguishing attribute is programmatically accessible and the DOM element can be unmarshaled to the desired Java type.

[Context.mainUnmarshalByName(...)][36]
~~~
public static Object mainUnmarshalByName(Element element)
{
    Object value = null;
    String name = element.getAttribute("name");
    switch (name)
    {
        case "platce-info":
            value = mainUnmarshal(element, METHODXX1_CLASS);
            break;
        case "platce-data":
            value = mainUnmarshal(element, METHODXX2_CLASS);
            break;
        default:
            value = element;
            break;
    }
    return value;
}
~~~

The key point for marshalling is the different `MethodXXN` instances can be marshaled to the one _method_ element using a declared [QName][12] with or without a namespace.

[Context.mainWrapAsMethod(...)][36]
~~~
@SuppressWarnings({ "rawtypes", "unchecked" })
public static Object mainWrapAsMethod(Object value)
{
    Object wrap = null;
    if ( value != null )
    {
        if ( isJAXBElement(value) )
            value = ((JAXBElement) value).getValue();

        if ( METHODXX1_CLASS.equals(value.getClass()) )
            wrap = new JAXBElement(METHOD_QNAME, METHODXX1_CLASS, value);
        else if ( METHODXX2_CLASS.equals(value.getClass()) )
            wrap = new JAXBElement(METHOD_QNAME, METHODXX2_CLASS, value);
    }
    return wrap;
}
~~~

#### Implementation

The [sample project][21] generates the JAXB classes using the [HiSrc HigherJAXB][2] Maven plugin and XJC plugins from [HiSrc BasicJAXB][1]. The XML Schema defines the `MethodXXN` types as sub-types of `MethodBase` which contains common attributes and elements.

**Classes generated from [platce.xsd][31] by XJC**
~~~
org
└── example
    └── platce
        ├── MethodBase.java
        ├── MethodXX1.java
        ├── MethodXX2.java
        ├── ObjectFactory.java
        ├── Response.java
        └── VeraWSClass.java
~~~

The [platce.xsd][31] schema defines the `VeraWSClass` type to hold the _any_ element; but, the [HiSrc HigherJAXB][2] Maven plugin uses the [HiSrc BasicJAXB][1] customization XJC plugin and the [HiSrc HyperJAXB Annox][3] annotation XJC plugin to add the '@XmlAnyElement' and '@XmlJavaTypeAdapter' annotation using a separate configuration file: [VeraWSClass.any.xml][30].

#### Execution

For the demo, you can run the test and/or application using:

~~~
mvn -Ptest clean test
mvn -Pexec compile exec:java -Dexec.args="src/test/samples/response01.xml"
mvn -Pexec compile exec:java -Dexec.args="src/test/samples/response02.xml"
~~~

<!-- References -->

[1]: https://github.com/patrodyne/hisrc-basicjaxb#readme
[2]: https://github.com/patrodyne/hisrc-higherjaxb#readme
[3]: https://github.com/patrodyne/hisrc-hyperjaxb-annox#readme
[10]: https://www.w3.org/TR/WD-DOM/introduction.html
[11]: https://www.w3.org/2003/01/dom2-javadoc/org/w3c/dom/Element.html
[12]: https://docs.oracle.com/en/java/javase/17/docs/api/java.xml/javax/xml/namespace/QName.html
[20]: https://github.com/patrodyne/hisrc-basicjaxb/releases/download/2.1.1/hisrc-basicjaxb-sample-platce-2.1.1-mvn-src.zip
[21]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/platce-01/README.md
[22]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/platce-01/OUTPUT.txt
[23]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/platce-01/project-pom.xml
[24]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/platce-01/bin/run.sh
[25]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/platce-01/bin/run.cmd
[30]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/platce-01/src/main/resources/org/example/platce/VeraWSClass.any.xml
[31]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/platce-01/src/main/resources/platce.xsd
[32]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/platce-01/src/main/resources/platce.xjb
[33]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/platce-01/src/main/java/org/example/platce/Demo.java
[34]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/platce-01/src/main/java/org/example/platce/util/DOMUtils.java
[35]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/platce-01/src/main/java/org/example/platce/util/XmlTypeUtils.java
[36]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/platce-01/src/main/java/org/example/platce/Context.java
[37]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/platce-01/src/main/java/org/example/platce/MethodXmlAdapter.java
[41]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/platce-01/src/test/samples/response01.xml
[42]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/platce-01/src/test/samples/response02.xml
[43]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/platce-01/src/test/resources/jvmsystem.arguments
[44]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/platce-01/src/test/resources/jvmsystem.properties
[45]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/platce-01/src/test/resources/simplelogger.properties
[46]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/higher/assembly/samples/platce-01/src/test/java/org/example/platce/ContextTest.java
