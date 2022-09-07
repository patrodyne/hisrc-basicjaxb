package org.jvnet.jaxb2_commons.plugin.simplify.tests01;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Gh6Test {

	private JAXBContext context;

	@BeforeEach
	public void setUp() throws Exception {
		context = JAXBContext.newInstance(getClass().getPackage().getName());
	}

	@Test
	public void compiles() {
		final SimplifyElementsPropertyAsElementPropertyType item = new SimplifyElementsPropertyAsElementPropertyType();
		item.getInts();
		item.getStrings();
	}
	
	@Test
	public void testElementsPropertyAsElementPropertyType() throws Exception {

		@SuppressWarnings("unchecked")
		SimplifyElementsPropertyAsElementPropertyType value = ((JAXBElement<SimplifyElementsPropertyAsElementPropertyType>) context
				.createUnmarshaller().unmarshal(
						getClass().getResourceAsStream("simplifyElementsPropertyAsElementProperty.xml")))
				.getValue();

		assertEquals(3, value.getStrings().size());
		assertEquals(3, value.getInts().size());
	}
}
