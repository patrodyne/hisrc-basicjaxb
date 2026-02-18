package org.jvnet.basicjaxb.xml.bind.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapt a {@link List} of XML {@code gDay} values
 * for custom unmarshalling and marshaling.
 */
public class GDayListXmlAdapter
	extends XmlAdapter<String, List<Integer>>
{
	private final static GDayXmlAdapter GDAY_XML_ADAPTER = new GDayXmlAdapter();

	@Override
	public List<Integer> unmarshal(String values)
	{
		if ( values == null || values.trim().isEmpty() )
			return new ArrayList<>();
		return Arrays.stream(values.split("\\s+"))
			.map(GDAY_XML_ADAPTER::unmarshal)
			.collect(Collectors.toList());
	}

	@Override
	public String marshal(List<Integer> valueList)
	{
		if ( valueList == null || valueList.isEmpty() )
			return null;
        return valueList.stream()
        	.map(GDAY_XML_ADAPTER::marshal)
        	.collect(Collectors.joining(" "));
	}
}
