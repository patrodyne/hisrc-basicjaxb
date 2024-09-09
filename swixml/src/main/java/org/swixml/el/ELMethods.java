package org.swixml.el;

import static javax.swing.UIManager.getDefaults;
import static javax.swing.UIManager.getLookAndFeelDefaults;
import static org.swixml.Parser.ELVAR_DOM_ELEMENT;

import java.awt.Container;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.List;
import java.util.Map;

import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import org.swixml.SwingEngine;
import org.swixml.converters.FontConverter;
import org.swixml.jsr296.SwingApplication;
import org.w3c.dom.Element;

import jakarta.el.ELContext;
import jakarta.el.ELProcessor;
import jakarta.el.ValueExpression;
import jakarta.el.VariableMapper;

/**
 * Expression Language methods.
 * 
 * <p>
 * Register an instance of this class in your {@link SwingApplication} implementation:
 * </p>
 * {@code getELProcessor().defineBean("el", getSwingEngine().getELMethods()); }
 */
public class ELMethods<T extends Container>
{
	// Represents the font to use as the default font.
	private static final String DEFAULT_FONT = "Panel.font";
	// Represents the widest letter in the alphabet.
	public static final String WIDEST_LETTER = "W";

	/**
	 * Construct with a {@link SwingEngine}.
	 * 
	 * @param engine A {@link SwingEngine} instance.
	 */
	public ELMethods(SwingEngine<T> engine)
	{
		setSwingEngine(engine);
	}
	
	private SwingEngine<T> swingEngine;
	public SwingEngine<T> getSwingEngine()
	{
		return swingEngine;
	}
	public void setSwingEngine(SwingEngine<T> swingEngine)
	{
		this.swingEngine = swingEngine;
	}

	public T getContainer() { return getSwingEngine().getClient(); }
	public ELContext getELContext() { return getSwingEngine().getELContext(); }
	public ELProcessor getELProcessor() { return getSwingEngine().getELProcessor(); }
	
	private Font defaultFont = null;
	public Font getDefaultFont()
	{
	    // In general, developers should use the {@code UIDefaults} returned from
	    // {@code getDefaults()}. As the current look and feel may expect
	    // certain values to exist, altering the {@code UIDefaults} returned
	    // from this method could have unexpected results.
		if ( defaultFont == null )
		{
			// getUIDefaults() returns a UIDefaults that is created by UIManager.
			// This table is monitored by the custom LAF (using PropertyChangeListener)
			// and the LAF updates its internal default values. The LAF uses these
			// internal values for UI and doesn't refer back to get the values
			// from UIManager. 
			defaultFont = getDefaults().getFont(DEFAULT_FONT);
			if ( defaultFont == null )
			{
				// getLookAndFeelDefaults() returns a UIDefaults that is created
				// by a custom LAF at the initialization time. The LAF
				// itself is not monitoring the changes for this table. But
				// UIManager gives a value from this table whenever requested by
				// a component before looking at the one maintained by it.
				defaultFont = getLookAndFeelDefaults().getFont(DEFAULT_FONT);
			}
		}
		return defaultFont;
	}
	public void setDefaultFont(Font defaultFont)
	{
		this.defaultFont = defaultFont;
	}
	
	/**
	 * Get the graphics context for the container.
	 * 
	 * @return The graphics context for the container.
	 */
	private Graphics getGraphics()
	{
		return getContainer().getGraphics();
	}
	
	/**
	 * Get information about the rendering of a particular or current font.
	 * 
	 * @param font The font to use for the metrics or null for the current font.
	 * 
	 * @return The information about the rendering of a particular or current font.
	 */
	private FontMetrics getFontMetrics(Font font)
	{
		FontMetrics metrics = null;
		if ( getGraphics() != null )
		{
			// get metrics from the graphics
			metrics = (font != null)
				? getGraphics().getFontMetrics(font)
				: getGraphics().getFontMetrics();
		}
		else
			metrics = getContainer().getFontMetrics(font);
		return metrics;
	}
	
	/**
	 * Get information about the rendering of a particular or current font.
	 * 
	 * @param fontSpec The font specification to use for the metrics or null for the current font.
	 * 
	 * @return The information about the rendering of a particular or current font.
	 */
	private FontMetrics getFontMetrics(String fontSpec)
	{
		Font font = null;
		if ( fontSpec != null )
			font = FontConverter.convert(fontSpec, fontSize());
		else
			font = currentFont();
		return getFontMetrics(font);
	}

	/**
	 * Determine the current font as either an EL variable reference or
	 * the UI default font.
	 * 
	 * @return the current or default font.
	 */
	public Font currentFont()
	{
		Font currentFont = getDefaultFont();
		int defaultFontSize = currentFont.getSize();
		Element domElement = resolveVariable(ELVAR_DOM_ELEMENT, Element.class);
		if ( domElement != null )
		{
			Element element = domElement;
			do
			{
				String fontSpec = element.getAttribute("font");
				if ( !fontSpec.isBlank() )
				{
					Font font = FontConverter.convert(fontSpec, defaultFontSize);
					if ( font != null )
					{
						currentFont = font;
						element = null;
					}
				}
				else
				{
					if ( element.getParentNode() instanceof Element )
						element = (Element) element.getParentNode();
					else
						element = null;
				}
				
			} while ( element != null );
		}
		return currentFont;
	}

