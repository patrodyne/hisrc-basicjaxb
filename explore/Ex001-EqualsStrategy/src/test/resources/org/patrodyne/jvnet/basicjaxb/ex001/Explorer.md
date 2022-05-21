# HiSrc BasicJAXB Ex001 EqualsStrategy

This project is the first exploration of the HiSrc BasicJAXB library. It includes a Swing application named _Explorer_ to demonstrate two features of the HiSrc BasicJAXB Runtime library: `JAXBHashCodeStrategy` and `JAXBEqualsStrategy`. This _Explorer_ application is designed to present a narrative lesson together with dynamic output for real-time experimentation. Feel free to modify the _Explorer.java_ source file to add or modify the action methods with your own investigative code. The `Explorer` class is an extension of `AbstractExplorer` which contains the more boring mechanics of this implementation. Feel free to create an `Explorer` class in your own projects to help explain the purpose of your work too.

> **About AbstractExplorer:** Projects create their own custom Explorer by extending `AbstractExplorer` and providing an HTML lesson page plus JMenuItem(s) to trigger exploratory code.

> An `AbstractExplorer` implementation (like the one you see here) displays three panels: an HTML lesson, a print console and an error console. The lesson file is read as a resource relative to the implementation (i.e. `Explorer`) class. Text is sent to the print console by calling `println(text)` and error messages are sent to the error console by calling `errorln(msg)`. Additionally, 'standard out' / 'standard error' streams are sent to their respective consoles.

## Object Method Strategies

The focus of the HiSrc framework is the automatic generation of Java classes from XML
Schema(s). The XJC compiler from JAXB does the actual code generation using plug-ins from the HiSrc framework. Then JAXB is used to unmarshal/marshal XML instances to/from Java objects. 

XJC generates POJO classes with the standard and simple Java `Object` methods:

+ `hashCode()` - numeric value used for object identification or indexing. 
+ `equals(obj)` - determines when and how to objects are equal.
+ `toString()` - textual representation of an object.

By default, these methods work at the object level. For example, two distinct `Employee` object instances are considered _not equal_ even when they contain the same employee values because they have different object identifiers. The _HiSrc BasicJAXB Runtime Library_ provides strategies to implement these and other `Object` methods that take all values into account.

## Explore HashCode and Equals

Let's [explore][1] two strategies provided by this library: `JAXBHashCodeStrategy` and `JAXBEqualsStrategy`. We'll look at the other strategies in future explorations.

> **Hint:** As we cover each topic, you can run the code by clicking on the links or using the menu bar above.

The HiSrc framework uses its Maven plugin(s) to generate Java classes from XML schema files.
The schema file for this project is in the `src/main/resources/Person.xsd` file. To keep the design simple, the schema declares `Person` type to be an XML element with an anonymous complex type and the `Person` type and declares only two sub-elements: `FirstName` and `LastName`. The POM for this project configures this Maven plug-in to generate the JAXB source code for the `Person` type. Also, the Maven plug-in is configured to include the `equals` and `hashCode` XJC plug-ins from our HiSrc framework.

~~~
<plugin>
    <groupId>org.patrodyne.jvnet</groupId>
    <artifactId>hisrc-higherjaxb-maven-plugin</artifactId>
    ...
        <configuration>
            <args>
                <arg>-no-header</arg>
                <arg>-Xequals</arg>
                <arg>-XhashCode</arg>
            </args>
            ...
        </configuration>
</plugin>
~~~

The plug-in generates a `Person` class that implements the HiSrc interfaces (`HashCode2`, `Equals2`) for the `JAXBHashCodeStrategy` and `JAXBEqualsStrategy`. It overrides the relevant `Object` methods with the custom strategies. This implementation introduces another HiSrc interface, named `ObjectLocator`.

>**Note:** By convention, when XJC parses a top-level element with a anonymous complex type, it will add the `@XmlRootElement` annotation to the generated class.

### ObjectLocator

The `ObjectLocator` interface extends `javax.xml.bind.ValidationEventLocator` used by JAXB to report XML schema (in)validation events. It does this by adding a path from the event back to the root object. HiSrc strategies use the `ObjectLocator` to trace the location of events like a _field not equal_ event. For example, `DefaultEqualsStrategy` delegates the event notification to [SLF4J][2], for logging. Applications can configure [SLF4J][2] to ignore all reporting or override the default tracing with their own custom event coding.

>**Hint:** The POM for this project declares the `org.slf4j:slf4j-simple` dependency to implement the [SLF4J][2] API. This implementation is configured in the `src/test/resources/simplelogger.properties` file. Event notification can be activated by setting the logger level to `...JAXBEqualsStrategy=TRACE`.

The locators are optional. `ObjectLocator` parameters can be null when invoking the HiSrc strategies. When not used, the strategies report a more general location message.

> Normally, applications configure [SLF4J][2] to ignore the `ObjectLocator` events. Applications can choose to report events, when needed, to debug unexpected inequality issues. They are most commonly used in unit tests to verify round trip conformance (_XML instance_ to _Java Object_ to _XML instance_).

### Regenerate Xml Schema

This example starts with a  `Person` element and anonymous type in the XML Schema then generates the Java class. We can use the `Person` class to create a JAXB context.

~~~
private JAXBContext createJAXBContext() throws JAXBException
{
    return JAXBContext.newInstance(Person.class);
}
~~~

The `JAXBContext` class provides the application's entry point to the JAXB API. It provides an abstraction for managing the XML/Java binding information necessary to implement the JAXB binding framework operations: _unmarshal_, _marshal_ and _validate_. For display, we use JAXB's `SchemaOutputResolver` to generate a textual representation in two ways:

