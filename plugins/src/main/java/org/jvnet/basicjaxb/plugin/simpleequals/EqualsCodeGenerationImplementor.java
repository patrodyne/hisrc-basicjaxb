package org.jvnet.basicjaxb.plugin.simpleequals;

import java.util.Arrays;

import org.jvnet.basicjaxb.plugin.codegenerator.AbstractCodeGenerationImplementor;

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JFieldRef;
import com.sun.codemodel.JForLoop;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JOp;
import com.sun.codemodel.JVar;

public class EqualsCodeGenerationImplementor
	extends AbstractCodeGenerationImplementor<EqualsArguments>
{
	private static final String VALUE_INDEX_NAME = "index";
	private static final JFieldRef VALUE_INDEX = JExpr.ref(VALUE_INDEX_NAME);

	public EqualsCodeGenerationImplementor(JCodeModel codeModel)
	{
		super(codeModel);
	}

	private void returnFalseIfNotEqualsCondition(EqualsArguments arguments, JBlock block, boolean isAlwaysSet,
		final JExpression notEqualsCondition)
	{
		arguments.ifHasSetValue(block, isAlwaysSet, true)._if(notEqualsCondition)._then()._return(JExpr.FALSE);
	}

	private void returnFalseIfNotEqualsForCondition(EqualsArguments arguments, JBlock block, boolean isAlwaysSet,
		final JExpression notEqualsCondition)
	{
		 JForLoop forLoop = arguments.ifHasSetValue(block, isAlwaysSet, true)._for();
		 JVar initVar = forLoop.init(getCodeModel().INT, VALUE_INDEX_NAME, JExpr.lit(0));
		 forLoop.test(initVar.lt(arguments.leftValue().invoke("size")));
		 forLoop.update(initVar.incr());
		 forLoop.body()._if(notEqualsCondition)._then()._return(JExpr.FALSE);
	}

	private void returnFalseIfNe(EqualsArguments arguments, JBlock block, boolean isAlwaysSet)
	{
		returnFalseIfNotEqualsCondition(arguments, block, isAlwaysSet,
			JOp.ne(arguments.leftValue(), arguments.rightValue()));
	}

	@Override
	public void onArray(JBlock block, boolean isAlwaysSet, EqualsArguments arguments)
	{
		returnFalseIfNotEqualsCondition(arguments, block, isAlwaysSet, getCodeModel().ref(Arrays.class)
			.staticInvoke("equals").arg(arguments.leftValue()).arg(arguments.rightValue()).not());
	}

	@Override
	public void onBoolean(EqualsArguments arguments, JBlock block, boolean isAlwaysSet)
	{
		returnFalseIfNe(arguments, block, isAlwaysSet);
	}

	@Override
	public void onByte(EqualsArguments arguments, JBlock block, boolean isAlwaysSet)
	{
		returnFalseIfNe(arguments, block, isAlwaysSet);
	}

	@Override
	public void onChar(EqualsArguments arguments, JBlock block, boolean isAlwaysSet)
	{
		returnFalseIfNe(arguments, block, isAlwaysSet);
	}

	@Override
	public void onDouble(EqualsArguments arguments, JBlock block, boolean isAlwaysSet)
	{
		final JClass Double$class = getCodeModel().ref(Double.class);
		final JExpression leftValueLongBits = Double$class.staticInvoke("doubleToLongBits").arg(arguments.leftValue());
		final JExpression rightValueLongBits = Double$class.staticInvoke("doubleToLongBits")
			.arg(arguments.rightValue());
		returnFalseIfNotEqualsCondition(arguments, block, isAlwaysSet, JOp.ne(leftValueLongBits, rightValueLongBits));
	}

	@Override
	public void onFloat(EqualsArguments arguments, JBlock block, boolean isAlwaysSet)
	{
		final JClass Float$class = getCodeModel().ref(Float.class);
		final JExpression leftValueLongBits = Float$class.staticInvoke("floatToIntBits").arg(arguments.leftValue());
		final JExpression rightValueLongBits = Float$class.staticInvoke("floatToIntBits").arg(arguments.rightValue());
		returnFalseIfNotEqualsCondition(arguments, block, isAlwaysSet, JOp.ne(leftValueLongBits, rightValueLongBits));
	}

	@Override
	public void onInt(EqualsArguments arguments, JBlock block, boolean isAlwaysSet)
	{
		returnFalseIfNe(arguments, block, isAlwaysSet);
	}

	@Override
	public void onLong(EqualsArguments arguments, JBlock block, boolean isAlwaysSet)
	{
		returnFalseIfNe(arguments, block, isAlwaysSet);
	}

	@Override
	public void onShort(EqualsArguments arguments, JBlock block, boolean isAlwaysSet)
	{
		returnFalseIfNe(arguments, block, isAlwaysSet);
	}

	@Override
	public void onObject(EqualsArguments arguments, JBlock block, boolean isAlwaysSet)
	{
//		returnFalseIfNotEqualsCondition(arguments, block, isAlwaysSet,
//			arguments.leftValue().invoke("equals").arg(arguments.rightValue()).not());

		////

		JExpression lhsValue = arguments.leftValue();
		JExpression rhsValue = arguments.rightValue();
		JExpression notEqualsCondition = lhsValue.invoke("equals").arg(rhsValue).not();
		if ( arguments.valueIsCollection() )
		{
			if ( arguments.getIdGetterName() == null )
				returnFalseIfNotEqualsCondition(arguments, block, isAlwaysSet, notEqualsCondition);
			else
			{
				lhsValue = getIdByIndex(arguments.leftValue(), arguments.getIdGetterName());
				rhsValue = getIdByIndex(arguments.rightValue(), arguments.getIdGetterName());
				notEqualsCondition = lhsValue.invoke("equals").arg(rhsValue).not();
				returnFalseIfNotEqualsForCondition(arguments, block, isAlwaysSet, notEqualsCondition);
			}
		}
		else
		{
			if ( arguments.getIdGetterName() != null )
			{
				lhsValue = arguments.leftValue().invoke(arguments.getIdGetterName());
				rhsValue = arguments.rightValue().invoke(arguments.getIdGetterName());
				notEqualsCondition = lhsValue.invoke("equals").arg(rhsValue).not();
			}
			returnFalseIfNotEqualsCondition(arguments, block, isAlwaysSet, notEqualsCondition);
		}
	}

	// Define a helper method to generate the invocation chain
	private JInvocation getIdByIndex(JExpression baseValue, String idGetterName)
	{
	    return baseValue.invoke("get").arg(VALUE_INDEX).invoke(idGetterName);
	}
}
