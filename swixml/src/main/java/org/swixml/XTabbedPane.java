package org.swixml;

import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;

import org.swixml.converters.ImageIconConverter;
import org.swixml.dom.Attribute;

/**
 * XSplitPane simple extends JSplitPane to clear components during the
 * construction process
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 * 
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public class XTabbedPane extends JTabbedPane
{
	private static final long serialVersionUID = 20240701L;
	/**
	 * Sets the title for all tabs. An internal exception is raised if there is
	 * no tab at that index.
	 *
	 * @param titles <code>String</code> comma sep. list of titles, for all tabs
	 * @exception IndexOutOfBoundsException if index is out of range
	 *                                      (index &lt; 0 || index &gt;= tab count)
	 */
	public void setTitles(String titles)
	{
		StringTokenizer st = new StringTokenizer(titles, ",");
		int n = st.countTokens();
		for ( int i = 0; i < n; i++ )
		{
			super.setTitleAt(i, st.nextToken().trim());
		}
	}

	/**
	 * Sets the keyboard mnemonic for accessing the specified tab. The mnemonic
	 * is the key which when combined with the look and feel's mouseless
	 * modifier (usually Alt) will activate the specified tab.
	 * <p>
	 * A mnemonic must correspond to a single key on the keyboard and should be
	 * specified using one of the <code>VK_XXX</code> keycodes defined in
	 * <code>java.awt.event.KeyEvent</code>. Mnemonics are case-insensitive,
	 * therefore a key event with the corresponding keycode would cause the
	 * button to be activated whether or not the Shift modifier was pressed.
	 * <p>
	 * This will update the displayed mnemonic property for the specified tab.
	 *
	 * @since 1.4
	 * @param mnemonics <code>String</code> comma sep. list of mnemonic, for all
	 *            tabs
	 * @exception IndexOutOfBoundsException if index is out of range (index &lt;
	 *                0 || index &gt;= tab count)
	 * @see #getMnemonicAt(int)
	 * @see #setDisplayedMnemonicIndexAt(int,int)
	 */
	public void setMnemonics(String mnemonics)
	{
		StringTokenizer st = new StringTokenizer(mnemonics, ",");
		int n = st.countTokens();
		for ( int i = 0; i < n; i++ )
		{
			super.setMnemonicAt(i, Integer.parseInt(st.nextToken().trim()));
		}
	}

	/**
	 * Provides a hint to the look and feel as to which character in the text
	 * should be decorated to represent the mnemonic. Not all look and feels may
	 * support this. A value of -1 indicates either there is no mnemonic for
	 * this tab, or you do not wish the mnemonic to be displayed for this tab.
	 * <p>
	 * The value of this is updated as the properties relating to the mnemonic
	 * change (such as the mnemonic itself, the text...). You should only ever
	 * have to call this if you do not wish the default character to be
	 * underlined. For example, if the text at tab index 3 was 'Apple Price',
	 * with a mnemonic of 'p', and you wanted the 'P' to be decorated, as 'Apple
	 * <u>P</u>rice', you would have to invoke
	 * <code>setDisplayedMnemonicIndex(3, 6)</code> after invoking
	 * <code>setMnemonicAt(3, KeyEvent.VK_P)</code>.
	 * <p>
	 * Note that it is the programmer's responsibility to ensure that each tab
	 * has a unique mnemonic or unpredictable results may occur.
	 *
	 * @since 1.4
	 * 
	 * @param displaymnemonics <code>String</code> comma sep. list of index into
	 *                         the <code>String</code> to underline for all tabs
	 *            
	 * @exception IndexOutOfBoundsException if <code>tabIndex</code> is out of range
	 *                                      (<code>tabIndex &lt; 0 || tabIndex &gt;= tab count</code>)
	 * @exception IllegalArgumentException will be thrown if <code>mnemonicIndex</code> is
	 *                                     &gt;= length of the tab title , or &lt; -1
	 * 
	 * @see #setMnemonicAt(int,int)
	 * @see #getDisplayedMnemonicIndexAt(int)
	 */
	public void setDisplayedMnemonics(String displaymnemonics)
	{
		StringTokenizer st = new StringTokenizer(displaymnemonics, ",");
		int n = st.countTokens();
		for ( int i = 0; i < n; i++ )
		{
			super.setDisplayedMnemonicIndexAt(i, Integer.parseInt(st.nextToken().trim()));
		}
	}

	/**
	 * Sets whether or not the tab at <code>index</code> is enabled. An internal
	 * exception is raised if there is no tab at that index.
	 *
	 * @param enabled <code>String</code> comma sep. list of booleans, if or not
	 *            the tab should be enabled
	 * @exception IndexOutOfBoundsException if index is out of range (index &lt;
	 *                0 || index &gt;= tab count)
	 *
	 * @see #isEnabledAt
	 */
	public void setEnabled(String enabled)
	{
		StringTokenizer st = new StringTokenizer(enabled, ",");
		int n = st.countTokens();
		for ( int i = 0; i < n; i++ )
		{
			super.setEnabledAt(i, Boolean.valueOf(st.nextToken().trim()).booleanValue());
		}
	}

	/**
	 * Sets the tooltip text at <code>index</code> to <code>toolTipText</code>
	 * which can be <code>null</code>. An internal exception is raised if there
	 * is no tab at that index.
	 *
	 * @param toolTipTexts <code>String</code> comma sep. list of tooltip texts
	 *            to be displayed for all the tabs
	 * @exception IndexOutOfBoundsException if index is out of range (index &lt;
	 *                0 || index &gt;= tab count)
	 *
	 * @see #getToolTipTextAt
	 */
	public void setToolTipTexts(String toolTipTexts)
	{
		StringTokenizer st = new StringTokenizer(toolTipTexts, ",");
		int n = st.countTokens();
		for ( int i = 0; i < n; i++ )
		{
			super.setToolTipTextAt(i, st.nextToken().trim());
		}
	}

	/**
	 * Sets the icons for all tabs. Does not set disabled icon at
	 * <code>icon</code>. To disable the icon, use <code>setDisableIconAt()</code>.
	 * An internal exception is raised if there is no tab at that index.
	 *
	 * @param icons <code>String</code> comma separated list of icons
	 *              to be displayed in all tabs.
	 * 
	 * @exception IndexOutOfBoundsException if index is out of range
	 *            (index &lt; 0 || index &gt;= tab count)
	 *
	 * @see #setDisabledIconAt
	 * @see #getIconAt
	 */
	public void setIcons(String icons)
	{
		StringTokenizer st = new StringTokenizer(icons, ",");
		int n = st.countTokens();
		for ( int i = 0; i < n; i++ )
		{
			Attribute attr = new Attribute("icon", st.nextToken());
			super.setIconAt(i, (ImageIcon) ImageIconConverter.conv(null, attr, null));
		}
	}

	/**
	 * Sets the disabled icon at <code>index</code> to <code>icon</code> which
	 * can be <code>null</code>. An internal exception is raised if there is no
	 * tab at that index.
	 *
	 * @param icons <code>String</code> comma sep. list of icons to be displayed
	 *            in alls tabs
	 * @exception IndexOutOfBoundsException if index is out of range (index &lt;
	 *                0 || index &gt;= tab count)
	 *
	 * @see #getDisabledIconAt
	 */
	public void setDisabledIcons(String icons)
	{
		StringTokenizer st = new StringTokenizer(icons, ",");
		int n = st.countTokens();
		for ( int i = 0; i < n; i++ )
		{
			Attribute attr = new Attribute("icon", st.nextToken());
			super.setDisabledIconAt(i, (ImageIcon) ImageIconConverter.conv(null, attr, null));
		}
	}
}
