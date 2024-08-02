package org.swixml.legacy;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.swixml.SwingEngine;

/**
 * The Accelerator shows in the usage of accelerators.
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 *
 * @since swixml (#101)
 */
public class Accelerator extends JDialog
{
	private static final long serialVersionUID = 20240701L;

	private static final String DESCRIPTOR = "org/swixml/legacy/accelerator.xml";
	
	private SwingEngine<JDialog> swix = new SwingEngine<>(this);

	public Accelerator()
		throws Exception
	{
		swix.render(Accelerator.DESCRIPTOR).setVisible(true);
		setLocationRelativeTo(null);
//		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public Action newAction = new AbstractAction()
	{
		private static final long serialVersionUID = 20240701L;
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JOptionPane.showMessageDialog(Accelerator.this, "Sorry, not implemented yet.");
		}
	};
	public Action aboutAction = new AbstractAction()
	{
		private static final long serialVersionUID = 20240701L;
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JOptionPane.showMessageDialog(Accelerator.this, "This is the Accelerator Example.");
		}
	};
	public Action exitAction = new AbstractAction()
	{
		private static final long serialVersionUID = 20240701L;
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JOptionPane.showMessageDialog(Accelerator.this, "Sorry, not implemented yet.");
		}
	};

	public static void main(String[] args)
	{
		try
		{
			new Accelerator();
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
}
