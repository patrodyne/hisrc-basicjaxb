package org.swixml.jsr.widgets;

import static javax.swing.SwingConstants.LEFT;
import static org.swixml.converters.PrimitiveConverter.getConstantValue;
import static org.swixml.jsr295.BindingUtils.parseBind;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Converter;

/**
 * @see <a href="file:../../package-info.java">LICENSE: package-info</a>
 * 
 * @author softphone
 */
public class JSpinnerBind
	extends JSpinner
	implements BindableBasicWidget
{
	private static final long serialVersionUID = 20240701L;

	public static class Date extends JSpinnerBind
	{
		private static final long serialVersionUID = 20240701L;

		public Date()
		{
			super(new SpinnerDateModel());
		}

		private String dateFormat;
		public String getDateFormat() { return dateFormat; }
		public void setDateFormat(String dateFormat) { this.dateFormat = dateFormat; }
		
		@Override
		public void addNotify()
		{
			if ( getDateFormat() != null )
				setEditor(new JSpinner.DateEditor(this, getDateFormat()));
			else
				setEditor(new JSpinner.DateEditor(this));
			
			super.addNotify();
		}
	}

	public JSpinnerBind()
	{
		super();
	}

	public JSpinnerBind(SpinnerModel spinnerModel)
	{
		super(spinnerModel);
	}

	@Override
	public String getBindWith()
	{
		return (String) getClientProperty(BINDWITH_PROPERTY);
	}
	@Override
	public void setBindWith(String bindWith)
	{
		putClientProperty(BINDWITH_PROPERTY, bindWith);
	}

	@Override
	public Converter<?, ?> getConverter()
	{
		return (Converter<?, ?>) getClientProperty(CONVERTER_PROPERTY);
	}
	@Override
	public void setConverter(Converter<?, ?> converter)
	{
		putClientProperty(CONVERTER_PROPERTY, converter);
	}
	
	@Override
	public Binding<?, ?, ?, ?> getBinding()
	{
		return (Binding<?, ?, ?, ?>) getClientProperty(BINDING_PROPERTY);
	}
	@Override
	public void setBinding(Binding<?, ?, ?, ?> binding)
	{
		putClientProperty(BINDING_PROPERTY, binding);
	}
	
	@Override
	public BindingGroup getBindingGroup()
	{
		BindingGroup bindingGroup = (BindingGroup) getClientProperty(BINDING_GROUP_PROPERTY);
		if ( bindingGroup == null )
		{
			bindingGroup = new BindingGroup();
			setBindingGroup(bindingGroup);
			return bindingGroup;
		}
		else
			return (BindingGroup) bindingGroup;
	}
	@Override
	public void setBindingGroup(BindingGroup bindingGroup)
	{
		putClientProperty(BINDING_GROUP_PROPERTY, bindingGroup);
	}
	
	private String columns;
	public String getColumns() { return columns; }
	public void setColumns(String columns) { this.columns = columns; }

	private String horizontalAlignment;
	public String getHorizontalAlignment() { return horizontalAlignment; }
	public void setHorizontalAlignment(String horizontalAlignment) { this.horizontalAlignment = horizontalAlignment; }
	
	/**
	 * Create and add {@link AutoBinding} instance(s) to synchronize model
	 * properties with this {@link JSpinner}.
	 * 
	 * <p>Notifies this {@link Component} that it now has a parent component. It
	 * makes the {@link Container} displayable by connecting it to a native
	 * screen resource.</p>
	 */
	@Override
	public void addNotify()
	{
		final String bindWith = getBindWith();
		if ( (null != bindWith) && !bindWith.isEmpty() )
		{
			setBinding(parseBind(getBindingGroup(), this, "value", bindWith, getConverter()));
			getBindingGroup().bind();
		}

		JFormattedTextField textField = null;
		if ( getEditor() instanceof JSpinner.DateEditor )
			textField = ((JSpinner.DateEditor) getEditor()).getTextField();
		else if ( getEditor() instanceof JSpinner.ListEditor )
			textField = ((JSpinner.ListEditor) getEditor()).getTextField();
		else if ( getEditor() instanceof JSpinner.NumberEditor )
			textField = ((JSpinner.NumberEditor) getEditor()).getTextField();

		if ( textField != null )
		{
			if ( getColumns() != null )
				textField.setColumns(Integer.parseInt(getColumns()));
			if ( getHorizontalAlignment() != null )
			{
				Integer alignment = getConstantValue(JTextField.class, getHorizontalAlignment(), LEFT);
				textField.setHorizontalAlignment(alignment);
			}
		}
		
		super.addNotify();
	}
}
