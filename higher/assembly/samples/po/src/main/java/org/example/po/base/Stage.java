
package org.example.po.base;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for stage.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <pre>{@code
 * <simpleType name="stage">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="Hold"/>
 *     <enumeration value="Open"/>
 *     <enumeration value="Active"/>
 *     <enumeration value="Closed"/>
 *     <enumeration value="Canceled"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "stage")
@XmlEnum
public enum Stage {

    @XmlEnumValue("Hold")
    HOLD("Hold"),
    @XmlEnumValue("Open")
    OPEN("Open"),
    @XmlEnumValue("Active")
    ACTIVE("Active"),
    @XmlEnumValue("Closed")
    CLOSED("Closed"),
    @XmlEnumValue("Canceled")
    CANCELED("Canceled");
    
    // Represents the XML enumeration value for each instance.
    private final String value;

    /**
     * Construct from XML enumeration value.
     * @param xmlEnumValue The XML enumeration value.
     */
    private Stage(String xmlEnumValue)
    {
        this.value = xmlEnumValue;
    }

    /**
     * Return the XML enumeration value for this instance.
     * @return The XML enumeration value for this instance.
     */
    public String value()
    {
        return value;
    }

    /**
     * Find (@link Stage} from XML enumeration value.
     * 
     * @param xmlEnumValue The XML enumeration value.
     * 
     * @return The matching (@link Stage} instance.
     */
    public static Stage fromValue(String xmlEnumValue)
    {
        for (Stage stage: Stage.values())
        {
            if (stage.value.equals(xmlEnumValue))
                return stage;
        }
        throw new IllegalArgumentException(xmlEnumValue);
    }
}
