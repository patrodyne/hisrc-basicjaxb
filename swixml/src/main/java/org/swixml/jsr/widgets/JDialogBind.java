package org.swixml.jsr.widgets;

import static java.awt.event.KeyEvent.VK_ENTER;
import static java.awt.event.KeyEvent.VK_ESCAPE;
import static javax.swing.KeyStroke.getKeyStroke;
import static org.jdesktop.application.Application.getInstance;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.KeyStroke;

import org.jdesktop.application.ApplicationActionMap;

/**
 * @see <a href="file:../../package-info.java">LICENSE: package-info</a>
 * @author softphone
 */
public class JDialogBind extends JDialog
{
	private static final long serialVersionUID = 20240701L;
	
	private JButton defaultButton;
	public JButton getDefaultButton() { return defaultButton; }
	public void setDefaultButton(JButton defaultButton) { this.defaultButton = defaultButton; }

	public JDialogBind()
	{
		super();
	}

	public JDialogBind(Dialog owner, boolean modal)
	{
		super(owner, modal);
	}

	public JDialogBind(Dialog owner, String title, boolean modal, GraphicsConfiguration gc)
	{
		super(owner, title, modal, gc);
	}

	public JDialogBind(Dialog owner, String title, boolean modal)
	{
		super(owner, title, modal);
	}

	public JDialogBind(Dialog owner, String title)
	{
		super(owner, title);
	}

	public JDialogBind(Dialog owner)
	{
		super(owner);
	}

	public JDialogBind(Frame owner, boolean modal)
	{
		super(owner, modal);
	}

	public JDialogBind(Frame owner, String title, boolean modal, GraphicsConfiguration gc)
	{
		super(owner, title, modal, gc);
	}

	public JDialogBind(Frame owner, String title, boolean modal)
	{
		super(owner, title, modal);
	}

	public JDialogBind(Frame owner, String title)
	{
		super(owner, title);
	}

	public JDialogBind(Frame owner)
	{
		super(owner);
	}

	public JDialogBind(Window owner, ModalityType mt)
	{
		super(owner, mt);
	}

	public JDialogBind(Window owner, String title, ModalityType mt, GraphicsConfiguration gc)
	{
		super(owner, title, mt, gc);
	}

	public JDialogBind(Window owner, String arg1, ModalityType arg2)
	{
		super(owner, arg1, arg2);
	}

	public JDialogBind(Window owner, String title)
	{
		super(owner, title);
	}

	public JDialogBind(Window owner)
	{
		super(owner);
	}

	@Override
	public void addNotify()
	{
		KeyStroke escape = getKeyStroke(VK_ESCAPE, 0);
		KeyStroke enter = getKeyStroke(VK_ENTER, 0);
		InputMap im = rootPane.getInputMap();
		im.put(enter, "enterAction");
		im.put(escape, "escapeAction");
		
		if ( getDefaultButton() != null )
			rootPane.setDefaultButton(getDefaultButton());
		
		ActionMap am = rootPane.getActionMap();
		ApplicationActionMap aam = getInstance().getContext().getActionMap(this);
		
		Action escapeAction = aam.get("escapeAction");
		if ( escapeAction != null )
			am.put("escapeAction", escapeAction);
		
		Action enterAction = aam.get("enterAction");
		if ( enterAction != null )
			am.put("enterAction", enterAction);
		
		super.addNotify();
	}
}
