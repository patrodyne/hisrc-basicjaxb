package org.swixml.plaf.metal;

import static java.awt.Color.HSBtoRGB;
import static java.awt.Font.BOLD;
import static java.awt.Font.PLAIN;
import static java.lang.Float.valueOf;
import static java.lang.Math.round;
import static javax.swing.UIManager.getDefaults;
import static org.swixml.SwingEngine.DEFAULT_COLOR_KEY;
import static org.swixml.SwingEngine.DEFAULT_FONT_KEY;

import java.awt.Font;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;

public class MatteMetalTheme extends DefaultMetalTheme
{
	// Private Fonts: name, style, size
	private static final Object[] FONT_DIALOG_PLAIN = { "Dialog", PLAIN, 12 };
	private static final Object[] FONT_DIALOG_BOLD1 = { "Dialog", BOLD,  12 };
	private static final Object[] FONT_DIALOG_BOLD2 = { "Dialog", BOLD,  10 };

	// Public Fonts: name, style, size
	private static final Object[] FONT_CONTROL_TEXT = FONT_DIALOG_PLAIN;
	private static final Object[] FONT_SYSTEM_TEXT  = FONT_DIALOG_PLAIN;
	private static final Object[] FONT_USER_TEXT    = FONT_DIALOG_PLAIN;
	private static final Object[] FONT_MENU_TEXT    = FONT_DIALOG_PLAIN;
	private static final Object[] FONT_WINDOW_TEXT  = FONT_DIALOG_BOLD1;
	private static final Object[] FONT_SUB_TEXT     = FONT_DIALOG_BOLD2;

	private static class FontSpec
	{
		private String name;
		private int style;
		private int size;
		private FontSpec(Object[] specs)
		{
			if ( (specs == null) || (specs.length < 3) )
				specs = FONT_SYSTEM_TEXT;
			
			this.name = (String) specs[0];
			this.style = (int) specs[1];
			this.size = (int) specs[2];
		}
	}
	
	private static Font toFont(Object[] specs)
	{
		FontSpec fs = new FontSpec(specs);
		return new Font(fs.name, fs.style, fs.size);
	}
	
	private Font toScaledFont(Object[] specs)
	{
		FontSpec fs = new FontSpec(specs);
		
		if ( (getScale() != null) && getScale() > 0.0f )
			fs.size = scalePoints(fs.size);
		
		return new Font(fs.name, fs.style, fs.size);
	}
	
	public static FontUIResource getDefaultFontUIResource()
	{
		return new FontUIResource(toFont(FONT_SYSTEM_TEXT));
	}
	
	private void customScale()
	{
		getDefaults().put(DEFAULT_FONT_KEY, getSystemTextFont());
		// See also toFont(int)
	}
	
	// Public Colors: Hue [0 -> 360], Saturation [0, 100], Value [0, 100]
	private static final float[] HSV_PRIMARY1    = { 195f, 34.0f,  34.0f };
	private static final float[] HSV_PRIMARY2    = { 195f, 30.0f,  62.0f };
	private static final float[] HSV_PRIMARY3    = { 195f, 28.0f,  78.0f };
	private static final float[] HSV_SECONDARY1  = { 195f, 44.0f,  44.0f };
	private static final float[] HSV_SECONDARY2  = { 195f, 31.0f,  70.0f };
	private static final float[] HSV_SECONDARY3  = { 195f, 21.0f,  91.0f };
	private static final float[] HSV_BLACK       = { 195f,  0.0f,   0.0f };
	private static final float[] HSV_WHITE       = { 195f,  0.0f, 100.0f };
	
	public static ColorUIResource getDefaultColorUIResource()
	{
		return new ColorUIResource(hsb2rgb(HSV_PRIMARY1));
	}
	
	private void customHue()
	{
		if ( getHue() >= 0f && getHue() <= 360f)
		{
			HSV_PRIMARY1[0] = getHue();
			HSV_PRIMARY2[0] = getHue();
			HSV_PRIMARY3[0] = getHue();
			HSV_SECONDARY1[0] = getHue();
			HSV_SECONDARY2[0] = getHue();
			HSV_SECONDARY3[0] = getHue();
			HSV_BLACK[0] = getHue();
			HSV_WHITE[0] = getHue();
			getDefaults().put(DEFAULT_COLOR_KEY, getPrimary1());
		}
	}
	
    @Override
	public String getName()
	{
		return "Matte";
	}
	
	private FontUIResource controlTextFont = null;
	@Override
	public FontUIResource getControlTextFont()
	{
		if ( controlTextFont == null )
			setControlTextFont(new FontUIResource(toScaledFont(FONT_CONTROL_TEXT)));
		return controlTextFont;
	}
	public void setControlTextFont(FontUIResource controlTextFont)
	{
		this.controlTextFont = controlTextFont;
	}

	private FontUIResource systemTextFont = null;
	@Override
	public FontUIResource getSystemTextFont()
	{
		if ( systemTextFont == null )
			setSystemTextFont(new FontUIResource(toScaledFont(FONT_SYSTEM_TEXT)));
		return systemTextFont;
	}
	public void setSystemTextFont(FontUIResource systemTextFont)
	{
		this.systemTextFont = systemTextFont;
	}

	private FontUIResource userTextFont = null;
	@Override
	public FontUIResource getUserTextFont()
	{
		if ( userTextFont == null )
			setUserTextFont(new FontUIResource(toScaledFont(FONT_USER_TEXT)));
		return userTextFont;
	}
	public void setUserTextFont(FontUIResource userTextFont)
	{
		this.userTextFont = userTextFont;
	}

