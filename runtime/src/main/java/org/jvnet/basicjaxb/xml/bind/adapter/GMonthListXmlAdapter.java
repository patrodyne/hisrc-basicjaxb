package org.jvnet.basicjaxb.xml.bind.adapter;

import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapt a {@link List} of XML {@code gMonth} values
 * for custom unmarshalling and marshaling.
 */
public class GMonthListXmlAdapter
	extends XmlAdapter<String, List<Month>>
{
	private final static GMonthXmlAdapter GMONTH_XML_ADAPTER = new GMonthXmlAdapter();

	@Override
	public List<Month> unmarshal(String values)
	{
		if ( values == null || values.trim().isEmpty() )
			return new ArrayList<>();
		return Arrays.stream(values.split("\\s+"))
			.map(GMONTH_XML_ADAPTER::unmarshal)
			.collect(Collectors.toList());
	}

	@Override
	public String marshal(List<Month> valueList)
	{
		if ( valueList == null || valueList.isEmpty() )
			return null;
        return valueList.stream()
        	.map(GMONTH_XML_ADAPTER::marshal)
        	.collect(Collectors.joining(" "));
	}
}
