package org.swixml.converters;

import static javax.swing.SwingConstants.CENTER;
import static javax.swing.SwingConstants.LEFT;
import static javax.swing.SwingConstants.RIGHT;
import static org.jvnet.basicjaxb.lang.StringUtils.isBlank;
import static org.swixml.Parser.ELVAR_DOM_ELEMENT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import org.swixml.SwingEngine;
import org.swixml.dom.Attribute;
import org.swixml.jsr.widgets.TableColumnBind;
import org.swixml.renderers.AlignableTableCellHeaderRenderer;
import org.swixml.renderers.AlignableTableCellRenderer;
import org.w3c.dom.Element;

public class TableCellRendererConverter
	extends AbstractConverter<TableCellRenderer>
{
	/** converter's return type */
	public static final Class<TableCellRenderer> TEMPLATE = TableCellRenderer.class;
	
	private static final int DEFAULT_HORIZONTAL_ALIGNMENT = LEFT;
	
	private static final Pattern TABLE_CELL_RENDERER_PATTERN = Pattern.compile("(\\w+)(?:[(]([\\w, -\\\\*]+)*[)])?");

	@Override
	public Class<TableCellRenderer> convertsTo()
	{
		return TEMPLATE;
	}

	@Override
	public TableCellRenderer convert(String value, Class<TableCellRenderer> type, Attribute attr, SwingEngine<?> engine)
		throws Exception
	{
		DefaultTableCellRenderer tableCellRenderer = null;
		
		Matcher matcher = TABLE_CELL_RENDERER_PATTERN.matcher(value);
		if ( matcher.matches() )
		{
			int groupCount = matcher.groupCount();
			String tableCellRendereType = matcher.group(1);
			if ( AlignableTableCellRenderer.class.getSimpleName().equals(tableCellRendereType) )
			{
				String[] paramArray = new String[0];
				if ( groupCount > 1 )
				{
					String params = matcher.group(2);
					if ( params != null )
						paramArray = params.split(",");
					if ( paramArray.length > 0 )
					{
						//
						// The first parameter might be a pre-defined constant's name
						//
						Attribute tokenAttr = new Attribute("horizontalAlignment", paramArray[0]);
						Object ha = PrimitiveConverter.conv(Object.class, tokenAttr, engine);
						tableCellRenderer = new AlignableTableCellRenderer((int) ha);
					}
				}

				// Resolve alignment by field type.
				if ( tableCellRenderer == null )
					tableCellRenderer = new AlignableTableCellRenderer(alignmentByType(engine));
			}
			else if ( AlignableTableCellHeaderRenderer.class.getSimpleName().equals(tableCellRendereType) )
			{
				String[] paramArray = new String[0];
				if ( groupCount > 1 )
				{
					String params = matcher.group(2);
					if ( params != null )
						paramArray = params.split(",");
					if ( paramArray.length > 0 )
					{
						//
						// The first parameter might be a pre-defined constant's name
						//
						Attribute tokenAttr = new Attribute("horizontalAlignment", paramArray[0]);
						Object ha = PrimitiveConverter.conv(Object.class, tokenAttr, engine);
						tableCellRenderer = new AlignableTableCellHeaderRenderer((int) ha);
					}
				}

				// Header defaults to CENTER alignment.
				if ( tableCellRenderer == null )
					tableCellRenderer = new AlignableTableCellHeaderRenderer(CENTER);
			}
		}
		
		if ( tableCellRenderer == null )
		{
			if ( "headerRenderer".equals(attr.getLocalName()) )
				tableCellRenderer = new AlignableTableCellHeaderRenderer(CENTER);
			else
				tableCellRenderer = new AlignableTableCellRenderer(alignmentByType(engine));
		}
			
		return tableCellRenderer;
	}

	protected int alignmentByType(SwingEngine<?> engine)
	{
		String fieldTypeName = null;
		
		// First, attempt to resolve type by tag object field.
		TableColumnBind tcb = engine.getELMethods().resolveBean("this", TableColumnBind.class);
		if ( tcb != null )
		{
			if ( ! isBlank(tcb.getType()) )
				fieldTypeName = tcb.getType();
		}
		
		// Second, attempt to resolve type by DOM element attribute.
		if ( fieldTypeName == null )
		{
			Element elm = engine.getELMethods().resolveVariable(ELVAR_DOM_ELEMENT, Element.class);
			fieldTypeName = elm.getAttribute("type");
		}
		
		int alignment = DEFAULT_HORIZONTAL_ALIGNMENT;
		if ( String.class.getName().equals(fieldTypeName) )
			alignment = LEFT;
		else
			alignment = RIGHT;
		
		return alignment;
	}
}
