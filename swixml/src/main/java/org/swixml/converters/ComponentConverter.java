package org.swixml.converters;

import java.awt.Component;
import org.swixml.Converter;

import org.swixml.SwingEngine;
import org.swixml.dom.Attribute;

/**
 * The ComponentConverter class defines a dummy converter It's simply here to
 * allow the registration of setter-methods excepting Components
 * 
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 */
public class ComponentConverter implements Converter<Component>
{
	/**
	 * converter's return type
	 */
	public static final Class<Component> TEMPLATE = Component.class;

	/**
	 * Convert the value of the given <code>Attribute</code> object into an
	 * output object of the specified type.
	 *
	 * @param attr <code>Attribute</code> the attribute, providing the value to
	 *            be converted.
	 */
	public static Component conv(Attribute attr)
		throws Exception
	{
		return null;
	}

	/**
	 * Convert the value of the given <code>Attribute</code> object into an
	 * output object of the specified type.
	 *
	 * @param type <code>Class</code> Data type to which the Attribute's value
	 *            should be converted
	 * @param attr <code>Attribute</code> the attribute, providing the value to
	 *            be converted.
	 */
	@Override
	public Component convert(Class<Component> type, Attribute attr, SwingEngine<?> engine)
		throws Exception
	{
		return ComponentConverter.conv(attr);
	}

	/**
	 * A <code>Converters</code> conversTo method informs about the Class type
	 * the converter is returning when its <code>convert</code> method is called
	 *
	 * @return <code>Class</code> - the Class the converter is returning when
	 *         its convert method is called
	 */
	@Override
	public Class<Component> convertsTo()
	{
		return TEMPLATE;
	}
}
