package org.patrodyne.jvnet.basicjaxb.explore;

import java.awt.Font;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

/**
 * This class extends from OutputStream to redirect output to a JTextArea.
 *
 * @author Rick O'Sulllivan
 */
public class ConsoleOutputStream extends OutputStream
{
	// Represents this OutputStream's text area.
	private JTextArea textArea;
	public JTextArea getTextArea()
	{
		return textArea;
	}

	/**
	 * Construct with built-in, read-only JTextArea.
	 *
	 * @param points The font size.
	 */
	public ConsoleOutputStream(int points)
	{
		super();
		this.textArea = new JTextArea();
		this.textArea.setEditable(false);
		this.textArea.setFont(new Font("monospaced", Font.PLAIN, points));
	}

	/**
	 * Appends the given byte to the end of the document.
	 * @param b The byte to be appended.
	 */
	@Override
	public void write(int b) throws IOException
	{
		// Redirects data to the text area
		getTextArea().append(String.valueOf((char)b));
		// Scrolls the text area to the end of data
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
	public void close() throws IOException
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
}
