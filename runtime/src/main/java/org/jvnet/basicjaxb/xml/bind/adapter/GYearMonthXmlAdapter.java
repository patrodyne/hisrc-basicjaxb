package org.jvnet.basicjaxb.xml.bind.adapter;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapt the XML {@code 'xsd:gYearMonth'} type to {@link java.time.YearMonth}.
 */
public class GYearMonthXmlAdapter extends XmlAdapter<String, YearMonth>
{
    // Pattern: uuuu (Year) hyphen and MM (Month)
	// followed by optional Offset ([XXX])
    private static final DateTimeFormatter G_YEAR_MONTH_PARSER =
    	DateTimeFormatter.ofPattern("uuuu-MM[XXX]");
    private static final DateTimeFormatter G_YEAR_MONTH_FORMATTER =
    	DateTimeFormatter.ofPattern("uuuu-MM");

	@Override
	public YearMonth unmarshal(String gYearMonth)
	{
		// 'xsd:gYearMonth' is the XML string, e.g., "2025-02Z"
		if ( gYearMonth == null || gYearMonth.isEmpty() )
			return null;
		// Parse with an optional timezone.
		return YearMonth.parse(gYearMonth, G_YEAR_MONTH_PARSER);
	}

	@Override
	public String marshal(YearMonth yearMonth)
	{
		if ( yearMonth == null )
			return null;
		// Format as an 'xsd:gYearMonth' string, e.g., "2025-02"
		LocalDateTime ldt = LocalDateTime.now()
			.withYear(yearMonth.getYear())
			.withMonth(yearMonth.getMonthValue());
		return ldt.format(G_YEAR_MONTH_FORMATTER);
	}
}
