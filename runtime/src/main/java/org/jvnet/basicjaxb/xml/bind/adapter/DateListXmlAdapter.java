package org.jvnet.basicjaxb.xml.bind.adapter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapt a {@link List} of XML {@code date} values
 * for custom unmarshalling and marshaling.
 */
public class DateListXmlAdapter
	extends XmlAdapter<String, List<LocalDate>>
{
	@Override
	public List<LocalDate> unmarshal(String values)
	{
		if ( values == null || values.trim().isEmpty() )
			return null;
		else
			return Arrays.stream(values.split("\\s+"))
				.map(LocalDate::parse)
				.collect(Collectors.toList());
	}

	@Override
	public String marshal(List<LocalDate> valueList)
	{
		if ( valueList == null || valueList.isEmpty() )
			return null;
		else
	        return valueList.stream()
	        	.map(LocalDate::toString)
	        	.collect(Collectors.joining(" "));
	}
}
