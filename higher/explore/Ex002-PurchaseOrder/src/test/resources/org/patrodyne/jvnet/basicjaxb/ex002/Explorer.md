# HiSrc BasicJAXB Ex002 PurchaseOrder

Let's [explore][1] the three most commonly used plug-ins in the _HiSrc BasicJAXB_ framework: `hashCode`, `equals` and `toString`. These plug-ins extend the [XJC][7] API to implement their eponymous Object methods. Unlike their default Object methods, these overrides incorporate the declared field values from the schema definition. To make it interesting, we will use the _Purchase Order_ model to define the XML Schema and generate classes.

> **Hint:** As you read through this exploration, feel free to edit your copy of "Explore.java" to experiment with the conepts being presented.

## Purchase Order Schema

A purchase order (PO) is a commercial document issued by a buyer to a seller, indicating types, quantities, and agreed prices for products or services.

In our [model][2], a purchase order has billing/shipping addresses, carrier/payment preferences and item lines. The items have product references, quantity and a shipping date. Products are grouped separately into catalogues. Our schema includes a concept for _Stageable_ to represent the current processing stage of an entity: open, hold, active, closed or canceled.

For example, when the stage of an item is _open_ then its quantity or other values can be changed.

Our schema includes batch elements for the key entities: `AddressBatch`, `CatalogueBatch` and `PurchaseOrderBatch`. These root elements can be used to marshal and unmarshal _lists_ of addresses, catalogues and/or purchase orders.

This project includes sample [XML files][3] to populate our PO model.

> **Disclaimer:** All names, places and addresses contained in this project are fictitious. No identification with actual persons (living or deceased), places, buildings, and products is intended or should be inferred.

## Generate the XML Schema

This project uses Maven plug-ins to generate the Java classes from an [XML Schema][4]. Use the links below (or the menu bar above) to re-create the XML schema from the generated classes. The third link adds a validator to the unmarshaller that will check that the XML files are well-formed and properly populated. Also, the red/green flag in the tool bar toggles the schema validator between inactive (red) and active (green).

+ [Generate Xml Schema From String](!generateXmlSchemaFromString)
+ [Generate Xml Schema From DOM](!generateXmlSchemaFromDom)
+ [Generate Xml Schema Validator From DOM](!generateXmlSchemaValidatorFromDom)

Also, our schema is customized using an [XML Binding file][5] to define some global bindings and add Stageable / Batch support.

## Unmarshal Address, Catalogue and Purchase Order

Without using a schema validator, click these links to read the example files and unmarshal the XML into JAXB objects.

+ [Unmarshal Address Batch](!unmarshalAddressBatch)
+ [Unmarshal Catalogue Batch](!unmarshalCatalogueBatch)
+ [Unmarshal Purchase Order Batch](!unmarshalPurchaseOrderBatch)

If you activate the schema validator, you will get a surprise when unmarshalling the _Catalogue Batch_.

### Invalid attribute name
~~~
SAXParseException: ../CatalogueBatch.xml; lineNumber: 3; columnNumber: 63; 
Attribute 'batchTime' is not allowed to appear in element 'CatalogueBatch'.
~~~

The example file, as provided, contains an invalid attribute name. The attribute `batchTime` should be `BatchTime`. Edit the file and correct the attribute name then unmarshal it with the an active validator to verify your fix.

## Marshal Address, Catalogue and Purchase Order

After unmarshalling the example files, this _Explorer_ keeps a list of the populated JAXB objects. Click these links to marshal each batch and review the XML in the output pane on the right.

+ [Marshal Address Batch](!marshalAddressBatch)
+ [Marshal Catalogue Batch](!marshalCatalogueBatch)
+ [Marshal Purchase Order Batch](!marshalPurchaseOrderBatch)

### Experiments

+ From scratch, without unmarshalling,
	+ Attempts to marshal with validation OFF will return empty batches.
	+ Attempts to marshal with validation ON will throw a `BatchTime` is required exception.
+ Unmarshalling the invalid catalogue batch (i.e. validation OFF) then marshaling it with validation turned on will result with an validation exception because `BatchTime` is a required attribute.

## Maven / HiSrc JAXB Basic Plug-Ins

This project configures three HiSrc JAXB Basic plug-ins in the Maven [POM][6]. Here is a snippet from the POM:

~~~
<configuration>
    <args>
        ...
        <arg>-XhashCode</arg>
        <arg>-Xequals</arg>
        <arg>-XtoString</arg>
        ...
    </args>
    <plugins>
        <plugin>
            <groupId>org.patrodyne.jvnet</groupId>
            <artifactId>hisrc-basicjaxb</artifactId>
        </plugin>
    </plugins>
</configuration>
~~~

### Description

These three plug-ins use default 'strategy' classes. This design allows you to provide alternative strategies for custom solutions. We will leave the study of custom strategy classes to a future exploration. For this exploration and in most cases, the default strategies work well!

