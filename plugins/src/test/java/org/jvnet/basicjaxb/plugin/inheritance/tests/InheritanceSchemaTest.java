package org.jvnet.basicjaxb.plugin.inheritance.tests;

import static jakarta.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.plugin.inheritance.ExtendsClass;
import org.jvnet.basicjaxb.plugin.inheritance.ImplementsInterface;
import org.jvnet.basicjaxb.plugin.inheritance.ObjectFactory;
import org.jvnet.basicjaxb.plugin.inheritance.ObjectFactoryCustomization;
import org.patrodyne.jvnet.basicjaxb.validation.SchemaOutputStringResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

/**
 * Test (explore)
 */
public class InheritanceSchemaTest
{
    private static Logger logger = LoggerFactory.getLogger(InheritanceSchemaTest.class);
    public static Logger getLogger() { return logger; }

	private JAXBContext jaxbContext;
	public JAXBContext getJaxbContext() { return jaxbContext; }
	public void setJaxbContext(JAXBContext jaxbContext) { this.jaxbContext = jaxbContext; }

	private Marshaller marshaller;
	public Marshaller getMarshaller() { return marshaller; }
	public void setMarshaller(Marshaller marshaller) { this.marshaller = marshaller; }

	private Unmarshaller unmarshaller;
	public Unmarshaller getUnmarshaller() { return unmarshaller; }
	public void setUnmarshaller(Unmarshaller unmarshaller) { this.unmarshaller = unmarshaller; }
	
	@BeforeEach
	public void beforeEach() throws JAXBException
	{
		setJaxbContext(createJAXBContext());
		setMarshaller(createMarshaller(getJaxbContext()));
		setUnmarshaller(createUnmarshaller(getJaxbContext()));
		
		assertNotNull(getJaxbContext(), "JAXBContext is required");
		assertNotNull(getMarshaller(), "Marshaller is required");
		assertNotNull(getUnmarshaller(), "Unmarshaller is required");
	}
	
	@Test
	public void testSchema() throws Exception
	{
		generateXmlSchemaFromString();
		
		ObjectFactory of = new ObjectFactory();
		
		ObjectFactoryCustomization ofc1 = of.createObjectFactoryCustomization();
		{
			ExtendsClass ec = of.createExtendsClass();
			ec.setClassName("org.example.demo.Stageable");
			
			ImplementsInterface ii = of.createImplementsInterface();
			ii.setInterfaceName("java.lang.Cloneable");
			
			ofc1.setExtendsClass(ec);
			ofc1.getImplementsInterface().add(ii);
			ofc1.setPackageName("org.example.demo");
		}
		
		String ofc1Xml = marshalToString(ofc1);
		println("ObjectFactoryCustomization XML:\n\n" +ofc1Xml);
		
		Object obj2 = unmarshalFromString(ofc1Xml);
		assertTrue(obj2 instanceof ObjectFactoryCustomization, "Expect ObjectFactoryCustomization");
		
		ObjectFactoryCustomization ofc2 = (ObjectFactoryCustomization) obj2;
		
		assertEquals(ofc1.getPackageName(), ofc2.getPackageName(), " Expect package name");
		assertEquals(ofc1.getExtendsClass().getClassName(), ofc2.getExtendsClass().getClassName(), " Expect extends class");
		assertFalse(ofc2.getImplementsInterface().isEmpty(), "Expect ImplementsInterface not empty");
		assertEquals(ofc1.getImplementsInterface().get(0).getInterfaceName(), ofc2.getImplementsInterface().get(0).getInterfaceName(), " Expect implements interface");
	}

	private JAXBContext createJAXBContext() throws JAXBException
	{
		return JAXBContext.newInstance
		(
			ExtendsClass.class,
			ImplementsInterface.class,
			ObjectFactory.class,
			ObjectFactoryCustomization.class
		);
	}

	private Marshaller createMarshaller(JAXBContext jaxbContext) throws JAXBException
	{
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(JAXB_FORMATTED_OUTPUT, true);
		return marshaller;
	}
	
	private Unmarshaller createUnmarshaller(JAXBContext jaxbContext) throws JAXBException
	{
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		return unmarshaller;
	}

	private String marshalToString(Object instance) throws JAXBException, IOException
	{
		try ( StringWriter writer = new StringWriter() )
		{
			getMarshaller().marshal(instance, writer);
			return writer.toString();
		}
	}

	@SuppressWarnings("unchecked")
	private <T> T unmarshalFromString(String xml) throws JAXBException
	{
		try ( StringReader reader = new StringReader(xml) )
		{
			Object instance = getUnmarshaller().unmarshal(reader);
			return (T) instance;
		}
	}

	private void generateXmlSchemaFromString()
	{
		try
		{
			SchemaOutputStringResolver sosr = new SchemaOutputStringResolver();
			getJaxbContext().generateSchema(sosr);
			println("Xml Schema from String:\n\n" + sosr.getSchemaString());
		}
		catch ( IOException ex )
		{
			errorln("generateXmlSchemaFromString", ex);
		}
	}

	private void errorln(String msg, Exception ex)
	{
		getLogger().error(msg, ex);
		
	}
	
	private void println(String msg)
	{
		getLogger().info(msg);
	}
}
