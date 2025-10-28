package org.swixml.examples.text;

import static org.jdesktop.application.Application.getInstance;

import java.awt.event.ActionEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import javax.swing.undo.UndoManager;

import org.jdesktop.application.Action;
import org.swixml.jsr.widgets.JDialogBind;
import org.swixml.jsr.widgets.JTextFieldBind;

public class TextDialog extends JDialogBind
{
	private static final long serialVersionUID = 20240701L;

	private class UndoableDocument extends PlainDocument
	{
		private static final long serialVersionUID = 20240701L;

		private UndoManager undoManager;
		public UndoManager getUndoManager()
		{
			if ( undoManager == null )
				setUndoManager(new UndoManager());
			return undoManager;
		}
		public void setUndoManager(UndoManager undoManager)
		{
			this.undoManager = undoManager;
		}

		public UndoableDocument()
		{
			super();
			addUndoableEditListener(getUndoManager());
		}
	}

	/*
	 * An extension of UndoableDocument to limit the text length
	 * using the TextField columns property.
	 */
	private class LimitLengthDocument extends UndoableDocument
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
	public void edit(ActionEvent ae)
	{
		// Sufficient for this application...
		JTextComponent text = getLimitedTextField();
		// ... but this discovers the text component dynamically!
		if ( ae.getSource() instanceof JMenuItem )
		{
			JMenuItem mi = (JMenuItem) ae.getSource();
			if ( mi.getParent() instanceof JPopupMenu )
			{
				JPopupMenu pm = (JPopupMenu) mi.getParent();
				if ( pm.getInvoker() instanceof JTextComponent )
					text = (JTextComponent) pm.getInvoker();
			}
		}
		switch ( ae.getActionCommand() )
		{
			case "Undo":
				System.err.println("edit: Undo");
				UndoableDocument ud = (UndoableDocument) text.getDocument();
				if ( ud.getUndoManager().canUndo() )
					ud.getUndoManager().undo();
				break;
			case "Redo":
				System.err.println("edit: Redo");
				UndoableDocument rd = (UndoableDocument) text.getDocument();
				if ( rd.getUndoManager().canRedo() )
					rd.getUndoManager().redo();
				break;
			case "Cut":
				System.err.println("edit: Cut");
				text.cut();
				break;
			case "Copy":
				System.err.println("edit: Copy");
				text.copy();
				break;
			case "Paste":
				System.err.println("edit: Paste");
				text.paste();
				break;
			case "Select All":
				System.err.println("edit: Select All");
				text.selectAll();
				break;
		}
	}

	@Action
	public void close()
	{
		setVisible(false);
		getInstance().exit();
	}
}
