
package org.jvnet.basicjaxb.plugin.beaninfo.model;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * 
 * 
 * <p>Java class for alignmentType</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * <pre>{@code
 * <simpleType name="alignmentType">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="LEFT"/>
 *     <enumeration value="RIGHT"/>
 *     <enumeration value="CENTER"/>
 *     <enumeration value="LEADING"/>
 *     <enumeration value="TRAILING"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "alignmentType")
@XmlEnum
public enum AlignmentType {

    LEFT,
    RIGHT,
    CENTER,
    LEADING,
    TRAILING;

    public String value() {
        return name();
    }

    public static AlignmentType fromValue(String v) {
        return valueOf(v);
    }

}
