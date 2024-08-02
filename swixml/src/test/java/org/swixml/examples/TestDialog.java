package org.swixml.examples;

import java.awt.Frame;

import javax.swing.JDialog;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.swixml.jsr296.SwingApplication;

public class TestDialog extends JDialog
{
	private static final long serialVersionUID = 20240701L;
	private String testValue = "TEST1";

	public TestDialog(Frame owner)
	{
		super(owner);
	}

	public final String getTestValue()
	{
		return testValue;
	}

	public final void setTestValue(String testValue)
	{
		this.testValue = testValue;
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

	public static void showDialog(Frame parent)
	{
		try
		{
			@SuppressWarnings("unchecked")
			SwingApplication<JDialog> app = Application.getInstance(SwingApplication.class);
			JDialog dlg = app.render(new TestDialog(parent), "xml/jsr296component.xml");
			app.show(dlg);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
