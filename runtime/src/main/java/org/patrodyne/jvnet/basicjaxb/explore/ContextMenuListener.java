package org.patrodyne.jvnet.basicjaxb.explore;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;

/**
 * A context menu (popup) listener.
 * 
 * @author Rick O'Sullivan
 */
public class ContextMenuListener extends MouseAdapter
{
	private JPopupMenu popup;
	public JPopupMenu getPopup() { return popup; }
	public void setPopup(JPopupMenu popup) { this.popup = popup; }

	public ContextMenuListener(JPopupMenu popup)
	{
		super();
		setPopup(popup);
	}
	
    public void mousePressed(MouseEvent me)
    {
        maybeShowPopup(me);
    }

    public void mouseReleased(MouseEvent me)
    {
        maybeShowPopup(me);
    }

    private void maybeShowPopup(MouseEvent me)
    {
        if (me.isPopupTrigger())
        {
            getPopup().show(me.getComponent(), me.getX(), me.getY());
        }
    }

}
