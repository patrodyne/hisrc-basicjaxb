# Sample: Purchase Order

This is a Maven project to demonstrate the unmarshalling of XML files using an XML Schema to generate the **JAXB** classes. When _local scoping_ is set to _toplevel_ as a _global binding_, this demo shows how to avoid Java class name duplication by configuring distinct names in the bindings file.

It includes examples of XJC plugins to add custom `hashCode`, `equals`, `toString` and implementations to each generated JAXB class.

Further, it includes examples of the XJC inheritance and simplify plugins.

## Execution

This is a stand-alone Maven project. You can run the test using:

~~~
mvn -Ptest clean test
mvn -Pexec compile exec:java
~~~
