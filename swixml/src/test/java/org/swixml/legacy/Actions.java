package org.swixml.legacy;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import org.swixml.SwingEngine;

/**
 * The Actions class shows how to use the <code>Actions</code> and
 * <code>ActionCommand</code> attributes.
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 * @since swixml #065
 */
public class Actions extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 20240701L;

	// Instantiated by initclass="org.swixml.legacy.Actions$ComboModel"
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

	private SwingEngine<JFrame> swix;
	
	// SwingEngine parses "actions.xml" and binds "id"
	// components to these fields.
	
	public static Actions frame;
	public JMenuBar mb;
	public JMenu mu_file, mu_help;
	public JMenuItem mi_exit, mi_save, mi_help, mi_about;
	public JPanel pnl_North;
	public JToolBar tb;
	public JComboBox<Object> cbx;
	
	//
	// For every "Action", there needs to be a "public AbstractAction" member
	// variable with an anonymous inner class implementation.
	//
	
	/**
	 * <code>Action</code> newAction handles the <em>new</em> action attribute
	 */
	public Action newAction = new AbstractAction()
	{
		private static final long serialVersionUID = 20240701L;

		@Override
		public void actionPerformed(ActionEvent ae)
		{
			// show the access outer class member ..
			showMessageDialog(Actions.this, "'New' is not implemented.");
			// disables ALL buttons that are tied to this action
			this.setEnabled(false); 
		}
	};
	
	/**
	 * <code>Action</code> modifyAction handles the <em>modify</em> action
	 * attribute
	 */
	public Action openAction = new AbstractAction()
	{
		private static final long serialVersionUID = 20240701L;
		
		/** Invoked when an action occurs. */
		@Override
		public void actionPerformed(ActionEvent ae)
		{
			showMessageDialog(Actions.this, "'Open' is not implemented.");
		}
	};
	
	/** <code>Action</code> petAction handles the <em>combobox</em> */
	public Action petAction = new AbstractAction()
	{
		private static final long serialVersionUID = 20240701L;
		@Override
		public void actionPerformed(ActionEvent ae)
		{
			Object pet = ((JComboBox<?>) ae.getSource()).getSelectedItem();
			showMessageDialog(Actions.this, "'Pet' is " + pet);
		}
	};

	/**
	 * Constructs a new Actions object, registering action handlers for
	 * center_panel components.
	 * 
	 * @throws Exception When {@link SwingEngine} cannot render the XML.
	 */
	public Actions() throws Exception
	{
		System.out.println(ComboModel.class.getName());
		swix = new SwingEngine<>(this);
		swix.getELProcessor().defineBean("el", swix.getELMethods());
		swix.getELProcessor().defineBean("window", this);
		swix.render("org/swixml/legacy/actions.xml");
		setLocationRelativeTo(null);
		
		// at this point all AbstractActions are linked with the buttons
		// in id="pnl_North".
		swix.setActionListener(pnl_North, this);

		// ActionCommands however need to be linked manually, see below ...
		// add this class as an action listener to all buttons inside the
		// panel with the id = center_panel

		// add these classes as an action listener to MenuItem with the
		// id =  mi_exit.
		mi_exit.addActionListener(this);
		// id = mi_save
		mi_save.addActionListener(this);
		// id = mi_help
		mi_help.addActionListener(this);
		// id = mi_about
		mi_about.addActionListener(this);

		//
		// Note: The mi_about MenuItem was not linked at all so far.
		// Therefore, no action is performed when this menu item gets
		// requested. 2024-10-17, mi_help and mi_about have been linked!
		//
		// The Toolbar button with the Actions="newAction" attribute is
		// covered twice, during parsing the AbstactAction newAction is
		// linked in and later, the setActionListener() adds this object's
		// actionPerformed(). Therefore, when clicked, both actionPerformed()
		// methods are getting called; but, command is null on one.
		//
	}
	
	//
	// Implement ActionListener
	//

	/**
	 * Invoked when an action occurs because
	 * Actions implements ActionListener, here.
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		String command = ae.getActionCommand();
		if ( command != null )
		{
			switch (command)
			{
				case "AC_EXIT":
					showMessageDialog(Actions.this, "Good Bye!");
					this.windowClosing(null);
					break;
				case "AC_ABOUT":
				case "AC_HELP":
				case "AC_SAVE":
					showMessageDialog(Actions.this, command + " is not implemented.");
					break;
				default:
					showMessageDialog(Actions.this, "'Click' is not implemented.");
					break;
			}
		}
	}

	/**
	 * Invoked when the user attempts to close the window from the window's
	 * system menu. If the program does not explicitly hide or dispose the
	 * window while processing this event, the window close operation will be
	 * cancelled.
	 */
	public void windowClosing(WindowEvent we)
	{
		frame.setVisible(false);
		System.exit(0);
	}
	
	//
	// Make the class bootable
	//
	public static void main(String[] args)
	{
		SwingEngine.DEBUG_MODE = true;
		SwingEngine.invokeLater(Actions.class);
	}
}
