package org.swixml.jsr.widgets;

import javax.swing.JSpinner;

import org.jdesktop.beansbinding.Converter;
import org.swixml.jsr295.BindingUtils;

/**
 * @see <a href="file:../../package-info.java">LICENSE: package-info</a>
 * 
 * @author softphone
 */
public class JSpinnerEx
	extends JSpinner
	implements BindableBasicWidget
{
	private static final long serialVersionUID = 20240701L;

	public static class Date extends JSpinnerEx
	{
		private static final long serialVersionUID = 20240701L;
		private String dateFormat;

		public Date()
		{
			super();
		}

		public String getDateFormat()
		{
			return dateFormat;
		}

		public void setDateFormat(String dateFormat)
		{
			this.dateFormat = dateFormat;
		}

		@Override
		public void addNotify()
		{
			if ( getDateFormat() != null )
			{
				setEditor(new JSpinner.DateEditor(this, getDateFormat()));
			}
			else
			{
				setEditor(new JSpinner.DateEditor(this));
			}
			super.addNotify();
		}
	}

	public JSpinnerEx()
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
			BindingUtils.parseBind(this, "value", bindWith, getConverter());
		}
		super.addNotify();
	}
}
