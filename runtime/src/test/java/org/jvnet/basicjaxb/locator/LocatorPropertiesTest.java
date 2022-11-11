package org.jvnet.basicjaxb.locator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.jvnet.basicjaxb.config.LocatorInputFactory.PROTOCOL_CLASSPATH;
import static org.jvnet.basicjaxb.config.LocatorInputFactory.PROTOCOL_FILE;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.config.LocatorLoader;
import org.jvnet.basicjaxb.config.LocatorProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocatorPropertiesTest
{
	protected Logger logger = LoggerFactory.getLogger(LocatorPropertiesTest.class);

	private final String RESOURCE_ROOT = System.getProperty("user.dir");
	private final String RESOURCE_BASE = "src/test/resources/";
	private final String RESOURCE_PATH = "org/jvnet/basicjaxb/config/";
	private final String RESOURCE_NAME = "card-name.properties";

	@Test
	void testClasspathAbsoluteLoad() throws IOException
	{
		LocatorLoader<Object, Object> locatorLoader = new LocatorProperties();
		String locator = PROTOCOL_CLASSPATH + "/" + RESOURCE_PATH + RESOURCE_NAME;
		locatorLoader.load(locator);
		assertFalse(locatorLoader.isEmpty(), "Properties are expected.");
	}
	
	@Test
	void testClasspathRelativeLoad() throws IOException
	{
		LocatorLoader<Object, Object> locatorLoader = new LocatorProperties();
		String locator = PROTOCOL_CLASSPATH + RESOURCE_NAME;
		locatorLoader.load(locator);
		assertFalse(locatorLoader.isEmpty(), "Properties are expected.");
	}

	@Test
	void testRelativeFileProtocolLoad() throws IOException
	{
		LocatorLoader<Object, Object> locatorLoader = new LocatorProperties();
		String locator = PROTOCOL_FILE + RESOURCE_BASE + RESOURCE_PATH + RESOURCE_NAME;
		locatorLoader.load(locator);
		assertFalse(locatorLoader.isEmpty(), "Properties are expected.");
	}


	@Test
	void testAbsoluteFileProtocolLoad() throws IOException
	{
		LocatorLoader<Object, Object> locatorLoader = new LocatorProperties();
		String locator = PROTOCOL_FILE + "//" + RESOURCE_ROOT + "/" +RESOURCE_BASE + RESOURCE_PATH + RESOURCE_NAME;
		locatorLoader.load(locator);
		assertFalse(locatorLoader.isEmpty(), "Properties are expected.");
	}

	@Test
	void testRelativeFileLoad() throws IOException
	{
		LocatorLoader<Object, Object> locatorLoader = new LocatorProperties();
		String locator = RESOURCE_BASE + RESOURCE_PATH + RESOURCE_NAME;
		locatorLoader.load(locator);
		assertFalse(locatorLoader.isEmpty(), "Properties are expected.");
	}

	@Test
	void testAbsoluteFileLoad() throws IOException
	{
		LocatorLoader<Object, Object> locatorLoader = new LocatorProperties();
		String locator = RESOURCE_ROOT + "/" +RESOURCE_BASE + RESOURCE_PATH + RESOURCE_NAME;
		locatorLoader.load(locator);
		assertFalse(locatorLoader.isEmpty(), "Properties are expected.");
	}
}
