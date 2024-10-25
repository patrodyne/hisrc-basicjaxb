package org.jvnet.basicjaxb.tests.swixml;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.swixml.schema.model.Dialog;
import org.swixml.schema.model.JLabel;
import org.swixml.schema.model.JPanel;
import org.swixml.schema.model.JScrollPane;
import org.swixml.schema.model.JTableBind;
import org.swixml.schema.model.JTableHeader;
import org.swixml.schema.model.TableColumnBind;
import org.swixml.schema.model.XSplitPane;

public class BindingRoundtripTest extends AbstractSwixmlTest
{
	@Test
	public void testBindingTableDialog()
		throws Exception
	{
		File sample = getSampleMap().get("binding-TableDialog.xml");
		Dialog dialog = unmarshalSample(sample, Dialog.class);
		
		assertEquals("${el.pageSize(80,25)}", dialog.getSize());
		assertEquals("${el.size()}", dialog.getPreferredSize());
		assertEquals(TRUE, dialog.isResizable());
		assertEquals("Example Table usage", dialog.getTitle());
		assertEquals(FALSE, dialog.isUndecorated());
		assertEquals(TRUE, dialog.isAlwaysOnTop());
		assertEquals("DISPOSE_ON_CLOSE", dialog.getDefaultCloseOperation());
		assertEquals(FALSE, dialog.isModal());
		assertEquals("javax.swing.plaf.metal.MetalLookAndFeel", dialog.getPlaf());
		assertEquals("org.swixml.plaf.metal.MatteMetalTheme(330,2.0)", dialog.getPlafTheme());
		
		List<Object> dialogContent = removeStrings(dialog.getContent());
		
		// Dialog Content #0
		{
			XSplitPane splitpane = unwrap(dialogContent.get(0), XSplitPane.class);
			assertNotNull(splitpane);
			
			assertEquals("VERTICAL_SPLIT", splitpane.getOrientation());
			assertEquals(TRUE, splitpane.isOneTouchExpandable());
			assertEquals("${el.size()}", splitpane.getSize());
			
			List<Object> splitpaneContent = removeStrings(splitpane.getContent());
			
			// SplitPane Content #0
			{
				JPanel panel1 = unwrap(splitpaneContent.get(0), JPanel.class);
				assertNotNull(panel1);
				
				assertEquals("BorderLayout", panel1.getLayout());
				assertEquals("${el.scaleSize(1.0,1/4)}", panel1.getMinimumSize());
				assertEquals("${el.size()}", panel1.getSize());
				
				List<Object> panel1Content = removeStrings(panel1.getContent());
				
				// Panel Content #0
				{
					JLabel label = unwrap(panel1Content.get(0), JLabel.class);
					assertNotNull(label);
					
					assertEquals("NORTH", label.getConstraints());
					assertEquals("Table using meta data from external beanInfo", label.getText());
					assertEquals("SansSerif-BOLD", label.getFont());
				}
				
				// Panel Content #1
				{
					JScrollPane scrollpane = unwrap(panel1Content.get(1), JScrollPane.class);
					assertNotNull(scrollpane);
					
					assertEquals("CENTER", scrollpane.getConstraints());
					assertEquals("HORIZONTAL_SCROLLBAR_AS_NEEDED", scrollpane.getHorizontalScrollBarPolicy());
					assertEquals("VERTICAL_SCROLLBAR_AS_NEEDED", scrollpane.getVerticalScrollBarPolicy());
					
					List<Object> scrollpaneContent = removeStrings(scrollpane.getContent());
					
					// ScrollPane Content #0
					{
						JTableBind table = unwrap(scrollpaneContent.get(0), JTableBind.class);
						assertNotNull(scrollpane);
						
						assertEquals("table1", table.getId());
						assertEquals("selectRow", table.getAction());
						assertEquals("activateRow", table.getDblClickAction());
						assertEquals(TRUE, table.isCellSelectionEnabled());
						assertEquals("${window.myData1}", table.getBindList());
						assertEquals("${window.myDataClass}", table.getBindClass());
						assertEquals("0", table.getAutoResizeMode());
						assertEquals("Monospaced-PLAIN", table.getFont());
						assertEquals("${el.fieldHeight(10)}", table.getRowHeight());
						assertEquals("#FFFF80", table.getBackground());
						
						List<Object> tableContent = removeStrings(table.getContent());
						
						// Table Content #0
						{
							JTableHeader tableHeader = unwrap(tableContent.get(0), JTableHeader.class);
							assertNotNull(tableHeader);
							
							assertEquals("tableHeader1", tableHeader.getId());
							assertEquals(FALSE, tableHeader.isOpaque());
							assertEquals("*-BOLD", tableHeader.getFont());
							assertEquals("${color:rgb('#FFFF00').brighter()}", tableHeader.getBackground());
						}
						
						// Table Content #1
						{
							TableColumnBind tableColumn = unwrap(tableContent.get(1), TableColumnBind.class);
							assertNotNull(tableColumn);
							
							assertEquals("name", tableColumn.getBindWith());
							assertEquals("${el.fieldWidth(20)}", tableColumn.getMaxWidth());
							assertEquals("${this.maxWidth}", tableColumn.getPreferredWidth());
							assertEquals(FALSE, tableColumn.isEditable());
							assertEquals(TRUE, tableColumn.isResizable());
						}
					}
				}
			}
			
			// SplitPane Content #1
			{
				JPanel panel = unwrap(splitpaneContent.get(1), JPanel.class);
				assertNotNull(panel);
				
				assertEquals("BorderLayout", panel.getLayout());
				
				List<Object> panelContent2 = removeStrings(panel.getContent());
				
				// Panel Content #0
				{
					JLabel label = unwrap(panelContent2.get(0), JLabel.class);
					assertNotNull(label);
					
					assertEquals("NORTH", label.getConstraints());
					assertEquals("Table using new sub-tag tableColumn", label.getText());
					assertEquals("Serif-BOLDITALIC", label.getFont());
				}
				
				// Panel Content #1
				{
					JScrollPane scrollpane = unwrap(panelContent2.get(1), JScrollPane.class);
					assertNotNull(scrollpane);
					
					assertEquals("CENTER", scrollpane.getConstraints());
					assertEquals("HORIZONTAL_SCROLLBAR_AS_NEEDED", scrollpane.getHorizontalScrollBarPolicy());
					assertEquals("VERTICAL_SCROLLBAR_AS_NEEDED", scrollpane.getVerticalScrollBarPolicy());

					List<Object> scrollpaneContent = removeStrings(scrollpane.getContent());
					
					// ScrollPane Content #0
					{
						JTableBind table = unwrap(scrollpaneContent.get(0), JTableBind.class);
						assertNotNull(table);
						
						assertEquals("table2", table.getId());
						assertEquals("selectRow", table.getAction());
						assertEquals("activateRow", table.getDblClickAction());
						assertEquals(FALSE, table.isCellSelectionEnabled());
						assertEquals(TRUE, table.isRowSelectionAllowed());
						assertEquals("RED", table.getSelectionBackground());
						assertEquals("${window.myData2}", table.getBindList());
						assertEquals("0", table.getAutoResizeMode());
						assertEquals("${el.font('Monospaced','PLAIN')}", table.getFont());
						assertEquals("${el.fieldHeight(10)}", table.getRowHeight());
						assertEquals("${color:hsb(240/360,0.2,1.0)}", table.getBackground());
						
						List<Object> tableContent = removeStrings(table.getContent());
						
						// Table Content #0
						{
							JTableHeader tableHeader = unwrap(tableContent.get(0), JTableHeader.class);
							assertNotNull(tableHeader);
							
							assertEquals("tableHeader2", tableHeader.getId());
							assertEquals(FALSE, tableHeader.isOpaque());
							assertEquals("${el.font('*','BOLD')}", tableHeader.getFont());
							assertEquals("${color:hsb(240/360,0.4,1.0)}", tableHeader.getBackground());
						}
						
						// Table Content #1
						{
							TableColumnBind tableColumn = unwrap(tableContent.get(1), TableColumnBind.class);
							assertNotNull(tableColumn);
							
							assertEquals("field2", tableColumn.getBindWith());
							assertEquals("java.lang.Boolean", tableColumn.getType());
							assertEquals("${el.fieldWidth(5)}", tableColumn.getPreferredWidth());
							assertEquals(FALSE, tableColumn.isResizable());
							assertEquals("X", tableColumn.getHeaderValue());
							assertEquals(TRUE, tableColumn.isEditable());
						}
						
						// Table Content #2
						{
							TableColumnBind tableColumn = unwrap(tableContent.get(2), TableColumnBind.class);
							assertNotNull(tableColumn);

							assertEquals("field1", tableColumn.getBindWith());
							assertEquals("java.lang.Integer", tableColumn.getType());
							assertEquals("#", tableColumn.getHeaderValue());
							assertEquals("${el.fieldWidth(5)}", tableColumn.getPreferredWidth());
							assertEquals(FALSE, tableColumn.isResizable());
						}
						
						// Table Content #3
						{
							TableColumnBind tableColumn = unwrap(tableContent.get(3), TableColumnBind.class);
							assertNotNull(tableColumn);
							
							assertEquals("field3", tableColumn.getBindWith());
							assertEquals("${el.fieldWidth(30)}", tableColumn.getMaxWidth());
							assertEquals("${el.scale(this.minWidth, this.maxWidth, 0.80)}", tableColumn.getPreferredWidth());
							assertEquals(TRUE, tableColumn.isEditable());
							assertEquals(TRUE, tableColumn.isResizable());
						}
						
						// Table Content #4
						{
							TableColumnBind tableColumn = unwrap(tableContent.get(4), TableColumnBind.class);
							assertNotNull(tableColumn);
							
							assertEquals("field4", tableColumn.getBindWith());
							assertEquals("600", tableColumn.getMaxWidth());
							assertEquals("600", tableColumn.getPreferredWidth());
							assertEquals(TRUE, tableColumn.isEditable());
							assertEquals(FALSE, tableColumn.isResizable());
						}
					}
				}
			}
		}
		
		marshalSample(dialog, sample.getName());
	}
}
