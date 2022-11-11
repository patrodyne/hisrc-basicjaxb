package org.jvnet.basicjaxb.plugin.simplify.tests01;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Gh4Test {

	private JAXBContext context;

	@BeforeEach
	public void setUp() throws Exception {
		context = JAXBContext.newInstance(getClass().getPackage().getName());
	}

	@Test
	public void compiles() {
		final SimplifyReferencesPropertyAsElementPropertyType item = new SimplifyReferencesPropertyAsElementPropertyType();
		item.getBases();
		item.getBaseElements();

	}

	@Test
	public void unmarshalls() throws Exception {

		@SuppressWarnings("unchecked")
		SimplifyReferencesPropertyAsElementPropertyType value = ((JAXBElement<SimplifyReferencesPropertyAsElementPropertyType>) context
				.createUnmarshaller()
				.unmarshal(
						getClass()
								.getResourceAsStream(
										"simplifyReferencesPropertyAsElementProperty.xml")))
				.getValue();

		assertEquals(3, value.getBases().size());
		assertEquals(3, value.getBaseElements().size());
	}
}
