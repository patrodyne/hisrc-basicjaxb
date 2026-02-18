package org.jvnet.basicjaxb.lang;

import static org.jvnet.basicjaxb.lang.StringUtils.isBlank;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;

import jakarta.xml.bind.DatatypeConverter;
import jakarta.xml.bind.annotation.adapters.HexBinaryAdapter;

public class ValueUtils
{
	public static final String REGEX_SPACES = "\\s+";
	public static final HexBinaryAdapter HEX_BINARY_ADAPTER = new HexBinaryAdapter();
    public static final DatatypeFactory DATATYPE_FACTORY;
    static
    {
        try
        {
            DATATYPE_FACTORY = DatatypeFactory.newInstance();
        }
        catch (DatatypeConfigurationException ex)
        {
            throw new RuntimeException("Unable to initialize DatatypeFactory", ex);
        }
    }

	// BigDecimal

	public static List<BigDecimal> toBigDecimalList(String valueList)
	{
		return toBigDecimalList(valueList, REGEX_SPACES);
	}

	public static List<BigDecimal> toBigDecimalList(String valueList, String regex)
	{
		List<BigDecimal> list = null;
		if ( !isBlank(valueList) )
		{
			String[] values = valueList.split(regex);
			list = new ArrayList<>(values.length);
			for ( String value : values )
				list.add(toBigDecimal(value));
		}
		return list;
	}

	public static BigDecimal toBigDecimal(String value)
	{
	    return new BigDecimal(value);
	}

	// BigInteger

	public static List<BigInteger> toBigIntegerList(String valueList)
	{
		return toBigIntegerList(valueList, REGEX_SPACES);
	}

	public static List<BigInteger> toBigIntegerList(String valueList, String regex)
	{
		List<BigInteger> list = null;
		if ( !isBlank(valueList) )
		{
			String[] values = valueList.split(regex);
			list = new ArrayList<>(values.length);
			for ( String value : values )
				list.add(toBigInteger(value));
		}
		return list;
	}

	public static BigInteger toBigInteger(String value)
	{
	    return new BigInteger(value);
	}

	// Boolean

	public static List<Boolean> toBooleanList(String valueList)
	{
		return toBooleanList(valueList, REGEX_SPACES);
	}

	public static List<Boolean> toBooleanList(String valueList, String regex)
	{
		List<Boolean> list = null;
		if ( !isBlank(valueList) )
		{
			String[] values = valueList.split(regex);
			list = new ArrayList<>(values.length);
			for ( String value : values )
				list.add(toBoolean(value));
		}
		return list;
	}

	public static Boolean toBoolean(String value)
	{
	    return Boolean.valueOf(value);
	}

	// Byte

	public static List<Byte> toByteList(String valueList)
	{
		return toByteList(valueList, REGEX_SPACES);
	}

	public static List<Byte> toByteList(String valueList, String regex)
	{
		List<Byte> list = null;
		if ( !isBlank(valueList) )
		{
			String[] values = valueList.split(regex);
			list = new ArrayList<>(values.length);
			for ( String value : values )
				list.add(toByte(value));
		}
		return list;
	}

	public static Byte toByte(String value)
	{
	    return Byte.valueOf(value);
	}

	public static byte[] parseBase64Binary(String value)
	{
	    return DatatypeConverter.parseBase64Binary(value);
	}

	public static byte[] parseHexBinary(String value)
	{
	    return HEX_BINARY_ADAPTER.unmarshal(value);
	}

	// Double

	public static List<Double> toDoubleList(String valueList)
	{
		return toDoubleList(valueList, REGEX_SPACES);
	}

	public static List<Double> toDoubleList(String valueList, String regex)
	{
		List<Double> list = null;
		if ( !isBlank(valueList) )
		{
			String[] values = valueList.split(regex);
			list = new ArrayList<>(values.length);
			for ( String value : values )
				list.add(toDouble(value));
		}
		return list;
	}

	public static Double toDouble(String value)
	{
		switch ( value )
		{
			case "INF": return Double.POSITIVE_INFINITY;
			case "-INF": return Double.NEGATIVE_INFINITY;
			case "NaN": return Double.NaN;
			default: return Double.valueOf(value);
		}
	}

	// Duration

	public static List<Duration> toDurationList(String valueList)
	{
		return toDurationList(valueList, REGEX_SPACES);
	}

	public static List<Duration> toDurationList(String valueList, String regex)
	{
		List<Duration> list = null;
		if ( !isBlank(valueList) )
		{
			String[] values = valueList.split(regex);
			list = new ArrayList<>(values.length);
			for ( String value : values )
				list.add(toDuration(value));
		}
		return list;
	}

