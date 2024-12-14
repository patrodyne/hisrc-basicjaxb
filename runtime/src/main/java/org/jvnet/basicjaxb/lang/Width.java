package org.jvnet.basicjaxb.lang;

/**
 * Represents the minimum and maximum widths, in character count.
 * 
 * <p>Typically, the minimum width is used to display a field value
 * and the maximum width is used to store a field value.</p>.
 */
public class Width
{
	private int min = 0;
	public int getMin() { return min; }
	public void setMin(int min) { this.min = min; }
	
	private int max = 0;
	public int getMax() { return max; }
	public void setMax(int max) { this.max = max; }
	
	/**
	 * The preferred width is used for the initial display of
	 * a resizable field.
	 * 
	 * @return The preferred width for initial display.
	 */
	public int getPreferred() { return getMin(); }
	
	/**
	 * Construct a {@code Width} instance for the given values.
	 * 
	 * @param min The minimum width is used when displaying a field value.
	 * @param max The maximum width is used when storing a field value.
	 */
	public Width(int min, int max)
	{
		setMin(min);
		setMax(max);
	}
	
	/**
	 * Construct a {@code Width} instance for the given value.
	 * 
	 * @param width The minimum width is used when displaying a field value.
	 */
	public Width(int width)
	{
		setMin(width);
		setMax(width);
	}
	
	public String toString()
	{
		return "width[min="+getMin()+", max="+getMax()+"]";
	}
}
