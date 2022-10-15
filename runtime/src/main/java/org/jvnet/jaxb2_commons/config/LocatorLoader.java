package org.jvnet.jaxb2_commons.config;

import java.io.IOException;
import java.util.Map;

/**
 * An interface for a map of key-value pairs that is compatible with java.util.Properties.
 * This interface adds a method to load properties using a string locator. The locator
 * represents either a classpath, a URL or a file.
 * 
 * @param <K> The key type.
 * @param <V> The value type.
 */
public interface LocatorLoader<K,V> extends Map<K,V>, Cloneable, java.io.Serializable
{
	/**
	 * Load properties for the given locator. The locator can represent a
	 * URL or a File. If the URL protocol is "classpath:" then the properties
	 * file will be located as a resource stream.
	 * 
	 * @param locator The location of a property file or resource.
	 * 
	 * @throws IOException If an error occurred when inputting from the reader.
	 */
	public void load(String locator) throws IOException;
	
}
