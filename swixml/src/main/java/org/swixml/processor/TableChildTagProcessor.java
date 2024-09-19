package org.swixml.processor;

import static org.swixml.LogUtil.logger;
import static org.swixml.Parser.TAG_TABLECOLUMN;
import static org.swixml.Parser.TAG_TABLEHEADER;

import java.awt.LayoutManager;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.swixml.Parser;

/**
 * @see <a href="file:../package-info.java">LICENSE: package-info</a>
 * 
 * @author softphone
 */
public class TableChildTagProcessor implements TagProcessor
{
	@Override
	public boolean process(Parser parser, Object obj, org.w3c.dom.Element child, LayoutManager layoutMgr)
		throws Exception
	{
		boolean result = false;
		
		if ( obj instanceof JTable )
		{
			final JTable table = (JTable) obj;
			
			if ( TAG_TABLEHEADER.equalsIgnoreCase(child.getLocalName()) )
			{
				JTableHeader tableHeader = (JTableHeader) parser.getSwing(child, null);
				tableHeader.setTable(table);
				tableHeader.setColumnModel(table.getColumnModel());
				table.setTableHeader(tableHeader);
				//logger.info("header {} ", tableHeader);
				result = true;
			}
			else if ( TAG_TABLECOLUMN.equalsIgnoreCase(child.getLocalName()) )
			{
				TableColumn tc = (TableColumn) parser.getSwing(child, null);
				tc.setModelIndex(table.getColumnModel().getColumnCount());
				table.getColumnModel().addColumn(tc);
				logger.info(String.format(
					"column [%s] header=[%s] modelIndex=[%d] resizable=[%b] minWidth=[%s] maxWidth=[%d] preferredWidth=[%d]",
					tc.getIdentifier(), tc.getHeaderValue(), tc.getModelIndex(), tc.getResizable(), tc.getMinWidth(),
					tc.getMaxWidth(), tc.getPreferredWidth()));
				result = true;
			}
		}
		else
			logger.warn(String.format("%s tag is valid only inside Table Tag. Ignored!", "tablecolumn"));
		
		return result;
	}
}
