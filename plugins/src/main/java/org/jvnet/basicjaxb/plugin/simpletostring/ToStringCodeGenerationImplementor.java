package org.jvnet.basicjaxb.plugin.simpletostring;

import org.jvnet.basicjaxb.plugin.codegenerator.AbstractCodeGenerationImplementor;

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JOp;

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
		if (!isAlwaysSet && arguments.hasDefaultValue())
		{
			subBlock._if(arguments.hasSetValue().not())._then()
				.add(arguments.stringBuilder().invoke("append").arg(JExpr.lit("(default)")));
		}
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
			JExpression condLength = JOp.cond(arguments.hasSetValue(), valueToAppend.ref("length"), JExpr.lit("null"));
			subBlock.add(arguments.stringBuilder().invoke("append").arg(JExpr.lit("<length=")));
			subBlock.add(arguments.stringBuilder().invoke("append").arg(condLength));
		}
		else
		{
			JExpression condSize = JOp.cond(arguments.hasSetValue(), valueToAppend.invoke("size"), JExpr.lit("null"));
			subBlock.add(arguments.stringBuilder().invoke("append").arg(JExpr.lit("<size=")));
			subBlock.add(arguments.stringBuilder().invoke("append").arg(condSize));
		}
		subBlock.add(arguments.stringBuilder().invoke("append").arg(JExpr.lit(">")));
	}

	@Override
	public void onArray(JBlock block, boolean isAlwaysSet, ToStringArguments arguments)
	{
		if ( arguments.isShowChildItems() )
			ifHasSetValue_AppendToStringBuilder(arguments, block, arguments.value(), isAlwaysSet, false);
		else
			ifHasSetValue_AppendCountToStringBuilder(arguments, block, arguments.value(), isAlwaysSet, false);
	}

	@Override
	public void onBoolean(ToStringArguments arguments, JBlock block, boolean isAlwaysSet)
	{
		ifHasSetValue_AppendToStringBuilder(arguments, block, arguments.value(), isAlwaysSet, false);
	}

	@Override
	public void onByte(ToStringArguments arguments, JBlock block, boolean isAlwaysSet)
	{
		ifHasSetValue_AppendToStringBuilder(arguments, block, arguments.value(), isAlwaysSet, false);
	}

	@Override
	public void onChar(ToStringArguments arguments, JBlock block, boolean isAlwaysSet)
	{
		ifHasSetValue_AppendToStringBuilder(arguments, block, arguments.value(), isAlwaysSet, false);
	}

	@Override
	public void onDouble(ToStringArguments arguments, JBlock block, boolean isAlwaysSet)
	{
		ifHasSetValue_AppendToStringBuilder(arguments, block, arguments.value(), isAlwaysSet, false);
	}

	@Override
	public void onFloat(ToStringArguments arguments, JBlock block, boolean isAlwaysSet)
	{
		ifHasSetValue_AppendToStringBuilder(arguments, block, arguments.value(), isAlwaysSet, false);
	}

	@Override
	public void onInt(ToStringArguments arguments, JBlock block, boolean isAlwaysSet)
	{
		ifHasSetValue_AppendToStringBuilder(arguments, block, arguments.value(), isAlwaysSet, false);
	}

	@Override
	public void onLong(ToStringArguments arguments, JBlock block, boolean isAlwaysSet)
	{
		ifHasSetValue_AppendToStringBuilder(arguments, block, arguments.value(), isAlwaysSet, false);
	}

	@Override
	public void onShort(ToStringArguments arguments, JBlock block, boolean isAlwaysSet)
	{
		ifHasSetValue_AppendToStringBuilder(arguments, block, arguments.value(), isAlwaysSet, false);
	}

	@Override
	public void onObject(ToStringArguments arguments, JBlock block,	boolean isAlwaysSet)
	{
		if ( arguments.valueIsCollection() && !arguments.isShowChildItems() )
			ifHasSetValue_AppendCountToStringBuilder(arguments, block, arguments.value(), isAlwaysSet, false);
		else
			ifHasSetValue_AppendToStringBuilder(arguments, block, arguments.value(), isAlwaysSet, false);
	}
}
