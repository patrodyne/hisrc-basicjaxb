package org.jvnet.basicjaxb.xml.bind.adapter;

import java.time.OffsetTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapt a {@link List} of XML {@code time} values
 * for custom unmarshalling and marshaling.
 */
public class TimeListXmlAdapter
	extends XmlAdapter<String, List<OffsetTime>>
{
	@Override
	public List<OffsetTime> unmarshal(String values)
	{
		if ( values == null || values.trim().isEmpty() )
			return null;
		else
			return Arrays.stream(values.split("\\s+"))
				.map(OffsetTime::parse)
				.collect(Collectors.toList());
	}

	@Override
	public String marshal(List<OffsetTime> valueList)
	{
		if ( valueList == null || valueList.isEmpty() )
			return null;
		else
	        return valueList.stream()
	        	.map(OffsetTime::toString)
	        	.collect(Collectors.joining(" "));
	}
}
