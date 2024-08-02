package org.swixml.converters;

import org.swixml.Converter;
import org.swixml.Localizer;
import org.swixml.LogAware;
import org.swixml.SwingEngine;
import org.swixml.dom.Attribute;
import org.swixml.el.ELUtility;

/**
 * Abstract implementation of a general purpose data type converter that
 * can be registered and used within the SwingEngine package to manage the
 * conversion of objects from one type to another.
 * 
 * @author softphone
 * 
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 * 
 * @apiNote T The Swing conversion type.
 */
public abstract class AbstractConverter<T> implements Converter<T>, LogAware
{
	public Localizer getLocalizer(SwingEngine<?> engine)
	{
		return Util.getLocalizer(engine);
	}

	/**
	 * Evaluate SWIXML {@link Attribute}.
	 * 
     * @param attr A SWIXML attribute (wraps {@link org.w3c.dom.Attr})
	 * @param engine {@link SwingEngine} the rendering swingEngine to convert an XML descriptor a GUI.
     * 
	 * @return The attribute value.
	 */
	public Object evaluateAttribute(Attribute attr, SwingEngine<?> engine)
	{
		return ELUtility.evaluateAttribute(engine.getELProcessor(), attr);
	}

	/**
	 * Convert the value of the given <code>Attribute</code> object into an output object of the
	 * specified Swing type.
	 * 
	 * @param type {@link Class} Data type to which the Attribute's value should be converted.
	 * @param attr {@link Attribute} the attribute, providing the value to be converted.
	 * @param engine {@link SwingEngine} the rendering swingEngine to convert an XML descriptor a GUI.
	 * 
	 * @return The converted value.
	 * 
	 * @throws Exception When the {@link Attribute} cannot be converted.
	 * 
	 * @apiNote T The Swing conversion type.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public final T convert(Class<T> type, Attribute attr, SwingEngine<?> engine)
		throws Exception
	{
		if ( attr == null )
		{
			logger.warn("attribute is null!");
			return null;
		}
		
		final Object value = evaluateAttribute(attr, engine);
		if ( value == null )
			return null;
		if ( convertsTo().isInstance(value) )
			return (T) value;
		return convert(value.toString(), type, attr, engine);
	}
	
	/**
	 * Convert the value of the given <code>Attribute</code> object into an output object of the
	 * specified Swing type.
	 * 
	 * @param value {@link String} The evaluated value.
	 * @param type {@link Class} Data type to which the Attribute's value should be converted.
	 * @param attr {@link Attribute} The attribute, providing the value to be converted.
	 * @param engine {@link SwingEngine} the rendering swingEngine to convert an XML descriptor a GUI.
	 * 
	 * @return The converted value.
	 * 
	 * @throws Exception When the {@link Attribute} cannot be converted.
	 *  
	 * @apiNote T The Swing conversion type.
	 */
	public abstract T convert(String value, Class<T> type, Attribute attr, SwingEngine<?> engine)
		throws Exception;
}
