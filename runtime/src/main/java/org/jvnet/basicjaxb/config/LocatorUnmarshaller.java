package org.jvnet.basicjaxb.config;

import java.io.IOException;
import java.io.Reader;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

/**
 * An facade for an unmarshaller of an XML file that delegates to a JAXB Unmarshaller.
 * This implementation adds a method to unmarshal using a string locator. The locator
 * represents either a classpath, a URL or a file.
 * 
 * <p>Sample locators:</p>
 * 
 * <ul>
 *   <li>file:///PATH/src/test/samples/po.xml</li>
 *   <li>file:src/test/samples/po.xml</li>
 *   <li>classpath:/org/jvnet/basicjaxb/tests/po/po.xml</li>
 *   <li>classpath:po.xml</li>
 *   <li>/PATH/src/test/samples/po.xml</li>
 *   <li>src/test/samples/po.xml</li>
 * </ul>
 * 
 * @param <T>
 */
public class LocatorUnmarshaller<T>
{
	/** Represents a URL protocol for a classpath lookup. */
	public static final String PROTOCOL_CLASSPATH = "classpath:";
	/** Represents a URL protocol for a file lookup. */
	public static final String PROTOCOL_FILE = "file:";
	
	private Unmarshaller unmarshaller;
	public Unmarshaller getUnmarshaller() { return unmarshaller; }
	public void setUnmarshaller(Unmarshaller unmarshaller) { this.unmarshaller = unmarshaller; }
	
	/**
	 * Construct with a JAXB Unmarshaller.
	 * 
	 * @param unmarshaller The unmarshaller to delegate to.
	 */
	public LocatorUnmarshaller(Unmarshaller unmarshaller)
	{
		setUnmarshaller(unmarshaller);
	}

	/**
	 * Unmarshal an XML instance using the given locator. The locator can
	 * represent a URL or a File. If the URL protocol is "classpath:" then
	 * the XML file will be located as a resource stream.
	 * 
	 * @param locator The location of an XML file or resource.
	 * 
	 * @return An object instance of type T.
	 * 
	 * @throws IOException If an error occurred when inputting from the reader.
	 * @throws JAXBException If any unexpected errors occur while unmarshalling.
	 */
	public T unmarshal(String locator) throws IOException, JAXBException
	{
		return unmarshal(locator, null);
	}

	/**
	 * Unmarshal an XML instance using the given locator. The locator can
	 * represent a URL or a File. If the URL protocol is "classpath:" then
	 * the XML file will be located as a resource stream.
	 * 
	 * @param locator The location of an XML file or resource.
	 * @param clazz A classpath location for relative locators.
	 * 
	 * @return An object instance of type T.
	 * 
	 * @throws IOException If an error occurred when inputting from the reader.
	 * @throws JAXBException If any unexpected errors occur while unmarshalling.
	 */
	@SuppressWarnings("unchecked")
	public T unmarshal(String locator, Class<?> clazz) throws IOException, JAXBException
	{
		T instance = null;
		try ( Reader reader = LocatorInputFactory.createReader(locator, clazz) )
		{
			Object object = getUnmarshaller().unmarshal(reader);
			if ( object instanceof JAXBElement )
				instance = ((JAXBElement<T>) object).getValue();
			else
				instance = (T) object;
		}
		return instance;
	}
}
