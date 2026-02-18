package org.jvnet.basicjaxb.xml.bind.adapter;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapt a {@link List} of XML {@code dateTime} values
 * for custom unmarshalling and marshaling.
 */
public class DateTimeListXmlAdapter
	extends XmlAdapter<String, List<OffsetDateTime>>
{
	@Override
	public List<OffsetDateTime> unmarshal(String values)
	{
		if ( values == null || values.trim().isEmpty() )
			return null;
		else
			return Arrays.stream(values.split("\\s+"))
				.map(OffsetDateTime::parse)
				.collect(Collectors.toList());
	}

	@Override
	public String marshal(List<OffsetDateTime> valueList)
	{
		if ( valueList == null || valueList.isEmpty() )
			return null;
		else
	        return valueList.stream()
	        	.map(OffsetDateTime::toString)
	        	.collect(Collectors.joining(" "));
	}
}
