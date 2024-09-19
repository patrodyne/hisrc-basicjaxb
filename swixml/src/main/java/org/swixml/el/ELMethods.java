package org.swixml.el;

import static javax.swing.UIManager.getDefaults;
import static javax.swing.UIManager.getLookAndFeelDefaults;
import static org.swixml.Parser.ELVAR_DOM_ATTRIBUTE;
import static org.swixml.Parser.ELVAR_DOM_ELEMENT;
import static org.swixml.converters.DimensionConverter.isZero;
import static org.swixml.converters.FontConverter.encode;
import static org.swixml.jsr295.BindingUtils.isELPattern;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Window;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import org.swixml.SwingEngine;
import org.swixml.converters.DimensionConverter;
import org.swixml.converters.FontConverter;
import org.swixml.jsr296.SwingApplication;
import org.w3c.dom.Attr;
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
	
	// Represents the widest, average and thinest letters in the alphabet.
	public static final String WIDEST_LETTER = "W";
	public static final String AVERAGE_LETTER = "e";
	public static final String THINEST_LETTER = "l";

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
	protected Font getDefaultFont()
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
	protected void setDefaultFont(Font defaultFont)
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
	public FontMetrics getFontMetrics(Font font)
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
					element = parentElement(element);
				
			} while ( element != null );
		}
		return currentFont;
	}
	
	private Element parentElement(Element element)
	{
		if ( element.getParentNode() instanceof Element )
			element = (Element) element.getParentNode();
		else
			element = null;
		return element;
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
			text = AVERAGE_LETTER;
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
	 * <p>If <code>text</code> is a single character then it will be used
	 * to the text to determine the length for; otherwise, it will
	 * be used as the font specification</p>
	 * 
	 * @param text The text to determine the length for or font specification.
	 * @param repeat The number of times to repeat the single width.
	 * 
	 * @return The repeated width (pixels) of the text field for the current font.
	 */
	public int fieldWidth(String text, int repeat)
	{
		if ( (text != null) && (text.length() == 1) ) 
			return fieldWidth(null, text, repeat);
		else
			return fieldWidth(text, AVERAGE_LETTER, repeat);
	}
	
	/**
	 * Gets the repeated width of an average letter for the current font. 
	 * 
	 * @param repeat The number of times to repeat the single width.
	 * 
	 * @return The repeated width (pixels) of a average letter for the current font.
	 */
	public int fieldWidth(int repeat)
	{
		return fieldWidth(AVERAGE_LETTER, repeat);
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
	
	public Dimension size()
	{
		return currentSize();
	}
	
	public Dimension size(int width, int height)
	{
		Dimension size = new Dimension(width, height);
		Attr domAttribute = resolveVariable(ELVAR_DOM_ATTRIBUTE, Attr.class);
		if ( domAttribute != null )
		{
			if ( domAttribute.getName().equalsIgnoreCase("size") )
			{
				Element domElement = resolveVariable(ELVAR_DOM_ELEMENT, Element.class);
				getSizeMap().put(domElement, size);
			}
		}
		return size;
	}
	
	public Dimension pageSize(String fontSpec, int cols, int rows, int rowgap, String text)
	{
		int width = fieldWidth(fontSpec, text, cols);
		int height = rows * ( fontHeight(fontSpec) + rowgap);
		return size(width, height);
	}

	public Dimension pageSize(String fontSpec, int cols, int rows, int rowgap)
	{
		return pageSize(fontSpec, cols, rows, rowgap, AVERAGE_LETTER);
	}

	public Dimension pageSize(String fontSpec, int cols, int rows)
	{
		return pageSize(fontSpec, cols, rows, 0);
	}

	public Dimension pageSize(int cols, int rows)
	{
		return pageSize(encode(currentFont()), cols, rows, 0);
	}
	
	public Dimension scaleSize(double widthScale, double heightScale)
	{
		return scaleSize(currentSize(), widthScale, heightScale);
	}
	
	public Dimension scaleSize(Window window, double widthScale, double heightScale)
	{
		return scaleSize(window.getSize(), widthScale, heightScale);
	}
	
	private Dimension scaleSize(Dimension size, double widthScale, double heightScale)
	{
		int width = (int) (widthScale * size.getWidth());
		int height = (int) (heightScale * size.getHeight());
		return size(width, height);
	}
	
	private Map<Element, Dimension> sizeMap;
	public Map<Element, Dimension> getSizeMap()
	{
		if ( sizeMap == null )
			setSizeMap(new HashMap<>());
		return sizeMap;
	}
	public void setSizeMap(Map<Element, Dimension> sizeMap)
	{
		this.sizeMap = sizeMap;
	}
	
	private Dimension currentSize()
	{
		Dimension currentDimension = new Dimension();
		Attr domAttribute = resolveVariable(ELVAR_DOM_ATTRIBUTE, Attr.class);
		if ( domAttribute != null )
		{
			Element domElement = resolveVariable(ELVAR_DOM_ELEMENT, Element.class);
			// Guard against recursion
			Element element = domElement;
//			if ( domAttribute.getName().equalsIgnoreCase("size") )
//				element = parentElement(domElement);
			if ( element != null )
			{
				do
				{
					String sizeSpec = element.getAttribute("size");
					if ( !sizeSpec.isBlank() )
					{
						Dimension size = null;
						
						if ( isELPattern(sizeSpec) )
						{
							if ( getSizeMap().containsKey(element) )
								size = getSizeMap().get(element);
						}
						else
						{
							size = DimensionConverter.convert(sizeSpec);
							if ( !isZero(size) )
								if ( element != domElement )
									getSizeMap().put(element, size);
						}
						
						if ( !isZero(size) )
						{
							currentDimension = size;
							getSizeMap().put(domElement, size);
							element = null;
						}
						else
							element = parentElement(element);
					}
					else
						element = parentElement(element);
				} while ( element != null );
			}
		}
		return currentDimension;
	}
	
	public int scale(int minValue, int maxValue, double scale)
	{
		Long value = Math.round( scale * (maxValue - minValue) );
		return Math.toIntExact(value);
	}
}
