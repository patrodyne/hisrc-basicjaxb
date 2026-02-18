package org.jvnet.basicjaxb.xml.bind.adapter;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapt a {@link List} of XML {@code gYearMonth} values
 * for custom unmarshalling and marshaling.
 */
public class GYearMonthListXmlAdapter
	extends XmlAdapter<String, List<YearMonth>>
{
	private final static GYearMonthXmlAdapter GYEARMONTH_XML_ADAPTER = new GYearMonthXmlAdapter();

	@Override
	public List<YearMonth> unmarshal(String values)
	{
		if ( values == null || values.trim().isEmpty() )
			return new ArrayList<>();
		return Arrays.stream(values.split("\\s+"))
			.map(GYEARMONTH_XML_ADAPTER::unmarshal)
			.collect(Collectors.toList());
	}

	@Override
	public String marshal(List<YearMonth> valueList)
	{
		if ( valueList == null || valueList.isEmpty() )
			return null;
        return valueList.stream()
        	.map(GYEARMONTH_XML_ADAPTER::marshal)
        	.collect(Collectors.joining(" "));
	}
}
