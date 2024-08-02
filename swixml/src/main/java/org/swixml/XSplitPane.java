package org.swixml;

import javax.swing.JSplitPane;

/**
 * XSplitPane simply extends JSplitPane to clear components during the
 * construction process
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 * 
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public class XSplitPane extends JSplitPane
{
	private static final long serialVersionUID = 20240701L;
	private boolean _added = false;
	private int _dividerLocation = -1;

	public XSplitPane()
	{
		super(JSplitPane.HORIZONTAL_SPLIT, null, null);
		setTopComponent(null);
		setBottomComponent(null);
	}

	@Override
	public void setDividerLocation(int i)
	{
		if ( !_added )
		{
			_dividerLocation = i;
		}
		else
		{
			super.setDividerLocation(i);
			_dividerLocation = -1;
		}
	}

	@Override
	public void addNotify()
	{
		super.addNotify();
		if ( _dividerLocation >= 0 )
		{
			super.setDividerLocation(_dividerLocation);
		}
		_added = true;
	}
}
