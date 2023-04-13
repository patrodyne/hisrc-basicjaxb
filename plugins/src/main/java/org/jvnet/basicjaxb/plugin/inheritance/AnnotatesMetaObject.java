package org.jvnet.basicjaxb.plugin.inheritance;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;

/**
 * <p>Java class for annotates element declaration.</p>
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <element name="annotates">
 *   <complexType>
 *     <complexContent>
 *       <restriction base="&#123;http://www.w3.org/2001/XMLSchema&#125;anyType">
 *         <sequence>
 *           <element name="element" maxOccurs="unbounded" minOccurs="0">
 *             <complexType>
 *               <simpleContent>
 *                 <extension base="<http://www.w3.org/2001/XMLSchema>string">
 *                   <attribute name="name" type="&#123;http://www.w3.org/2001/XMLSchema&#125;string" default="value" />
 *                   <attribute name="type" type="&#123;http://www.w3.org/2001/XMLSchema&#125;string" default="java.lang.String" />
 *                 </extension>
 *               </simpleContent>
 *             </complexType>
 *           </element>
 *           <element name="elements" maxOccurs="unbounded" minOccurs="0">
 *             <complexType>
 *               <simpleContent>
 *                 <extension base="<http://jvnet.org/basicjaxb/xjc/inheritance>stringList">
 *                   <attribute name="name" type="&#123;http://www.w3.org/2001/XMLSchema&#125;string" default="value" />
 *                   <attribute name="type" type="&#123;http://www.w3.org/2001/XMLSchema&#125;string" default="java.lang.String" />
 *                 </extension>
 *               </simpleContent>
 *             </complexType>
 *           </element>
 *         </sequence>
 *         <attribute name="annotation" type="&#123;http://www.w3.org/2001/XMLSchema&#125;string" />
 *       </restriction>
 *     </complexContent>
 *   </complexType>
 * </element>
 * }</pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "element" , "elements" })
@XmlRootElement(namespace = "http://jvnet.org/basicjaxb/xjc/inheritance", name = "annotates")
public class AnnotatesMetaObject
{
    @XmlElement(namespace = "http://jvnet.org/basicjaxb/xjc/inheritance", name = "element")
    protected List<AnnotatesMetaObject.Element> element;

    @XmlElement(namespace = "http://jvnet.org/basicjaxb/xjc/inheritance", name = "elements")
    protected List<AnnotatesMetaObject.Elements> elements;

    @XmlAttribute(name = "annotation")
    protected String annotation;

    /**
     * Gets the value of the element property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the elements property.
     * </p> 
     *
     * <p>
     * For example, to add a new item, do as follows:
     * </p> 
     * <pre>
     *    getElement().add(newItem);
     * </pre>
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AnnotatesMetaObject.Element }
     * </p> 
     * 
     * @return The value of the element property.
     */
    public List<AnnotatesMetaObject.Element> getElement()
    {
        if (element == null)
            element = new ArrayList<>();
        return this.element;
    }

    /**
     * Gets the value of the elements property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the elements property.
     * </p> 
     *
     * <p>
     * For example, to add a new item, do as follows:
     * </p> 
     * <pre>
     *    getElements().add(newItem);
     * </pre>
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AnnotatesMetaObject.Elements }
     * </p> 
     * 
     * @return The value of the elements property.
     */
    public List<AnnotatesMetaObject.Elements> getElements()
    {
        if (elements == null)
            elements = new ArrayList<>();
        return this.elements;
    }

    /**
     * Gets the value of the annotation property.
     * 
     * @return Possible object is {@link String }
     */
    public String getAnnotation()
    {
        return annotation;
    }
    /**
     * Sets the value of the annotation property.
     * 
     * @param value Allowed object is {@link String }
     */
    public void setAnnotation(String value)
    {
        this.annotation = value;
    }

    /**
     * <p>Java class for anonymous complex type.</p>
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.</p>
     * 
     * <pre>{@code
     * <complexType>
     *   <simpleContent>
     *     <extension base="<http://www.w3.org/2001/XMLSchema>string">
     *       <attribute name="name" type="&#123;http://www.w3.org/2001/XMLSchema&#125;string" default="value" />
     *       <attribute name="type" type="&#123;http://www.w3.org/2001/XMLSchema&#125;string" default="java.lang.String" />
     *     </extension>
     *   </simpleContent>
     * </complexType>
     * }</pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(namespace = "http://jvnet.org/basicjaxb/xjc/inheritance", name="element", propOrder = { "value" })
    public static class Element
    {
        @XmlValue
        protected String value;

        @XmlAttribute(name = "name")
        protected String name;

        @XmlAttribute(name = "type")
        protected String type;

        /**
         * Gets the value of the value property.
         * 
         * @return Possible object is {@link String }
         */
        public String getValue()
        {
            return value;
        }
        /**
         * Sets the value of the value property.
         * 
         * @param value Allowed object is {@link String }
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the name property.
         * 
         * @return Possible object is {@link String }
         */
        public String getName()
        {
            if (name == null)
                return "value";
            else
                return name;
        }
        /**
         * Sets the value of the name property.
         * 
         * @param value Allowed object is {@link String }
         */
        public void setName(String value)
        {
            this.name = value;
        }

        /**
         * Gets the value of the type property.
         * 
         * @return Possible object is {@link String }
         */
        public String getType()
        {
            if (type == null)
                return "java.lang.String";
            else
                return type;
        }
        /**
         * Sets the value of the type property.
         * 
         * @param value Allowed object is {@link String }
         */
        public void setType(String value)
        {
            this.type = value;
        }
    }
    
    /**
     * <p>Java class for anonymous complex type.</p>
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.</p>
     * 
     * <pre>{@code
     * <complexType>
     *   <simpleContent>
     *     <extension base="<http://jvnet.org/basicjaxb/xjc/inheritance>stringList">
     *       <attribute name="name" type="&#123;http://www.w3.org/2001/XMLSchema&#125;string" default="value" />
     *       <attribute name="type" type="&#123;http://www.w3.org/2001/XMLSchema&#125;string" default="java.lang.String" />
     *     </extension>
     *   </simpleContent>
     * </complexType>
     * }</pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(namespace = "http://jvnet.org/basicjaxb/xjc/inheritance", name="elements", propOrder = { "value" })
    public static class Elements
    {
        @XmlValue
        protected List<String> value;

        @XmlAttribute(name = "name")
        protected String name;

        @XmlAttribute(name = "type")
        protected String type;

        /**
         * <p>Gets the value of the value property.</p>
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a {@code set} method for the value property.
         * </p>
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * </p>
         * <pre>
         *    getValue().add(newItem);
         * </pre>
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * </p>
         * 
         * @return The value of the value property.
         */
        public List<String> getValue()
        {
            if (value == null)
                value = new ArrayList<>();
            return this.value;
        }

        /**
         * Gets the value of the name property.
         * 
         * @return Possible object is {@link String }
         */
        public String getName()
        {
            if (name == null)
                return "value";
            else
                return name;
        }
        /**
         * Sets the value of the name property.
         * 
         * @param value Allowed object is {@link String }
         */
        public void setName(String value)
        {
            this.name = value;
        }

        /**
         * Gets the value of the type property.
         * 
         * @return Possible object is {@link String }
         */
        public String getType()
        {
            if (type == null)
                return "java.lang.String";
            else
                return type;
        }
        /**
         * Sets the value of the type property.
         * 
         * @param value Allowed object is {@link String }
         */
        public void setType(String value)
        {
            this.type = value;
        }
    }
}
