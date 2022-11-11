package org.jvnet.basicjaxb.config;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A factory to create an InputStream or Reader from a string locator. The locator
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
public class LocatorInputFactory
{
	private static Logger logger = LoggerFactory.getLogger(LocatorInputFactory.class);
	
	/** Represents a URL protocol for a classpath lookup. */
	public static final String PROTOCOL_CLASSPATH = "classpath:";
	/** Represents a URL protocol for a file lookup. */
	public static final String PROTOCOL_FILE = "file:";
	
	/**
	 * Create input stream for the given locator. The locator can represent a
	 * URL or a File. If the URL protocol is "classpath:" then the input stream
	 * will be located as a resource stream.
	 * 
	 * This method uses a ClassLoader to resolve classpath resources; thus, a
	 * "classpath:" locator must provide the full path relative to the classpath
	 * root and any leading '/' will be ignored.
	 * 
	 * @param locator The location of a file or resource.
	 * 
	 * @throws IOException If an error occurred when inputting from the stream.
	 */
	public static InputStream createInputStream(String locator) throws IOException
	{
		return createInputStream(locator, null);
	}
	
	/**
	 * Create input stream for the given locator. The locator can represent a
	 * URL or a File. If the URL protocol is "classpath:" then the input stream
	 * will be located as a resource stream.
	 * 
	 * If the class parameter is null, a ClassLoader is used to resolve classpath
	 * resources; thus, a "classpath:" locator must provide the full path relative
	 * to the classpath root and any leading '/' will be ignored.
	 * 
	 * @param locator The location of a file or resource.
	 * @param clazz A classpath location for relative locators.
	 * 
	 * @throws IOException If an error occurred when inputting from the stream.
	 */
	public static InputStream createInputStream(String locator, Class<?> clazz) throws IOException
	{
		logger.debug("Locating: '" + locator + "' with " + clazz);
		InputStream inputStream = null;
		if ( locator != null )
		{
			if ( locator.startsWith(PROTOCOL_CLASSPATH) )
			{
				String resourcePath = locator.substring(PROTOCOL_CLASSPATH.length());
				if ( clazz != null )
					inputStream = clazz.getResourceAsStream(resourcePath);			
				else
				{
					if ( locator.startsWith("/") )
						locator = locator.substring(1);
					inputStream = LocatorInputFactory.class.getClassLoader().getResourceAsStream(resourcePath);
				}
			}
			else
			{
				try
				{
					URL url = new URL(locator);
					inputStream = url.openStream();
				}
				catch (MalformedURLException ex)
				{
					File file = new File(locator);
					inputStream = new FileInputStream(file);
				}
			}
		}
		return inputStream;
	}

	/**
	 * Create reader with UTF-8 character encoding for the given locator.
	 * The locator can represent a URL or a File. If the URL protocol is 
	 * "classpath:" then the reader will be located as a resource stream.
	 * 
	 * This method uses a ClassLoader to resolve classpath resources; thus, a
	 * "classpath:" locator must provide the full path relative to the classpath
	 * root and any leading '/' will be ignored.
	 * 
	 * @param locator The location of a file or resource.
	 * @param clazz A classpath location for relative locators.
	 * 
	 * @throws IOException If an error occurred when inputting from the reader.
	 */
	public static Reader createReader(String locator) throws IOException
	{
		return createReader(locator, null);
	}
	
	/**
	 * Create reader with UTF-8 character encoding for the given locator.
	 * The locator can represent a URL or a File. If the URL protocol is 
	 * "classpath:" then the reader will be located as a resource stream.
	 * 
	 * If the class parameter is null, a ClassLoader is used to resolve classpath
	 * resources; thus, a "classpath:" locator must provide the full path relative
	 * to the classpath root and any leading '/' will be ignored.
	 * 
	 * @param locator The location of a file or resource.
	 * @param clazz A classpath location for relative locators.
	 * 
	 * @throws IOException If an error occurred when inputting from the reader.
	 */
	public static Reader createReader(String locator, Class<?> clazz) throws IOException
	{
		InputStream inputStream = createInputStream(locator, clazz);
		if ( inputStream != null )
			return new InputStreamReader(inputStream, UTF_8);
		else
			throw new FileNotFoundException(locator);
	}
}
