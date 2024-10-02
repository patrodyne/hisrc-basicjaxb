package org.swixml.plaf.basic;

import static java.awt.Color.HSBtoRGB;
import static java.awt.Color.RGBtoHSB;
import static java.lang.Float.valueOf;
import static java.lang.Math.round;
import static javax.swing.UIManager.getDefaults;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.MetalTheme;

/**
 * Hint: Set -Dswing.metalTheme=steel when using this {@link MetalTheme}
 */
public class MatteBasicTheme
{
	// Ceiling for floating point near zero.
	private static final float NEAR_ZERO = 0.001f;
	
	// Default saturation for colorizing grey colors.
	private static final float GREY_SAT = 0.25f;
	
	public String getName()
	{
		return "Matte";
	}
	
	private Float hue;
	public Float getHue() { return hue; }
	public void setHue(Float hue) { this.hue = hue; }
	
	private Float saturation;
	public Float getSaturation()
	{
		if ( saturation == null )
			setSaturation(GREY_SAT);
		return saturation;
	}
	public void setSaturation(Float saturation)
	{
		this.saturation = saturation;
	}

	private Float scale;
	public Float getScale() { return scale; }
	public void setScale(Float scale) { this.scale = scale; }
	
	private void resetPlafColor(Map<Object, Color> keyColorMap, float colorHue, float colorSat)
	{
		for (Map.Entry<Object, Object> entry : getDefaults().entrySet())
		{
		    Object key = entry.getKey();
		    if ( !keyColorMap.containsKey(key) )
		    {
			    Object value = UIManager.get(key);
			    if ( value instanceof ColorUIResource )
			    {
			    	ColorUIResource colorUI = (ColorUIResource) value;
			    	Color color = replaceHueSat(key, colorUI, colorHue, colorSat);
			        getDefaults().put(key, new ColorUIResource(color));
			        keyColorMap.put(key, color);
			    }
			    else if ( value instanceof List )
			    {
			    	@SuppressWarnings("unchecked")
					List<Object> items = (List<Object>) value;
			    	for ( int index = 0; index < items.size(); ++index )
			    	{
			    		Object item = items.get(index);
					    if ( item instanceof ColorUIResource )
					    {
					    	ColorUIResource colorUI = (ColorUIResource) item;
					    	Color color = replaceHueSat(key, colorUI, colorHue, colorSat);
							items.set(index, new ColorUIResource(color));
					    }
			    	}
			    }
		    }
		}
	}
	
	// Color (SV) hints by UI key.
	private static final Map<String, Float[]> COLOR_HINT_MAP = new LinkedHashMap<>();
	static
	{
		COLOR_HINT_MAP.put( "background", new Float[] { GREY_SAT, 1.000f } );
		COLOR_HINT_MAP.put( "darkshadow", new Float[] { GREY_SAT, 0.700f } );
		COLOR_HINT_MAP.put( "foreground", new Float[] { GREY_SAT, 0.200f } );
		COLOR_HINT_MAP.put( "highlight",  new Float[] { GREY_SAT, 0.300f } );
		COLOR_HINT_MAP.put( "dkshadow",   new Float[] { GREY_SAT, 0.700f } );
		COLOR_HINT_MAP.put( "shadow",     new Float[] { GREY_SAT, 0.800f } );
		COLOR_HINT_MAP.put( "black",      new Float[] { GREY_SAT, 0.000f } );
		COLOR_HINT_MAP.put( "color",      new Float[] { GREY_SAT, 0.500f } );
		COLOR_HINT_MAP.put( "focus",      new Float[] { GREY_SAT, 0.600f } );
		COLOR_HINT_MAP.put( "light",      new Float[] { GREY_SAT, 0.900f } );
		COLOR_HINT_MAP.put( "dark",       new Float[] { GREY_SAT, 0.100f } );
		COLOR_HINT_MAP.put( "text",       new Float[] { GREY_SAT, 0.000f } );
		COLOR_HINT_MAP.put( "mid",        new Float[] { GREY_SAT, 0.500f } );
	}
	
