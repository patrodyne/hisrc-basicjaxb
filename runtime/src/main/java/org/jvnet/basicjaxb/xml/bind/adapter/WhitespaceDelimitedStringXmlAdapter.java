package org.jvnet.basicjaxb.xml.bind.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class WhitespaceDelimitedStringXmlAdapter
	extends XmlAdapter<String, List<String>>
{
	@Override
	public List<String> unmarshal(String values)
	{
		if ( values == null || values.trim().isEmpty() )
			return new ArrayList<>();
		return Arrays.asList(values.trim().split("\\s+"));
	}

	@Override
	public String marshal(List<String> valueList)
	{
		if ( valueList == null || valueList.isEmpty() )
			return null;
		return String.join(" ", valueList);
	}
}
