package org.jvnet.basicjaxb.plugin.inheritance.model;

import jakarta.xml.bind.annotation.XmlRegistry;

/**
 * <p>This object contains factory methods for each Java content interface and
 * Java element interface generated in the org.jvnet.basicjaxb.plugin.inheritance
 * package.</p>
 *
 * <p>An ObjectFactory allows
 * you to programatically construct new instances of the Java representation
 * for XML content. The Java representation of XML content can consist of
 * schema derived interfaces and classes representing the binding of schema
 * type definitions, element declarations and model groups.  Factory methods
 * for each of these are provided in this class.</p>
 */
@XmlRegistry
public class ObjectFactory
{
	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: org.jvnet.basicjaxb.plugin.inheritance
	 */
	public ObjectFactory() { }

	/**
	 * Create an instance of {@link AnnotatesMetaObject }
	 * 
	 * @return The new instance of {@link AnnotatesMetaObject }
	 */
	public AnnotatesMetaObject createAnnotatesMetaObject()
	{
		return new AnnotatesMetaObject();
	}

	/**
	 * Create an instance of {@link ObjectFactoryCustomization }
	 * 
	 * @return The new instance of {@link ObjectFactoryCustomization }
	 */
	public ObjectFactoryCustomization createObjectFactoryCustomization()
	{
		return new ObjectFactoryCustomization();
	}

	/**
	 * Create an instance of {@link ExtendsClass }
	 * 
	 * @return The new instance of {@link ExtendsClass }
	 */
	public ExtendsClass createExtendsClass()
	{
		return new ExtendsClass();
	}

	/**
	 * Create an instance of {@link ImplementsInterface }
	 * 
	 * @return The new instance of {@link ImplementsInterface }
	 */
	public ImplementsInterface createImplementsInterface()
	{
		return new ImplementsInterface();
	}

	/**
	 * Create an instance of {@link AnnotatesMetaObject.Element }
	 * 
	 * @return The new instance of {@link AnnotatesMetaObject.Element }
	 */
	public AnnotatesMetaObject.Element createAnnotatesMetaObjectElement()
	{
		return new AnnotatesMetaObject.Element();
	}

	/**
	 * Create an instance of {@link AnnotatesMetaObject.Elements }
	 * 
	 * @return The new instance of {@link AnnotatesMetaObject.Elements }
	 */
	public AnnotatesMetaObject.Elements createAnnotatesMetaObjectElements()
	{
		return new AnnotatesMetaObject.Elements();
	}
}
