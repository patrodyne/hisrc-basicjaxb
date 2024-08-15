package org.swixml;

import static org.swixml.LogUtil.logger;

import java.util.ServiceLoader;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.table.JTableHeader;

import org.swixml.factory.BoxFactory;
import org.swixml.factory.BoxFactory.Type;
import org.swixml.factory.SplitPaneFactory;
import org.swixml.jsr.widgets.JCheckBoxBind;
import org.swixml.jsr.widgets.JComboBoxBind;
import org.swixml.jsr.widgets.JLabelBind;
import org.swixml.jsr.widgets.JListBind;
import org.swixml.jsr.widgets.JPasswordFieldBind;
import org.swixml.jsr.widgets.JRadioButtonBind;
import org.swixml.jsr.widgets.JSliderBind;
import org.swixml.jsr.widgets.JSpinnerBind;
import org.swixml.jsr.widgets.JTableBind;
import org.swixml.jsr.widgets.JTextAreaBind;
import org.swixml.jsr.widgets.JTextFieldBind;
import org.swixml.jsr.widgets.JTreeBind;
import org.swixml.jsr.widgets.TableColumnBind;
import org.swixml.processor.NopTagProcessor;
import org.swixml.processor.TableColumnTagProcessor;

/**
 * The SwingTagLibrary contains Factories for all Swing Objects that can be
 * instantiated by parsing an XML descriptor at runtime.
 *
 * Date: Dec 9, 2002
 *
 * @author <a href="mailto:wolf@wolfpaulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 * @see org.swixml.TagLibrary
 * 
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public final class SwingTagLibrary extends TagLibrary
{
	private static SwingTagLibrary INSTANCE = new SwingTagLibrary();

	public static SwingTagLibrary getInstance(SwingEngine<?> engine)
	{
		INSTANCE.setSwingEngine(engine);
		return INSTANCE;
	}

	/**
	 * Constructs a Swing Library by registering swings widgets
	 */
	private SwingTagLibrary()
	{
		registerTags();
	}

	/**
	 * Registers the tags this TagLibrary is all about. Strategy method called
	 * by the super class, allowing derived classes to change the registration
	 * behavior.
	 */
	@Override
	protected void registerTags()
	{
		registerTag("ButtonGroup", ButtonGroup.class, NopTagProcessor.instance);
		registerTag("Separator", JSeparator.class, NopTagProcessor.instance);
		registerTag("Button", JButton.class);
		registerTag("CheckBoxMenuItem", JCheckBoxMenuItem.class);
		registerTag("Component", JComponent.class);
		registerTag("DesktopPane", JDesktopPane.class);
		registerTag("Dialog", XDialog.class);
		registerTag("EditorPane", JEditorPane.class);
		registerTag("FormattedTextField", JFormattedTextField.class);
		registerTag("Frame", JFrame.class);
		registerTag("Glue", XGlue.class);
		registerTag("GridBagConstraints", XGridBagConstraints.class);
		registerTag("InternalFrame", JInternalFrame.class);
		registerTag("Menu", JMenu.class);
		registerTag("Menubar", JMenuBar.class);
		registerTag("Menuitem", JMenuItem.class);
		registerTag("Panel", JPanel.class);
		registerTag("PopupMenu", JPopupMenu.class);
		registerTag("ProgressBar", JProgressBar.class);
		registerTag("RadioButtonMenuItem", JRadioButtonMenuItem.class);
		registerTag("OptionPane", JOptionPane.class);
		registerTag("ScrollPane", XScrollPane.class);
		registerTag("SplitPane", new SplitPaneFactory(getSwingEngine()));
		registerTag("TabbedPane", XTabbedPane.class);
		registerTag("TableHeader", JTableHeader.class);
		registerTag("TextPane", JTextPane.class);
		registerTag("ToggleButton", JToggleButton.class);
		registerTag("Toolbar", JToolBar.class);
		// LET'S INTRODUCE (JSR295) BINDING AND (JSR296) ACTION SUPPORT
		// registerTag( "List", JList.class );
		registerTag("List", JListBind.class);
		// registerTag( "ComboBox", JComboBox.class );
		registerTag("ComboBox", JComboBoxBind.class);
		// registerTag( "Checkbox", JCheckBox.class );
		registerTag("Checkbox", JCheckBoxBind.class);
		// registerTag( "PasswordField", JPasswordField.class );
		registerTag("PasswordField", JPasswordFieldBind.class);
		// registerTag( "TextArea", JTextArea.class );
		registerTag("TextArea", JTextAreaBind.class);
		// registerTag( "Table", JTable.class );
		registerTag("Table", JTableBind.class, new TableColumnTagProcessor());
		// registerTag( "Label", JLabel.class );
		registerTag("Label", JLabelBind.class);
		// registerTag( "Tree", JTree.class );
		registerTag("Tree", JTreeBind.class);
		// registerTag( "TextField", JTextField.class );
		registerTag("TextField", JTextFieldBind.class);
		// registerTag( "RadioButton", JRadioButton.class );
		registerTag("RadioButton", JRadioButtonBind.class);
		// registerTag( "Spinner", JSpinner.class );
		registerTag("Spinner", JSpinnerBind.class);
		registerTag("Spinner.Date", JSpinnerBind.Date.class);
		// registerTag( "Slider", JSlider.class );
		registerTag("Slider", JSliderBind.class);
		// NEW TAGs
		registerTag("box.glue", new BoxFactory(Type.GLUE, getSwingEngine()));
		registerTag("box.hglue", new BoxFactory(Type.HGLUE, getSwingEngine()));
		registerTag("box.vglue", new BoxFactory(Type.VGLUE, getSwingEngine()));
		registerTag("box.hstrut", new BoxFactory(Type.HSTRUT, getSwingEngine()));
		registerTag("box.vstrut", new BoxFactory(Type.VSTRUT, getSwingEngine()));
		registerTag("box.rigidarea", new BoxFactory(Type.RIGIDAREA, getSwingEngine()));
		registerTag("vgapbox", BoxFactory.VGapBox.class);
		registerTag("hgapbox", BoxFactory.HGapBox.class);
		// registerTag( "HBox", XHBox.class );
		registerTag("VBox", BoxFactory.VGapBox.class);
		// registerTag( "VBox", XVBox.class );
		registerTag("HBox", BoxFactory.HGapBox.class);
		registerTag("tableColumn", TableColumnBind.class);
		ServiceLoader<TagLibraryService> loader = ServiceLoader.load(TagLibraryService.class);
		if ( loader == null )
			return;
		for ( TagLibraryService tls : loader )
		{
			logger.info("processing TagLibrary service provider " + tls);
			tls.registerTags(this);
		}
	}
}
