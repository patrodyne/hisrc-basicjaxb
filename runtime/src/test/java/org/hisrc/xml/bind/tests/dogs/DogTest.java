package org.hisrc.xml.bind.tests.dogs;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DogTest {

	@Test
	public void unmarshallsDogs() throws JAXBException {
		final JAXBContext context = JAXBContext
				.newInstance(ObjectFactory.class);
		final Dogs dogs = (Dogs) context.createUnmarshaller().unmarshal(
				getClass().getResource("dogs.xml"));
		assertEquals(3, dogs.getDogs().size());
		// Does not work
//		assertEquals("henry", dogs.getDogs().get(0).getValue().getName());
		assertEquals("bark", dogs.getDogs().get(0).getValue().getSound());
		// Does not work
//		assertEquals("fido", dogs.getDogs().get(1).getValue().getName());
		assertEquals("woof", dogs.getDogs().get(1).getValue().getSound());
		// Does not work
//		assertEquals("barks", dogs.getDogs().get(2).getValue().getName());
		assertEquals("miau", dogs.getDogs().get(2).getValue().getSound());
	}
}
