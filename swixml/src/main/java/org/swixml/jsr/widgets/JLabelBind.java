package org.swixml.jsr.widgets;

import static org.swixml.jsr295.BindingUtils.parseBindRead;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JLabel;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Converter;

/**
 * @see <a href="file:../../package-info.java">LICENSE: package-info</a>
 * 
 * @author sorrentino
 */
public class JLabelBind extends JLabel implements BindableBasicWidget
{
	private static final long serialVersionUID = 20240701L;
	
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
	
	/**
	 * Create and add {@link AutoBinding} instance(s) to synchronize model
	 * properties with this {@link JLabel}.
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
			setBinding(parseBindRead(getBindingGroup(), this, "text", bindWith, getConverter()));
			getBindingGroup().bind();
		}
		
		super.addNotify();
	}
}
