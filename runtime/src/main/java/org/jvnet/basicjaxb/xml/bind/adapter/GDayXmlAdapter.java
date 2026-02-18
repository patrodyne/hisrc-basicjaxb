package org.jvnet.basicjaxb.xml.bind.adapter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapt the XML {@code 'xsd:gDay'} type to an integer day of month.
 */
public class GDayXmlAdapter extends XmlAdapter<String, Integer>
{
    // Pattern: "---" followed by 2-digit day, then optional timezone
    // 'X' handles ISO-8601 offsets like Z, +05, +0530, +05:30
    private static final DateTimeFormatter G_DAY_PARSER =
    	DateTimeFormatter.ofPattern("---dd[XXX]");
    private static final DateTimeFormatter G_DAY_FORMATTER =
    		    	DateTimeFormatter.ofPattern("---dd");

	@Override
	public Integer unmarshal(String gDay)
	{
		// 'xsd:gDay' is the XML string, e.g., "---15Z"
		if ( gDay == null || gDay.isEmpty() )
			return null;
		// Remove the "---" prefix and/or TZ suffix then
		// parse as an integer
		TemporalAccessor ta = G_DAY_PARSER.parse(gDay);
		return ta.get(ChronoField.DAY_OF_MONTH);
	}

	@Override
	public String marshal(Integer dom)
	{
		// 'dom' is the Java Integer, e.g., 15
		if ( dom == null )
			return null;
		// Format as an 'xsd:gDay' string, e.g., "---15"
		LocalDateTime ldt = LocalDateTime.now().withDayOfMonth(dom);
		return ldt.format(G_DAY_FORMATTER);
	}
}
