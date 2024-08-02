package org.swixml.processor;

import static org.swixml.LogUtil.logger;

import java.awt.LayoutManager;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import org.swixml.Parser;

/**
 * @see <a href="file:../package-info.java">LICENSE: package-info</a>
 * 
 * @author softphone
 */
public class TableColumnTagProcessor implements TagProcessor
{
	@Override
	public boolean process(Parser p, Object obj, org.w3c.dom.Element child, LayoutManager layoutMgr)
		throws Exception
	{
		if ( !Parser.TAG_TABLECOLUMN.equalsIgnoreCase(child.getLocalName()) )
			return false;
		if ( !(obj instanceof JTable) )
		{
			logger.warn(String.format("%s tag is valid only inside Table Tag. Ignored!", "tablecolumn"));
			return false;
		}
		final JTable table = (JTable) obj;
		final javax.swing.table.TableColumn tc = (TableColumn) p.getSwing(child, null);
		tc.setModelIndex(table.getColumnModel().getColumnCount());
		table.getColumnModel().addColumn(tc);
		logger.info(String.format(
			"column [%s] header=[%s] modelIndex=[%d] resizable=[%b] minWidth=[%s] maxWidth=[%d] preferredWidth=[%d]",
			tc.getIdentifier(), tc.getHeaderValue(), tc.getModelIndex(), tc.getResizable(), tc.getMinWidth(),
			tc.getMaxWidth(), tc.getPreferredWidth()));
		return true;
	}
}
