package org.jvnet.basicjaxb.plugin.simplify.tests01;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.StringWriter;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Gh1Test
{
	private Logger logger = LoggerFactory.getLogger(Gh1Test.class);
	public Logger getLogger() { return logger; }
	
	@Test
	public void compiles()
	{
		final Gh1 item = new Gh1();
		item.getAs();
		item.getBs();
		item.getMixedContent();
	}

	@Test
	public void contextIsSuccessfullyCreated()
		throws JAXBException
	{
		final JAXBContext context = JAXBContext.newInstance(Gh1.class);
		final Gh1 value = new Gh1();
		value.getAs().add("a");
		value.getBs().add(2);
		value.getMixedContent().add("Test");
		final StringWriter sw = new StringWriter();
		context.createMarshaller().marshal(new JAXBElement<Gh1>(new QName("test"), Gh1.class, value), sw);
		getLogger().debug("Gh1: " + sw);
		assertTrue(sw.toString().contains("Test"));
	}
}
