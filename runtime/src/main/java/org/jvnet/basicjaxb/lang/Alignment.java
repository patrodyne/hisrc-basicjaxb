package org.jvnet.basicjaxb.lang;

import javax.swing.SwingConstants;

/**
* A collection of {@link SwingConstants} generally used for positioning and orienting
* components on the screen.
*/
public enum Alignment
{
	CENTER(SwingConstants.CENTER),
	TOP(SwingConstants.TOP),
	LEFT(SwingConstants.LEFT),
	BOTTOM(SwingConstants.BOTTOM),
	RIGHT(SwingConstants.RIGHT),
	NORTH(SwingConstants.NORTH),
	NORTH_EAST(SwingConstants.NORTH_EAST),
	EAST(SwingConstants.EAST),
	SOUTH_EAST(SwingConstants.SOUTH_EAST),
	SOUTH(SwingConstants.SOUTH),
	SOUTH_WEST(SwingConstants.SOUTH_WEST),
	WEST(SwingConstants.WEST),
	NORTH_WEST(SwingConstants.NORTH_WEST),
	HORIZONTAL(SwingConstants.HORIZONTAL),
	VERTICAL(SwingConstants.VERTICAL),
	LEADING(SwingConstants.LEADING),
	TRAILING(SwingConstants.TRAILING),
	NEXT(SwingConstants.NEXT),
	PREVIOUS(SwingConstants.PREVIOUS);
	
	private int constant;
	public int getConstant() { return constant; }
	public void setConstant(int constant) { this.constant = constant; }

	private Alignment(int pos)
	{
		setConstant(pos);
	}
}
