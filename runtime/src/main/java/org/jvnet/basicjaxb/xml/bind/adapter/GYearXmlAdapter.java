package org.jvnet.basicjaxb.xml.bind.adapter;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapt the XML {@code 'xsd:gYear'} type to {@link java.time.Year}.
 */
public class GYearXmlAdapter extends XmlAdapter<String, Year>
{
    // Pattern: uuuu (Year) followed by optional Offset ([XXX])
    private static final DateTimeFormatter G_YEAR_PARSER =
    	DateTimeFormatter.ofPattern("uuuu[XXX]");
    private static final DateTimeFormatter G_YEAR_FORMATTER =
    		    	DateTimeFormatter.ofPattern("uuuu");

	@Override
	public Year unmarshal(String gYear)
	{
		// 'xsd:gYear' is the XML string, e.g., "2025"
		if ( gYear == null || gYear.isEmpty() )
			return null;
		// Parse with an optional timezone.
		return Year.parse(gYear, G_YEAR_PARSER);
	}

	@Override
	public String marshal(Year year)
	{
		// 'year' is the Java Integer, e.g., "2025"
		if ( year == null )
			return null;
		// Format as an 'xsd:gYear' string, e.g., "2025"
		LocalDateTime ldt = LocalDateTime.now().withYear(year.getValue());
		return ldt.format(G_YEAR_FORMATTER);
	}
}
