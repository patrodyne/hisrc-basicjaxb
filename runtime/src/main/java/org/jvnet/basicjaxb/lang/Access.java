package org.jvnet.basicjaxb.lang;

/**
 * An enumeration of property access types.
 */
public enum Access
{
    READ_ONLY,
    WRITE_ONLY,
    READ_WRITE;

    public String value()
	{
        return name();
    }

    public static Access fromValue(String v)
	{
        return valueOf(v);
    }
}
