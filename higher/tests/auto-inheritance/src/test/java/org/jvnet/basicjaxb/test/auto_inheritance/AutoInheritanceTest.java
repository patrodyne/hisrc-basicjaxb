package org.jvnet.basicjaxb.test.auto_inheritance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.example.address.AddressType;
import org.example.address.AreaType;
import org.example.address.CityAddressType;
import org.example.address.UKAddress;
import org.example.address.USAddress;
import org.example.base.Jeopardy;
import org.example.base.Nameable;
import org.example.base.Stageable;
import org.example.contact.Contact;
import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.lang.ContextUtils;
import org.jvnet.basicjaxb.test.AbstractSamplesTest;
import org.xml.sax.SAXException;

import jakarta.xml.bind.JAXBException;

public class AutoInheritanceTest extends AbstractSamplesTest
{
	@Override
	protected String getContextPath()
	{
		return ContextUtils.getContextPath(org.example.address.ObjectFactory.class, org.example.contact.ObjectFactory.class);
	}

	@Override
	protected void checkSample(File sampleFile)
		throws Exception
	{
		assertNotNull(sampleFile, "Sample must exist.");
		getSampleMap().put(sampleFile.getName(), sampleFile);
	}
	
	@Test
	public void testAutoInheritance()
		throws Exception
	{
		// Types Extend: <arg>-XautoInheritance-xmlTypesExtend=org.example.base.Stageable</arg>

		assertFalse(Stageable.class.isAssignableFrom(Contact.class), "Root Contact is NOT Stageable");
		assertTrue(Stageable.class.isAssignableFrom(UKAddress.class), "Root UKAddress is Stageable");
		assertTrue(Stageable.class.isAssignableFrom(USAddress.class), "Root USAddress is Stageable");
		assertTrue(Stageable.class.isAssignableFrom(CityAddressType.class), "Type CityAddressType is Stageable");
		assertTrue(Stageable.class.isAssignableFrom(AddressType.class), "Type AddressType is Stageable");
		assertFalse(Stageable.class.isAssignableFrom(AreaType.class), "Enum AreaType is NOT Stageable");
		
		// Types Implement: <arg>-XautoInheritance-xmlTypesImplement=java.lang.Cloneable</arg>
		
		assertFalse(Cloneable.class.isAssignableFrom(Contact.class), "Root Contact is NOT Cloneable");
		assertTrue(Cloneable.class.isAssignableFrom(UKAddress.class), "Root UKAddress is Cloneable");
		assertTrue(Cloneable.class.isAssignableFrom(USAddress.class), "Root USAddress is Cloneable");
		assertTrue(Cloneable.class.isAssignableFrom(CityAddressType.class), "Type CityAddressType is Cloneable");
		assertTrue(Cloneable.class.isAssignableFrom(AddressType.class), "Type AddressType is Cloneable");
		assertFalse(Cloneable.class.isAssignableFrom(AreaType.class), "Enum AreaType is NOT Cloneable");

		// Root Elements Extend: <arg>-XautoInheritance-xmlRootElementsExtend=org.example.base.Jeopardy</arg>

		assertTrue(Jeopardy.class.isAssignableFrom(Contact.class), "Root Contact is Jeopardy");
		assertFalse(Jeopardy.class.isAssignableFrom(UKAddress.class), "Root UKAddress is NOT Jeopardy");
		assertFalse(Jeopardy.class.isAssignableFrom(USAddress.class), "Root USAddress is NOT Jeopardy");
		assertFalse(Jeopardy.class.isAssignableFrom(CityAddressType.class), "Type CityAddressType is NOT Jeopardy");
		assertFalse(Jeopardy.class.isAssignableFrom(AddressType.class), "Type AddressType is NOT Jeopardy");
		assertFalse(Jeopardy.class.isAssignableFrom(AreaType.class), "Enum AreaType is NOT Jeopardy");
		
		// Root Elements Implement: <arg>-XautoInheritance-xmlRootElementsImplement=org.example.base.Nameable</arg>
		
		assertTrue(Nameable.class.isAssignableFrom(Contact.class), "Root Contact is Nameable");
		assertTrue(Nameable.class.isAssignableFrom(UKAddress.class), "Root UKAddress is Nameable");
		assertTrue(Nameable.class.isAssignableFrom(USAddress.class), "Root USAddress is Nameable");
		assertFalse(Nameable.class.isAssignableFrom(CityAddressType.class), "Type CityAddressType is NOT Nameable");
		assertFalse(Nameable.class.isAssignableFrom(AddressType.class), "Type AddressType is NOT Nameable");
		assertFalse(Nameable.class.isAssignableFrom(AreaType.class), "Enum AreaType is NOT Nameable");

		// Extensions
		
		assertEquals(Jeopardy.class, Contact.class.getSuperclass(), "Root Contact extends Jeopardy");
		assertEquals(CityAddressType.class, UKAddress.class.getSuperclass(), "Root UKAddress extends CityAddressType");
		assertEquals(CityAddressType.class, USAddress.class.getSuperclass(), "Root USAddress extends CityAddressType");
		assertEquals(AddressType.class, CityAddressType.class.getSuperclass(), "Type CityAddressType extends AddressType");
		assertEquals(Stageable.class, AddressType.class.getSuperclass(), "Type AddressType extends Stageable");
		assertEquals(Enum.class, AreaType.class.getSuperclass(), "Enum AreaType extends Object");

	}

	@Test
	public void testUKAddressType() throws IOException, SAXException, JAXBException
	{
		// Note: Inheriting a non-schema-derived JAXB class may not pass schema validation!
		// enableSchemaValidation( "classpath:address.xsd" );
		
		File addressesUK = getSampleMap().get("addressesUK.xml");
		UKAddress ukAddress = (UKAddress) getUnmarshaller().unmarshal(addressesUK);
		getLogger().debug(marshalToString(ukAddress));
		assertNotNull(ukAddress, "UKAddress expected.");
		assertEquals("W1W8UU", ukAddress.getPostcode());
	}

	@Test
	public void testUSAddressType() throws IOException, SAXException, JAXBException
	{
		// Note: Inheriting a non-schema-derived JAXB class may not pass schema validation!
		// enableSchemaValidation( "classpath:address.xsd" );
		
		File addressesUS = getSampleMap().get("addressesUS.xml");
		USAddress usAddress = (USAddress) getUnmarshaller().unmarshal(addressesUS);
		getLogger().debug(marshalToString(usAddress));
		assertNotNull(usAddress, "USAddress expected.");
		assertEquals("34543", usAddress.getZipcode());
	}

	@Test
	public void testContact1() throws IOException, SAXException, JAXBException
	{
		enableSchemaValidation( "classpath:contact.xsd" );
		File contacts = getSampleMap().get("contact1.xml");
		Contact contact1 = (Contact) getUnmarshaller().unmarshal(contacts);
		getLogger().debug(marshalToString(contact1));
		assertNotNull(contact1, "Contact1 expected.");
		assertEquals("George Washington", contact1.getFullName());
	}
}