+ [Regenerate Xml Schema From String](!generateXmlSchemaFromString)
+ [Regenerate Xml Schema From Dom](!generateXmlSchemaFromDom)

The first way (above) outputs the XML Schema to a `String` backed `SchemaOutputResolver`. The second way outputs the XML Schema to a `DOMSource` backed resolver. The output from both approaches are similar but the second way enables us to generate a _schema validator object_ that can be wired into the marshaller and unmarshaller objects.

+ [Generate Xml Schema Validator From Dom](!generateXmlSchemaValidatorFromDom)

> **Note:** This exploration does not wire in the _schema validator object_ automatically. This allows you explore the effect of unmarshalling an invalid schema, as described below. If you generate the validator, it will be active for the current session. Restart this application to clear the validator and explore the other actions below.

### Marshal Person Objects to XML

For this demo, `Person` is a JAXB annotated class with fields for first and last name. The demo is initialized with three instances: 

+ [Person 1, Arthur Dent](!marshalPerson1)
+ [Person 2, Arthur Dent](!marshalPerson2)
+ [Person 3, Ford Prefect](!marshalPerson3)

All three instances are distinct Java objects. The first two objects refer to "Arthur Dent" and the third object identifies "Ford Prefect".

### Compare Objects

The HiSrc framework provides an `ObjectLocator` and `HashCodeStrategy2` to generate a hash code that is based on the object values. In this case, the hash code and equality is based on the person's first and last name. 

#### Compare Hash Codes

Select [Compare Hash Codes](!compareHashCodes) to show the HiSrc hash and the System hash for the three `Person` instances. The HiSrc hash codes for Person 1 & 2 are the same because these two objects both identify "Arthur Dent" while the hash code for Person #3 is different from the others because that object identifies "Ford Prefect". However, the system hash codes for all three objects are unique because they are individual JVM objects.

Here's how the `Person` class uses the HiSrc interfaces for the `hashCode()` method. See [Explorer.java][1] for the field level details.

~~~
public int hashCode()
{
    ObjectLocator thisLocator = new DefaultRootObjectLocator(this);
    HashCodeStrategy2 strategy = JAXBHashCodeStrategy.getInstance();
    return this.hashCode(thisLocator, strategy);
}
~~~

#### Compare Equality

Select [Compare Equality](!compareEquality) to compare (A) `person1` with `person2` and (B) `person2` with `person3`.

Case (A) compares two individual objects for "Arthur Dent" and finds that they are equal.

Case (B) compares an "Arthur Dent" object with a "Ford Prefect" object and finds that they are _not_ equal. Further, case (B) provides a trace log to report the first non-equal field.

Here's how the `Person` class uses the HiSrc interfaces for the `equals(Object that)` method. See [Explorer.java][1] for the field level details.

> **Note:** The `ObjectLocator` parameters are optional; they can be null. When they are null, the field level locator utility will use basic location tracing. When the root level locators are used, the field level locators create a nested path that is used for trace level logging. HiSrc uses [SLF4J][2] as its logging framework and that framework is usually configured to skip trace level messaging.

~~~
public boolean equals(Object that)
{
    // Create locators for enhanced debugging.
    ObjectLocator thisLocator = new DefaultRootObjectLocator(this);
    ObjectLocator thatLocator = new DefaultRootObjectLocator(that);
    
    // Return deep object equality.
    JAXBEqualsStrategy strategy = new JAXBEqualsStrategy();
    return equals(thisLocator, thatLocator, that, strategy);
}
~~~

> **Important:** The HiSrc `JAXBEqualsStrategy` extends `DefaultEqualsStrategy` and both include logic to differentiate objects that use _default_ vs _set_ values. In JAXB, an effort is made to track how an element or attribute receives its value. If the XML Schema defines a _default_ value for an element or attribute and the XML instance does not set the value then the JAXB generated class will return the default value from the accessor while keeping the field value null. This may surprise a maintainer when reviewing the Java code but it is how JAXB is designed to work! This allows JAXB to detect when a property is a **set value** versus a **default value** and can help with round tripping.

#### Compare ToString

Select [Compare ToString](!compareToString) to compare `person1`, `person2` and `person3`.

### Roundtrip

In JAXB, a _round trip_ is an _xml-to-object-to-xml_ or an _object-to-xml-to-object_ sequence. Ideally, the initial XML and final XML instance will be the same. There are mechanisms, such as `JAXBElement`, that can be used to preserve the fine details and are often used. However, most projects do not require an exact reproduction of the XML and are satisfied with being `equal-by-value`.

#### Valid Roundtrip

The `JAXBEqualsStrategy` can be used to verify deep `equal-by-value`. Select [Roundtrip Valid](!roundtripValid) to marshal the `person1` object into its XML representation then unmarshal the XML back into a second object; finally, this test will use the object's equal method to verify equal by value.

#### Invalid Roundtrip

Select [Roundtrip Invalid](!roundtripInvalid) to create a bit of chaos. This exploration intentionally corrupts the XML by replacing the `lastName` tag with a random `babelFish` tag. Because of this, the corrupted XML will not have a `lastName` value but will have unknown tag from the XML Schema point of view.

If you have not selected _Generate Xml Schema Validator From Dom_ then JAXB will attempt to complete the roundtrip but the final XML will contain this bit of chaos:

~~~
<LastName>lastName</LastName>
~~~

To detect this chaos programatically, use [Generate Xml Schema Validator From Dom](!generateXmlSchemaValidatorFromDom) then execute [Roundtrip Invalid](!roundtripInvalid). This time, JAXB validation will throw an exception.

## End of this Exploration

<!-- References -->

[1]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/explore/Ex001-EqualsStrategy/src/test/java/org/patrodyne/jvnet/basicjaxb/ex001/Explorer.java?ts=4
[2]: https://www.slf4j.org/
