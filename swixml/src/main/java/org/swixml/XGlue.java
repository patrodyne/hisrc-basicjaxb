package org.swixml;

import java.awt.Dimension;

import javax.swing.Box;

/**
 * Creates a Glue for BoxLayout.
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 * 
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public class XGlue extends Box.Filler
{
	private static final long serialVersionUID = 20240701L;
	public XGlue()
	{
		super(new Dimension(0, 0), new Dimension(0, 0), new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
	}
}
