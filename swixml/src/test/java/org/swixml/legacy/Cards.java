package org.swixml.legacy;

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

	public Cards()
		throws Exception
	{
		swix.getELProcessor().defineBean("el", swix.getELMethods());
		swix.getELProcessor().defineBean("window", this);
		swix.render(Cards.DESCRIPTOR);
		setLocationRelativeTo(null);
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

	public static void main(String[] args)
	{
		SwingEngine.invokeLater(Cards.class);
	}
}
