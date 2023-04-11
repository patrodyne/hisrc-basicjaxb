package org.patrodyne.jvnet.basicjaxb.explore;

import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.Writer;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;

/**
 * This class extends from Writer to redirect output to a JTextArea.
 *
 * @author Rick O'Sulllivan
 */
public class ConsoleWriter extends Writer
{
	// Represents this Writer's text area.
	private JTextArea textArea;
	public JTextArea getTextArea()
	{
		return textArea;
	}
	private void setTextArea(JTextArea textArea)
	{
		this.textArea = textArea;
	}

	/**
	 * Construct with built-in, read-only JTextArea.
	 *
	 * @param points The font size.
	 */
	public ConsoleWriter(int points)
	{
		super();
		setTextArea(new JTextArea());
		getTextArea().setEditable(false);
		getTextArea().setFont(new Font("monospaced", Font.PLAIN, points));
		getTextArea().setTabSize(4);
		getTextArea().addMouseListener(createContextMenuListener());
		getTextArea().addFocusListener(createFocusListener());
	}

	private FocusListener createFocusListener()
	{
		return new FocusAdapter()
		{
			public void focusGained(FocusEvent e)
			{
				// Enable arrow scrolling!!!
				getTextArea().getCaret().setVisible(true); 
		    }
		};
	}
	
	private MouseListener createContextMenuListener()
	{
		JPopupMenu contextMenu = new JPopupMenu();
		contextMenu.add(new JMenuItem( new DefaultEditorKit.CopyAction() ));
		contextMenu.add(new JMenuItem( new SelectAllAction() ));
		return new ContextMenuListener(contextMenu);
	}

	/** Increase text font (float) size. */
	public void largerText()
	{
		Font curFont = getTextArea().getFont();
		if ( curFont.getSize() < 32 )
			getTextArea().setFont(curFont.deriveFont(curFont.getSize() + 1.0f));
	}

	/** Decrease text font (float) size. */
	public void smallerText()
	{
		Font curFont = getTextArea().getFont();
		if ( curFont.getSize() > 8 )
			getTextArea().setFont(curFont.deriveFont(curFont.getSize() - 1.0f));
	}

	/**
	 * Append a sub-array of characters to the console.
	 *
	 * @param  chars Array of characters.
	 * @param  offset Index of the first character of the sub-array.
	 * @param  count Specifies the length of the sub-array to write.
	 *
	 * @throws	IndexOutOfBoundsException for invalid offset and count.
	 * @throws	IOException If an I/O error occurs.
	 */
	@Override
	public void write(char[] chars, int offset, int count)
		throws IOException
	{
		getTextArea().append(new String(chars, offset, count));
		getTextArea().setCaretPosition(getTextArea().getDocument().getLength());
	}

	/**
	 * Clear the text area document.
	 */
	public void clear()
	{
		try { close(); }
		catch (IOException ex) { throw new RuntimeException("cannot clear", ex); }
	}

	/**
	 * Close the text area document.
	 *
	 * @throws IOException When JTextArea document cannot be closed.
	 */
	@Override
	public void close()	throws IOException
	{
		try
		{
			int length = getTextArea().getDocument().getLength();
			getTextArea().getDocument().remove(0, length);
		}
		catch (BadLocationException ex)
		{
			throw new IOException("cannot close", ex);
		}
	}

	/**
	 * Flush the text area document.
	 */
	@Override
	public void flush()	throws IOException
	{
	}
}
