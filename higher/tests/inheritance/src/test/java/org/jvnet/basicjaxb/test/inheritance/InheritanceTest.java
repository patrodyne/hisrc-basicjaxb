package org.jvnet.basicjaxb.test.inheritance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.example.address.UKAddress;
import org.example.address.USAddress;
import org.example.contact.Contact;
import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.lang.ContextUtils;
import org.jvnet.basicjaxb.test.AbstractSamplesTest;
import org.xml.sax.SAXException;

import jakarta.xml.bind.JAXBException;

public class InheritanceTest extends AbstractSamplesTest
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
