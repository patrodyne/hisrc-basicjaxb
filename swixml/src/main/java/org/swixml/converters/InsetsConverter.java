package org.swixml.converters;

import java.awt.Insets;
import java.util.StringTokenizer;

import org.swixml.SwingEngine;
import org.swixml.dom.Attribute;

/**
 * The <code>InsetsConverter</code> class defines a converter that creates
 * Insets objects based on a provided String.
 *
 * <p><b>Examples for Valid XML attribute notations:</b></p>
 * 
 * <ul>
 * <li>border="MatteBorder(4,4,4,4,red)"</li>
 * <li>insets="2,2,2,2"</li>
 * </ul>
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 *
 * @see java.awt.Insets
 * @see org.swixml.ConverterLibrary
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public class InsetsConverter extends AbstractConverter<Insets>
{
	/** converter's return type */
	public static final Class<Insets> TEMPLATE = Insets.class;

	/**
	 * Converts a Strings into an Insets object
	 *
	 * @param type <code>Class</code> not used
	 * @param attr <code>Attribute</code> value needs to provide String
	 *            containing comma sep. integers
	 * @return <code>Object</code> runtime type is subclass of
	 *         <code>Insets</code>
	 */
	@Override
	public Insets convert(String value, Class<Insets> type, final Attribute attr, SwingEngine<?> engine)
	{
		Insets insets = null;
		StringTokenizer st = new StringTokenizer(value, "(,)");
		if ( 5 == st.countTokens() )
		{ // assume "insets(...)"
			st.nextToken().trim();
		}
		int[] param = Util.ia(st);
		if ( 4 <= param.length )
		{
			insets = new Insets(param[0], param[1], param[2], param[3]);
		}
		return insets;
	}

	/**
	 * A <code>Converters</code> conversTo method informs about the Class type
	 * the converter is returning when its <code>convert</code> method is called
	 * 
	 * @return <code>Class</code> - the Class the converter is returning when
	 *         its convert method is called
	 */
	@Override
	public Class<Insets> convertsTo()
	{
		return TEMPLATE;
	}
}
