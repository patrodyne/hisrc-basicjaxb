package org.jvnet.basicjaxb.xjc.model.concrete.tests.ahpla;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KnownReferencedType", propOrder = { "str" })
public class KnownReferencedType implements Serializable
{
	private static final long serialVersionUID = 20221101L;
	protected String str;

	public String getStr()
	{
		return str;
	}

	public void setStr(String value)
	{
		this.str = value;
	}
}
