package org.swixml.jsr.widgets;

import javax.swing.JRadioButton;

import org.jdesktop.beansbinding.Converter;
import org.swixml.jsr295.BindingUtils;

/**
 * @see <a href="file:../../package-info.java">LICENSE: package-info</a>
 * 
 * @author sorrentino
 */
public class JRadioButtonEx
	extends JRadioButton
	implements BindableBasicWidget
{
	private static final long serialVersionUID = 20240701L;

	public JRadioButtonEx()
	{
		super();
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
	public void setConverter(Converter<?, ?> converter)
	{
		putClientProperty(CONVERTER_PROPERTY, converter);
	}

	@Override
	public Converter<?, ?> getConverter()
	{
		return (Converter<?, ?>) getClientProperty(CONVERTER_PROPERTY);
	}

	@Override
	public void addNotify()
	{
		final String bindWith = getBindWith();
		if ( null != bindWith && !bindWith.isEmpty() )
		{
			BindingUtils.parseBind(this, "selected", bindWith, getConverter());
		}
		super.addNotify();
	}
}
