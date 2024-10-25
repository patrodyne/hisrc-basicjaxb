package org.jvnet.basicjaxb.tests.swixml;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.swixml.schema.model.Box;
import org.swixml.schema.model.BoxFactoryHGapBox;
import org.swixml.schema.model.BoxFactoryVGapBox;
import org.swixml.schema.model.JButton;
import org.swixml.schema.model.JDesktopPane;
import org.swixml.schema.model.Frame;
import org.swixml.schema.model.JInternalFrame;
import org.swixml.schema.model.JLabel;
import org.swixml.schema.model.JMenu;
import org.swixml.schema.model.JMenuBar;
import org.swixml.schema.model.JMenuItem;
import org.swixml.schema.model.JPanel;
import org.swixml.schema.model.JScrollPane;
import org.swixml.schema.model.JSeparator;
import org.swixml.schema.model.JSplitPane;
import org.swixml.schema.model.JTable;
import org.swixml.schema.model.JTableBind;
import org.swixml.schema.model.JTableHeader;
import org.swixml.schema.model.JTextArea;
import org.swixml.schema.model.JTextField;
import org.swixml.schema.model.JToolBar;
import org.swixml.schema.model.JTree;
import org.swixml.schema.model.TableColumnBind;
import org.swixml.schema.model.XGlue;
import org.swixml.schema.model.XGridBagConstraints;

public class LegacyRoundtripTest extends AbstractSwixmlTest
{
	@Test
	public void testLegacyHelloWorld()
		throws Exception
	{
		File sample = getSampleMap().get("legacy-helloworld.xml");
		Frame frame = unmarshalSample(sample, Frame.class);
		
		assertEquals("javax.swing.plaf.metal.MetalLookAndFeel", frame.getPlaf());
		assertEquals("org.swixml.plaf.metal.MatteMetalTheme(75,1.00)", frame.getPlafTheme());
		assertEquals("650,400", frame.getSize());
		assertEquals("BorderLayout", frame.getLayout());
		assertEquals("Hello SWIXML World", frame.getTitle());
		assertEquals("EXIT_ON_CLOSE", frame.getDefaultCloseOperation());
		
		for ( Object content : frame.getContent() )
		{
			JPanel panel = unwrap(content, JPanel.class);
			if ( panel != null )
			{
				if ( "CENTER".equals(panel.getConstraints()) )
				{
					List<Object> panelContent = removeStrings(panel.getContent());
					
					// Content #0
					{
						JLabel label = unwrap(panelContent.get(0), JLabel.class);
						assertNotNull(label);
						assertEquals("tf", label.getLabelFor());
						assertEquals("*-BOLD-*", label.getFont());
						assertEquals("a180-100-100", label.getForeground());
						assertEquals("Hello World!", label.getText());
					}

					// Content #1
					{
						JTextField textField = unwrap(panelContent.get(1), JTextField.class);
						assertNotNull(textField);
						assertEquals("tf", textField.getId());
						assertEquals("20", textField.getColumns());
						assertEquals("Swixml", textField.getText());
					}
					
					// Content #2
					{
						JButton button = unwrap(panelContent.get(2), JButton.class);
						assertNotNull(button);
						assertEquals("Click Here", button.getText());
						assertEquals("submit", button.getAction());
					}
				}
				else if ( "SOUTH".equals(panel.getConstraints()) )
				{
					List<Object> panelContent = removeStrings(panel.getContent());
					
					// Content #0
					{
						JLabel label = unwrap(panelContent.get(0), JLabel.class);
						assertNotNull(label);
						assertEquals("*-BOLD-300%", label.getFont());
						assertEquals("Clicks:", label.getText());
					}
					
					// Content #1
					{
						JLabel label = unwrap(panelContent.get(1), JLabel.class);
						assertNotNull(label);
						assertEquals("*-BOLD-300%", label.getFont());
						assertEquals("cnt", label.getId());
					}
				}
				else
					fail("Expected 'constraints' to be CENTER or SOUTH.");
			}
		}
		
		marshalSample(frame, sample.getName());
	}
	
