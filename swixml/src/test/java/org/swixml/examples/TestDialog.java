package org.swixml.examples;

import java.awt.Frame;

import org.jdesktop.application.Action;
import org.swixml.SwingEngine;
import org.swixml.XDialog;

public class TestDialog extends XDialog
{
	private static final long serialVersionUID = 20240701L;
	
	private SwingEngine<XDialog> swix = null;
	
	private String testValue = "TEST1";
	public final String getTestValue()
	{
		return testValue;
	}
	public final void setTestValue(String testValue)
	{
		this.testValue = testValue;
	}

	/**
	 * Construct without a {@link Frame} owner.
	 */
	public TestDialog()
	{
		try
		{
			swix = new SwingEngine<>(this);
			swix.render("org/swixml/examples/TestDialog.xml");
			setLocationRelativeTo(null);
			setVisible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public boolean isSubmitEnabled()
	{
		return true;
	}

	@Action(enabledProperty = "submitEnabled")
	public void submit()
	{
		System.out.printf("submit\n");
	}
	
	public static void main(String[] args)
	{
		new TestDialog();
	}
}
