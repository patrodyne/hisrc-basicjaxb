# Data Binding in Java

[This article](https://www.artima.com/articles/data-binding-in-java) is sponsored by the Java Community Process.

## A Conversation with Shannon Hickey about the Beans Binding API, JSR 295

by Frank Sommers<br>January 10, 2008

---

Summary
-------

In this interview with [Artima](https://www.artima.com/), Shannon Hickey, spec lead for the Beans Binding API, JSR 295, discusses the challenges of Java data binding, and how the JSR 295 API simplifies that task.  

**Frank Sommers:** What problem does the Data Binding API solve?

**Shannon Hickey**: Data binding is about making connections between beans or objects, and about syncing up properties of two different objects.

Data binding is particularly interesting with Swing components, where you have application objects, and you want to bind them to user interface components. That way, the UI components can display the values of those application objects. Further, you can use the UI components to allow the user to set the values of the application objects.

Before the Beans Binding framework, a lot of boilerplate code had to be written to listen for changes of one property of an object, set a value of another object's property, and vice versa. The initial goal of Beans Binding is to simplify making connections between such properties and objects. There is an additional emphasis on Swing components, and especially on binding to complex Swing components, such as a `JList`, `JTable` and `JTree`.

**Frank Sommers**: What are the special technical challenges in the use-cases you mentioned? Couldn't you just use the JavaBeans properties and listener patterns to notify one object of changes in another object?

**Shannon Hickey**: You certainly can. For the simple case of connecting two properties of JavaBeans, you can write code to add a `PropertyChangeListener` to the first JavaBean, listen for changes, and then set the value on the second bean, and vice versa. You can use those two listeners to propagate the changes back and forth. That's simple with JavaBeans, although it's a lot easier with Beans Binding, where you can create an object with a strategy that dictates how the beans should sync up.

This gets more complex with some Swing components that don't behave as proper beans. For example, the `TextComponent` fires `DocumentChangeEvent`s when the value of the text changes. In that case, you need to add a `DocumentListener` to the document of a `TextComponent` to listen for changes. Likewise, `JComboBox` fires `ActionEvent`s, and `JList` fires `ListSelectionEvent`s. You need to remember all that boilerplate code when interacting with different components to figure out how you can connect them.

Even more complex is the case with something like `JList` or `JTable` when you work with the `Collections` classes. Let's say you have a `List` of `Item`s. You would have to create a custom model to set on your target component, and propagate the changes from the `List` to that model. With Beans Binding, you can create a binding between the `List` and a `JTable` or a `JList`, and have the `List` behind the scenes automatically create the model and listen for changes to display in the table.

**Frank Sommers**: How does Beans Binding reduce the boilerplate code developers have to write when connecting bean properties?

**Shannon Hickey**: Beans Binding raises the abstraction level for connecting bean properties.

The first thing we've done is create an abstract `Property` class that represents a way to get at a property on some source object. Beans Binding provides two implementations of `Property`: One is `BeanProperty`, which uses a simple property path syntax to indicate which property is being referenced. With this simple syntax, if you have a `Person` object with a `mother` property, and you wanted to create a `Property` that refers to the `mother`'s `firstName`, you could create a `BeanProperty` called `mother.firstName`. The other way to specify properties is via more complex expressions of a property, using the Java EL expression language. For that latter use-case, we define an `ELProperty` class.

As a result of that higher level of abstraction, there is a lot less boilerplate code for you to write. Suppose you want to bind the selected item of a `JList` to the text of a `TextField`. With Beans Binding, you would create a `Property` that describes the selected item, and another `Property` that represents the text in a `JTextField`. Then you would just create the binding between those two `Property` objects. There is no more figuring out what type of listener needs to be added to the `JList`, or what type of listener needs to be added to the `TextComponent`. You're using a common pattern, regardless of that you're binding.

In addition to raising the level of abstraction for your code, Beans Binding does more: It allows you to traverse paths along properties, and reference multiple methods on those paths. For instance, you can create a `BeanProperty` representing the first name of a person's mother. That `Property`, in effect, represents the call to `getMother().getFirstName()`. And that `Property` also listens to property changes along the path and updates its value any time any of the objects in the path change. If the person's mother property changes, or the mother's first name changes, the first name property value will automatically be updated.

In addition, the `Person` itself may be referenced from another bean. In that case, even changing the `Person` would update the property. For example, you may have a map of some keys to `Person` objects. When the value of `Person` in the map changes, Beans Binding will update the mother's first name property in response to that change.

Once you start dealing with more complex paths, there's a lot more boiler plate code you would have to write without Beans Binding. Consider this example: You want to bind a `JTable` to a list of `Person` objects. In addition, you also want to bind some other object to the table's selected item: For example, you want to show the selected person's mother's first name in a `TextField`.

Any time the selected item in the table changes, you want that first name field's text to update. But you also want to update that first name field if the person's mother's first name is changed in the list. You would have to write a lot of error-prone, repetitious code that Beans Binding simplifies for you.

**Frank Sommers**: You described how `BeanProperty` and `ELProperty` abstract away the complexity of adding listeners to a bean. How do you wire up these properties for notifications with Beans Binding?

**Shannon Hickey**: Suppose you want to bind the `Person` object's `firstName` property to a text field. You start by creating the `Property` object: invoke the `BeanProperty.create()` method with the string "firstName" to create the first bean property. Similarly, create a bean property called "text" that represents the `text` property of the `TextComponent`. Then, you create an auto-binding with `Bindings.createAutoBindings()`, passing that method four parameters: the first bean (the person), the "firstName" property, which says that you want to apply that property to the `Person` object, then the `TextField`, and then the "text" property.

When creating an auto-binding, you also must pass in the update strategy. An auto-binding has three update strategies: read once, read, and read-write. The first strategy means that the property is read only when the binding is made. With read, any time the first property changes, the binding will refresh the target from the source. And read-write applies the changes in both directions.

**Frank Sommers**: What are the requirements for a bean to participate in this process?

**Shannon Hickey**: The `BeanProperty` and `ELProperty`, which are our implementations, require JavaBeans: the properties these bindings represent are JavaBeans properties.

For a property to be readable, the property requires a `get()` or `is()`set() method as well. If there are not going to be any changes to the bean, then that's enough, such as when you just want to display the value of something in a label without the source ever changing.

Once you start dealing with bindings whose source is going to change, you need to fire property change notifications in order to have them work properly with `BeanProperty` and `ELProperty`. This follows the common pattern of writing a bean: a bean needs to maintain a list of property change listeners, and notify those listeners with the correct property names and values. I believe the Swing Application Framework [JSR 296] may even provide an abstract bean you can build on that follows all these patterns.

Additionally, `BeanProperty` and `ELProperty` work with a `Map`, in that they can use the property name as the key into a map. If you map the string "key" to some value, fetching the bean property with that string represents calling `get()` with that string on that map. In order to respond to changes in the map, the map has ith that string represents calling `get()` with that string on that map. In order to respond to changes in the map, the map has to be an instance of `ObservableMap`. `ObservableMap` and `ObservableList` are provided by this project. We have convenience met As I mentioned, many of the Swing components are not proper JavaBeans, or do not have proper JavaBean properties with respect to some of the interesting things you'd want to listen to. For example, `JSlider` doesn't provide a `value` property, a `TextComponent` doesn't offer a `text` property, and `JTable` doesn't have a `selectedElement` property. Those are situations where you need to know all the boiler plate code to write in order to listen for changes.

To address that issue, in BeansBinding we provide the ability to register synthetic properties. A synthetic property creates a drop-in replacement for a bean to be used in Beans Binding.

`JTable.selectedElement` is an example of this. Since `JTable` doesn't have a `selectedElement` property, we provide a behind the scenes a drop-in, which is a bean that provides a `selectedElement` method, listens to `JTable` changes, and updates what it considers to be the table's `selectedElement` in response to those changes. That allows developers to just bind `selectedElement` like they would any other proper JavaBean property.

The way we support these synthetic properties is via a registry of what we call adapters. We hope to eventually open this registry up so that third parties can provide adapters for their beans. We currently provide synthetic properties for the interesting Swing components. In addition to `JTable.selectedElement`, we also provide `List.selectedElement`, `ComboBox.selectedItem`, `TextComponent.text`, a `Spinner` and `Slider`'s `value`: all these things should be proper JavaBean properties, but aren't. We could fix these in future versions of Swing, but Beans Binding is designed to work with Java 5.0 and forward.

**Frank Sommers**: Swing's single event threading rule states that you are only supposed to change the state of a Swing component in the event handling thread. When you update the property of a visual bean, such as a Swing component, does the Beans Binding API take into account the Swing event handling thread?

**Shannon Hickey**: No, everything occurs on the thread in which the event is called. You can use one of the existing patterns and tools that are available to address that. If you're getting an object from the network, and you want to change the binding, then you need to change that binding on the event handler thread.

**Frank Sommers**: The Beans Binding API has been in development for a while now. How has the API changed over time? What sort of feedback from the expert group and users benefited the API the most?

**Shannon Hickey**: JSR 295 was initiated by Scott Violet before he left Sun, and I inherited it form him. The implementation of the API changed quite significantly with respect to the design goals that Scott had set out.

In the initial version, EL was a requirement. A binding was created with four parameters: source and target object, and two strings. The first string was interpreted as EL all the time, and the second one was interpreted as a simple path. A lot of the expert group and the community didn't want to have to deal with EL. With this community, we decided to create this abstract `Property` class so people can use `BeanProperty` or, if they wanted to, ELProperty. Users can even create their implementations of `Property`. So the API is no longer hard-coded to EL. That was a big win, and it got a lot people happy and interested in the project.

The second major change was about ad-hoc parameters for configuring special bindings. For example, when binding to a `JTable`, you want the table to display properties of some elements in different columns. In the old implementation, doing this was confusing, and there was no type safety. The second major re-architecture for 1.0 was to create binding subclasses for these complex cases. There is a `JTableBinding`, a `JComboBox` binding, and a `JList` binding. In the `JTableBinding` there are methods for adding column binding, and column bindings have methods on them for setting whether or not they're editable and what class they represent.

**Frank Sommers**: There are currently several proposals to add native property support to the Java language. To what extent would language-level properties make data binding an easier task?

**Shannon Hickey**: There has been a lot of confusion about Beans Binding and its relation to proper property support in the language. Some people are afraid that Beans Binding will somehow stall adding property support for the language.

Any effort about adding property support to the language is happening separately from Beans Binding. The Beans Binding API is designed to work with Java 5.0, and with old JavaBeans, and to solve an existing problem.

Had actual property support been in the language, it would have made some of the architecture in Beans Binding different than what it is now. My goal with the Beans Binding project is to ensure that if, and when, property support is added to the language, it's easy to create an adapter or custom property type in Beans Binding. We designed the abstract `Property` class such that when proper language support is added, we could provide an additional implementation that uses native properties.

**Frank Sommers**: There are several other projects in the Java community that aim to address the problem of binding JavaBeans properties. How does the JSR 295 API relate to those projects?

**Shannon Hickey**: As I mentioned, I inherited the JSR 295 API from Scott Violet, and Scott had evaluated related projects in the community at the time JSR 295 commenced. So I cannot speak what specific projects and APIs influenced the initial design of the Beans Binding API.

Currently, the Beans Binding API and the related java.net project work closely with several other community initiatives, especially in the Swing community. Perhaps the most significant example of a project using the JSR 295 API is NetBeans 6, which already has support for the Beans Binding project in its GUI builder.

In NetBeans 6's UI designer, dragging a database table bean onto a form will automatically create bindings between some database object and Swing components. NetBeans also provides form editors for creating bindings. Since NetBeans 6 also supports the Swing Application Framework API [JSR 296], it is possible to rapidly create a UI that relies on the Swing App Framework as well as uses the Beans Binding framework.

The Beans Binding project also makes use of several other projects from the Java community. The EL expression language we use in specifying `ELProperty` objects is from the GlassFish project, with some minor changes that we needed for Beans Binding. Hopefully, we can get those changes back into the official EL version in the future.

**Frank Sommers**: How stable is the Beans Binding API now? What are your future plans for JSR 295?

**Shannon Hickey**: The JSR is ongoing, and the end goal is to have it be a part of Java 7. The reference implementation is being developed in the open on java.net. I've been releasing builds when I think the project code is stable, and that has allowed people to provide feedback and to start using it even now. It is being used now quite widely, and the feedback from the community has really been invaluable.

### Resources

JSR 295, Beans Binding
: [http://www.jcp.org/en/jsr/detail?id=295](https://jcp.org/en/jsr/detail?id=295)

JSR 296, Swing Application Framework
: [https://www.jcp.org/en/jsr/detail?id=296](https://www.jcp.org/en/jsr/detail?id=296)

---

#### Talk back!

Have an opinion? Be the first to [post a comment](https://www.artima.com/articles/data-binding-in-java/flat-comments) about this article.

#### About the author

Frank Sommers is a Senior Editor with Artima Developer. He also serves as chief editor of the IEEE Technical Committee on Scalable Computing's newsletter, and is an elected member of the Jini Community's Technical Advisory Committee. Prior to joining Artima, Frank wrote the Jiniology and Web services columns for JavaWorld.