+ **XhashCode** - reflection-free, strategy-based 'hash code' implementation
+ **Xequals** - reflection-free, strategy-based 'equals' implementation
+ **XtoString** - reflection-free, strategy-based 'toString' implementation

## Compare Hash Codes

In Java, as much as is reasonably practical, the _hashCode_ method defined by the class `Object` returns distinct integers for distinct objects. This is typically implemented by converting the internal address of the object into an integer.

The HiSrc JAXB `XhashCode` plug-in generates an override of the Object level _hashCode_ method to differentiate objects by their field/property values instead of using the internal address of the object.

For example, this plug-in ensures that two `Address` objects with the same field/property values produce the same hash code even through their object identifiers are distinct.

This hash number is used by _hash table based_ collections like `Hashtable`, `HashSet` and `HashMap` to store objects in small containers called "buckets". Each bucket is associated with a hash code, and each bucket contains only objects having identical hash code.

After unmarshalling, click these links to compare the object versus the plug-in (entity) hash code values. 

+ [Compare Hash Codes Address Batch](!compareHashCodesAddressBatch)
+ [Compare Hash Codes Catalogue Batch](!compareHashCodesCatalogueBatch)
+ [Compare Hash Codes Purchase Order Batch](!compareHashCodesPurchaseOrderBatch)

## Compare Equals

The equals method for class `Object` implements the most discriminating possible equivalence relation on objects; that is, for any non-null reference values x and y, this method returns true if and only if x and y refer to the same object (x == y has the value true).

> **Note:** It is generally necessary to override the _hashCode_ method whenever the _equals_ method is overridden, so as to maintain the general contract for the _hashCode_ method, which states that equal objects must have equal hash codes.

After unmarshalling, click these links to compare the object versus the plug-in (entity) _equals_ values. 

+ [Compare Equality Address Batch](!compareEqualityAddressBatch)
+ [Compare Equality Catalogue Batch](!compareEqualityCatalogueBatch)
+ [Compare Equality Purchase Order Batch](!compareEqualityPurchaseOrderBatch)

For each of the above comparisons, our [Explorer][1] randomly selects an entity then iterates over the batch using the _equals_ method to locate the selection.

By default, the HiSrc JAXB `Xequals` plug-in uses `JAXBEqualsStrategy` to evaluate equality and `DefaultRootObjectLocator` to log results. Logging is configurable. The HiSrc framework uses [SLF4J][8] for its logging interface. The Maven [POM][6] for this project implements the logging interface using the `org.slf4j:slf4j-simple` dependency which provides the [SimpleLogger][9] implementation. You can edit this project's configuration at `src/test/resources/simplelogger.properties` or view the original at [simplelogger.properties][10].

The log level for the `JAXBEqualsStrategy` determines what is logged by the HiSrc JAXB `Xequals` plug-in.

### simplelogger.properties
~~~
org.slf4j.simpleLogger.log.org.jvnet.jaxb2_commons.lang.DefaultEqualsStrategy=TRACE
~~~

Set `JAXBEqualsStrategy` to `TRACE` to produce log messages when two entities _are not_ equal. Setting `JAXBEqualsStrategy` to `DEBUG`, `INFO`, `WARN`, `ERROR` or `OFF` produces no log output.

> **Important:** This mode should only be used for deep trouble-shooting or testing! 

## Compare toString

The _toString_ method in Java's `Object` class provides a simple representation of any object using its name and identifier. Generally, our JAXB entities represent rich data objects with fields/properties of interest to us. The HiSrc JAXB `XtoString` plug-in uses `JAXBToStringStrategy` to display these values.

The `XtoString` plug-in is configurable using the `simplelogger.properties` file, as mentioned earlier. There are three modes:

+ `INFO` - compact class, id, value and child size representation.
+ `DEBUG` - as INFO but expands size into child items.
+ `TRACE` - as DEBUG but uses full class name and includes field names.

### simplelogger.properties
~~~
org.slf4j.simpleLogger.log.org.jvnet.jaxb2_commons.lang.DefaultToStringStrategy=INFO
~~~

Each mode is, in fact, a log level for `JAXBToStringStrategy`. As provided, the log level is `INFO` for a compact representation from the _toString_ method. Edit the `simplelogger.properties` file and change this logger to `DEBUG` to expand child lists or to `TRACE` for the most verbose representation.

After unmarshalling, click of these links and/or edit the `simplelogger.properties` file to experiment with alternative `JAXBToStringStrategy` formats.

+ [Compare To String Address Batch](!compareToStringAddressBatch)
+ [Compare To String Catalogue Batch](!compareToStringCatalogueBatch)
+ [Compare To String Purchase Order Batch](!compareToStringPurchaseOrderBatch)

