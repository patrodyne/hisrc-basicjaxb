# JSR 295 Beans Binding Hits 1.0

From InfoQ: [Beans-Bindings, Sep 2007](https://www.infoq.com/news/2007/09/beans-binding/)

by Scott Delap<br>Sep 24, 2007

---

Shannon Hickey of Sun recently announced 1.0 of the Beans Binding Framework. This is the reference implementation of JSR 295 that seeks to:

> ...define an API that greatly simplifies connecting a pair of Java Beans properties to keep them in sync. The connection will be configurable: type conversion and validation operations may be applied before updating a property.

Shannon states that work on the JSR will still continue however and may result in API changes. Version 1.0 does however represent a major re-architecture of the Beans Binding API with the following highlights:

+ The concept of a property has been factored out into an abstract `Property` class, with two concrete implementations of interest: `BeanProperty` and `ELProperty`.
+ `Binding` is now an abstract class representing a binding between two `Property` instances (typically associated with two objects).
+ `Binding` with automatic syncing is implemented by a new concrete `AutoBinding` sub-class.
+ Bindings to complex Swing components (such as `JTable`, `JList` and `JComboBox`) are now handled by custom `Binding` sub-classes.
+ The synthetic Swing properties that offer multiple possible behaviors are now exposed via multiple versions of the property. For example:
    + "text", `text_ON_FOCUS_LOST` and `text_ON_ACTION_OR_FOCUS_LOST` for `JTextField`
    + "selectedElement" and `selectedElement_IGNORE_ADJUSTING` for `JList`and `JTable`.
+ Everything has been repackaged into org.jdesktop packages.

Basic API for the framework is as follows:

~~~
// Bind Duke's first name to the text property of a Swing JTextField
   BeanProperty textP = BeanProperty.create("text");
   Binding binding =
      Bindings.createAutoBinding(READ_WRITE, duke, firstP, textfield, textP);
   binding.bind();

// Bind Duke's mother's first name to the text property of a Swing JTextField,
// specifying that the JTextField's text property only reports change
// (thereby updating the source of the `READ_WRITE` binding) on focus lost
   BeanProperty textP = BeanProperty.create(`text_ON_FOCUS_LOST`);
   Binding binding =
      Bindings.createAutoBinding(READ_WRITE, duke, motherFirstP, textfield, textP);
   binding.bind();
~~~

Beans Binding arrives in a Swing space that has used projects such as JGoodies Binding in the past. There is also the JFace Data Binding from the Eclipse Foundation. It provides core implementations for SWT, JFace and JavaBeans. However, the framework has been written with future expansion for API's like Swing and EMF.
