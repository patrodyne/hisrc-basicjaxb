package org.jvnet.basicjaxb.xml.bind.adapter;

import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapt a {@link List} of XML {@code duration} values
 * for custom unmarshalling and marshaling.
 */
public class DurationListXmlAdapter
	extends XmlAdapter<String, List<Period>>
{
	@Override
	public List<Period> unmarshal(String values)
	{
		if ( values == null || values.trim().isEmpty() )
			return null;
		else
			return Arrays.stream(values.split("\\s+"))
				.map(Period::parse)
				.collect(Collectors.toList());
	}

	@Override
	public String marshal(List<Period> valueList)
	{
		if ( valueList == null || valueList.isEmpty() )
			return null;
		else
	        return valueList.stream()
	        	.map(Period::toString)
	        	.collect(Collectors.joining(" "));
	}
}
