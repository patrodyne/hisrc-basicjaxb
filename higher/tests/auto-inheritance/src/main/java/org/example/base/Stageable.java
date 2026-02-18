
package org.example.base;

import java.io.Serializable;
import java.time.OffsetDateTime;

import io.github.threetenjaxb.core.LenientOffsetDateTimeXmlAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>Java class for Stageable complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>{@code
 * <complexType name="Stageable">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <attribute name="Created" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       <attribute name="Updated" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       <attribute name="Stage" use="required" type="{http://example.org/base}stage" />
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Stageable")
public class Stageable implements Serializable
{
    private static final long serialVersionUID = 20230401L;

    @XmlAttribute(name = "Created")
    @XmlSchemaType(name = "dateTime")
    @XmlJavaTypeAdapter(LenientOffsetDateTimeXmlAdapter.class)
    protected OffsetDateTime created;
    /**
     * Gets the value of the created property.
     *
     * @return possible object is {@link OffsetDateTime }
     */
    public OffsetDateTime getCreated()
    {
        return created;
    }
    /**
     * Sets the value of the created property.
     *
     * @param value allowed object is {@link OffsetDateTime }
     */
    public void setCreated(OffsetDateTime value)
    {
        this.created = value;
    }

    @XmlAttribute(name = "Updated")
    @XmlSchemaType(name = "dateTime")
    @XmlJavaTypeAdapter(LenientOffsetDateTimeXmlAdapter.class)
    protected OffsetDateTime updated;
    /**
     * Gets the value of the updated property.
     *
     * @return possible object is {@link OffsetDateTime }
     */
    public OffsetDateTime getUpdated()
    {
        return updated;
    }
    /**
     * Sets the value of the updated property.
     *
     * @param value allowed object is {@link OffsetDateTime }
     */
    public void setUpdated(OffsetDateTime value)
    {
        this.updated = value;
    }

    @XmlAttribute(name = "Stage", required = true)
    protected Stage stage;
    /**
     * Gets the value of the stage property.
     *
     * @return possible object is {@link Stage }
     */
    public Stage getStage()
    {
        return stage;
    }
    /**
     * Sets the value of the stage property.
     *
     * @param value allowed object is {@link Stage }
     */
    public void setStage(Stage value)
    {
        this.stage = value;
    }
}