	private Color replaceHueSat(Object key, ColorUIResource colorUI, float colorHue, float colorSat)
	{
		float[] hsb = RGBtoHSB(colorUI.getRed(), colorUI.getGreen(), colorUI.getBlue(), null);
		
		float hue = colorHue;
		float sat = hsb[1];
		float brt = hsb[2];
		
		if ( hsb[1] < NEAR_ZERO )
			sat = colorSat;
		
		if ( hsb[2] < NEAR_ZERO )
		{
			if ( key instanceof String )
			{
				for ( Entry<String, Float[]> colorHint : COLOR_HINT_MAP.entrySet() )
				{
					String lcKey = ((String) key).toLowerCase();
					int dotIndex = lcKey.lastIndexOf('.');
					String lcSfx = lcKey;
					if ( dotIndex >= 0 )
						lcSfx = lcKey.substring(dotIndex+1);
					if ( lcSfx.endsWith(colorHint.getKey()) )
					{
						sat = colorHint.getValue()[0];
						brt = colorHint.getValue()[0];
						break;
					}
				}
			}
		}

		return new Color(HSBtoRGB(hue, sat, brt));
	}

	/**
	 * Reset the font size of all UI {@link FontUIResource}s.
	 * 
	 * @param scale The scale for Font point size.
	 */
	private void resetFontSize(Float scale)
	{
		for (Map.Entry<Object, Object> entry : getDefaults().entrySet())
		{
		    Object key = entry.getKey();
		    Object value = UIManager.get(key);
		    if ( value instanceof FontUIResource )
		    {
		        FontUIResource fr1= (FontUIResource) value;
		        int fontSize = scalePoints(fr1.getSize(), scale);
		        FontUIResource fr2= new FontUIResource(fr1.getFamily(), fr1.getStyle(), fontSize);
		        getDefaults().put(key, fr2);
		    }
		}
	}
	
	private int scalePoints(Object points, Float scale)
	{
		// widen the integer to a float during multiplication
		// then round to integer.
		return round( ((int) points) * getScale() );
	}
	
	private void customHue()
	{
		Map<Object, Color> keyColorMap = new HashMap<>();
		resetPlafColor(keyColorMap, getHue(), getSaturation());
	}

	private void customScale()
	{
		resetFontSize(getScale());
	}
	
	/**
	 * Construct with the given hue, scale and saturation
	 * as Strings.
	 * 
	 * @param hue The hue for colonization.
	 * @param scale The scale for Font point size.
	 * @param sat The sat for grey colorization.
	 */
	public MatteBasicTheme(String hue, String scale, String sat)
	{
		this
		(
			hue != null ? valueOf(hue) : null,
			scale != null ? valueOf(scale) : null,
			sat != null ? valueOf(sat) : null
		);
	}
	
	/**
	 * Construct with the given hue and scale
	 * as Strings.
	 * 
	 * @param hue The hue for colonization.
	 * @param scale The scale for Font point size.
	 */
	public MatteBasicTheme(String hue, String scale)
	{
		this
		(
			hue != null ? valueOf(hue) : null,
			scale != null ? valueOf(scale) : null
		);
	}
	
	/**
	 * Construct with the given hue and scale
	 * as Floats.
	 * 
	 * @param hue The hue for colonization.
	 * @param scale The scale for Font point size.
	 */
	public MatteBasicTheme(Float hue, Float scale)
	{
		this(hue, scale, null);
	}
	
	/**
	 * Construct with the given hue and scale
	 * as Floats.
	 * 
	 * @param hue The hue for colorization.
	 * @param scale The scale for Font point size.
	 * @param sat The sat for grey colorization.
	 */
	public MatteBasicTheme(Float hue, Float scale, Float sat)
	{
		this();
		
		setHue(hue/360.0f);
		setScale(scale);

		if ( sat != null )
			setSaturation(sat/100.0f);

		if ( getHue() != null )
			customHue();
		
		if ( getScale() != null )
			customScale();
	}
	
	/**
	 * Construct without arguments.
	 */
	public MatteBasicTheme()
	{
		super();
	}
}
