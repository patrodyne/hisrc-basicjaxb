package org.swixml.examples.text;

import static org.jdesktop.application.Application.getInstance;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import org.jdesktop.application.Action;
import org.swixml.jsr.widgets.JDialogBind;
import org.swixml.jsr.widgets.JTextFieldBind;

public class TextDialog extends JDialogBind
{
	private static final long serialVersionUID = 20240701L;
	
	/*
	 * An extension of PlainDocument to limit the text length
	 * using the TextField columns property.
	 */
	private class LimitLengthDocument extends PlainDocument
	{
		private static final long serialVersionUID = 20240701L;
		
		private Integer limit = null;
		public Integer getLimit()
		{
			if ( limit == null )
				setLimit(getLimitedTextField().getColumns());
			return limit;
		}
		public void setLimit(int limit)
		{
			this.limit = limit;
		}

		@Override
		public void insertString(int offset, String str, AttributeSet attr)
			throws BadLocationException
		{
			if ( str != null )
			{
				if ( (getLength() + str.length()) <= getLimit() )
					super.insertString(offset, str, attr);
			}
		}
	}

	/*
	 * property bound with id="limitedTextField",
	 * see TextDialog.xml
	 */
	private JTextFieldBind limitedTextField;
	public JTextFieldBind getLimitedTextField() { return limitedTextField; }
	
	/*
	 * property referenced by "${window.limitLengthDocument}",
	 * see TextDialog.xml
	 */
	private LimitLengthDocument limitLengthDocument = new LimitLengthDocument();
	public LimitLengthDocument getLimitLengthDocument()
	{
		if ( limitLengthDocument == null )
			setLimitLengthDocument(new LimitLengthDocument());
		return limitLengthDocument;
	}
	public void setLimitLengthDocument(LimitLengthDocument limitLengthDocument)
	{
		this.limitLengthDocument = limitLengthDocument;
	}

	@Action
	public void close()
	{
		setVisible(false);
		getInstance().exit();
	}
}
