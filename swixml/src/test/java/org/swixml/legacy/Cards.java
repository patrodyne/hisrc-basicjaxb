package org.swixml.legacy;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.swixml.SwingEngine;

/**
 * The <code>Cards</code> class shows an example for the usage of a CardLayout.
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 *
 * @since swixml #109
 */
public class Cards extends JFrame
{
	private static final long serialVersionUID = 20240701L;
	
	private static final String DESCRIPTOR = "org/swixml/legacy/cards.xml";
	
	private SwingEngine<JFrame> swix = new SwingEngine<>(this);
	
	/** panel with a CardLayout */
	public JPanel c1, pnl;

	private Cards()
		throws Exception
	{
		swix.getELProcessor().defineBean("el", swix.getELMethods());
		swix.getELProcessor().defineBean("window", this);
		swix.render(Cards.DESCRIPTOR);
		setLocationRelativeTo(null);
		setVisible(true);
		this.showAction.actionPerformed(null);
	}

	/** shows the next card */
	public Action nextAction = new AbstractAction()
	{
		private static final long serialVersionUID = 20240701L;
		@Override
		public void actionPerformed(ActionEvent e)
		{
			CardLayout cl = (CardLayout) (pnl.getLayout());
			cl.next(pnl);
		}
	};
	
	/** shows the card with the id requested in the action command */
	public Action showAction = new AbstractAction()
	{
		private static final long serialVersionUID = 20240701L;
		@Override
		public void actionPerformed(ActionEvent e)
		{
			// System.err.println( "ActionCommand=" + e.getActionCommand() );
			CardLayout cl = (CardLayout) (pnl.getLayout());
			if ( e != null )
			{
				cl.show(pnl, e.getActionCommand());
			}
		}
	};
	
	private static void showErrorDialog(Exception ex)
	{
		ex.printStackTrace();
		String msg = ex.getClass().getSimpleName() + ": " + ex.getMessage() +"\n";
		showMessageDialog(null, msg, "ERROR", ERROR_MESSAGE);
	}
	
	public static void main(String[] args)
	{
		try
		{
			new Cards();
		}
		catch (Exception ex)
		{
			System.err.println(ex.getMessage());
			showErrorDialog(ex);
		}
	}
}
