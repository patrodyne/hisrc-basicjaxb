package org.swixml;

import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * <code>XGridBagConstraints</code> simple extends
 * <code>GridBagConstraints</code> to make bean style getters and setters
 * available for all public fields in the GridBagConstraints class.
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 * 
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public class XGridBagConstraints extends GridBagConstraints
{
	private static final long serialVersionUID = 20240701L;
	public int getAnchor()
	{
		return anchor;
	}

	public void setAnchor(int anchor)
	{
		this.anchor = anchor;
	}

	public int getFill()
	{
		return fill;
	}

	public void setFill(int fill)
	{
		this.fill = fill;
	}

	public int getGridheight()
	{
		return gridheight;
	}

	public void setGridheight(int gridheight)
	{
		this.gridheight = gridheight;
	}

	public int getGridwidth()
	{
		return gridwidth;
	}

	public void setGridwidth(int gridwidth)
	{
		this.gridwidth = gridwidth;
	}

	public int getGridx()
	{
		return gridx;
	}

	public void setGridx(int gridx)
	{
		this.gridx = gridx;
	}

	public int getGridy()
	{
		return gridy;
	}

	public void setGridy(int gridy)
	{
		this.gridy = gridy;
	}

	public Insets getInsets()
	{
		return insets;
	}

	public void setInsets(Insets insets)
	{
		this.insets = insets;
	}

	public int getIpadx()
	{
		return ipadx;
	}

	public void setIpadx(int ipadx)
	{
		this.ipadx = ipadx;
	}

	public int getIpady()
	{
		return ipady;
	}

	public void setIpady(int ipady)
	{
		this.ipady = ipady;
	}

	public double getWeightx()
	{
		return weightx;
	}

	public void setWeightx(double weightx)
	{
		this.weightx = weightx;
	}

	public double getWeighty()
	{
		return weighty;
	}

	public void setWeighty(double weighty)
	{
		this.weighty = weighty;
	}
}