	public static Duration toDuration(String value)
	{
	    return DATATYPE_FACTORY.newDuration(value);
	}

	// Float

	public static List<Float> toFloatList(String valueList)
	{
		return toFloatList(valueList, REGEX_SPACES);
	}

	public static List<Float> toFloatList(String valueList, String regex)
	{
		List<Float> list = null;
		if ( !isBlank(valueList) )
		{
			String[] values = valueList.split(regex);
			list = new ArrayList<>(values.length);
			for ( String value : values )
				list.add(toFloat(value));
		}
		return list;
	}

	public static Float toFloat(String value)
	{
		switch ( value )
		{
			case "INF": return Float.POSITIVE_INFINITY;
			case "-INF": return Float.NEGATIVE_INFINITY;
			case "NaN": return Float.NaN;
			default: return Float.valueOf(value);
		}
	}

	// Integer

	public static List<Integer> toIntegerList(String valueList)
	{
		return toIntegerList(valueList, REGEX_SPACES);
	}

	public static List<Integer> toIntegerList(String valueList, String regex)
	{
		List<Integer> list = null;
		if ( !isBlank(valueList) )
		{
			String[] values = valueList.split(regex);
			list = new ArrayList<>(values.length);
			for ( String value : values )
				list.add(toInteger(value));
		}
		return list;
	}

	public static Integer toInteger(String value)
	{
	    return Integer.valueOf(value);
	}

	// Long

	public static List<Long> toLongList(String valueList)
	{
		return toLongList(valueList, REGEX_SPACES);
	}

	public static List<Long> toLongList(String valueList, String regex)
	{
		List<Long> list = null;
		if ( !isBlank(valueList) )
		{
			String[] values = valueList.split(regex);
			list = new ArrayList<>(values.length);
			for ( String value : values )
				list.add(toLong(value));
		}
		return list;
	}

	public static Long toLong(String value)
	{
	    return Long.valueOf(value);
	}

	// Short

	public static List<Short> toShortList(String valueList)
	{
		return toShortList(valueList, REGEX_SPACES);
	}

	public static List<Short> toShortList(String valueList, String regex)
	{
		List<Short> list = null;
		if ( !isBlank(valueList) )
		{
			String[] values = valueList.split(regex);
			list = new ArrayList<>(values.length);
			for ( String value : values )
				list.add(toShort(value));
		}
		return list;
	}

	public static Short toShort(String value)
	{
	    return Short.valueOf(value);
	}

	// String

	public static List<String> toStringList(String valueList)
	{
		return toStringList(valueList, REGEX_SPACES);
	}

	public static List<String> toStringList(String valueList, String regex)
	{
		List<String> list = null;
		if ( !isBlank(valueList) )
		{
			String[] values = valueList.split(regex);
			list = new ArrayList<>(values.length);
			for ( String value : values )
				list.add(toString(value));
		}
		return list;
	}

	public static String toString(String value)
	{
	    return value;
	}

	// XMLGregorianCalendar

	public static List<XMLGregorianCalendar> toXMLGregorianCalendarList(String valueList)
	{
		return toXMLGregorianCalendarList(valueList, REGEX_SPACES);
	}

	public static List<XMLGregorianCalendar> toXMLGregorianCalendarList(String valueList, String regex)
	{
		List<XMLGregorianCalendar> list = null;
		if ( !isBlank(valueList) )
		{
			String[] values = valueList.split(regex);
			list = new ArrayList<>(values.length);
			for ( String value : values )
				list.add(toXMLGregorianCalendar(value));
		}
		return list;
	}

	public static XMLGregorianCalendar toXMLGregorianCalendar(String value)
	{
	    return DATATYPE_FACTORY.newXMLGregorianCalendar(value);
	}

	public static LocalDate toLocalDate(String value)
	{
	    return LocalDate.parse(value);
	}

	public static OffsetTime toOffsetTime(String value)
	{
	    return OffsetTime.parse(value);
	}

	public static OffsetDateTime toOffsetDateTime(String value)
	{
	    return OffsetDateTime.parse(value);
	}

	public static java.time.Duration toTimeDuration(String value)
	{
	    return java.time.Duration.parse(value);
	}

	public static Month toMonth(String value)
	{
	    return Month.valueOf(value);
	}

	public static MonthDay toMonthDay(String value)
	{
	    return MonthDay.parse(value);
	}

	public static Year toYear(String value)
	{
	    return Year.parse(value);
	}

	public static YearMonth toYearMonth(String value)
	{
	    return YearMonth.parse(value);
	}
}

