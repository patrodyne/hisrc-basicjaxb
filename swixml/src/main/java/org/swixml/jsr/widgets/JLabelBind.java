package org.swixml.jsr.widgets;

import static org.swixml.jsr295.BindingUtils.parseBindRead;

import javax.swing.JLabel;

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
	public void addNotify()
	{
		final String bindWith = getBindWith();
		if ( (null != bindWith) && !bindWith.isEmpty() )
			parseBindRead(this, "text", bindWith, getConverter());
		
		super.addNotify();
	}
}
