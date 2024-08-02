package org.swixml.converters;

import java.awt.event.ActionEvent;
import java.lang.reflect.Field;

import javax.swing.AbstractAction;
import javax.swing.Action;
import org.swixml.Converter;
import org.swixml.LogAware;

import org.swixml.SwingEngine;
import org.swixml.XAction;
import org.swixml.dom.Attribute;
import org.swixml.el.ELAction;
import org.swixml.el.ELUtility;

/**
 * The ActionConverter is a tagging class that is only used to register the
 * Action.class with the ConverterLibrary
 * 
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 *
 * @since swixml 1.0
 */
public class ActionConverter implements Converter<Action>, LogAware
{
	private Action EMPTY_ACTION = new AbstractAction()
	{
		private static final long serialVersionUID = 20240701L;

		@Override
		public void actionPerformed(ActionEvent e)
		{
			logger.info("empty action performed {0}", e.getSource());
		}
	};

	/**
	 * Convert the value of the given <code>Attribute</code> object into an
	 * output object of the specified type.
	 *
	 * @param type {@link Class} Data type to which the Attribute's value should be converted.
	 * @param attr {@link Attribute} the attribute, providing the value to be converted.
	 * 
	 * @return The {@link Action} converted from the {@link Attribute} instance.
	 */
	@Override
	public Action convert(Class<Action> type, Attribute attr, SwingEngine<?> engine)
		throws Exception
	{
		Action para = null;
		logger.info(String.format("ActionConverter attr[%s]=[%s] namespace uri[%s] prefix [%s]", attr.getLocalName(),
			attr.getValue(), attr.getNamespaceURI(), attr.getPrefix()));
		
		final Object client = engine.getClient();
		if ( null == client )
			return EMPTY_ACTION;
		
		if ( ELUtility.isELAttribute(attr) )
		{
			para = new ELAction(engine, attr.getValue());
		}
		else
		{
			try
			{
				Field f = client.getClass().getField(attr.getValue());
				para = (Action) f.get(client);
			}
			catch (NoSuchFieldException e)
			{
				try
				{
					para = new XAction(client, attr.getValue());
				}
				catch (NoSuchMethodException e1)
				{
					para = EMPTY_ACTION;
				}
			}
		}
		return para;
	}

	/**
	 * A <code>conversTo</code> method informs about the Class type the
	 * converter is returning when its <code>convert</code> method is called
	 *
	 * @return <code>Class</code> - the Class the converter is returning when
	 *         its convert method is called
	 */
	@Override
	public Class<Action> convertsTo()
	{
		return Action.class;
	}
}