	/**
	 * Gets the standard height (pixels) of a line of text in this font. 
	 * 
	 * @param fontSpec The font specification to use or null to use the current font.
	 * 
	 * @return The standard height (pixels) of a line of text in this font.
	 */
	public int fontHeight(String fontSpec)
	{
		return getFontMetrics(fontSpec).getHeight();
	}

	/**
	 * Gets the standard height of a line of text in the current font.
	 * 
	 * @return The standard height (pixels) of a line of text in the current font.
	 */
	public int fontHeight()
	{
		return fontHeight(null);
	}
	
	/**
	 * The current font size in points;
	 */
	public Integer fontSize()
	{
		return currentFont().getSize();
	}
	
	/**
	 * Reset the font size of all UI {@link FontUIResource}s
	 * 
	 * @param fontSize The new font size.
	 */
	public void resetFontSize(Integer fontSize)
	{
		if ( fontSize != null )
		{
			for (Map.Entry<Object, Object> entry : getDefaults().entrySet())
			{
			    Object key = entry.getKey();
			    Object value = UIManager.get(key);
			    if ( value instanceof FontUIResource )
			    {
			        FontUIResource fr1= (FontUIResource) value;
			        FontUIResource fr2= new FontUIResource(fr1.getFamily(), fr1.getStyle(), fontSize);
			        getDefaults().put(key, fr2);
			    }
			}
		}
	}
	
	/**
	 * Calculate the current font size plus a gap, in points.
	 * 
	 * @param gap The number of points to add to the point size.
	 * 
	 * @return The current font size plus a gap, in points
	 */
	public Integer fieldHeight(int gap)
	{
		return fontSize()+gap;
	}
	
	/**
	 * Gets the standard width of a text field in the given or current font. 
	 * 
	 * @param fontSpec The font specification to use or null to use the current font.
	 * @param text The text to provide the length.
	 * 
	 * @return The standard length (pixels) of a text field in the given or current font.
	 */
	public int fieldWidth(String fontSpec, String text)
	{
		if ( (text == null) || text.isBlank() )
			text = WIDEST_LETTER;
		return getFontMetrics(fontSpec).stringWidth(text);
	}
	
	/**
	 * Gets the standard width (pixels) of a text field in the current font. 
	 * 
	 * @param text The text to determine the length for.
	 * 
	 * @return The standard width (pixels) of a text field in the current font.
	 */
	public int fieldWidth(String text)
	{
		return fieldWidth(null, text);
	}
	
	/**
	 * Gets the repeated width of a text field in the given or current font. 
	 * 
	 * @param font The font to use or null to use the current font.
	 * @param text The text to determine the length for.
	 * @param repeat The number of times to repeat the single width.
	 * 
	 * @return The repeated width (pixels) of a text field in the given or current font.
	 */
	public int fieldWidth(String font, String text, int repeat)
	{
		return fieldWidth(font, text) * repeat;
	}
	
	/**
	 * Gets the repeated width of the text field for the current font. 
	 * 
	 * @param text The text to determine the length for.
	 * @param repeat The number of times to repeat the single width.
	 * 
	 * @return The repeated width (pixels) of the text field for the current font.
	 */
	public int fieldWidth(String text, int repeat)
	{
		if ( (text != null) && (text.length() == 1) ) 
			return fieldWidth(null, text, repeat);
		else
			return fieldWidth(text, WIDEST_LETTER, repeat);
	}
	
	/**
	 * Gets the repeated width of "W" for the current font. 
	 * 
	 * @param repeat The number of times to repeat the single width.
	 * 
	 * @return The repeated width (pixels) of a "W" for the current font.
	 */
	public int fieldWidth(int repeat)
	{
		return fieldWidth(WIDEST_LETTER, repeat);
	}
	
	@SuppressWarnings("unchecked")
	private <VT> VT resolveVariable(String varname, Class<VT> type)
	{
		VT value = null;
		VariableMapper vm = getELContext().getVariableMapper();
		ValueExpression ve = vm.resolveVariable(varname);
		if ( ve != null )
		{
			Class<?> expectedType = ve.getExpectedType();
			if ( type.isAssignableFrom(expectedType) )
				value = (VT) ve.getValue(getELContext());
		}
		return value;
	}
	
	public String bindWith(String path, String value)
	{
		String bindWith = null;
		if ( (path != null) && !path.isBlank() )
		{
			int lastDot = path.lastIndexOf('.');
			if ( lastDot >= 0 )
				bindWith = path.substring(lastDot+1);
			getELProcessor().setValue(path, value);
		}
		return bindWith;
	}
	
	public List<String> bindList(String path, List<String> value)
	{
		List<String> bindList = value;
		if ( (path != null) && !path.isBlank() )
			getELProcessor().setValue(path, value);
		return bindList;
	}
	
//	public List<String> bindList(String path, String value)
//	{
//		List<String> bindList = null;
//		if ( (path != null) && !path.isBlank() )
//		{
//			String trimValue = trim(value, "[]");
//			bindList = asList(trimValue.split(",", -1));
//			getELProcessor().eval(value + ".stream().toList()");
//			getELProcessor().setValue(path, bindList);
//		}
//		return bindList;
//	}
}
