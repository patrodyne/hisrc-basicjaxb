package org.jvnet.basicjaxb.xml.bind.adapter;

import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapt a {@link List} of XML {@code gMonthDay} values
 * for custom unmarshalling and marshaling.
 */
public class GMonthDayListXmlAdapter
	extends XmlAdapter<String, List<MonthDay>>
{
	private final static GMonthDayXmlAdapter GMONTHDAY_XML_ADAPTER = new GMonthDayXmlAdapter();

	@Override
	public List<MonthDay> unmarshal(String values)
	{
		if ( values == null || values.trim().isEmpty() )
			return new ArrayList<>();
		return Arrays.stream(values.split("\\s+"))
			.map(GMONTHDAY_XML_ADAPTER::unmarshal)
			.collect(Collectors.toList());
	}

	@Override
	public String marshal(List<MonthDay> valueList)
	{
		if ( valueList == null || valueList.isEmpty() )
			return null;
        return valueList.stream()
        	.map(GMONTHDAY_XML_ADAPTER::marshal)
        	.collect(Collectors.joining(" "));
	}
}