	@Test
	public void testLegacyLocalization()
		throws Exception
	{
		File sample = getSampleMap().get("legacy-localization.xml");
		Frame frame = unmarshalSample(sample, Frame.class);
		
		assertEquals("mainframe", frame.getName());
		assertEquals("javax.swing.plaf.metal.MetalLookAndFeel", frame.getPlaf());
		assertEquals("org.swixml.plaf.metal.MatteMetalTheme(165,1.50)", frame.getPlafTheme());
		assertEquals("${el.pageSize(120,30)}", frame.getSize());
		assertEquals("${el.size()}", frame.getPreferredSize());
		assertEquals("S W I X M L", frame.getTitle());
		assertEquals("EXIT_ON_CLOSE", frame.getDefaultCloseOperation());
		assertEquals(TRUE, frame.isAlwaysOnTop());
		assertEquals("BorderLayout", frame.getLayout());
		assertEquals("org/swixml/icons/Duke.png", frame.getIconImage());
		assertEquals("org/swixml/legacy/locale/swix", frame.getBundle());
		assertEquals("en", frame.getLocale());
		
		List<Object> frameContent = removeStrings(frame.getContent());
		
		// Frame Content #0
		{
			JMenuBar menubar = unwrap(frameContent.get(0), JMenuBar.class);
			assertNotNull(menubar);
			assertEquals("menubar", menubar.getName());
			
			List<Object> menubarContent = removeStrings(menubar.getContent());
			
			// MenuBar Content #0
			{
				JMenu menu = unwrap(menubarContent.get(0), JMenu.class);
				assertNotNull(menu);
				assertEquals("filemenu", menu.getName());
				assertEquals("mus_File", menu.getText());
				
				List<Object> menuContent = removeStrings(menu.getContent());
				
				// Menu Content #0
				{
					JMenuItem menuItem = unwrap(menuContent.get(0), JMenuItem.class);
					assertNotNull(menuItem);
					
					assertEquals("mi_new", menuItem.getName());
					assertEquals("mis_New", menuItem.getText());
					assertEquals("org/swixml/icons/new.gif", menuItem.getIcon());
					assertEquals("mn_New", menuItem.getMnemonic());
					assertEquals("new", menuItem.getActionCommand());
					assertEquals("acc_New", menuItem.getAccelerator());
				}
				
				// Menu Content #1
				{
					JMenuItem menuItem = unwrap(menuContent.get(1), JMenuItem.class);
					assertNotNull(menuItem);
					
					assertEquals("mi_open", menuItem.getName());
					assertEquals("mis_Open", menuItem.getText());
					assertEquals("org/swixml/icons/open.gif", menuItem.getIcon());
					assertEquals("mn_Open", menuItem.getMnemonic());
					assertEquals("open", menuItem.getActionCommand());
				}
				
				// Menu Content #2
				{
					JMenuItem menuItem = unwrap(menuContent.get(2), JMenuItem.class);
					assertNotNull(menuItem);
					
					assertEquals("mi_save", menuItem.getName());
					assertEquals("mis_Save", menuItem.getText());
					assertEquals("org/swixml/icons/save.gif", menuItem.getIcon());
					assertEquals("mn_Save", menuItem.getMnemonic());
					assertEquals("save", menuItem.getActionCommand());
				}
				
				// Menu Content #3
				{
					JSeparator separator = unwrap(menuContent.get(3), JSeparator.class);
					assertNotNull(separator);
				}
				
				// Menu Content #4
				{
					JMenuItem menuItem = unwrap(menuContent.get(4), JMenuItem.class);
					assertNotNull(menuItem);
					
					assertEquals("mi_options", menuItem.getName());
					assertEquals("mis_Options", menuItem.getText());
					assertEquals("optionsAction", menuItem.getAction());
				}
				
				// Menu Content #5
				{
					JSeparator separator = unwrap(menuContent.get(5), JSeparator.class);
					assertNotNull(separator);
				}
				
				// Menu Content #6
				{
					JMenuItem menuItem = unwrap(menuContent.get(6), JMenuItem.class);
					assertNotNull(menuItem);
					
					assertEquals("mi_exit", menuItem.getName());
					assertEquals("mis_Exit", menuItem.getText());
					assertEquals("org/swixml/icons/exit.gif", menuItem.getIcon());
					assertEquals("mn_Exit", menuItem.getMnemonic());
					assertEquals("exitAction", menuItem.getAction());
					assertEquals("acc_Exit", menuItem.getAccelerator());
				}
			}
			
			// MenuBar Content #1
			{
				JMenu menu = unwrap(menubarContent.get(1), JMenu.class);
				assertNotNull(menu);
				assertEquals("mus_Help", menu.getText());
				
				List<Object> menuContent = removeStrings(menu.getContent());
				
				// Menu Content #0
				{
					JMenuItem menuItem = unwrap(menuContent.get(0), JMenuItem.class);
					assertNotNull(menuItem);
					
					assertEquals("mi_help", menuItem.getName());
					assertEquals("mis_Help", menuItem.getText());
					assertEquals("org/swixml/icons/help.gif", menuItem.getIcon());
					assertEquals("helpAction", menuItem.getAction());
					assertEquals("acc_Help", menuItem.getAccelerator());
				}
				
				// Menu Content #1
				{
					JSeparator separator = unwrap(menuContent.get(1), JSeparator.class);
					assertNotNull(separator);
				}
				
				// Menu Content #2
				{
					JMenuItem menuItem = unwrap(menuContent.get(2), JMenuItem.class);
					assertNotNull(menuItem);
					
					assertEquals("mi_about", menuItem.getName());
					assertEquals("mis_About", menuItem.getText());
					assertEquals("org/swixml/icons/about.gif", menuItem.getIcon());
					assertEquals("aboutAction", menuItem.getAction());
					assertEquals("acc_About", menuItem.getAccelerator());
				}
			}
		}
		
		// Frame Content #1
		{
			JPanel panel = unwrap(frameContent.get(1), JPanel.class);
			assertNotNull(panel);
			
			assertEquals("borderlayout", panel.getLayout());
			assertEquals("NORTH", panel.getConstraints());
			
			List<Object> panelContent = removeStrings(panel.getContent());
			
			// Panel Content #0
			{
				JToolBar toolbar = unwrap(panelContent.get(0), JToolBar.class);
				assertNotNull(toolbar);
				
				assertEquals(TRUE, toolbar.isFloatable());
				assertEquals(FALSE, toolbar.isBorderPainted());
				assertEquals("HORIZONTAL", toolbar.getOrientation());
				
				List<Object> toolbarContent = removeStrings(toolbar.getContent());
				
				// Toolbar Content #0
				{
					JButton button = unwrap(toolbarContent.get(0), JButton.class);
					assertNotNull(button);
					
					assertEquals("btn_panel", button.getName());
					assertEquals("tt_Panel", button.getToolTipText());
					assertEquals("org/swixml/icons/panel.gif", button.getIcon());
					assertEquals("widget_panel", button.getActionCommand());
				}
				
				// Toolbar Content #1
				{
					JButton button = unwrap(toolbarContent.get(1), JButton.class);
					assertNotNull(button);
					
					assertEquals("btn_button", button.getName());
					assertEquals("tt_Button", button.getToolTipText());
					assertEquals("org/swixml/icons/button.gif", button.getIcon());
					assertEquals("widget_button", button.getActionCommand());
				}
				
				// Toolbar Content #2
				{
					JSeparator separator = unwrap(toolbarContent.get(2), JSeparator.class);
					assertNotNull(separator);
				}
				
				// Toolbar Content #3
				{
					JButton button = unwrap(toolbarContent.get(3), JButton.class);
					assertNotNull(button);
					
					assertEquals("btn_label", button.getName());
					assertEquals("tt_Label", button.getToolTipText());
					assertEquals("org/swixml/icons/label.gif", button.getIcon());
					assertEquals("widget_label", button.getActionCommand());
				}
			}
		}

		// Frame Content #2
		{
			JPanel panel1 = unwrap(frameContent.get(2), JPanel.class);
			assertNotNull(panel1);
			
			assertEquals("borderlayout", panel1.getLayout());
			
			List<Object> panel1Content = removeStrings(panel1.getContent());
			
			// Panel Content #0
			{
				JSplitPane splitpane1 = unwrap(panel1Content.get(0), JSplitPane.class);
				assertNotNull(splitpane1);
				
				assertEquals(TRUE, splitpane1.isOneTouchExpandable());
				assertEquals("${el.scaleSizeWidth('15%')}", splitpane1.getDividerLocation());
				
				List<Object> splitpane1Content = removeStrings(splitpane1.getContent());
				
				// Splitpane1 Content #0
				{
					JScrollPane scrollpane = unwrap(splitpane1Content.get(0), JScrollPane.class);
					assertNotNull(scrollpane);
					
					List<Object> scrollpaneContent = removeStrings(scrollpane.getContent());
					
					// Scrollpane Content #0
					{
						JTree tree = unwrap(scrollpaneContent.get(0), JTree.class);
						assertNotNull(tree);
						
						assertEquals("tree", tree.getId());
						assertEquals("tree", tree.getName());
						assertEquals("*-05-100", tree.getBackground());
						assertEquals("${window.treeCellRenderer}", tree.getCellRenderer());
					}
				}
				
				// Splitpane1 Content #1
				{
					JSplitPane splitpane2 = unwrap(splitpane1Content.get(1), JSplitPane.class);
					assertNotNull(splitpane2);
					
					assertEquals(TRUE, splitpane2.isOneTouchExpandable());
					assertEquals("${el.scaleSizeWidth('10%')}", splitpane2.getDividerLocation());
					assertEquals("HORIZONTAL", splitpane2.getOrientation());
					
					List<Object> splitpane2Content = removeStrings(splitpane2.getContent());
					
					// Splitpane2 Content #0
					{
						JPanel panel = unwrap(splitpane2Content.get(0), JPanel.class);
						assertNotNull(panel);
						
						assertEquals("preview", panel.getName());
						assertEquals("LoweredBevelBorder", panel.getBorder());
						assertEquals("*-05-100", panel.getBackground());
						
						List<Object> panelContent = removeStrings(panel.getContent());
						
						// Panel Content #0
						{
							JTextArea textarea = unwrap(panelContent.get(0), JTextArea.class);
							assertNotNull(textarea);
							
							assertEquals("ta", textarea.getName());
							assertEquals("Hello World!", textarea.getText());
							assertEquals("*-05-100", textarea.getBackground());
						}
					}
					
					// Splitpane2 Content #1
					{
						JPanel panel2 = unwrap(splitpane2Content.get(1), JPanel.class);
						assertNotNull(panel2);
						
						assertEquals("borderlayout", panel2.getLayout());

						List<Object> panel2Content = removeStrings(panel2.getContent());
						
						// Panel2 Content #0
						{
							JPanel panel3 = unwrap(panel2Content.get(0), JPanel.class);
							assertNotNull(panel3);
							
							assertEquals("NORTH", panel3.getConstraints());
							assertEquals("FlowLayout(LEFT)", panel3.getLayout());

							List<Object> panel3Content = removeStrings(panel3.getContent());
							
							// Panel3 Content #0
							{
								JButton button = unwrap(panel3Content.get(0), JButton.class);
								assertNotNull(button);
								
								assertEquals("btn_copy", button.getName());
								assertEquals("tt_Copy", button.getToolTipText());
								assertEquals(TRUE, button.isBorderPainted());
								assertEquals(null, button.isFocusPainted());
								assertEquals("org/swixml/icons/copy.gif", button.getIcon());
								assertEquals("cmdAction", button.getAction());
								assertEquals("copy", button.getActionCommand());
							}
							
							// Panel3 Content #1
							{
								JButton button = unwrap(panel3Content.get(1), JButton.class);
								assertNotNull(button);
								
								assertEquals("btn_paste", button.getName());
								assertEquals("tt_Paste", button.getToolTipText());
								assertEquals(TRUE, button.isBorderPainted());
								assertEquals(null, button.isFocusPainted());
								assertEquals("org/swixml/icons/paste.gif", button.getIcon());
								assertEquals("cmdAction", button.getAction());
								assertEquals("paste", button.getActionCommand());
							}
							
							// Panel3 Content #2
							{
								JButton button = unwrap(panel3Content.get(2), JButton.class);
								assertNotNull(button);
								
								// borderPainted="false" focusPainted="false" size="24,24"
								assertEquals("btn_cut", button.getName());
								assertEquals("tt_Cut", button.getToolTipText());
								assertEquals(TRUE, button.isBorderPainted());
								assertEquals(null, button.isFocusPainted());
								assertEquals("org/swixml/icons/cut.gif", button.getIcon());
								assertEquals("cmdAction", button.getAction());
								assertEquals("cut", button.getActionCommand());
							}
						}
						
						// Panel2 Content #1
						{
							JScrollPane scrollpane = unwrap(panel2Content.get(1), JScrollPane.class);
							assertNotNull(scrollpane);
							
							assertEquals("CENTER", scrollpane.getConstraints());
							assertEquals("*-05-100", scrollpane.getBackground());
							
							List<Object> scrollpaneContent = removeStrings(scrollpane.getContent());
							
							// ScrollPane Content #0
							{
								JTableBind table = unwrap(scrollpaneContent.get(0), JTableBind.class);
								assertNotNull(table);
								
								assertEquals("table", table.getId());
								assertEquals("Monospaced-PLAIN-*", table.getFont());
								assertEquals(FALSE, table.isAutoCreateColumnsFromModel());
								assertEquals("0", table.getAutoResizeMode());
								assertEquals("*-10-100", table.getBackground());
								
								List<Object> tableContent = removeStrings(table.getContent());
								
								// Table Content #0
								{
									JTableHeader tableHeader = unwrap(tableContent.get(0), JTableHeader.class);
									assertNotNull(tableHeader);
									
									assertEquals(FALSE, tableHeader.isOpaque());
								}
								
								// Table Content #1
								{
									TableColumnBind tableColumn = unwrap(tableContent.get(1), TableColumnBind.class);
									assertNotNull(tableColumn);
									
									assertEquals("PartNum", tableColumn.getHeaderValue());
									assertEquals("java.lang.String", tableColumn.getType());
									assertEquals("${el.fieldWidth(14)}", tableColumn.getPreferredWidth());
									assertEquals("AlignableTableCellHeaderRenderer", tableColumn.getHeaderRenderer());
									assertEquals("AlignableTableCellRenderer", tableColumn.getCellRenderer());
								}
								
								// Table Content #2
								{
									TableColumnBind tableColumn = unwrap(tableContent.get(2), TableColumnBind.class);
									assertNotNull(tableColumn);

									assertEquals("Picture", tableColumn.getHeaderValue());
									assertEquals("java.lang.String", tableColumn.getType());
									assertEquals("${el.fieldWidth(3)}", tableColumn.getPreferredWidth());
									assertEquals("AlignableTableCellRenderer(SwingConstants.CENTER)", tableColumn.getCellRenderer());
								}
								
								// Table Content #3
								{
									TableColumnBind tableColumn = unwrap(tableContent.get(3), TableColumnBind.class);
									assertNotNull(tableColumn);
									
									assertEquals("Description", tableColumn.getHeaderValue());
									assertEquals("java.lang.String", tableColumn.getType());
									assertEquals("${el.fieldWidth(20)}", tableColumn.getPreferredWidth());
									assertEquals("AlignableTableCellHeaderRenderer(SwingConstants.CENTER)", tableColumn.getHeaderRenderer());
									assertEquals("AlignableTableCellRenderer()", tableColumn.getCellRenderer());
								}
								
								// Table Content #4
								{
									TableColumnBind tableColumn = unwrap(tableContent.get(4), TableColumnBind.class);
									assertNotNull(tableColumn);
									
									assertEquals("Price", tableColumn.getHeaderValue());
									assertEquals("java.math.BigDecimal", tableColumn.getType());
									assertEquals("${el.fieldWidth(10)}", tableColumn.getPreferredWidth());
									assertEquals("AlignableTableCellHeaderRenderer(SwingConstants.RIGHT)", tableColumn.getHeaderRenderer());
									assertEquals("AlignableTableCellRenderer(SwingConstants.RIGHT)", tableColumn.getCellRenderer());
								}
							}
						}
					}
				}
			}
			
			// Panel Content #1
			{
				JPanel panel = unwrap(panel1Content.get(1), JPanel.class);
				assertNotNull(panel);
				
				assertEquals("SOUTH", panel.getConstraints());
				
				List<Object> panelContent = removeStrings(panel.getContent());
				
				// Panel Content #0
				{
					JLabel label = unwrap(panelContent.get(0), JLabel.class);
					assertNotNull(label);
					assertEquals("Status:", label.getText());
				}
				
				// Panel Content #1
				{
					JTextField testfield = unwrap(panelContent.get(1), JTextField.class);
					assertNotNull(testfield);
					assertEquals("OK", testfield.getText());
					assertEquals("*-05-100", testfield.getBackground());
				}
			}
		}
		
		marshalSample(frame, sample.getName());
	}
	
