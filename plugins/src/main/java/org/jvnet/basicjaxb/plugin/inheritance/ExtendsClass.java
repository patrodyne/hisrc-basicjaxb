package org.jvnet.basicjaxb.plugin.inheritance;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlValue;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * This JAXB class is used to direct the {@link InheritancePlugin} to
 * add an extension to any schema-derived class.
 * 
 * <pre>
 * &lt;inh:extends&gt;org.example.BaseClass&lt;/inh:extends&gt;
 * </pre>
 */
@XmlRootElement(namespace = "http://jvnet.org/basicjaxb/xjc/inheritance", name = "extends")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ExtendsClass
{
	private String className;

	@XmlValue
	@XmlJavaTypeAdapter(value = CollapsedStringAdapter.class)
	public String getClassName()
	{
		return className;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}
}
