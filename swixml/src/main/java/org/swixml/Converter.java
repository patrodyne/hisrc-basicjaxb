package org.swixml;

import org.swixml.dom.Attribute;

/**
 * <p>
 * General purpose data type converter that can be registered and used within
 * the SwingEngine package to manage the conversion of objects from one type to
 * another.
 * </p>
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.2 $
 * 
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 * 
 * @apiNote T The Swing conversion type.
 */
public interface Converter<T>
{
	/**
	 * Convert the value of the given <code>Attribute</code> object into an
	 * output object of the specified Swing type.
	 *
	 * @param type <code>Class</code> Data type to which the Attribute's value
	 *            should be converted
	 * @param attr <code>Attribute</code> the attribute, providing the value to
	 *            be converted.
	 * 
	 * @return T The Swing conversion type.
	 */
	public T convert(final Class<T> type, final Attribute attr, SwingEngine<?> engine)
		throws Exception;

	/**
	 * A <code>Converters</code> conversTo method informs about the Class type
	 * the converter is returning when its <code>convert</code> method is
	 * called.
	 * 
	 * @return <code>Class</code> - the Class the converter is returning when
	 *         its convert method is called
	 */
	public Class<T> convertsTo();
}
