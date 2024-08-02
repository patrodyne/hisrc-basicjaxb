package org.swixml;

import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

import com.jgoodies.forms.factories.DefaultComponentFactory;

/**
 * A wrapper class for JGoodies Forms
 * <code>ComponentFactory.createSeparator()</code>, which creates a titled
 * separator.
 * 
 * @author Karl Tauber
 * 
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public class XTitledSeparator extends JComponent
{
	private static final long serialVersionUID = 20240701L;
	private String text;
	private int alignment = SwingConstants.LEFT;

	public XTitledSeparator()
	{
		super();
		setLayout(new BorderLayout());
		update();
	}

	private void update()
	{
		removeAll();
		add(DefaultComponentFactory.getInstance().createSeparator(text, alignment));
	}

	/**
	 * Returns the title of the separator.
	 */
	public String getText()
	{
		return text;
	}

	/**
	 * Sets the title of the separator.
	 */
	public void setText(String text)
	{
		this.text = text;
		update();
	}

	/**
	 * Returns the title alignment. One of <code>SwingConstants.LEFT</code>,
	 * <code>SwingConstants.CENTER</code> or <code>SwingConstants.RIGHT</code>.
	 */
	public int getAlignment()
	{
		return alignment;
	}

	/**
	 * Sets the title alignment. One of <code>SwingConstants.LEFT</code>,
	 * <code>SwingConstants.CENTER</code> or <code>SwingConstants.RIGHT</code>.
	 */
	public void setAlignment(int alignment)
	{
		this.alignment = alignment;
		update();
	}
}
