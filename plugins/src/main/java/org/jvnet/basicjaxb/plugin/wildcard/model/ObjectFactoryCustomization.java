
package org.jvnet.basicjaxb.plugin.wildcard.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for objectFactory element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <element name="objectFactory">
 *   <complexType>
 *     <complexContent>
 *       <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         <sequence>
 *           <element ref="{urn:jvnet.org:basicjaxb:xjc:wildcard}handler" minOccurs="0"/>
 *         </sequence>
 *         <attribute name="packageName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       </restriction>
 *     </complexContent>
 *   </complexType>
 * </element>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "handler"
})
@XmlRootElement(name = "objectFactory")
public class ObjectFactoryCustomization {

    protected HandlerClass handler;
    @XmlAttribute(name = "packageName")
    protected String packageName;

    /**
     * Gets the value of the handler property.
     * 
     * @return
     *     possible object is
     *     {@link HandlerClass }
     *     
     */
    public HandlerClass getHandler() {
        return handler;
    }

    /**
     * Sets the value of the handler property.
     * 
     * @param value
     *     allowed object is
     *     {@link HandlerClass }
     *     
     */
    public void setHandler(HandlerClass value) {
        this.handler = value;
    }

    /**
     * Gets the value of the packageName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Sets the value of the packageName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackageName(String value) {
        this.packageName = value;
    }

}
