
package org.jvnet.basicjaxb.plugin.beaninfo.model;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * 
 * 
 * <p>Java class for accessType</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * <pre>{@code
 * <simpleType name="accessType">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="READ_ONLY"/>
 *     <enumeration value="WRITE_ONLY"/>
 *     <enumeration value="READ_WRITE"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "accessType")
@XmlEnum
public enum AccessType {

    READ_ONLY,
    WRITE_ONLY,
    READ_WRITE;

    public String value() {
        return name();
    }

    public static AccessType fromValue(String v) {
        return valueOf(v);
    }

}
