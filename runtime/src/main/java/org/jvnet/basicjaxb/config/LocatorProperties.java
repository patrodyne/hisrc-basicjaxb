package org.jvnet.basicjaxb.config;

import static org.jvnet.basicjaxb.config.LocatorInputFactory.createReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

/**
 * An implementation for a map of key-value pairs that is compatible with java.util.Properties.
 * This implementation adds a method to load properties using a string locator. The locator
 * represents either a classpath, a URL or a file.
 * 
 * <p>Sample locators:</p>
 * 
 * <ul>
 *   <li>file:///PATH/src/main/resources/org/jvnet/basicjaxb/locator/messages.properties</li>
 *   <li>file:src/main/resources/org/jvnet/basicjaxb/locator/messages.properties</li>
 *   <li>classpath:/org/jvnet/basicjaxb/locator/messages.properties</li>
 *   <li>classpath:messages.properties</li>
 *   <li>/PATH/src/main/resources/org/jvnet/basicjaxb/locator/messages.properties</li>
 *   <li>src/main/resources/org/jvnet/basicjaxb/locator/messages.properties</li>
 * </ul>
 */
public class LocatorProperties extends Properties implements LocatorLoader<Object,Object>
{
	private static final long serialVersionUID = 20220901L;
	
	/**
	 * Load properties for the given locator. The locator can represent a
	 * URL or a File. If the URL protocol is "classpath:" then the properties
	 * file will be located as a resource stream.
	 * 
	 * @param locator The location of a property file or resource.
	 * 
	 * @throws IOException If an error occurred when inputting from the reader.
	 */
	@Override
	public void load(String locator) throws IOException
	{
		try (  Reader resourceReader = createReader(locator, getClass()) )
		{
			if ( resourceReader != null )
				load(resourceReader);
			else
				throw new FileNotFoundException(locator);
		}
	}
}
