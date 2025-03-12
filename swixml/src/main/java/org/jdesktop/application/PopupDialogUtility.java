package org.jdesktop.application;

import static java.awt.Font.MONOSPACED;
import static java.awt.Font.PLAIN;
import static java.awt.event.ActionEvent.CTRL_MASK;
import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import static java.awt.event.KeyEvent.VK_A;
import static java.awt.event.KeyEvent.VK_C;
import static java.awt.event.KeyEvent.VK_V;
import static java.awt.event.KeyEvent.VK_X;
import static java.awt.event.KeyEvent.VK_Y;
import static java.awt.event.KeyEvent.VK_Z;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.KeyStroke.getKeyStroke;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import javax.swing.undo.UndoManager;

/**
 * Utility class to add a standard popup menu to any {@link JTextComponent}
 * and/or provide a scrollable text area.
 * 
 * Ref: https://stackoverflow.com/questions/9021269
 */
public class PopupDialogUtility
{
	private static int WIDTH = 680;
	private static int HEIGHT = 420;
	
	public enum PaneSize
	{
		SMALL, MEDIUM, LARGE;
	}
	
	/**
	 * Add the given message to a scrollable text area.
	 * 
	 * @param msg The message to be displayed in a {@link JScrollPane}.
	 * 
	 * @return A {@link JScrollPane} containing the {@link JTextArea}.
	 */
	public static JScrollPane toScrollableTextArea(String msg)
	{
		return toScrollableTextArea(msg, PaneSize.MEDIUM);
	}
	
	/**
	 * Add the given message to a scrollable text area.
	 * 
	 * @param msg The message to be displayed in a {@link JScrollPane}.
	 * @param size The relative size of the {@link JScrollPane}.
	 * 
	 * @return A {@link JScrollPane} containing the {@link JTextArea}.
	 */
	public static JScrollPane toScrollableTextArea(String msg, PaneSize size)
	{
		JScrollPane jsp = null;
		switch (size)
		{
			case SMALL: jsp = toScrollableTextArea(msg, WIDTH/2, HEIGHT/2); break;
			case MEDIUM: jsp = toScrollableTextArea(msg, WIDTH, HEIGHT); break;
			case LARGE: jsp = toScrollableTextArea(msg, WIDTH*2, HEIGHT*2); break;
		}
		return jsp;
	}
	
	/**
	 * Add the given message to a scrollable text area.
	 * 
	 * @param msg The message to be displayed in a {@link JScrollPane}.
     * @param width the specified width.
     * @param height the specified height.
	 * 
	 * @return A {@link JScrollPane} containing the {@link JTextArea}.
	 */
	public static JScrollPane toScrollableTextArea(String msg, int width, int height)
	{
		JTextArea jta = new JTextArea(msg);
		jta.setFont(new Font(MONOSPACED, PLAIN, jta.getFont().getSize()));
		jta.setTabSize(4);
		JScrollPane jsp = new JScrollPane(jta)
		{
			private static final long serialVersionUID = 20250301L;

			@Override
			public Dimension getPreferredSize()
			{
				return new Dimension(width, height);
			}
		};
		return jsp;
	}
	
	/**
	 * Create a scrollable text area with a common popup menu for the given message.
	 * 
	 * @param msg The message to be displayed in a {@link JScrollPane}.
	 * @param size The relative size of the {@link JScrollPane}.
     * 
	 * @return A scrollable text area with a common popup menu for the given message.
	 */
	public static JScrollPane toScrollableTextAreaWithCommonMenu(String msg, PaneSize size)
	{
		JScrollPane jsp = null;
		switch (size)
		{
			case SMALL: jsp  = toScrollableTextAreaWithCommomMenu(msg, WIDTH/2, HEIGHT/2); break;
			case MEDIUM: jsp = toScrollableTextAreaWithCommomMenu(msg, WIDTH, HEIGHT); break;
			case LARGE: jsp = toScrollableTextAreaWithCommomMenu(msg, WIDTH*2, HEIGHT*2); break;
		}
		return jsp;
	}

