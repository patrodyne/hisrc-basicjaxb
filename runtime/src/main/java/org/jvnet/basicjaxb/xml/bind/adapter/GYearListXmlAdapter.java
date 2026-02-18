package org.jvnet.basicjaxb.xml.bind.adapter;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapt a {@link List} of XML {@code gYear} values
 * for custom unmarshalling and marshaling.
 */
public class GYearListXmlAdapter
	extends XmlAdapter<String, List<Year>>
{
	private final static GYearXmlAdapter GYEAR_XML_ADAPTER = new GYearXmlAdapter();

	@Override
	public List<Year> unmarshal(String values)
	{
		if ( values == null || values.trim().isEmpty() )
			return new ArrayList<>();
		return Arrays.stream(values.split("\\s+"))
			.map(GYEAR_XML_ADAPTER::unmarshal)
			.collect(Collectors.toList());
	}

	@Override
	public String marshal(List<Year> valueList)
	{
		if ( valueList == null || valueList.isEmpty() )
			return null;
        return valueList.stream()
        	.map(GYEAR_XML_ADAPTER::marshal)
        	.collect(Collectors.joining(" "));
	}
}
