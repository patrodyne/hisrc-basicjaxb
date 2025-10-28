package org.jdesktop.application;

import javax.swing.event.UndoableEditListener;
import javax.swing.text.PlainDocument;
import javax.swing.undo.UndoManager;

/**
 * Extent a {@link PlainDocument} with support for an
 * {@link UndoManager} and an {@link UndoableEditListener}.
 */
public class UndoableDocument extends PlainDocument
{
	private static final long serialVersionUID = 20250601L;

	// UndoManager manages a list of UndoableEdits; thus,
	// providing a way to undo or redo the appropriate edits.
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

	/**
	 * Construct a Document with an undo/redo listener and
	 * an UndoManager.
	 */
	public UndoableDocument()
	{
		super();
	    // Adds as undo listener for notification of any changes.
	    // Undo/Redo operations performed on the UndoableEdit
	    // will cause the appropriate DocumentEvent to be fired
		// to keep the view(s) in sync with the model.
		addUndoableEditListener(getUndoManager());
	}
}
