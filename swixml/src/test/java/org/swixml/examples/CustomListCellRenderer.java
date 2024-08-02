package org.swixml.examples;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.jdesktop.application.Application;
import org.jdesktop.application.ResourceMap;
import org.swixml.LogUtil;

public class CustomListCellRenderer extends JLabel implements ListCellRenderer<Object>
{
	private static final long serialVersionUID = 20240701L;

	public CustomListCellRenderer()
	{
		super();
		Application app = Application.getInstance();
		try
		{
			ResourceMap res = app.getContext().getResourceMap();
			Icon icon = res.getIcon("listIcon");
			if ( null != icon )
			{
				super.setIconTextGap(6);
				super.setIcon(icon);
			}
		}
		catch (Exception ex)
		{
			LogUtil.logger.warn("icon doesn't exist", ex);
		}
	}

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
		boolean cellHasFocus)
	{
		String s = value.toString();
		setText(s);
		// setIcon((s.length() > 10) ? longIcon : shortIcon);
		if ( isSelected )
		{
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		}
		else
		{
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}
		setEnabled(list.isEnabled());
		setFont(list.getFont());
		setOpaque(true);
		return this;
	}
}
