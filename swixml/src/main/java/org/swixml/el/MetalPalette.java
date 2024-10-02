package org.swixml.el;

import java.awt.Color;

public enum MetalPalette
{
	TEXT1(0.0f, 0.00f, 0.00f),
	TEXT2(0.0f, 0.00f, 0.24f),
	DRAG(0.0f, 0.00f, 0.25f),
	GRID(0.0f, 0.00f, 0.50f),
	SCROLLBAR(0.0f, 0.00f, 0.88f),
	DESKTOP(0.0f, 0.00f, 0.98f),
	TEXTBG(0.0f, 0.00f, 1.00f),
	SELECTION(15.5f, 0.86f, 0.91f),
	DOCK(0.0f, 1.00f, 1.00f);
	
	private float hue;
	public float getHue() { return hue; }
	public void setHue(float hue) { this.hue = hue; }

	private float sat;
	public float getSat() { return sat; }
	public void setSat(float sat) { this.sat = sat; }

	private float val;
	public float getVal() { return val; }
	public void setVal(float val) { this.val = val; }

	private MetalPalette(float hue, float sat, float val)
	{
		setHue(hue);
		setSat(sat);
		setVal(val);
	}
	
	public Color getHSBColor()
	{
		return Color.getHSBColor(hue, sat, val);
	}

	public Color newHSBColor(float hue, float sat)
	{
		Color newColor = null;
		switch ( this )
		{
			case DOCK:
				newColor = Color.getHSBColor(hue, getSat(), getVal());
				break;
			case SELECTION:
				newColor = Color.getHSBColor(hue, getSat(), getVal());
				break;
			default:
				newColor = Color.getHSBColor(hue, sat, getVal());
				break;
		}
		return newColor;
	}
}