> **Hint:** In Linux, set `-Dsun.java2d.xrender=false` to fix bug viewing long lines in `JTextArea`.

## Roundtrip Equality

In this exercise, we will randomly select an entity from a batch and assign it to `sample1`. Next, we will marshal `sample1` into an XML string then unmarshal the string into `sample2`. The new `sample2` instance is a different object but having the same values as `sample1`, hopefully. Finally, we will use our generated _equals_ method to verify equality.

~~~
println("Sample1 vs Sample2: " + (sample1.equals(sample2) ? "EQUAL" : "UNEQUAL"));
~~~

After unmarshalling each batch (from the menu or earlier links), click on each experiment below to verify equality for a random sample:

+ [Roundtrip Equality Address Batch](!roundtripEqualityAddressBatch)
+ [Roundtrip Equality Catalogue Batch](!roundtripEqualityCatalogueBatch)
+ [Roundtrip Equality Purchase Order Batch](!roundtripEqualityPurchaseOrderBatch)

## Roundtrip Inequality

Let's introduce some chaos [亂][11] into our sampling! This experiment works the same as the previous section except that `sample2` is surrounded by a _chaos_ method. The _chaos_ method randomly picks one property to disrupt. If the property is complex or a list, the _chaos_ is delegated deeper until one primative field is selected for disruption.

After `sample2` is disrupted, we check for inequality. If `DefaultEqualsStrategy=TRACE` then you should see the location of the disruption in the error log console.

After unmarshalling each batch (from the menu or earlier links), click on each experiment below to verify inequality for a random sample and a randomly disrupted field:

+ [Roundtrip Inequality Address Batch](!roundtripInequalityAddressBatch)
+ [Roundtrip Inequality Catalogue Batch](!roundtripInequalityCatalogueBatch)
+ [Roundtrip Inequality Purchase Order Batch](!roundtripInequalityPurchaseOrderBatch)

## Bonus: XsimpleHashCode, XsimpleEquals, XsimpleToString

The HiSrc JAXB Basic framework provides an alternative to the strategy based plug-ins described above, known as the _simple_ plug-ins. These plug-ins generate in-line to code to implement the _hashCode_, _equals_ and _toString_ methods. This approach eliminates a runtime dependency and simplifies the design.

The price of simplification is elimination of the "not equals" locator, logger modes and using your own custom strategies. On the other hand, you can configure `XsimpleToString` to use a compact or expanded format.

> **Note:** The original strategy based plug-in provides run-time selection of the _toString_ method representation via the log level (mode). The _simple_ plug-ins provide a build-time configuration for the _toString_ method representation.

Change the Maven [POM][6] for your copy of this project to use the alternative _simple_ HiSrc JAXB Basic plug-ins. Rebuild this project and repeat your explorations!

~~~
<configuration>
    <args>
        ...
        <!--
        <arg>-XhashCode</arg>
        <arg>-Xequals</arg>
        <arg>-XtoString</arg>
        -->
        <arg>-XsimpleHashCode</arg>
        <arg>-XsimpleEquals</arg>
        <arg>-XsimpleToString</arg>
        <arg>-XsimpleToString-fullClassName=false</arg>
        <arg>-XsimpleToString-showFieldNames=false</arg>
        <arg>-XsimpleToString-showChildItems=false</arg>
        ...
    </args>
    <plugins>
        <plugin>
            <groupId>org.patrodyne.jvnet</groupId>
            <artifactId>hisrc-basicjaxb</artifactId>
        </plugin>
    </plugins>
</configuration>
~~~

## End of this Exploration

<!-- References -->

[1]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/explore/Ex002-PurchaseOrder/src/test/java/org/patrodyne/jvnet/basicjaxb/ex002/Explorer.java?ts=4
[2]: https://raw.githubusercontent.com/patrodyne/hisrc-basicjaxb/master/explore/Ex002-PurchaseOrder/src/main/resources/PurchaseOrder.svg
[3]: https://github.com/patrodyne/hisrc-basicjaxb/tree/master/explore/Ex002-PurchaseOrder/src/test/examples
[4]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/explore/Ex002-PurchaseOrder/src/main/resources/PurchaseOrder.xsd?ts=4
[5]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/explore/Ex002-PurchaseOrder/src/main/resources/PurchaseOrder.xjb?ts=4
[6]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/explore/Ex002-PurchaseOrder/project-pom.xml?ts=4
[7]: https://eclipse-ee4j.github.io/jaxb-ri/
[8]: https://www.slf4j.org/
[9]: https://www.slf4j.org/api/org/slf4j/simple/SimpleLogger.html
[10]: https://github.com/patrodyne/hisrc-basicjaxb/blob/master/explore/Ex002-PurchaseOrder/src/test/resources/simplelogger.properties
[11]: https://www.compart.com/en/unicode/U+4E82
