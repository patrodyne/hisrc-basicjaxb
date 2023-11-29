package org.jvnet.basicjaxb.plugin.wildcard;

import com.sun.codemodel.JClass;
import com.sun.tools.xjc.model.nav.NClass;
import com.sun.tools.xjc.outline.Aspect;
import com.sun.tools.xjc.outline.Outline;

/**
 * A special {@link NClass} that represents an unknown class (except its name.)
 */
public class LazyNClass implements NClass
{
	private String fullName;
	public String getFullName() { return fullName; }
	public void setFullName(String fullName) { this.fullName = fullName; }

	public LazyNClass(String fullName)
	{
		setFullName(fullName);
	}
	
	@Override
	public String fullName()
	{
		return getFullName();
	}

	@Override
	public JClass toType(Outline outline, Aspect aspect)
	{
		return new LazyJClass(outline.getCodeModel(), getFullName());
	}

	@Override
	public boolean isAbstract()
	{
		return false;
	}

	@Override
	public boolean isBoxedType()
	{
		return false;
	}
}
