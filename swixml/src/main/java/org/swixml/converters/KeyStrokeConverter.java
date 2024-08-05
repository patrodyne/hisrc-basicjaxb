package org.swixml.converters;

import javax.swing.KeyStroke;

import org.swixml.Localizer;
import org.swixml.Parser;
import org.swixml.SwingEngine;
import org.swixml.dom.Attribute;

/**
 * A Converter that turns a Strings in the form of a filename into an KeyStroke
 * objects.
 * 
 *   <p><b>Valid syntax includes:</b></p>
 *   <ul>
 *     <li>accelerator="F7"</li>
 *     <li>accelerator="control N"</li>
 *     <li>accelerator="alt A"</li>
 *   </ul>
 * 
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @author <a href="mailto:Eric.LAFARGUE@cec.eu.int">Eric Lafargue</a>
 * 
 * @version $Revision: 1.1 $
 * 
 * @see javax.swing.KeyStroke#getKeyStroke(java.lang.String)
 * @see org.swixml.ConverterLibrary
 */
public class KeyStrokeConverter extends AbstractConverter<KeyStroke>
{
	/** converter's return type */
	public static final Class<KeyStroke> TEMPLATE = KeyStroke.class;

	/**
	 * Converts a String into an KeyStroke through a Resource lookup
	 * 
	 * @param value <code>String</code> not used
	 * @param type <code>Class</code> not used
	 * @param attr <code>Attribute</code> attribute provides the value to be converted
	 * @param engine Provides <code>Localizer</code> allow the use of resource lookups
	 * 
	 * @return <code>Object</code> - a <code>KeyStroke</code>
	 */
	@Override
	public KeyStroke convert(String value, Class<KeyStroke> type, final Attribute attr, SwingEngine<?> engine)
	{
		return KeyStrokeConverter.conv(type, attr, engine);
	}

	/**
	 * Converts a String into an KeyStroke.
	 *
	 * <p>Parses a string and returns a <code>KeyStroke</code>. The string must
	 * have the following syntax:</p>
	 * 
	 * <pre>
	 *    &lt;modifiers&gt;* (&lt;typedID&gt; | &lt;pressedReleasedID&gt;)
	 *
	 *    modifiers := shift | control | ctrl | meta | alt | button1 | button2 | button3
	 *    typedID := typed &lt;typedKey&gt;
	 *    typedKey := string of length 1 giving Unicode character.
	 *    pressedReleasedID := (pressed | released) key
	 *    key := KeyEvent key code name, i.e. the name following "VK_".
	 * </pre>
	 * 
	 * <p>If typed, pressed or released is not specified, pressed is assumed. Here
	 * are some examples:</p>
	 * 
	 * <pre>
	 *     "INSERT" => getKeyStroke(KeyEvent.VK_INSERT, 0);
	 *     "control DELETE" => getKeyStroke(KeyEvent.VK_DELETE, InputEvent.CTRL_MASK);
	 *     "alt shift X" => getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK | InputEvent.SHIFT_MASK);
	 *     "alt shift released X" => getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK | InputEvent.SHIFT_MASK, true);
	 *     "typed a" => getKeyStroke('a');
	 * </pre>
	 *
	 * <p>In order to maintain backward-compatibility, specifying a null String, or
	 * a String which is formatted incorrectly, returns null.</p>
	 *
	 * @param type <code>Class</code> not used
	 * @param attr <code>Attribute</code> attribute provides the value to be converted
	 * @param engine Provides <code>Localizer</code> allow the use of resource lookups
	 * 
	 * @return <code>Object</code> - a <code>KeyStroke</code> object for that
	 *         String, or null if the specified String is null, or is formatted
	 *         incorrectly
	 */
	public static KeyStroke conv(final Class<KeyStroke> type, final Attribute attr, SwingEngine<?> engine)
	{
		KeyStroke keyStroke = null;
		final Localizer localizer = Util.getLocalizer(engine);
		if ( attr != null )
		{
			if ( Parser.LOCALIZED_ATTRIBUTES.contains(attr.getLocalName().toLowerCase()) )
			{
				attr.setValue(localizer.getString(attr.getValue()));
			}
			keyStroke = KeyStroke.getKeyStroke(attr.getValue());
		}
		return keyStroke;
	}

	/**
	 * A <code>Converters</code> conversTo method informs about the Class type
	 * the converter is returning when its <code>convert</code> method is called
	 * 
	 * @return <code>Class</code> - the Class the converter is returning when
	 *         its convert method is called
	 */
	@Override
	public Class<KeyStroke> convertsTo()
	{
		return TEMPLATE;
	}
}
