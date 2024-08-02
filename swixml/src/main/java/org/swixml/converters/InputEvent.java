package org.swixml.converters;

import java.awt.Event;

/**
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
@SuppressWarnings("deprecation")
public interface InputEvent
{
	/**
	 * The shift key modifier constant. It is recommended that SHIFT_DOWN_MASK
	 * to be used instead.
	 */
	public static final int SHIFT_MASK = Event.SHIFT_MASK;
	/**
	 * The control key modifier constant. It is recommended that CTRL_DOWN_MASK
	 * to be used instead.
	 */
	public static final int CTRL_MASK = Event.CTRL_MASK;
	/**
	 * The meta key modifier constant. It is recommended that META_DOWN_MASK to
	 * be used instead.
	 */
	public static final int META_MASK = Event.META_MASK;
	/**
	 * The alt key modifier constant. It is recommended that ALT_DOWN_MASK to be
	 * used instead.
	 */
	public static final int ALT_MASK = Event.ALT_MASK;
	/**
	 * The alt-graph key modifier constant.
	 */
	public static final int ALT_GRAPH_MASK = 1 << 5;
	/**
	 * The mouse button1 modifier constant. It is recommended that
	 * BUTTON1_DOWN_MASK to be used instead.
	 */
	public static final int BUTTON1_MASK = 1 << 4;
	/**
	 * The mouse button2 modifier constant. It is recommended that
	 * BUTTON2_DOWN_MASK to be used instead.
	 */
	public static final int BUTTON2_MASK = Event.ALT_MASK;
	/**
	 * The mouse button3 modifier constant. It is recommended that
	 * BUTTON3_DOWN_MASK to be used instead.
	 */
	public static final int BUTTON3_MASK = Event.META_MASK;
	/**
	 * The SHIFT key extended modifier constant.
	 * 
	 * @since 1.4
	 */
	public static final int SHIFT_DOWN_MASK = 1 << 6;
	/**
	 * The CTRL key extended modifier constant.
	 * 
	 * @since 1.4
	 */
	public static final int CTRL_DOWN_MASK = 1 << 7;
	/**
	 * The META key extended modifier constant.
	 * 
	 * @since 1.4
	 */
	public static final int META_DOWN_MASK = 1 << 8;
	/**
	 * The ALT key extended modifier constant.
	 * 
	 * @since 1.4
	 */
	public static final int ALT_DOWN_MASK = 1 << 9;
	/**
	 * The mouse button1 extended modifier constant.
	 * 
	 * @since 1.4
	 */
	public static final int BUTTON1_DOWN_MASK = 1 << 10;
	/**
	 * The mouse button2 extended modifier constant.
	 * 
	 * @since 1.4
	 */
	public static final int BUTTON2_DOWN_MASK = 1 << 11;
	/**
	 * The mouse button3 extended modifier constant.
	 * 
	 * @since 1.4
	 */
	public static final int BUTTON3_DOWN_MASK = 1 << 12;
	/**
	 * The alt-graph key extended modifier constant.
	 * 
	 * @since 1.4
	 */
	public static final int ALT_GRAPH_DOWN_MASK = 1 << 13;
	static final int JDK_1_3_MODIFIERS = SHIFT_DOWN_MASK - 1;
};