	@Test
	public void testLegacyFunLayout()
		throws Exception
	{
		File sample = getSampleMap().get("legacy-funlayout.xml");
		Frame frame = unmarshalSample(sample, Frame.class);
		
		assertEquals("mainframe", frame.getName());
		assertEquals("javax.swing.plaf.metal.MetalLookAndFeel", frame.getPlaf());
		assertEquals("org.swixml.plaf.metal.MatteMetalTheme(135,1.00)", frame.getPlafTheme());
		assertEquals("${el.pageSize(120,30)}", frame.getSize());
		assertEquals("100%,100%", frame.getMinimumSize());
		assertEquals("Fun with SWIXML - Layouts", frame.getTitle());
		assertEquals("DISPOSE_ON_CLOSE", frame.getDefaultCloseOperation());
		
		List<Object> frameContent = removeStrings(frame.getContent());
		
		// Frame Content #0
		{
			JDesktopPane desktoppane = unwrap(frameContent.get(0), JDesktopPane.class);
			assertNotNull(desktoppane);
			
			List<Object> desktoppaneContent = removeStrings(desktoppane.getContent());
			
			// DesktopPane Content #0
			{
				JInternalFrame internalframe = unwrap(desktoppaneContent.get(0), JInternalFrame.class);
				assertNotNull(internalframe);
				
				assertEquals("Grid Layout", internalframe.getTitle());
				assertEquals("1.5%,2%,25%,35%", internalframe.getBounds());
				assertEquals("GridLayout(2,2)", internalframe.getLayout());
				assertEquals(TRUE, internalframe.isVisible());
				assertEquals(TRUE, internalframe.isResizable());
				
				List<Object> internalframeContent = removeStrings(internalframe.getContent());
				
				// InternalFrame Content #0 to #3
				for ( int index=0; index < 4; ++index )
				{
					JButton button = unwrap(internalframeContent.get(index), JButton.class);
					assertNotNull(button);
					assertEquals(Integer.toString(index+1), button.getText());
				}
			}
			
			// DesktopPane Content #1
			{
				JInternalFrame internalframe = unwrap(desktoppaneContent.get(1), JInternalFrame.class);
				assertNotNull(internalframe);
				
				assertEquals("Gridbag Layout", internalframe.getTitle());
				assertEquals("55%,2%,43%,35%", internalframe.getBounds());
				assertEquals("GridBagLayout", internalframe.getLayout());
				assertEquals(TRUE, internalframe.isVisible());
				assertEquals(TRUE, internalframe.isResizable());
				
				List<Object> internalframeContent = removeStrings(internalframe.getContent());
				
				// InternalFrame Content #0 to #3
				for ( int index=0; index < 4; ++index )
				{
					JButton button = unwrap(internalframeContent.get(index), JButton.class);
					assertNotNull(button);

					List<Object> buttonContent = removeStrings(button.getContent());
					
					XGridBagConstraints constraints = unwrap(buttonContent.get(0), XGridBagConstraints.class);
					assertNotNull(constraints);

					switch (index)
					{
						case 0: assertEquals("Wonderful", button.getText());
							assertEquals("gbc_1", constraints.getId());
							assertEquals("2,2,2,2", constraints.getInsets());
							assertEquals("0", constraints.getGridx());
							assertEquals("0", constraints.getGridy());
							assertEquals("15", constraints.getIpadx());
							assertEquals("15", constraints.getIpady());
							assertEquals("1", constraints.getWeightx());
							assertEquals("1", constraints.getWeighty());
							break;
						case 1: assertEquals("World", button.getText());
							assertEquals("gbc_1", constraints.getRefid());
							assertEquals("1", constraints.getGridx());
							break;
						case 2: assertEquals("of", button.getText());
							assertEquals("gbc_1", constraints.getRefid());
							assertEquals("1", constraints.getGridy());
							break;
						case 3: assertEquals("SwixML", button.getText());
							assertEquals("gbc_1", constraints.getRefid());
							assertEquals("1", constraints.getGridx());
							assertEquals("1", constraints.getGridy());
							break;
					}
				}
			}
			
			// DesktopPane Content #2
			{
				JInternalFrame internalframe = unwrap(desktoppaneContent.get(2), JInternalFrame.class);
				assertNotNull(internalframe);
				
				assertEquals("Vertical Box", internalframe.getTitle());
				assertEquals("1.5%,40%,25%,56%", internalframe.getBounds());
				assertEquals(TRUE, internalframe.isVisible());
				assertEquals(TRUE, internalframe.isResizable());
				
				List<Object> internalframeContent = removeStrings(internalframe.getContent());

				// InternalFrame Content #0
				{
					Box vbox = unwrap(internalframeContent.get(0), BoxFactoryVGapBox.class);
					assertNotNull(vbox);
					
					List<Object> vboxContent = removeStrings(vbox.getContent());

					// VBox Content #0 to #3
					for ( int index=0; index < 4; ++index )
					{
						if ( index == 3 )
						{
							XGlue glue = unwrap(vboxContent.get(index), XGlue.class);
							assertNotNull(glue);
						}
						else
						{
							JButton button = unwrap(vboxContent.get(index), JButton.class);
							assertNotNull(button);
							assertEquals(Integer.toString(index+1), button.getText());
						}
					}
				}
			}

			// DesktopPane Content #3
			{
				JInternalFrame internalframe = unwrap(desktoppaneContent.get(3), JInternalFrame.class);
				assertNotNull(internalframe);
				
				assertEquals("Border Layout", internalframe.getTitle());
				assertEquals("28%,2%,25%,35%", internalframe.getBounds());
				assertEquals("borderlayout", internalframe.getLayout());
				assertEquals(TRUE, internalframe.isVisible());
				assertEquals(TRUE, internalframe.isResizable());
				
				List<Object> internalframeContent = removeStrings(internalframe.getContent());
				
				// InternalFrame Content #0 to #3
				for ( int index=0; index < 4; ++index )
				{
					JButton button = unwrap(internalframeContent.get(index), JButton.class);
					assertNotNull(button);
					switch (index)
					{
						case 0:
							assertEquals("NORTH", button.getConstraints());
							assertEquals("North", button.getContent().get(0));
							break;
						case 1:
							assertEquals("EAST", button.getConstraints());
							assertEquals("East", button.getContent().get(0));
							break;
						case 2:
							assertEquals("SOUTH", button.getConstraints());
							assertEquals("South", button.getContent().get(0));
							break;
						case 3:
							assertEquals("WEST", button.getConstraints());
							assertEquals("West", button.getContent().get(0));
							break;
						case 4:
							assertEquals("CENTER", button.getConstraints());
							assertEquals("Center", button.getContent().get(0));
							break;
					}
				}
			}

			// DesktopPane Content #4
			{
				JInternalFrame internalframe = unwrap(desktoppaneContent.get(4), JInternalFrame.class);
				assertNotNull(internalframe);
				
				assertEquals("Horizontal Box", internalframe.getTitle());
				assertEquals("28%,40%,70%,38%", internalframe.getBounds());
				assertEquals(TRUE, internalframe.isVisible());
				assertEquals(TRUE, internalframe.isResizable());
				
				List<Object> internalframeContent = removeStrings(internalframe.getContent());

				// InternalFrame Content #0
				{
					Box hbox = unwrap(internalframeContent.get(0), BoxFactoryHGapBox.class);
					assertNotNull(hbox);
					
					assertEquals("TitledBorder(with filler)", hbox.getBorder());
					
					List<Object> hboxContent = removeStrings(hbox.getContent());

					// HBox Content #0 to #3
					for ( int index=0; index < 4; ++index )
					{
						if ( index == 3 )
						{
							XGlue glue = unwrap(hboxContent.get(index), XGlue.class);
							assertNotNull(glue);
						}
						else
						{
							JButton button = unwrap(hboxContent.get(index), JButton.class);
							assertNotNull(button);
							assertEquals(Integer.toString(index+1), button.getText());
						}
					}
				}
			}

			// DesktopPane Content #5
			{
				JInternalFrame internalframe = unwrap(desktoppaneContent.get(5), JInternalFrame.class);
				assertNotNull(internalframe);
				
				assertEquals("Flow Layout (right aligned)", internalframe.getTitle());
				assertEquals("28%,81%,70%,15%", internalframe.getBounds());
				assertEquals("FlowLayout(RIGHT)", internalframe.getLayout());
				assertEquals(TRUE, internalframe.isVisible());
				assertEquals(TRUE, internalframe.isResizable());
				
				List<Object> internalframeContent = removeStrings(internalframe.getContent());
				
				// InternalFrame Content #0 to #3
				for ( int index=0; index < 4; ++index )
				{
					JButton button = unwrap(internalframeContent.get(index), JButton.class);
					assertNotNull(button);
					assertEquals(Integer.toString(index+1), button.getContent().get(0));
				}
			}
		}
		
		marshalSample(frame, sample.getName());
	}
}
