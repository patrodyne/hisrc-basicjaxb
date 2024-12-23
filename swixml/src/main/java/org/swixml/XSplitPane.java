package org.swixml;

import javax.swing.JSplitPane;

/**
 * XSplitPane simply extends JSplitPane to clear components during the
 * construction process and set the divider location.
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
	private int _dividerLocationInt = -1;
	private double _dividerLocationDbl = -1.0;

	public XSplitPane()
	{
		super(JSplitPane.HORIZONTAL_SPLIT, null, null);
		setTopComponent(null);
		setBottomComponent(null);
	}

	@Override
	public void setDividerLocation(int location)
	{
		if ( !_added )
			_dividerLocationInt = location;
		else
		{
			super.setDividerLocation(location);
			_dividerLocationInt = -1;
		}
	}

	@Override
	public void setDividerLocation(double location)
	{
		if ( !_added )
			_dividerLocationDbl = location;
		else
		{
			setDividerLocationSafe(location);
			_dividerLocationDbl = -1.0;
		}
	}
	
	private void setDividerLocationSafe(double location)
	{
		if ( location < 0.0 || location > 1.0 )
			throw new IllegalArgumentException("proportional location must be between 0.0 and 1.0.");
		
		if ( getOrientation() == VERTICAL_SPLIT )
		{
			if ( getHeight() >= getDividerSize() )
			{
				double adjHeight = (double) (getHeight() - getDividerSize());
				super.setDividerLocation((int) (adjHeight * location));
			}
		}
		else
		{
			if ( getWidth() >= getDividerSize())
			{
				double adjWidth = (double) (getWidth() - getDividerSize());
				super.setDividerLocation((int) (adjWidth * location));
			}
		}
	}

	@Override
	public void addNotify()
	{
		super.addNotify();
		if ( _dividerLocationInt >= 0 )
			super.setDividerLocation(_dividerLocationInt);
		else if ( _dividerLocationDbl >= 0.0 )
			setDividerLocationSafe(_dividerLocationDbl);
		_added = true;
	}
}
