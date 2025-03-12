package org.swixml.legacy;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import org.swixml.SwingEngine;

/**
 * The InitClass class demonstrates how to use the initclass attribute. Date:
 * Mar 10, 2003
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 * @since swixml 0.76
 */
public class InitClass extends JFrame
{
	private static final long serialVersionUID = 20240701L;

	public static class ComboModel extends DefaultComboBoxModel<Object>
	{
		private static final long serialVersionUID = 20240701L;
		
		/**
		 * Constructs a DefaultComboBoxModel object.
		 */
		public ComboModel()
		{
			super(new Object[] { "Bird", "Cat", "Dog", "Rabbit", "Pig" });
		}
	}
	
	public Action DO_SELECT = new AbstractAction()
	{
		private static final long serialVersionUID = 20240701L;
		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.out.println(((JComboBox<?>) e.getSource()).getSelectedItem().toString());
		}
	};

	public InitClass()
		throws Exception
	{
		SwingEngine<JFrame> swix = new SwingEngine<>(this);
		swix.getELProcessor().defineBean("el", swix.getELMethods());
		swix.getELProcessor().defineBean("window", this);
		swix.render("org/swixml/legacy/initclass.xml");
		setLocationRelativeTo(null);
	}

	public static void main(String[] args)
	{
		SwingEngine.invokeLater(InitClass.class);
	}
}
