package org.swixml.el;

import static java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment;
import static java.lang.Math.round;
import static java.lang.Math.toIntExact;
import static java.lang.String.format;
import static javax.swing.UIManager.getDefaults;
import static javax.swing.UIManager.getLookAndFeelDefaults;
import static org.jvnet.basicjaxb.lang.StringUtils.isBlank;
import static org.swixml.Parser.ELVAR_DOM_ATTRIBUTE;
import static org.swixml.Parser.ELVAR_DOM_ELEMENT;
import static org.swixml.SwingEngine.DEFAULT_COLOR_KEY;
import static org.swixml.SwingEngine.DEFAULT_FONT_KEY;
import static org.swixml.converters.AbstractConverter.PRECENT_FORMAT;
import static org.swixml.converters.DimensionConverter.isZero;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			defaultFont = getDefaults().getFont(DEFAULT_FONT_KEY);
			if ( defaultFont == null )
			{
				// getLookAndFeelDefaults() returns a UIDefaults that is created
				// by a custom LAF at the initialization time. The LAF
				// itself is not monitoring the changes for this table. But
				// UIManager gives a value from this table whenever requested by
				// a component before looking at the one maintained by it.
				defaultFont = getLookAndFeelDefaults().getFont(DEFAULT_FONT_KEY);
			}
		}
		return defaultFont;
	}
	public void setDefaultFont(Font defaultFont)
	{
		this.defaultFont = defaultFont;
	}
	
	private Color defaultColor = null;
	public Color getDefaultColor()
	{
		if ( defaultColor == null )
		{
			defaultColor = getDefaults().getColor(DEFAULT_COLOR_KEY);
			if ( defaultColor == null )
				defaultColor = getLookAndFeelDefaults().getColor(DEFAULT_COLOR_KEY);
		}
		return defaultColor;
	}
	public void setDefaultColor(Color defaultColor)
	{
		this.defaultColor = defaultColor;
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
			font = FontConverter.convert(fontSpec, parentFont());
		else
			font = currentFont();
		return getFontMetrics(font);
	}
	
	private Element parentElement(Element element)
	{
		if ( element.getParentNode() instanceof Element )
			element = (Element) element.getParentNode();
		else
			element = null;
		return element;
	}
	
	private Map<Element, Font> fontMap;
	public Map<Element, Font> getFontMap()
	{
		if ( fontMap == null )
			setFontMap(new HashMap<>());
		return fontMap;
	}
	public void setFontMap(Map<Element, Font> fontMap)
	{
		this.fontMap = fontMap;
	}

	/**
	 * Determine the parent font from the fontMap cache.
	 * 
	 * @return the parent or default font.
	 */
	public Font parentFont()
	{
		Element domElement = resolveVariable(ELVAR_DOM_ELEMENT, Element.class);
		return parentFont(domElement);
	}

	/**
	 * Determine the parent font from the fontMap cache
	 * and the given DOM element.
	 * 
	 * @param domElement A DOM element.
	 * 
	 * @return the parent or default font.
	 */
	private Font parentFont(Element domElement)
	{
		Font parentFont = getDefaultFont();
		if ( domElement != null )
		{
			Element element = parentElement(domElement);
			while ( element != null )
			{
				if ( getFontMap().containsKey(element) )
				{
					parentFont = getFontMap().get(element);
					element = null;
				}
				else
					element = parentElement(element);
				
			};
		}
		return parentFont;
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
		Element domElement = resolveVariable(ELVAR_DOM_ELEMENT, Element.class);
		if ( domElement != null )
		{
			if ( getFontMap().containsKey(domElement) )
				currentFont = getFontMap().get(domElement);
			else
			{
				Font parentFont = parentFont(domElement);
				String fontSpec = domElement.getAttribute("font");
				if ( !isBlank(fontSpec) )
				{
					Font font = FontConverter.convert(fontSpec, parentFont);
					if ( font != null )
						currentFont = font;
				}
				else
					currentFont = parentFont;
				getFontMap().put(domElement, currentFont);
			}
		}
		return currentFont;
	}
	
	/**
	 * Encode a font name and font style and the current font size into
	 * the {@link FontConverter} format and convert to a {@link Font}.
	 * 
	 * @param name The font name ("Monospaced", "SansSerif", "Serif", "Dialog", etc).
	 * @param style The font style ("PLAIN", "BOLD", "ITALIC", "BOLDITALIC")
	 * 
	 * @return The {@link FontConverter} representation.
	 */
	public Font font(String name, String style)
	{
		return font(format("%s-%s-%s", name, style, "*"));
	}
	
	/**
	 * Encode a font name, font style and font size into
	 * the {@link FontConverter} format and convert to a {@link Font}.
	 * 
	 * @param name The font name ("Monospaced", "SansSerif", "Serif", "Dialog", etc).
	 * @param style The font style ("PLAIN", "BOLD", "ITALIC", "BOLDITALIC")
	 * @param size The font size (in points).
	 * 
	 * @return The {@link FontConverter} representation.
	 */
	public Font font(String name, String style, int size)
	{
		return font(format("%s-%s-%02d", name, style, size));
	}
	
	private Font font(String fontSpec)
	{
		Font font = FontConverter.convert(fontSpec, parentFont());
		Element domElement = resolveVariable(ELVAR_DOM_ELEMENT, Element.class);
		if ( domElement != null )
		{
			if ( !getFontMap().containsKey(domElement) )
				getFontMap().put(domElement, font);
		}
		return font;
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
	private int fontHeight(Font font)
	{
		return getFontMetrics(font).getHeight();
	}
	
	/**
	 * Gets the standard height of a line of text in the current font.
	 * 
	 * @return The standard height (pixels) of a line of text in the current font.
	 */
	public int fontHeight()
	{
		return fontHeight((String) null);
	}
	
	/**
	 * The current font size in points;
	 */
	public Integer fontSize()
	{
		return currentFont().getSize();
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
	private int fieldWidth(Font font, String text)
	{
		if ( (text == null) || text.isBlank() )
			text = AVERAGE_LETTER;
		return getFontMetrics(font).stringWidth(text);
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
		return fieldWidth((String) null, text);
	}
	
	/**
	 * Gets the repeated width of a text field in the given or current font. 
	 * 
	 * @param fontSpec The font to use or null to use the current font.
	 * @param text The text to determine the length for.
	 * @param repeat The number of times to repeat the single width.
	 * 
	 * @return The repeated width (pixels) of a text field in the given or current font.
	 */
	public int fieldWidth(String fontSpec, String text, int repeat)
	{
		return fieldWidth(fontSpec, text) * repeat;
	}
	private int fieldWidth(Font font, String text, int repeat)
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
			return fieldWidth((String) null, text, repeat);
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
	public <VT> VT resolveVariable(String varName, Class<VT> type)
	{
		VT value = null;
		VariableMapper vm = getELContext().getVariableMapper();
		ValueExpression ve = vm.resolveVariable(varName);
		if ( ve != null )
		{
			Class<?> expectedType = ve.getExpectedType();
			if ( type.isAssignableFrom(expectedType) )
				value = (VT) ve.getValue(getELContext());
		}
		return value;
	}
	
	@SuppressWarnings("unchecked")
	public <BT> BT resolveBean(String beanName, Class<BT> type)
	{
		try
		{
			return (BT) getELProcessor().getValue(beanName, type);
		}
		catch ( Exception ex )
		{
			return null;
		}
	}
	
	/**
	 * Set the 'bindWith' path to the given value then
	 * return the 'bindWith path.
	 * 
	 * @param path The 'bindWith' path.
	 * @param value The 'bindWith' value.
	 * 
	 * @return The given 'bindWith' path.
	 */
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
	
	/**
	 * Set the 'bindList' path to the given value then
	 * return the 'bindList path.
	 * 
	 * @param path The 'bindList' path.
	 * @param value The 'bindList' value.
	 * 
	 * @return The given 'bindList' path.
	 */
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
	private Dimension pageSize(Font font, int cols, int rows, int rowgap, String text)
	{
		int width = fieldWidth(font, text, cols);
		int height = rows * ( fontHeight(font) + rowgap);
		return size(width, height);
	}
	
	public Dimension pageSize(String fontSpec, int cols, int rows, int rowgap)
	{
		return pageSize(fontSpec, cols, rows, rowgap, AVERAGE_LETTER);
	}
	private Dimension pageSize(Font font, int cols, int rows, int rowgap)
	{
		return pageSize(font, cols, rows, rowgap, AVERAGE_LETTER);
	}
	
	public Dimension pageSize(String fontSpec, int cols, int rows)
	{
		return pageSize(fontSpec, cols, rows, 0);
	}
	private Dimension pageSize(Font font, int cols, int rows)
	{
		return pageSize(font, cols, rows, 0);
	}
	
	public Dimension pageSize(int cols, int rows)
	{
		return pageSize(currentFont(), cols, rows);
	}
	
	public int scaleSizeWidth(String pw)
	{
		return scaleSizeWidth(parsePercent(pw));
	}
	
	public int scaleSizeWidth(double scale)
	{
		double sw = currentSize().getWidth();
		return toIntExact(round(scale * sw));
	}
	
	public int scaleSizeHeight(String ph)
	{
		return scaleSizeHeight(parsePercent(ph));
	}
	
	public int scaleSizeHeight(double scale)
	{
		return toIntExact(round(scale * currentSize().getHeight()));
	}
	
	public Dimension scaleSize(String pw, String ph)
	{
		double dw = parsePercent(pw);
		double dh = parsePercent(ph);
		return scaleSize(dw, dh);
	}
	
	private double parsePercent(String pct)
	{
		try
		{
			return PRECENT_FORMAT.parse(pct).doubleValue();
		}
		catch (ParseException pe)
		{
			throw new IllegalArgumentException("Cannot parse size: " + pct, pe);
		}
	}

	public Dimension scaleSize(String ps)
	{
		return scaleSize(ps, ps);
	}
	
	public Dimension scaleSize(double widthScale, double heightScale)
	{
		return scaleSize(currentSize(), widthScale, heightScale);
	}
	
	public Dimension scaleSize(double scale)
	{
		return scaleSize(scale, scale);
	}
	
	public Dimension scaleSize(Window window, String pw, String ph)
	{
		try
		{
			double dw = PRECENT_FORMAT.parse(pw).doubleValue();
			double dh = PRECENT_FORMAT.parse(ph).doubleValue();
			return scaleSize(window, dw, dh);
		}
		catch (ParseException pe)
		{
			throw new IllegalArgumentException("Cannot parse size", pe);
		}
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
	
	public Dimension currentSize()
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
						
						if ( getSizeMap().containsKey(element) )
							size = getSizeMap().get(element);
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
		Long value = round( scale * (maxValue - minValue) );
		return minValue + toIntExact(value);
	}
	
	public Rectangle scaleBounds(String px, String py, String pw, String ph)
	{
		try
		{
			double dx = PRECENT_FORMAT.parse(px).doubleValue();
			double dy = PRECENT_FORMAT.parse(py).doubleValue();
			double dw = PRECENT_FORMAT.parse(pw).doubleValue();
			double dh = PRECENT_FORMAT.parse(ph).doubleValue();
			return scaleBounds(currentSize(), dx, dy, dw, dh);
		}
		catch (ParseException pe)
		{
			throw new IllegalArgumentException("Cannot parse bounds", pe);
		}
	}
	
	public Rectangle scaleBounds(double dx, double dy, double dw, double dh)
	{
		return scaleBounds(currentSize(), dx, dy, dw, dh);
	}
	
	private Rectangle scaleBounds(Dimension size, double dx, double dy, double dw, double dh)
	{
		double sw = size.getWidth();
		double sh = size.getHeight();
		
		int bx = toIntExact(round(dx * sw));
		int by = toIntExact(round(dy * sh));
		int bw = toIntExact(round(dw * sw));
		int bh = toIntExact(round(dh * sh));
		
		return new Rectangle(bx, by, bw, bh);
	}
	
	public Rectangle centerBounds(int bw, int bh)
	{
		// FIXME: Need component's center point
		// Rectangle bounds = (Rectangle) getSwingEngine().getELProcessor().getValue("this.getBounds()", Rectangle.class);
		Point cp = getLocalGraphicsEnvironment().getCenterPoint();
		int bx = toIntExact(round(cp.x - (bw / 2.0)));
		int by = toIntExact(round(cp.y - (bh / 2.0)));
		return new Rectangle(bx, by, bw, bh);
	}
	
	public Rectangle centerBounds(double dw, double dh)
	{
		return centerBounds(toIntExact(round(dw)), toIntExact(round(dh)));
	}
	
	public Rectangle centerBounds(Dimension size, String pw, String ph)
	{
		try
		{
			double sw = size.getWidth();
			double sh = size.getHeight();
			double dw = PRECENT_FORMAT.parse(pw).doubleValue();
			double dh = PRECENT_FORMAT.parse(ph).doubleValue();
			return centerBounds(dw * sw, dh *sh);
		}
		catch (ParseException pe)
		{
			throw new IllegalArgumentException("Cannot parse bounds", pe);
		}
	}
	
	public Rectangle centerBounds(String pw, String ph)
	{
		return centerBounds(currentSize(), pw, ph);
	}
}
