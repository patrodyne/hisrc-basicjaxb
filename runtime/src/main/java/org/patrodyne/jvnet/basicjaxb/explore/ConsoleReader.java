package org.patrodyne.jvnet.basicjaxb.explore;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.Reader;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;

/**
 * This class extends from Reader to redirect input from a JTextArea.
 *
 * EXPERIMENTAL
 *
 * @author Rick O'Sulllivan
 */
public class ConsoleReader extends Reader
{
	private PipedWriter pipedWriter;
	public PipedWriter getPipedWriter()
	{
		return pipedWriter;
	}
	public void setPipedWriter(PipedWriter pipedWriter)
	{
		this.pipedWriter = pipedWriter;
	}

	private PipedReader pipedReader;
	public PipedReader getPipedReader()
	{
		return pipedReader;
	}
	public void setPipedReader(PipedReader pipedReader)
	{
		this.pipedReader = pipedReader;
	}

	// Represents this Reader's text area.
	private JTextArea textArea;
	public JTextArea getTextArea()
	{
		return textArea;
	}

	/**
	 * Construct with built-in, editable JTextArea.
	 *
	 * @param points The font size.
	 * @throws IOException 
	 */
	public ConsoleReader(int points) throws IOException
	{
		super();
		setPipedWriter(new PipedWriter());
		setPipedReader(new PipedReader(getPipedWriter()));
		getPipedWriter().connect(getPipedReader());
		this.textArea = new JTextArea();
		this.textArea.setEditable(true);
		this.textArea.setFont(new Font("monospaced", Font.PLAIN, points));
		this.textArea.setTabSize(4);
		this.textArea.addMouseListener(createContextMenuListener());
		this.textArea.addKeyListener(createKeyListener());
	}

	private KeyListener createKeyListener()
	{
		KeyListener keyListener = new KeyListener()
		{
			@Override
			public void keyReleased(KeyEvent ke)
			{
				if ( KeyEvent.VK_ENTER == ke.getKeyCode() )
				{
					JTextArea source = (JTextArea) ke.getSource();
					int caretPosition = source.getCaretPosition();
					String line = source.getText().substring(0, caretPosition);
					try
					{
						getPipedWriter().write(line);
					}
					catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			@Override
			public void keyPressed(KeyEvent ke) { }
			
			@Override
			public void keyTyped(KeyEvent ke) { }
		};
		return keyListener;
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
	
	@Override
	public int read(char[] cbuf, int off, int len)
		throws IOException
	{
		return getPipedReader().read(cbuf, off, len);
	}

	/**
	 * Clear the text area document.
	 */
	public void clear()
	{
		try
		{
			int length = getTextArea().getDocument().getLength();
			getTextArea().getDocument().remove(0, length);
		}
		catch (BadLocationException ex)
		{
			// 	TODO: errorln??
		}
	}
	
	@Override
	public void close()
		throws IOException
	{
		getPipedReader().close();
		getPipedWriter().close();
	}
}
