package org.jvnet.basicjaxb.plugin.simpletostring;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.ListIterator;
import java.util.Map;

import org.jvnet.basicjaxb.plugin.codegenerator.Arguments;

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JType;
import com.sun.codemodel.JVar;

public class ToStringArguments implements Arguments<ToStringArguments>
{
	public ToStringArguments(JCodeModel codeModel, JVar stringBuilder, JVar value, JExpression hasSetValue,
		String fieldSeparator, String fieldName, boolean showChildItems, boolean hasDefaultValue)
	{
		this.codeModel = requireNonNull(codeModel);
		this.stringBuilder = requireNonNull(stringBuilder);
		this.value = requireNonNull(value);
		this.hasSetValue = requireNonNull(hasSetValue);
		this.fieldSeparator = fieldSeparator;
		this.fieldName = fieldName;
		this.showChildItems = showChildItems;
		this.hasDefaultValue = hasDefaultValue;
	}

	private final JCodeModel codeModel;
	public JCodeModel getCodeModel() { return codeModel; }

	private final JVar stringBuilder;
	public JVar stringBuilder() { return stringBuilder; }

	private final JVar value;
	public JVar value() { return value; }

	private final JExpression hasSetValue;
	public JExpression hasSetValue() { return hasSetValue; }
	
	private String fieldSeparator;
	public String getFieldSeparator() { return fieldSeparator; }

	private String fieldName;
	public String getFieldName() { return fieldName; }
	
	private boolean showChildItems;
	public boolean isShowChildItems() { return showChildItems; }

	private boolean hasDefaultValue;
	public boolean hasDefaultValue() { return hasDefaultValue; }

	private ToStringArguments spawn(JVar value, JExpression hasSetValue)
	{
		return new ToStringArguments(getCodeModel(), stringBuilder(), value, hasSetValue,
			getFieldSeparator(), getFieldName(), isShowChildItems(), hasDefaultValue());
	}

	@Override
	public ToStringArguments property(JBlock block, String propertyName,
			String propertyMethod, JType declarablePropertyType,
			JType propertyType, Collection<JType> possiblePropertyTypes)
	{
		final JVar propertyValue = block.decl
		(
			JMod.FINAL,	declarablePropertyType,	value().name() + propertyName,
			value().invoke(propertyMethod)
		);
		
		// We assume that primitive properties are always set
		boolean isAlwaysSet = propertyType.isPrimitive();
		final JExpression propertyHasSetValue = isAlwaysSet ? JExpr.TRUE : propertyValue.ne(JExpr._null());
		
		return spawn(propertyValue, propertyHasSetValue);
	}

	@Override
	public ToStringArguments iterator(JBlock block, JType elementType)
	{
		final JVar listIterator = block.decl
		(
			JMod.FINAL, getCodeModel().ref(ListIterator.class).narrow(elementType), value().name() + "ListIterator",
			value().invoke("listIterator")
		);
		
		return spawn(listIterator, JExpr.TRUE);
	}

	@Override
	public ToStringArguments element(JBlock subBlock, JType elementType)
	{
		final JVar elementValue = subBlock.decl
		(
			JMod.FINAL, elementType, value().name() + "Element",
			value().invoke("next")
		);
		
		final boolean isElementAlwaysSet = elementType.isPrimitive();
		final JExpression elementHasSetValue = isElementAlwaysSet ? JExpr.TRUE : elementValue.ne(JExpr._null());
		
		return spawn(elementValue, elementHasSetValue);
	}

	@Override
	public JExpression _instanceof(JType type)
	{
		return value()._instanceof(type);
	}

	@Override
	public ToStringArguments cast(String suffix, JBlock block, JType jaxbElementType, boolean suppressWarnings)
	{
		final JVar castedValue = block.decl
		(
			JMod.FINAL, jaxbElementType, value().name() + suffix,
			JExpr.cast(jaxbElementType,	value())
		);
		
		if (suppressWarnings)
			castedValue.annotate(SuppressWarnings.class).param("value", "unchecked");
		
		return spawn(castedValue, JExpr.TRUE);
	}

	@Override
	public JBlock ifHasSetValue(JBlock block, boolean isAlwaysSet, boolean checkForNullRequired)
	{
		if (isAlwaysSet || !checkForNullRequired)
			return block;
		else
			return block._if(hasSetValue())._then();
	}

	@Override
	public JBlock _while(JBlock block)
	{
		final JBlock subBlock = block._while(value().invoke("hasNext")).body();
		return subBlock;
	}
	
	public boolean valueIsCollection()
	{
		boolean isCollection = false;
		if ( (value() != null) && value().type() instanceof JClass )
		{
			JClass jclass = (JClass) value().type();
			isCollection = getCodeModel().ref(Collection.class).isAssignableFrom(jclass) ||
				getCodeModel().ref(Map.class).isAssignableFrom(jclass);
		}
		return isCollection;
	}

}