	private FontUIResource menuTextFont = null;
	@Override
	public FontUIResource getMenuTextFont()
	{
		if ( menuTextFont == null )
			setMenuTextFont(new FontUIResource(toScaledFont(FONT_MENU_TEXT)));
		return menuTextFont;
	}
	public void setMenuTextFont(FontUIResource menuTextFont)
	{
		this.menuTextFont = menuTextFont;
	}

	private FontUIResource windowTitleFont = null;
	@Override
	public FontUIResource getWindowTitleFont()
	{
		if ( windowTitleFont == null )
			setWindowTitleFont(new FontUIResource(toScaledFont(FONT_WINDOW_TEXT)));
		return windowTitleFont;
	}
	public void setWindowTitleFont(FontUIResource windowTitleFont)
	{
		this.windowTitleFont = windowTitleFont;
	}

	private FontUIResource subTextFont = null;
	@Override
	public FontUIResource getSubTextFont()
	{
		if ( subTextFont == null )
			setSubTextFont(new FontUIResource(toScaledFont(FONT_SUB_TEXT)));
		return subTextFont;
	}
	public void setSubTextFont(FontUIResource subTextFont)
	{
		this.subTextFont = subTextFont;
	}
	
	private ColorUIResource primary1 = null;
    @Override
	public ColorUIResource getPrimary1()
	{
    	if ( primary1 == null )
    		setPrimary1(new ColorUIResource(hsb2rgb(HSV_PRIMARY1)));
		return primary1;
	}
	public void setPrimary1(ColorUIResource primary1)
	{
		this.primary1 = primary1;
	}
	
	private ColorUIResource primary2 = null;
    @Override
	public ColorUIResource getPrimary2()
	{
    	if ( primary2 == null )
    		setPrimary2(new ColorUIResource(hsb2rgb(HSV_PRIMARY2)));
		return primary2;
	}
	public void setPrimary2(ColorUIResource primary2)
	{
		this.primary2 = primary2;
	}
	
	private ColorUIResource primary3 = null;
    @Override
	public ColorUIResource getPrimary3()
	{
    	if ( primary3 == null )
    		setPrimary3(new ColorUIResource(hsb2rgb(HSV_PRIMARY3)));
		return primary3;
	}
	public void setPrimary3(ColorUIResource primary3)
	{
		this.primary3 = primary3;
	}
	
	private ColorUIResource secondary1 = null;
    @Override
	public ColorUIResource getSecondary1()
	{
    	if ( secondary1 == null )
    		setSecondary1(new ColorUIResource(hsb2rgb(HSV_SECONDARY1)));
		return secondary1;
	}
	public void setSecondary1(ColorUIResource secondary1)
	{
		this.secondary1 = secondary1;
	}
	
	private ColorUIResource secondary2 = null;
    @Override
	public ColorUIResource getSecondary2()
	{
    	if ( secondary2 == null )
    		setSecondary2(new ColorUIResource(hsb2rgb(HSV_SECONDARY2)));
		return secondary2;
	}
	public void setSecondary2(ColorUIResource secondary2)
	{
		this.secondary2 = secondary2;
	}
	
	private ColorUIResource secondary3 = null;
    @Override
	public ColorUIResource getSecondary3()
	{
    	if ( secondary3 == null )
    		setSecondary3(new ColorUIResource(hsb2rgb(HSV_SECONDARY3)));
		return secondary3;
	}
	public void setSecondary3(ColorUIResource secondary3)
	{
		this.secondary3 = secondary3;
	}
	
    private ColorUIResource black = null;
    @Override
	public ColorUIResource getBlack()
	{
    	if ( black == null )
    		setBlack(new ColorUIResource(hsb2rgb(HSV_BLACK)));
		return black;
	}
	public void setBlack(ColorUIResource black)
	{
		this.black = black;
	}

	private ColorUIResource white = null;
	@Override
	public ColorUIResource getWhite()
	{
    	if ( white == null )
    		setWhite(new ColorUIResource(hsb2rgb(HSV_WHITE)));
		return white;
	}
	public void setWhite(ColorUIResource white)
	{
		this.white = white;
	}
	
	private static int hsb2rgb(float[] hsv)
	{
		float hue = (hsv[0]/360f);
		float sat = (hsv[1]/100.0f);
		float val = (hsv[2]/100.0f);
		return HSBtoRGB( hue, sat, val );
	}
	
	private Float hue;
	public Float getHue() { return hue; }
	public void setHue(Float hue) { this.hue = hue; }
	
	private Float scale;
	public Float getScale() { return scale; }
	public void setScale(Float scale) { this.scale = scale; }

	private int scalePoints(int points)
	{
		// widen the integer to a float during multiplication
		// then round to integer.
		return round( points * getScale() );
	}
	
	public MatteMetalTheme(String hue, String scale)
	{
		this
		(
			hue != null ? valueOf(hue) : null,
			scale != null ? valueOf(scale) : null
		);
	}
	
	public MatteMetalTheme(Float hue, Float scale)
	{
		this();
		
		setHue(hue);
		setScale(scale);
		
		if ( getHue() != null )
			customHue();
		
		if ( getScale() != null )
			customScale();
	}
	
	public MatteMetalTheme()
	{
		super();
	}
}
