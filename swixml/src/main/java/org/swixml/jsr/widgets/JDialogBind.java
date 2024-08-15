package org.swixml.jsr.widgets;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.KeyStroke;

import org.jdesktop.application.Application;
import org.jdesktop.application.ApplicationActionMap;

/**
 * @see <a href="file:../../package-info.java">LICENSE: package-info</a>
 * @author softphone
 */
public class JDialogEx extends JDialog
{
	private static final long serialVersionUID = 20240701L;
	protected JButton defaultButton;

	public JDialogEx()
	{
		super();
	}

	public JDialogEx(Dialog owner, boolean modal)
	{
		super(owner, modal);
	}

	public JDialogEx(Dialog owner, String title, boolean modal, GraphicsConfiguration gc)
	{
		super(owner, title, modal, gc);
	}

	public JDialogEx(Dialog owner, String title, boolean modal)
	{
		super(owner, title, modal);
	}

	public JDialogEx(Dialog owner, String title)
	{
		super(owner, title);
	}

	public JDialogEx(Dialog owner)
	{
		super(owner);
	}

	public JDialogEx(Frame owner, boolean modal)
	{
		super(owner, modal);
	}

	public JDialogEx(Frame owner, String title, boolean modal, GraphicsConfiguration gc)
	{
		super(owner, title, modal, gc);
	}

	public JDialogEx(Frame owner, String title, boolean modal)
	{
		super(owner, title, modal);
	}

	public JDialogEx(Frame owner, String title)
	{
		super(owner, title);
	}

	public JDialogEx(Frame owner)
	{
		super(owner);
	}

	public JDialogEx(Window owner, ModalityType mt)
	{
		super(owner, mt);
	}

	public JDialogEx(Window owner, String title, ModalityType mt, GraphicsConfiguration gc)
	{
		super(owner, title, mt, gc);
	}

	public JDialogEx(Window owner, String arg1, ModalityType arg2)
	{
		super(owner, arg1, arg2);
	}

	public JDialogEx(Window owner, String title)
	{
		super(owner, title);
	}

	public JDialogEx(Window owner)
	{
		super(owner);
	}

	@Override
	public void addNotify()
	{
		KeyStroke escape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
		InputMap im = rootPane.getInputMap();
		im.put(enter, "enterAction");
		im.put(escape, "escapeAction");
		if ( defaultButton != null )
			rootPane.setDefaultButton(defaultButton);
		ActionMap am = rootPane.getActionMap();
		ApplicationActionMap aam = Application.getInstance().getContext().getActionMap(this);
		Action escapeAction = aam.get("escapeAction");
		if ( escapeAction != null )
		{
			am.put("escapeAction", escapeAction);
		}
		Action enterAction = aam.get("enterAction");
		if ( enterAction != null )
		{
			am.put("enterAction", enterAction);
		}
		super.addNotify();
	}
}
