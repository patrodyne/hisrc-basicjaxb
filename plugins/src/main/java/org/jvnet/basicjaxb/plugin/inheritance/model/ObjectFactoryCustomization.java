package org.jvnet.basicjaxb.plugin.inheritance.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>This JAXB class is used to direct the InheritancePlugin to
 * add an extension or interfaces to an <code>ObjectFactory</code> in
 * a given package.</p>
 * 
 * <pre>{@code
 * <element name="objectFactory">
 *   <complexType>
 *     <complexContent>
 *       <restriction base="&#123;http://www.w3.org/2001/XMLSchema&#125;anyType">
 *         <sequence>
 *           <element ref="&#123;http://jvnet.org/basicjaxb/xjc/inheritance&#125;annotates" maxOccurs="unbounded" minOccurs="0"/>
 *           <element ref="&#123;http://jvnet.org/basicjaxb/xjc/inheritance&#125;extends" minOccurs="0"/>
 *           <element ref="&#123;http://jvnet.org/basicjaxb/xjc/inheritance&#125;implements" maxOccurs="unbounded" minOccurs="0"/>
 *         </sequence>
 *         <attribute name="packageName" type="&#123;http://www.w3.org/2001/XMLSchema&#125;string" />
 *       </restriction>
 *     </complexContent>
 *   </complexType>
 * </element>
 * }</pre>
 * 
 * <pre>
 * &lt;inh:objectFactory packageName="org.example.my_model"&gt;
 *
 *	 &lt;inh:annotates annotation="java.lang.SuppressWarnings"&gt;
 *		&lt;inh:elements &gt;rawtypes unchecked&lt;/inh:elements&gt;
 *	 &lt;/inh:annotates&gt;
 * 
 *	 &lt;inh:annotates annotation="java.lang.Deprecated"&gt;
 *		&lt;inh:element name="since" type="java.lang.String"&gt;8&lt;/inh:element&gt;
 *		&lt;inh:element name="forRemoval" type="java.lang.Boolean"&gt;true&lt;/inh:element&gt;
 *	 &lt;/inh:annotates&gt;
 *	 
 *	 &lt;inh:annotates annotation="jakarta.xml.bind.annotation.XmlSeeAlso"&gt;
 *		&lt;inh:element type="java.lang.Class"&gt;javax.xml.datatype.XMLGregorianCalendar&lt;/inh:element&gt;
 *	 &lt;/inh:annotates&gt;
 *	 
 *	 &lt;inh:annotates annotation="jakarta.xml.bind.annotation.XmlAccessorType"&gt;
 *		&lt;inh:element type="java.lang.Enum"&gt;jakarta.xml.bind.annotation.XmlAccessType.FIELD&lt;/inh:element&gt;
 *	 &lt;/inh:annotates&gt;
 *	 
 *	 &lt;inh:extends&gt;org.example.my_base.ObjectFactory&lt;/inh:extends&gt;
 *	 
 *	 &lt;inh:implements&gt;org.example.my_base.MyInterface1&lt;/inh:implements&gt;
 *	 
 *	 &lt;inh:implements&gt;org.example.my_base.MyInterface2&lt;/inh:implements&gt;
 *	 
 * &lt;/inh:objectFactory&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "annotates", "_extends", "_implements" })
@XmlRootElement(name = "objectFactory")
public class ObjectFactoryCustomization {

    protected List<AnnotatesMetaObject> annotates;
    @XmlElement(name = "extends")
    protected ExtendsClass _extends;
    @XmlElement(name = "implements")
    protected List<ImplementsInterface> _implements;
    @XmlAttribute(name = "packageName")
    protected String packageName;

    /**
     * Gets the value of the annotates property.
     * 
     * <p>
     * This BOUND method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the annotates property.
     * </p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     *    getAnnotates().add(newItem);
     * </pre>
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AnnotatesMetaObject }
     * </p>
     * 
     * @return The value of the annotates property.
     */
    public List<AnnotatesMetaObject> getAnnotates()
    {
        if (annotates == null)
            annotates = new ArrayList<>();
        
        return this.annotates;
    }

    /**
     * Gets the value of the extends property.
     * 
     * @return Possible object is {@link ExtendsClass }
     *     
     */
    public ExtendsClass getExtends()
    {
        return _extends;
    }
    /**
     * Sets the value of the extends property.
     * 
     * @param value Allowed object is {@link ExtendsClass }
     */
    public void setExtends(ExtendsClass value)
    {
        this._extends = value;
    }

    /**
     * Gets the value of the implements property.
     * 
     * <p>
     * This BOUND method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the implements property.
     * </p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     *    getImplements().add(newItem);
     * </pre>
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ImplementsInterface }
     * </p>
     * 
     * @return The value of the implements property.
     */
    public List<ImplementsInterface> getImplements()
    {
        if (_implements == null)
            _implements = new ArrayList<>();
        
        return this._implements;
    }

    /**
     * Gets the value of the packageName property.
     * 
     * @return Possible object is {@link String }
     *     
     */
    public String getPackageName()
    {
        return packageName;
    }
    /**
     * Sets the value of the packageName property.
     * 
     * @param value Allowed object is {@link String }
     */
    public void setPackageName(String value)
    {
        this.packageName = value;
    }

}
