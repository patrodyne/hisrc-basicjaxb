package org.swixml.examples;

import static java.lang.System.out;
import static org.jdesktop.application.Application.AUTO_INJECTFIELD;
import static org.jdesktop.application.Application.getBooleanProperty;
import static org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE;
import static org.jdesktop.beansbinding.BeanProperty.create;

import java.awt.Frame;
import java.awt.Window;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.Bindings;
import org.swixml.SwingEngine;
import org.swixml.XDialog;
import org.swixml.jsr.widgets.JTextFieldBind;
import org.swixml.jsr296.SwingApplication;

public class BindingExamplesTestDialog extends XDialog
{
	private static final long serialVersionUID = 20240701L;
	
	private Window window;
	public Window getWindow() { return window; }
	public void setWindow(Window window) { this.window = window; }

	private SwingEngine<BindingExamplesTestDialog> swingEngine;
	public SwingEngine<BindingExamplesTestDialog> getSwingEngine()
	{
		if ( swingEngine == null )
		{
			SwingApplication<?> sa = Application.getInstance(SwingApplication.class);
			SwingEngine<BindingExamplesTestDialog> se = new SwingEngine<>(this);
			se.setClassLoader(getClass().getClassLoader());
			if ( getBooleanProperty(AUTO_INJECTFIELD) )
				sa.getContext().getResourceMap().injectFields(this);
			se.getELContext().putContext(BindingExamplesTestDialog.class, this);
			se.getELMethods().setSwingEngine(se);
			se.getELProcessor().defineBean("el", se.getELMethods());
			se.getELProcessor().defineBean("dialog", this);
			se.getELProcessor().defineBean("window", getWindow());
			setSwingEngine(se);
		}
		return swingEngine;
	}
	public void setSwingEngine(SwingEngine<BindingExamplesTestDialog> swingEngine)
	{
		this.swingEngine = swingEngine;
	}
	
	// Bound in BindingExamplesTestDialog.xml
	private JTextFieldBind textField;
	public JTextFieldBind getTextField()
	{
		return textField;
	}
	public void setTextField(JTextFieldBind textField)
	{
		this.textField = textField;
	}

	private String testValue = "TEST2";
	public final String getTestValue()
	{
		return testValue;
	}
	public final void setTestValue(String testValue)
	{
		this.testValue = testValue;
	}
	
	private Binding<BindingExamplesTestDialog, String, BindingExamplesFrame, String> testValueBinding;
	public Binding<BindingExamplesTestDialog, String, BindingExamplesFrame, String> getTestValueBinding()
	{
		return testValueBinding;
	}
	public void setTestValueBinding(Binding<BindingExamplesTestDialog, String, BindingExamplesFrame, String> testValueBinding)
	{
		this.testValueBinding = testValueBinding;
	}
	
	/**
	 * Construct with a given {@link Frame} owner.
	 * 
	 * @param bindingExamplesFrame The owning frame.
	 */
	public BindingExamplesTestDialog(BindingExamplesFrame target)
	{
		setWindow(target);
		try
		{
			getSwingEngine().render("org/swixml/examples/BindingExamplesTestDialog.xml");
			
			setTestValueBinding(createTestValueBinding(target));
			
			setLocationRelativeTo(null);
			setVisible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private Binding<BindingExamplesTestDialog, String, BindingExamplesFrame, String>
		createTestValueBinding(BindingExamplesFrame target)
	{
		BeanProperty<BindingExamplesTestDialog, String> testValueSource = create("testValue");
		BeanProperty<BindingExamplesFrame, String> testValueTarget = create("testValue");
		return
			Bindings.createAutoBinding
			(
				READ_WRITE,
				this, testValueSource,
				target, testValueTarget,
				"testValueBinding"
			);
	}

	public boolean isSubmitEnabled()
	{
		return true;
	}

	@Action(enabledProperty = "submitEnabled")
	public void submit()
	{
		getTestValueBinding().refresh();
		BindingExamplesFrame frame = getTestValueBinding().getTargetObject();
		out.printf("Submitted\n");
		out.printf("  TextField (Dialog): %s\n", getTextField().getText());
		out.printf("  TestValue (Dialog): %s\n", getTestValue());
		out.printf("  TestValue (Frame).: %s\n", frame.getTestValue());
		out.printf("  TextField (Frame).: %s\n", frame.getTextField().getText());
	}
}
