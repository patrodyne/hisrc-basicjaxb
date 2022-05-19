package org.jvnet.jaxb2_commons.plugin.simpletostring;

import org.jvnet.jaxb2_commons.plugin.codegenerator.AbstractCodeGenerationImplementor;

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;

public class ToStringCodeGenerationImplementor extends AbstractCodeGenerationImplementor<ToStringArguments>
{
	public ToStringCodeGenerationImplementor(JCodeModel codeModel)
	{
		super(codeModel);
	}

	private void ifHasSetValue_AppendToStringBuilder( ToStringArguments arguments, JBlock block,
		JExpression valueToAppend, boolean isAlwaysSet, boolean checkForNullRequired)
	{
		JBlock subBlock = arguments.ifHasSetValue(block, isAlwaysSet, checkForNullRequired);
		if ( arguments.getFieldSeparator() != null )
			subBlock.add(arguments.stringBuilder().invoke("append").arg(JExpr.lit(arguments.getFieldSeparator())));
		if ( arguments.getFieldName() != null )
			subBlock.add(arguments.stringBuilder().invoke("append").arg(JExpr.lit(arguments.getFieldName()+"=")));
		subBlock.add(arguments.stringBuilder().invoke("append").arg(valueToAppend));
	}

	private void ifHasSetValue_AppendCountToStringBuilder( ToStringArguments arguments, JBlock block,
		JExpression valueToAppend, boolean isAlwaysSet, boolean checkForNullRequired)
	{
		JBlock subBlock = arguments.ifHasSetValue(block, isAlwaysSet, checkForNullRequired);
		if ( arguments.getFieldSeparator() != null )
			subBlock.add(arguments.stringBuilder().invoke("append").arg(JExpr.lit(arguments.getFieldSeparator())));
		if ( arguments.getFieldName() != null )
			subBlock.add(arguments.stringBuilder().invoke("append").arg(JExpr.lit(arguments.getFieldName()+"=")));
		
		if ( arguments.value().type().isArray() )
		{
			subBlock.add(arguments.stringBuilder().invoke("append").arg(JExpr.lit("<length=")));
			subBlock.add(arguments.stringBuilder().invoke("append").arg(valueToAppend.ref("length")));
		}
		else
		{
			subBlock.add(arguments.stringBuilder().invoke("append").arg(JExpr.lit("<size=")));
			subBlock.add(arguments.stringBuilder().invoke("append").arg(valueToAppend.invoke("size")));
		}
		subBlock.add(arguments.stringBuilder().invoke("append").arg(JExpr.lit(">")));
	}

	@Override
	public void onArray(JBlock block, boolean isAlwaysSet, ToStringArguments arguments)
	{
		if ( arguments.isShowChildItems() )
			ifHasSetValue_AppendToStringBuilder(arguments, block, arguments.value(), isAlwaysSet, false);
		else
			ifHasSetValue_AppendCountToStringBuilder(arguments, block, arguments.value(), isAlwaysSet, true);
	}

	@Override
	public void onBoolean(ToStringArguments arguments, JBlock block, boolean isAlwaysSet)
	{
		ifHasSetValue_AppendToStringBuilder(arguments, block, arguments.value(), isAlwaysSet, true);
	}

	@Override
	public void onByte(ToStringArguments arguments, JBlock block, boolean isAlwaysSet)
	{
		ifHasSetValue_AppendToStringBuilder(arguments, block, arguments.value(), isAlwaysSet, true);
	}

	@Override
	public void onChar(ToStringArguments arguments, JBlock block, boolean isAlwaysSet)
	{
		ifHasSetValue_AppendToStringBuilder(arguments, block, arguments.value(), isAlwaysSet, true);
	}

	@Override
	public void onDouble(ToStringArguments arguments, JBlock block, boolean isAlwaysSet)
	{
		ifHasSetValue_AppendToStringBuilder(arguments, block, arguments.value(), isAlwaysSet, true);
	}

	@Override
	public void onFloat(ToStringArguments arguments, JBlock block, boolean isAlwaysSet)
	{
		ifHasSetValue_AppendToStringBuilder(arguments, block, arguments.value(), isAlwaysSet, true);
	}

	@Override
	public void onInt(ToStringArguments arguments, JBlock block, boolean isAlwaysSet)
	{
		ifHasSetValue_AppendToStringBuilder(arguments, block, arguments.value(), isAlwaysSet, true);
	}

	@Override
	public void onLong(ToStringArguments arguments, JBlock block, boolean isAlwaysSet)
	{
		ifHasSetValue_AppendToStringBuilder(arguments, block, arguments.value(), isAlwaysSet, true);
	}

	@Override
	public void onShort(ToStringArguments arguments, JBlock block, boolean isAlwaysSet)
	{
		ifHasSetValue_AppendToStringBuilder(arguments, block, arguments.value(), isAlwaysSet, true);
	}

	@Override
	public void onObject(ToStringArguments arguments, JBlock block,	boolean isAlwaysSet)
	{
		if ( arguments.valueIsCollection() && !arguments.isShowChildItems() )
			ifHasSetValue_AppendCountToStringBuilder(arguments, block, arguments.value(), isAlwaysSet, true);
		else
			ifHasSetValue_AppendToStringBuilder(arguments, block, arguments.value(), isAlwaysSet, true);
	}
}
