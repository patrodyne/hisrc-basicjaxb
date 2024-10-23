package org.swixml.legacy;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
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
	
	public Action okAction = new AbstractAction()
	{
		private static final long serialVersionUID = 20240701L;
		@Override
		public void actionPerformed(ActionEvent ae)
		{
			showMessageDialog(XPanel.this, "Sorry, 'OK' is not implemented yet.");
		}
	};
	
	public Action cancelAction = new AbstractAction()
	{
		private static final long serialVersionUID = 20240701L;
		@Override
		public void actionPerformed(ActionEvent ae)
		{
			showMessageDialog(XPanel.this, "Sorry, 'Cancel' is not implemented yet.");
		}
	};
}
