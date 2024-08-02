package org.swixml.jgoodies;

import java.awt.LayoutManager;
import java.util.StringTokenizer;

import org.swixml.LayoutConverter;
import org.swixml.converters.Util;
import org.swixml.dom.Attribute;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * A layout converter for <code>com.jgoodies.forms.layout.FormLayout</code>.
 * 
 * <p>
 * <b>Examples:</b>
 * </p>
 * 
 * <pre>
 * &lt;panel&gt;
 *   &lt;layout type="FormLayout"
 *       columns="p, 3dlu, p:grow"
 *       rows="p, 3dlu, p"/&gt;
 *
 *   &lt;label     constraints="1,1" text="Company"/&gt;
 *   &lt;textfield constraints="3,1"/&gt;
 *   &lt;label     constraints="1,3" text="Contact"/&gt;
 *   &lt;textfield constraints="3,3"/&gt;
 * &lt;/panel&gt;
 * </pre>
 * 
 * <pre>
 * &lt;panel&gt;
 *   &lt;layout type="FormLayout"
 *       columns="right:max(40dlu;pref), 3dlu, d:grow, 7dlu, right:pref, 3dlu, d:grow"
 *       rows="p, 3dlu, p, 9dlu, p, 3dlu, p"
 *       columnGroups="1,5; 3,7"/&gt;
 *
 *   &lt;label     constraints="1,1" text="Company"/&gt;
 *   &lt;textfield constraints="3,1,5,1"/&gt;
 *   &lt;label     constraints="1,3" text="Contact"/&gt;
 *   &lt;textfield constraints="3,3,5,1"/&gt;
 *
 *   &lt;label     constraints="1,5" text="PTI [kW]"/&gt;
 *   &lt;textfield constraints="3,5"/&gt;
 *   &lt;label     constraints="5,5" text="Power [kW]"/&gt;
 *   &lt;textfield constraints="7,5"/&gt;
 *   &lt;label     constraints="1,7" text="R [mm]"/&gt;
 *   &lt;textfield constraints="3,7"/&gt;
 *   &lt;label     constraints="5,7" text="D [mm]"/&gt;
 *   &lt;textfield constraints="7,7"/&gt;
 * &lt;/panel&gt;
 * </pre>
 *
 * @author Karl Tauber
 * 
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public class FormLayoutConverter implements LayoutConverter
{
	/**
	 * Returns "formlayout".
	 */
	@Override
	public String getID()
	{
		return "formlayout";
	}

	/**
	 * Returns always <code>null</code>.
	 */
	@Override
	public LayoutManager convertLayoutAttribute(final Attribute attr)
	{
		return null;
	}

	/**
	 * <p>
	 * Creates a FormLayout instance.
	 * </p>
	 * 
	 * <p>
	 * <b>Attributes:</b>
	 * </p>
	 * <ul>
	 * <li><code>columns</code> (required): The column specifications as
	 * documented in JGoodies FormLayout.</li>
	 * <li><code>row</code> (required): The row specifications as documented in
	 * JGoodies FormLayout.</li>
	 * <li><code>columnGroups</code> (optional): The column groups, where each
	 * column in a group gets the same group wide width. Groups are separated by
	 * semicolons, column indices in a group are separated by colons. E.g. "1,5;
	 * 3,7,9" defines two groups, where first group contains columns 1 and 5;
	 * and second group contains columns 3, 7 and 9. Note that column indices
	 * are 1-based.</li>
	 * <li><code>rowGroups</code> (optional): The row groups, where each row in
	 * a group gets the same group wide height. Groups are separated by
	 * semicolons, row indices in a group are separated by colons. E.g. "1,5;
	 * 3,7,9" defines two groups, where first group contains rows 1 and 5; and
	 * second group contains rows 3, 7 and 9. Note that row indices are
	 * 1-based.</li>
	 * </ul>
	 * 
	 * <p>
	 * <b>Examples for Valid XML element notations:</b>
	 * </p>
	 * <ul>
	 * <li><code>&lt;layout type="FormLayout" columns="p, 3dlu, p" rows="p, 3dlu, p"/&gt;</code></li>
	 * <li><code>&lt;layout type="FormLayout" columns="p, 3dlu, p, 3dlu, p, 3dlu, p" rows="p, 3dlu, p"
	 * columnGroups="1,5; 3,7" rowGroups="1,3"/&gt;</code></li>
	 * </ul>
	 */
	@Override
	public LayoutManager convertLayoutElement(final Element element)
	{
		String encodedColumnSpecs = ( element.getAttributeNode("columns") != null )
			? element.getAttribute("columns")
			: null;
		String encodedRowSpecs = ( element.getAttributeNode("rows") != null )
			? element.getAttribute("rows")
			: null;
		int[][] columnGroupIndices = convertGroupIndices(element.getAttributeNode("columnGroups"));
		int[][] rowGroupIndices = convertGroupIndices(element.getAttributeNode("rowGroups"));
		
		FormLayout lm = new FormLayout(encodedColumnSpecs, encodedRowSpecs);
		if ( columnGroupIndices != null )
			lm.setColumnGroups(columnGroupIndices);
		if ( rowGroupIndices != null )
			lm.setRowGroups(rowGroupIndices);
		return lm;
	}

	/**
	 * <p>
	 * Creates a CellConstraints instance.
	 * </p>
	 * 
	 * <p>
	 * Allowed syntaxes of attribute value:
	 * </p>
	 * <ul>
	 * <li><code>"x, y"</code></li>
	 * <li><code>"x, y, w, h"</code></li>
	 * <li><code>"x, y, hAlign, vAlign"</code></li>
	 * <li><code>"x, y, w, h, hAlign, vAlign"</code></li>
	 * </ul>
	 * <p>
	 * See JGoodies FormLayout for details.
	 * </p>
	 * 
	 * <p>
	 * <b>Examples for Valid XML attribute notations:</b>
	 * </p>
	 * <ul>
	 * <li><code>constraints="1, 3"</code></li>
	 * <li><code>constraints="1, 3, 2, 1"</code></li>
	 * <li><code>constraints="1, 3, left, bottom"</code></li>
	 * <li><code>constraints="1, 3, 2, 1, l, b"</code></li>
	 * </ul>
	 */
	@Override
	public Object convertConstraintsAttribute(final Attribute attr)
	{
		return new CellConstraints(attr.getValue());
	}

	/**
	 * Returns always <code>null</code>.
	 */
	@Override
	public Object convertConstraintsElement(final Element element)
	{
		return null;
	}

	private int[][] convertGroupIndices(final Attr groups)
	{
		if ( groups == null )
			return null;
		StringTokenizer st = new StringTokenizer(groups.getValue(), ";");
		int[][] groupIndices = new int[st.countTokens()][];
		int i = 0;
		while (st.hasMoreTokens())
		{
			String group = st.nextToken();
			groupIndices[i++] = Util.ia(new StringTokenizer(group, ","));
		}
		return groupIndices;
	}
}
