package org.swixml;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 * 
 * @author softphone
 */
public class XFrame extends JFrame
{
	private static final long serialVersionUID = 20240501L;
	Dimension _size;

	@Override
	public void setSize(Dimension value)
	{
		_size = new Dimension(value.width, value.height);
	}

	@Override
	public void addNotify()
	{
		super.addNotify();
		if ( _size != null )
		{
			super.setSize(_size);
		}
	}
}
