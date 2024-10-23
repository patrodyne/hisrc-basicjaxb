package org.swixml;

import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * <code>XGridBagConstraints</code> simply extends
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
	
	/** {@link GridBagConstraints#anchor} */
	public int getAnchor() { return anchor; }
	public void setAnchor(int anchor) { this.anchor = anchor; }

	/** {@link GridBagConstraints#fill} */
	public int getFill() { return fill; }
	public void setFill(int fill) { this.fill = fill; }

	/** {@link GridBagConstraints#gridheight} */
	public int getGridheight() { return gridheight; }
	public void setGridheight(int gridheight) { this.gridheight = gridheight; }

	/** {@link GridBagConstraints#gridwidth} */
	public int getGridwidth() { return gridwidth; }
	public void setGridwidth(int gridwidth) { this.gridwidth = gridwidth; }

	/** {@link GridBagConstraints#gridx} */
	public int getGridx() { return gridx; }
	public void setGridx(int gridx) { this.gridx = gridx; }

	/** {@link GridBagConstraints#gridy} */
	public int getGridy() { return gridy; }
	public void setGridy(int gridy) { this.gridy = gridy; }

	/** {@link GridBagConstraints#insets} */
	public Insets getInsets() { return insets; }
	public void setInsets(Insets insets) { this.insets = insets; }

	/** {@link GridBagConstraints#ipadx} */
	public int getIpadx() { return ipadx; }
	public void setIpadx(int ipadx) { this.ipadx = ipadx; }

	/** {@link GridBagConstraints#ipady} */
	public int getIpady() { return ipady; }
	public void setIpady(int ipady) { this.ipady = ipady; }

	/** {@link GridBagConstraints#weightx} */
	public double getWeightx() { return weightx; }
	public void setWeightx(double weightx) { this.weightx = weightx; }

	/** {@link GridBagConstraints#weighty} */
	public double getWeighty() { return weighty; }
	public void setWeighty(double weighty) { this.weighty = weighty; }
}
