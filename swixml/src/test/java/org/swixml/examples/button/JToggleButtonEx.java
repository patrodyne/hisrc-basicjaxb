package org.swixml.examples.button;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JToggleButton;

public class JToggleButtonEx extends JToggleButton
{
	private static final long serialVersionUID = 20240701L;
	
	private String unselectedText;
	public String getUnselectedText() { return unselectedText; }
	public void setUnselectedText(String unselectedText) { this.unselectedText = unselectedText; }

	private final String selectedText = "selectedText";
	public final String getSelectedText()
	{
		return (String) getClientProperty(selectedText);
	}
	public final void setSelectedText(String selectedText)
	{
		putClientProperty("selectedText", selectedText);
	}

	@Override
	public void addNotify()
	{
		super.addNotify();
		setUnselectedText(getText());
		addItemListener(_itemListener);
	}

	@Override
	public void removeNotify()
	{
		removeItemListener(_itemListener);
		super.removeNotify();
	}
	
	/*
	 * The listener interface for receiving item events. The class that is
	 * interested in processing an item event implements this interface.
	 */
	private ItemListener _itemListener = new ItemListener()
	{
		@Override
		public void itemStateChanged(ItemEvent e)
		{
			if ( ItemEvent.SELECTED == e.getStateChange() )
			{
				if ( getSelectedText() != null )
					setText(getSelectedText());
			}
			else
			{
				if ( getUnselectedText() != null )
					setText(getUnselectedText());
			}
		}
	};
}
