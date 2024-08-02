package org.swixml;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import org.jdesktop.application.Application;
import org.swixml.jsr296.SwingApplication;

/**
 * XDialog simply extends JDialog to allow instantiation with a parent frame
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.2 $
 * 
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public class XDialog extends JDialog
{
	private static final long serialVersionUID = 20240701L;
	/**
	 * Creates a non-modal dialog without a title and without a specified
	 * <code>Frame</code> owner. A shared, hidden frame will be set as the owner
	 * of the dialog.
	 * <p>
	 * This constructor sets the component's locale property to the value
	 * returned by <code>JComponent.getDefaultLocale</code>.
	 * </p>
	 * <p>
	 * setLocationRelativeTo is called for the instanced dialog if a parent
	 * could be provided by the SwingEngine.
	 * </p>
	 * 
	 * @exception HeadlessException if GraphicsEnvironment.isHeadless() returns
	 *                true.
	 * @see GraphicsEnvironment#isHeadless
	 * @see JComponent#getDefaultLocale
	 * @see Window#setLocationRelativeTo
	 */
	public XDialog()
		throws HeadlessException
	{
		// super( SwingEngine.getAppFrame() != null &&
		// SwingEngine.getAppFrame().isDisplayable() ? SwingEngine.getAppFrame()
		// : null );
		super((null != Application.getInstance(SwingApplication.class)) ? Application
			.getInstance(SwingApplication.class).getMainFrame() : null);
	}

	/**
	 * Makes the Dialog visible. If the dialog and/or its owner are not yet
	 * displayable, both are made displayable. The dialog will be validated
	 * prior to being made visible. If the dialog is already visible, this will
	 * bring the dialog to the front.
	 * <p>
	 * If the dialog is modal and is not already visible, this call will not
	 * return until the dialog is hidden by calling <code>hide</code> or
	 * <code>dispose</code>. It is permissible to show modal dialogs from the
	 * event dispatching thread because the toolkit will ensure that another
	 * event pump runs while the one which invoked this method is blocked.
	 * 
	 * @see Component#hide
	 * @see Component#isDisplayable
	 * @see Component#validate
	 * @see Dialog#isModal
	 */
	@Override
	@Deprecated
	public void show()
	{
		this.setLocationRelativeTo(SwingUtilities.windowForComponent(this));
		super.show();
	}

	/**
	 * Sets the application frame system icon.
	 * 
	 * <pre>
	 * <b>Note:</b><br>
	 * The provided icon is only applied if an enclosing frame doesn't really exists yet or does not have an icon set.
	 * </pre>
	 * 
	 * @param image <code>Image</code> the image to become the app's system
	 *            icon.
	 */
	@Override
	public synchronized void setIconImage(Image image)
	{
		Frame f = JOptionPane.getFrameForComponent(this);
		if ( f != null && f.getIconImage() == null )
		{
			f.setIconImage(image);
		}
	}

	/**
	 * Overwrites the <code>createRootPane</code> method to install Escape key
	 * handling.
	 * 
	 * <pre>
	 *  When using the JDialog window through a JOptionPane, you do not have to install the Escape key handling,
	 *  as the basic look-and-feel class for the option pane (BasicOptionPaneUI) already does this for you.
	 * </pre>
	 *
	 * @return <code>JRootPane</code> - the rootpane with some keyboard actions
	 *         registered.
	 *
	 */
	@Override
	protected JRootPane createRootPane()
	{
		ActionListener actionListener = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				setVisible(false);
			}
		};
		JRootPane rootPane = new JRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		rootPane.registerKeyboardAction(actionListener, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
		return rootPane;
	}
}