	/**
	 * Create a scrollable text area with a common popup menu for the given message.
	 * 
	 * @param msg The message to be displayed in a {@link JScrollPane}.
     * @param width the specified width.
     * @param height the specified height.
     * 
	 * @return A scrollable text area with a common popup menu for the given message.
	 */
	public static JScrollPane toScrollableTextAreaWithCommomMenu(String msg, int width, int height)
	{
		// Build a scroll pane to display the trace in a text area.
		JScrollPane jsp = toScrollableTextArea(msg, width, height);
		JTextArea jta =	(JTextArea) jsp.getViewport().getView();
		
		// Add a common popup menu to the text area.
		addCommonPopupMenuTo(jta);
		
		return jsp;
	}

	/**
	 * Add a common popup menu to the given {@link JTextComponent}.
	 * 
	 * @param textComponent The base class for {@link JTextField}, {@link JTextArea}, etc..
	 */
	public static void addCommonPopupMenuTo(JTextComponent textComponent)
	{
		JPopupMenu popup = new JPopupMenu();
		UndoManager undoManager = new UndoManager();
		textComponent.getDocument().addUndoableEditListener(undoManager);
		
		Action undoAction = new AbstractAction("Undo")
		{
			private static final long serialVersionUID = 20250301L;
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				performUndo(undoManager);
			}
		};
		Action redoAction = new AbstractAction("Redo")
		{
			private static final long serialVersionUID = 20250301L;
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				performRedo(undoManager);
			}
		};
		Action cutAction = new AbstractAction("Cut")
		{
			private static final long serialVersionUID = 20250301L;
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				performCut(textComponent);
			}
		};
		Action copyAction = new AbstractAction("Copy")
		{
			private static final long serialVersionUID = 20250301L;
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				performCopy(textComponent);
			}
		};
		Action pasteAction = new AbstractAction("Paste")
		{
			private static final long serialVersionUID = 20250301L;
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				performPaste(textComponent);
			}
		};
		Action selectAllAction = new AbstractAction("Select All")
		{
			private static final long serialVersionUID = 20250301L;
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				performSelectAll(textComponent);
			}
		};
		
		textComponent.addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent ke) { }
			
			@Override
			public void keyReleased(KeyEvent ke) { }
			
			@Override
		    public void keyPressed(KeyEvent ke)
			{
				if ( (ke.getModifiersEx() == CTRL_DOWN_MASK) )
				{
					switch ( ke.getKeyCode() )
					{
						case VK_Z: performUndo(undoManager); break;
						case VK_Y: performRedo(undoManager); break;
						case VK_X: performCut(textComponent); break;
						case VK_C: performCopy(textComponent); break;
						case VK_V: performPaste(textComponent); break;
						case VK_A: performSelectAll(textComponent); break;
						default: break;
					}
				}
			}
		});

		popup.add(accelerate(undoAction, VK_Z, CTRL_MASK));
		popup.add(accelerate(redoAction, VK_Y, CTRL_MASK));
		popup.addSeparator();
		popup.add(accelerate(cutAction, VK_X, CTRL_MASK));
		popup.add(accelerate(copyAction, VK_C, CTRL_MASK));
		popup.add(accelerate(pasteAction, VK_V, CTRL_MASK));
		popup.addSeparator();
		popup.add(accelerate(selectAllAction, VK_A, CTRL_MASK));
		
		textComponent.setComponentPopupMenu(popup);
	}
	
	private static JMenuItem accelerate(Action action, int keyCode, int modifiers)
	{
		JMenuItem menuItem = new JMenuItem(action);
		menuItem.setAccelerator(getKeyStroke(keyCode, modifiers));
		return menuItem;
	}

	private static void performUndo(UndoManager undoManager)
	{
		if ( undoManager.canUndo() )
			undoManager.undo();
		else
		{
			String msg = "Undoable: " + undoManager.canUndo();
			showMessageDialog(null, msg, "Undo Status", INFORMATION_MESSAGE);
		}
	}

	private static void performRedo(UndoManager undoManager)
	{
		if ( undoManager.canRedo() )
			undoManager.redo();
		else
		{
			String msg = "Redoable: " + undoManager.canRedo();
			showMessageDialog(null, msg, "Redo Status", INFORMATION_MESSAGE);
		}
	}

	private static void performCopy(JTextComponent textComponent)
	{
		textComponent.copy();
	}

	private static void performCut(JTextComponent textComponent)
	{
		textComponent.cut();
	}

	private static void performPaste(JTextComponent textComponent)
	{
		textComponent.paste();
	}

	private static void performSelectAll(JTextComponent textComponent)
	{
		textComponent.selectAll();
	}
}
