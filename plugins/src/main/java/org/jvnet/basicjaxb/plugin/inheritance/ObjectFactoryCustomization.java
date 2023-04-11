package org.jvnet.basicjaxb.plugin.inheritance;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * This JAXB class is used to direct the {@link InheritancePlugin} to
 * add an extension or interfaces to an <code>ObjectFactory</code> in
 * a given package.
 * 
 * <pre>
 * &lt;inh:objectFactory packageName="org.example.my_model"&gt;
 *   &lt;inh:extends&gt;org.example.my_base.ObjectFactory&lt;/inh:extends&gt;
 *   &lt;inh:implements&gt;org.example.my_base.MyInterface1&lt;/inh:implements&gt;
 *   &lt;inh:implements&gt;org.example.my_base.MyInterface2&lt;/inh:implements&gt;
 * &lt;/inh:objectFactory&gt;
 * </pre>
 */
@XmlRootElement(namespace = "http://jvnet.org/basicjaxb/xjc/inheritance", name = "objectFactory")
@XmlType(propOrder = { "extendsClass", "implementsInterface" })
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class ObjectFactoryCustomization
{
	private String packageName;
	@XmlAttribute
	public String getPackageName()
	{
		return packageName;
	}
	public void setPackageName(String packageName)
	{
		this.packageName = packageName;
	}

	private ExtendsClass extendsClass;
	@XmlElement(namespace = "http://jvnet.org/basicjaxb/xjc/inheritance", name = "extends")
	public ExtendsClass getExtendsClass()
	{
		return extendsClass;
	}
	public void setExtendsClass(ExtendsClass extendsClass)
	{
		this.extendsClass = extendsClass;
	}

	private List<ImplementsInterface> implementsInterface = new ArrayList<ImplementsInterface>();
	@XmlElement(namespace = "http://jvnet.org/basicjaxb/xjc/inheritance", name = "implements")
	public List<ImplementsInterface> getImplementsInterface()
	{
		return implementsInterface;
	}
	public void setImplementsInterface(List<ImplementsInterface> implementsInterface)
	{
		this.implementsInterface = implementsInterface;
	}
}
