package org.jvnet.basicjaxb.config;

import static java.lang.Thread.currentThread;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
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
	 * Does the given locator exist. The locator can represent a URL or a File.
	 * If the URL protocol is "classpath:" then the input stream  will be located
	 * as a resource stream.
	 * 
	 * This method uses a ClassLoader to resolve classpath resources; thus, a
	 * "classpath:" locator must provide the full path relative to the classpath
	 * root and any leading '/' will be ignored.
	 * 
	 * @param locator The location of a file or resource.
	 * 
	 * @return True when an input stream can be created for the given locator; otherwise, false.
	 * 
	 * @throws IOException If an error occurred when inputting from the stream.
	 */
	public static boolean locatorExists(String locator) throws IOException
	{
		boolean locatorExists = false;
		try ( InputStream is = createInputStream(locator) )
		{
			locatorExists = (is != null);
		}
		return locatorExists;
	}
	
	/**
	 * Does the given locator exist. The locator can represent a URL or a File.
	 * If the URL protocol is "classpath:" then the input stream will be located
	 * as a resource stream.
	 * 
	 * If the class parameter is null, a ClassLoader is used to resolve classpath
	 * resources; thus, a "classpath:" locator must provide the full path relative
	 * to the classpath root and any leading '/' will be ignored.
	 * 
	 * @param locator The location of a file or resource.
	 * @param clazz A classpath location for relative locators.
	 * 
	 * @return True when an input stream can be created for the given locator and class;
	 *         otherwise, false.
	 * 
	 * @throws IOException If an error occurred when inputting from the stream.
	 */	public static boolean locatorExists(String locator, Class<?> clazz) throws IOException
	{
		boolean locatorExists = false;
		try ( InputStream is = createInputStream(locator, clazz) )
		{
			locatorExists = (is != null);
		}
		return locatorExists;
	}

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
	 * @return An {@link InputStream} for the given locator.
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
	 * @return An {@link InputStream} for the given locator and class.
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
					if ( resourcePath.startsWith("/") )
						resourcePath = resourcePath.substring(1);
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
	 * Resolve a locator to an {@link URI}. The locator may represent a
	 * URL or a File. If the URL protocol is "classpath:" then the URI
	 * will be located as a resource.
	 * 
	 * If the class parameter is null, a {@link ClassLoader} is used to
	 * resolve classpath resources; thus, a "classpath:" locator must
	 * provide the full path relative to the classpath root and any
	 * leading '/' will be ignored.
	 * 
	 * @param locator The location of a file or resource.
	 * @param clazz A classpath location for relative locators.
	 * 
	 * @return A {@link URI} representing the locator.
	 * 
	 * @throws URISyntaxException When a URI cannot be parsed.
	 * @throws MalformedURLException When a URL cannot be parsed.
	 */
	public static URI resolveLocator(String locator, Class<?> clazz) throws URISyntaxException, MalformedURLException
	{
		URI resourceURI = null;
		URL resourceURL = null;
		
		if ( locator != null )
		{
			if ( locator.startsWith(PROTOCOL_CLASSPATH) )
			{
				String resourcePath = locator.substring(PROTOCOL_CLASSPATH.length());
				if ( clazz != null )
					resourceURL = clazz.getResource(resourcePath);	
				else
				{
					if ( resourcePath.startsWith("/") )
						resourcePath = resourcePath.substring(1);
					ClassLoader classloader = currentThread().getContextClassLoader();
					resourceURL = classloader.getResource(resourcePath);
				}
			}
			else
				resourceURL = new URL(locator);

			if ( resourceURL != null )
			{
				resourceURI = resourceURL.toURI();
				logger.debug("Resolved: '{}' to '{}'", locator, resourceURI.toString());
			}
		}
		else
			logger.warn("resolveLocator: locator is null");
		
		return resourceURI;
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
	 * 
	 * @return A {@link Reader} for the given locator.
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
	 * @return A {@link Reader} for the given locator and class.
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
