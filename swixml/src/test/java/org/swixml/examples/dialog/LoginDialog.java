package org.swixml.examples.dialog;

import static java.lang.String.format;
import static javax.swing.JOptionPane.showMessageDialog;
import static org.jdesktop.application.Application.getInstance;

import org.jdesktop.application.Action;
import org.swixml.jsr.widgets.JDialogEx;
import org.swixml.jsr296.SwingApplication;

public class LoginDialog extends JDialogEx
{
	private static final long serialVersionUID = 20240701L;
	private static final String DATA_VALID = "dataValid";
	
	/** bound property - MUST BE DEFINED getter & setter */
	private String login = "test";
	public final String getLogin()
	{
		return login;
	}
	public final void setLogin(String login)
	{
		this.login = login;
		validateData();
	}

	/** bound property - MUST BE DEFINED getter & setter */
	private String password;
	public final String getPassword()
	{
		return password;
	}
	public final void setPassword(String password)
	{
		this.password = password;
		validateData();
	}

	/**
	 * method that force the data validation
	 */
	protected void validateData()
	{
		// inherited method
		firePropertyChange(DATA_VALID, null, null);
	}

	/**
	 * simple data validation method
	 * 
	 * @return true whether data are valid
	 */
	public boolean isDataValid()
	{
		return null != password && (null != login && login.length() > 0);
	}

	/**
	 * JSR296 annotation
	 * 
	 * this method is bound with close button
	 */
	@Action(name = "escapeAction")
	public void close()
	{
		// Exit from application
		setVisible(false);
		getInstance().exit();
	}

	/**
	 * JSR296 annotation
	 * 
	 * this method is bound with submit button
	 * 
	 * action will be enabled when property isDataValid returns true
	 */
	@Action(name = "enterAction", enabledProperty = DATA_VALID)
	public void submit()
	{
		showMessageDialog
		(
			getInstance(SwingApplication.class).getMainFrame(),
			format("submit login=[%s] password=[%s]\n", getLogin(), getPassword())
		);
	}
}
