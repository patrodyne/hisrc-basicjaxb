package org.jvnet.basicjaxb.xml.bind.adapter;

import java.time.LocalDateTime;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapt the XML {@code 'xsd:gMonthDay'} type to {@link java.time.MonthDay}.
 */
public class GMonthDayXmlAdapter extends XmlAdapter<String, MonthDay>
{
    // Pattern: MM (Month) and dd (Day)
	// followed by optional Offset ([XXX])
    private static final DateTimeFormatter G_MONTH_DAY_PARSER =
    	DateTimeFormatter.ofPattern("--MM-dd[XXX]");
    private static final DateTimeFormatter G_MONTH_DAY_FORMATTER =
    		    	DateTimeFormatter.ofPattern("--MM-dd");

	@Override
	public MonthDay unmarshal(String gMonthDay)
	{
		// 'xsd:gMonthDay' is the XML string, e.g., "--02-28Z"
		if ( gMonthDay == null || gMonthDay.isEmpty() )
			return null;
		// Parse with an optional timezone.
		return MonthDay.parse(gMonthDay, G_MONTH_DAY_PARSER);
	}

	@Override
	public String marshal(MonthDay monthDay)
	{
		if ( monthDay == null )
			return null;
		// Format as an 'xsd:gMonthDay' string, e.g., "--02-28"
		LocalDateTime ldt = LocalDateTime.now()
			.withMonth(monthDay.getMonthValue())
			.withDayOfMonth(monthDay.getDayOfMonth());
		return ldt.format(G_MONTH_DAY_FORMATTER);
	}
}
