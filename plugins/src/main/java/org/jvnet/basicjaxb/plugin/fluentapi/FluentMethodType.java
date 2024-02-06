package org.jvnet.basicjaxb.plugin.fluentapi;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.uncapitalize;
import static org.jvnet.basicjaxb.plugin.fluentapi.FluentApiPlugin.GETTER_METHOD_PREFIX_LEN;
import static org.jvnet.basicjaxb.plugin.fluentapi.FluentApiPlugin.SETTER_METHOD_PREFIX_LEN;
import static org.jvnet.basicjaxb.util.CodeModelUtils.groupMethods;

import java.util.Collection;
import java.util.List;

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JConditional;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JForEach;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JType;
import com.sun.codemodel.JVar;

/**
 * An enumeration of the Fluent API method creators.
 * 
 * @author Hanson Char
 */
public enum FluentMethodType
{
	FLUENT_SETTER
	{
		/**
		 * Adds a Fluent API method, which invokes the given setter method, to
		 * the given class. This applies to both simple property setter method
		 * and indexed property setter method.
		 */
		@Override
		public void createFluentMethod(JDefinedClass implClass, FluentMethodInfo fluentMethodInfo)
		{
			JMethod setterMethod = fluentMethodInfo.getOriginalMethod();
			String setterName = setterMethod.name();
			
			// Create a fluent method for the respective set* method.
			String fluentMethodName =
				createFluentMethodName(fluentMethodInfo, setterName.substring(SETTER_METHOD_PREFIX_LEN));
			int mods = JMod.PUBLIC | setterMethod.mods().getValue() & JMod.FINAL;
			JMethod fluentMethod = implClass.method(mods, implClass, fluentMethodName);
			groupMethods(implClass, setterMethod, fluentMethod);
			
			if (fluentMethodInfo.isOverride())
				fluentMethod.annotate(Override.class);
			
			JVar[] jvars = setterMethod.listParams();
			// jvars.length == 1 means simple property setter method.
			// jvars.length == 2 means indexed property setter method.
			assert jvars.length == 1 || jvars.length == 2;
			
			// with the same parameter(s) as the set* method
			for (JVar jvar : jvars)
				fluentMethod.param(jvar.mods().getValue(), jvar.type(), jvar.name());
			
			JBlock jblock = fluentMethod.body();
			// The fluent method in turn invoke the setter method
			JInvocation jinvocation = jblock.invoke(setterMethod);
			
			// passing the same list of arguments
			for (JVar jvar : jvars)
				jinvocation.arg(jvar);
			
			// and return "this"
			jblock._return(JExpr._this());
			return;
		}
	},
	FLUENT_LIST_SETTER
	{
		/**
		 * Create a fluent setter method for List, with support of
		 * variable arguments.
		 */
		@Override
		public void createFluentMethod(JDefinedClass implClass, FluentMethodInfo fluentMethodInfo)
		{
			JMethod listGetterMethod = fluentMethodInfo.getOriginalMethod();
			JType returnJType = listGetterMethod.type();
			// As is already checked in isListGetterMethod(JMethod):
			// 1) the return type must be a subtype of JClass; and
			// 2) the number of type parameters must be 1
			JClass returnJClass = JClass.class.cast(returnJType);
			List<JClass> typeParams = returnJClass.getTypeParameters();
			
			assert typeParams.size() == 1;
			JClass typeParam = typeParams.get(0);
			
			// Guard against: Type safety: Potential heap pollution via varargs parameter values
			if ( !(fluentMethodInfo.getPlugin().getEnforceTypeSafety() && typeParam.isParameterized()) )
			{
				String listGetterName = listGetterMethod.name();
				
				// Create a fluent method for the respective List<T> get* method.
				String fluentMethodName =
					createFluentMethodName(fluentMethodInfo, listGetterName.substring(GETTER_METHOD_PREFIX_LEN));

				int mods = JMod.PUBLIC | listGetterMethod.mods().getValue() & JMod.FINAL;
				JMethod fluentMethod = implClass.method(mods, implClass, fluentMethodName);
				groupMethods(implClass, listGetterMethod, fluentMethod);
				
				if (fluentMethodInfo.isOverride())
					fluentMethod.annotate(Override.class);
				
				// Support variable arguments
				JVar jvarParam = fluentMethod.varParam(typeParam, VALUES);
				JBlock body = fluentMethod.body();
				JConditional cond = body._if(jvarParam.ne(JExpr._null()));
				JForEach forEach = cond._then().forEach(typeParam, VALUE, JExpr.ref(VALUES));
				JInvocation addInvocation = forEach.body().invoke(JExpr.invoke(listGetterMethod), "add");
				addInvocation.arg(JExpr.ref(VALUE));
				
				// and return "this"
				body._return(JExpr._this());
			}
			
			return;
		}
	},
	FLUENT_COLLECTION_SETTER
	{
		// Originally proposed by Alex Wei ozgwei@dev.java.net:
		// https://jaxb2-commons.dev.java.net/issues/show_bug.cgi?id=12
		/**
		 * Create a fluent setter method for List, with support of a
		 * java.util.Collection argument.
		 */
		@Override
		public void createFluentMethod(JDefinedClass implClass, FluentMethodInfo fluentMethodInfo)
		{
			JMethod listGetterMethod = fluentMethodInfo.getOriginalMethod();
			String listGetterName = listGetterMethod.name();
			
			// Create a fluent method for the respective List<T> get* method.
			String fluentMethodName =
				createFluentMethodName(fluentMethodInfo, listGetterName.substring(GETTER_METHOD_PREFIX_LEN));
			int mods = JMod.PUBLIC | listGetterMethod.mods().getValue() & JMod.FINAL;
			JMethod fluentMethod = implClass.method(mods, implClass, fluentMethodName);
			groupMethods(implClass, listGetterMethod, fluentMethod);
			
			if (fluentMethodInfo.isOverride())
				fluentMethod.annotate(Override.class);
			
			JType returnJType = listGetterMethod.type();
			// As is already checked in isListGetterMethod(JMethod):
			// 1) the return type must be a sub-type of JClass; and
			// 2) the number of type parameters must be 1
			JClass returnJClass = JClass.class.cast(returnJType);
			List<JClass> typeParams = returnJClass.getTypeParameters();
			
			assert typeParams.size() == 1;
			JClass typeParam = typeParams.get(0);
			
			// Support Collection with type parameter
			JClass narrowedCollectionJClass = implClass.owner().ref(Collection.class).narrow(typeParam);
			JVar jvarParam = fluentMethod.param(narrowedCollectionJClass, VALUES);
			JBlock body = fluentMethod.body();
			JConditional cond = body._if(jvarParam.ne(JExpr._null()));
			JInvocation addInvocation = cond._then().invoke(JExpr.invoke(listGetterMethod), "addAll");
			addInvocation.arg(jvarParam);
			
			// and return "this"
			body._return(JExpr._this());
			return;
		}
	};
	
	private static String createFluentMethodName(FluentMethodInfo fluentMethodInfo, String beanMethodName)
	{
		String fluentMethodPrefix = fluentMethodInfo.getPlugin().getFluentMethodPrefix();
		String fluentMethodName = null;
		if ( !isBlank(fluentMethodPrefix) )
			fluentMethodName = fluentMethodPrefix + capitalize(beanMethodName);
		else
			fluentMethodName = uncapitalize(beanMethodName);
		return fluentMethodName;
	}

	private static final String VALUE = "value";
	private static final String VALUES = "values";
	
	/**
	 * Abstract method to create a Fluent API. Implementations are enumerated above.
	 * 
	 * @param implClass The implementing class.
	 * @param fluentMethodInfo The Fluent method information.
	 */
	public abstract void createFluentMethod(JDefinedClass implClass, FluentMethodInfo fluentMethodInfo);
}
