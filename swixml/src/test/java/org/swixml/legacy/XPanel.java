package org.swixml.legacy;

import javax.swing.JPanel;

import org.swixml.SwingEngine;

/**
 * This file contains proprietary information of CarlsbadCubes.
 * 
 * <pre>
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2002-2003
 * </pre>
 *
 * Date: Feb 28, 2003
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 * @since
 */
public class XPanel extends JPanel
{
	private static final long serialVersionUID = 20240701L;
	private SwingEngine<JPanel> swix = new SwingEngine<>(this);

	public void setXml(String resource)
	{
		try
		{
			swix.insert("org/swixml/legacy/" + resource, this);
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
}
