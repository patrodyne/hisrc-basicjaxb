package org.swixml.examples.text;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import org.jdesktop.application.Action;
import org.swixml.jsr.widgets.JDialogEx;

public class TextDialog extends JDialogEx
{
	private static final long serialVersionUID = 20240701L;
	class JTextFieldLimit extends PlainDocument
	{
		private static final long serialVersionUID = 20240701L;
		private int limit;
		
		JTextFieldLimit(int limit)
		{
			super();
			this.limit = limit;
		}

		@Override
		public void insertString(int offset, String str, AttributeSet attr)
			throws BadLocationException
		{
			if ( str == null )
				return;
			if ( (getLength() + str.length()) <= limit )
				super.insertString(offset, str, attr);
		}
	}

	/*
	 * property bound with textField (see TextDialog.xml)
	 */
	private final JTextFieldLimit textLimit = new JTextFieldLimit(15);
	public JTextFieldLimit getTextLimit()
	{
		return textLimit;
	}

	@Action
	public void close()
	{
		setVisible(false);
		// SwingApplication.getInstance().exit();
	}
}
