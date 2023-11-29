package org.jvnet.basicjaxb.plugin.wildcard;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.JTypeVar;

/**
 * A special {@link JClass} that represents an unknown class (except its name.)
 *
 * @see JCodeModel#directClass(String)
 */
public class LazyJClass extends JClass
{
	private String fullName;
	public String getFullName() { return fullName; }
	public void setFullName(String fullName) { this.fullName = fullName; }

	public LazyJClass(JCodeModel _owner, String fullName)
	{
		super(_owner);
		setFullName(fullName);
	}

	@Override
	public String name()
	{
		int i = getFullName().lastIndexOf('.');
		if (i >= 0)
			return getFullName().substring(i + 1);
		return getFullName();
	}

	@Override
	public String fullName()
	{
		return getFullName();
	}

	@Override
	public JPackage _package()
	{
		int i = getFullName().lastIndexOf('.');
		if (i >= 0)
			return owner()._package(getFullName().substring(0, i));
		else
			return owner().rootPackage();
	}

	@Override
	public JClass _extends()
	{
		return owner().ref(Object.class);
	}

	@Override
	public Iterator<JClass> _implements()
	{
		return Collections.emptyIterator();
	}

	@Override
	public boolean isInterface()
	{
		return false;
	}

	@Override
	public boolean isAbstract()
	{
		return false;
	}

	@Override
	protected JClass substituteParams(JTypeVar[] variables, List<JClass> bindings)
	{
		return this;
	}
}
