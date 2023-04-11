package org.jvnet.basicjaxb.plugin.inheritance;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlValue;
import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * This JAXB class is used to direct the {@link InheritancePlugin} to
 * add interfaces to any schema-derived class.
 * 
 * <pre>
 * &lt;inh:implements&gt;org.example.my_base.MyInterface1&lt;/inh:implements&gt;
 * &lt;inh:implements&gt;org.example.my_base.MyInterface2&lt;/inh:implements&gt;
 * </pre>
 */
@XmlRootElement(namespace = "http://jvnet.org/basicjaxb/xjc/inheritance", name = "implements")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class ImplementsInterface
{
	private String interfaceName;

	@XmlValue
	@XmlJavaTypeAdapter(value = CollapsedStringAdapter.class)
	public String getInterfaceName()
	{
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName)
	{
		this.interfaceName = interfaceName;
	}
}
