package org.jvnet.basicjaxb.xml.bind.adapter;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapt the XML {@code 'xsd:gMonth'} type to {@link java.time.Month}.
 */
public class GMonthXmlAdapter extends XmlAdapter<String, Month>
{
    // Pattern: MM (Month) followed by optional Offset ([XXX])
    private static final DateTimeFormatter G_MONTH_PARSER =
    	DateTimeFormatter.ofPattern("--MM[XXX]");
    private static final DateTimeFormatter G_MONTH_FORMATTER =
    		    	DateTimeFormatter.ofPattern("--MM");

	@Override
	public Month unmarshal(String gMonth)
	{
		// 'xsd:gMonth' is the XML string, e.g., "--02Z"
		if ( gMonth == null || gMonth.isEmpty() )
			return null;
		// Parse with an optional timezone.
		return Month.from(G_MONTH_PARSER.parse(gMonth));
	}

	@Override
	public String marshal(Month month)
	{
		// 'year' is the Java Integer, e.g., "--02"
		if ( month == null )
			return null;
		// Format as an 'xsd:gMonth' string, e.g., "--02"
		LocalDateTime ldt = LocalDateTime.now().withMonth(month.getValue());
		return ldt.format(G_MONTH_FORMATTER);
	}
}
