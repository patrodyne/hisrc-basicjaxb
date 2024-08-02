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
import org.swixml.factory.ScriptFactory;
import org.swixml.factory.SplitPaneFactory;
import org.swixml.jsr.widgets.JCheckBoxEx;
import org.swixml.jsr.widgets.JComboBoxEx;
import org.swixml.jsr.widgets.JLabelEx;
import org.swixml.jsr.widgets.JListEx;
import org.swixml.jsr.widgets.JPasswordFieldEx;
import org.swixml.jsr.widgets.JRadioButtonEx;
import org.swixml.jsr.widgets.JSliderEx;
import org.swixml.jsr.widgets.JSpinnerEx;
import org.swixml.jsr.widgets.JTableEx;
import org.swixml.jsr.widgets.JTextAreaEx;
import org.swixml.jsr.widgets.JTextFieldEx;
import org.swixml.jsr.widgets.JTreeEx;
import org.swixml.jsr295.BindingUtils;
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
		registerTag("List", JListEx.class);
		// registerTag( "ComboBox", JComboBox.class );
		registerTag("ComboBox", JComboBoxEx.class);
		// registerTag( "Checkbox", JCheckBox.class );
		registerTag("Checkbox", JCheckBoxEx.class);
		// registerTag( "PasswordField", JPasswordField.class );
		registerTag("PasswordField", JPasswordFieldEx.class);
		// registerTag( "TextArea", JTextArea.class );
		registerTag("TextArea", JTextAreaEx.class);
		// registerTag( "Table", JTable.class );
		registerTag("Table", JTableEx.class, new TableColumnTagProcessor());
		// registerTag( "Label", JLabel.class );
		registerTag("Label", JLabelEx.class);
		// registerTag( "Tree", JTree.class );
		registerTag("Tree", JTreeEx.class);
		// registerTag( "TextField", JTextField.class );
		registerTag("TextField", JTextFieldEx.class);
		// registerTag( "RadioButton", JRadioButton.class );
		registerTag("RadioButton", JRadioButtonEx.class);
		// registerTag( "Spinner", JSpinner.class );
		registerTag("Spinner", JSpinnerEx.class);
		registerTag("Spinner.Date", JSpinnerEx.Date.class);
		// registerTag( "Slider", JSlider.class );
		registerTag("Slider", JSliderEx.class);
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
		registerTag("tableColumn", BindingUtils.Column.class);
		registerTag("script", new ScriptFactory());
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
