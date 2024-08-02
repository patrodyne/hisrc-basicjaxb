package org.swixml.examples.button;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JToggleButton;

public class JToggleButtonEx extends JToggleButton
{
	private static final long serialVersionUID = 20240701L;
	private ItemListener _itemListener = new ItemListener()
	{
		@Override
		public void itemStateChanged(ItemEvent e)
		{
			String v = getSelectedText();
			if ( ItemEvent.SELECTED == e.getStateChange() )
			{
				if ( v != null )
					setText(getSelectedText());
			}
			else
			{
				if ( v != null )
					setText(unselectedText);
			}
		}
	};
	String unselectedText;
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
		unselectedText = getText();
		addItemListener(_itemListener);
	}

	@Override
	public void removeNotify()
	{
		removeItemListener(_itemListener);
		super.removeNotify();
	}
}
