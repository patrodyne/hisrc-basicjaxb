package org.patrodyne.jvnet.basicjaxb.explore;

import java.awt.event.ActionEvent;

import javax.swing.text.DefaultEditorKit;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.TextAction;

/**
 * Action to select all text.
 * 
 * @author Rick O'Sullivan
 */
@SuppressWarnings("serial")
public class SelectAllAction extends TextAction
{
	/**
     * Create this action with the appropriate identifier.
     */
    SelectAllAction()
    {
        super(DefaultEditorKit.selectAllAction);
    }

    /** The operation to perform when this action is triggered. */
    @Override
	public void actionPerformed(ActionEvent e)
    {
        JTextComponent target = getTextComponent(e);
        if (target != null)
        {
            Document doc = target.getDocument();
            target.setCaretPosition(0);
            target.moveCaretPosition(doc.getLength());
        }
    }
}
