package org.jdesktop.beansbinding;

import java.math.BigInteger;
import java.math.BigDecimal;

/**
 * {@code Converter} is responsible for converting a value from one type to
 * another.
 * <p>
 * The conversion methods can throw {@code RuntimeExceptions} in response to a
 * problem in conversion. For example, a {@code String} to {@code Integer}
 * converter might throw a {@code NumberFormatException} if the {@code String}
 * can't be parsed properly into an {@code Integer}.
 *
 * @param <S> the {@code Converter's} source type
 * @param <T> the {@code Converter's} target type
 * 
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 *
 * @author Shannon Hickey
 * @author Jan Stola
 * @author Scott Violet
 */
public abstract class Converter<S, T>
{
	/**
	 * Converts a value from the source type to the target type. Can throw a
	 * {@code RuntimeException} to indicate a problem with the conversion.
	 *
	 * @param value the source value to convert
	 * @return the value, converted to the target type
	 */
	public abstract T convertForward(S value);

	/**
	 * Converts a value from the target type to the source type. Can throw a
	 * {@code RuntimeException} to indicate a problem with the conversion.
	 *
	 * @param value the target value to convert
	 * @return the value, converted to the source type
	 */
	public abstract S convertReverse(T value);

	static final Converter<Byte, String> BYTE_TO_STRING_CONVERTER = new Converter<>()
	{
		@Override
		public String convertForward(Byte value)
		{
			return Byte.toString(value);
		}

		public Byte convertReverse(String value)
		{
			return Byte.parseByte(value);
		}
	};
	static final Converter<Short, String> SHORT_TO_STRING_CONVERTER = new Converter<>()
	{
		public String convertForward(Short value)
		{
			return Short.toString(value);
		}

		public Short convertReverse(String value)
		{
			return Short.parseShort(value);
		}
	};
	static final Converter<Integer, String> INT_TO_STRING_CONVERTER = new Converter<>()
	{
		@Override
		public String convertForward(Integer value)
		{
			return Integer.toString(value);
		}

		@Override
		public Integer convertReverse(String value)
		{
			return Integer.parseInt(value);
		}
	};
	static final Converter<Long, String> LONG_TO_STRING_CONVERTER = new Converter<>()
	{
		public String convertForward(Long value)
		{
			return Long.toString(value);
		}

		public Long convertReverse(String value)
		{
			return Long.parseLong(value);
		}
	};
	static final Converter<Float, String> FLOAT_TO_STRING_CONVERTER = new Converter<>()
	{
		public String convertForward(Float value)
		{
			return Float.toString(value);
		}

		public Float convertReverse(String value)
		{
			return Float.parseFloat(value);
		}
	};
	static final Converter<Double, String> DOUBLE_TO_STRING_CONVERTER = new Converter<>()
	{
		public String convertForward(Double value)
		{
			return Double.toString(value);
		}

		public Double convertReverse(String value)
		{
			return Double.parseDouble(value);
		}
	};
	static final Converter<Character, String> CHAR_TO_STRING_CONVERTER = new Converter<>()
	{
		public String convertForward(Character value)
		{
			return value.toString();
		}

		public Character convertReverse(String value)
		{
			if ( value.length() != 1 )
			{
				throw new IllegalArgumentException("String doesn't represent a char");
			}
			return value.charAt(0);
		}
	};
	static final Converter<Boolean, String> BOOLEAN_TO_STRING_CONVERTER = new Converter<>()
	{
		public String convertForward(Boolean value)
		{
			return Boolean.toString(value);
		}

		public Boolean convertReverse(String value)
		{
			return Boolean.parseBoolean(value);
		}
	};
	static final Converter<Integer, Boolean> INT_TO_BOOLEAN_CONVERTER = new Converter<>()
	{
		public Boolean convertForward(Integer value)
		{
			if ( value.intValue() == 0 )
			{
				return Boolean.FALSE;
			}
			return Boolean.TRUE;
		}

		public Integer convertReverse(Boolean value)
		{
			if ( value.booleanValue() )
			{
				return 1;
			}
			return 0;
		}
	};
	static final Converter<BigInteger, String> BIGINTEGER_TO_STRING_CONVERTER = new Converter<>()
	{
		public String convertForward(BigInteger value)
		{
			return value.toString();
		}

		public BigInteger convertReverse(String value)
		{
			return new BigInteger(value);
		}
	};
	static final Converter<BigDecimal, String> BIGDECIMAL_TO_STRING_CONVERTER = new Converter<>()
	{
		public String convertForward(BigDecimal value)
		{
			return value.toString();
		}

		public BigDecimal convertReverse(String value)
		{
			return new BigDecimal(value);
		}
	};

	static final Object defaultConvert(Object source, Class<?> targetType)
	{
		Class<? extends Object> sourceType = source.getClass();
		if ( sourceType == targetType )
		{
			return source;
		}
		if ( targetType == String.class )
		{
			if ( sourceType == Byte.class )
			{
				return BYTE_TO_STRING_CONVERTER.convertForward((Byte) source);
			}
			else if ( sourceType == Short.class )
			{
				return SHORT_TO_STRING_CONVERTER.convertForward((Short) source);
			}
			else if ( sourceType == Integer.class )
			{
				return INT_TO_STRING_CONVERTER.convertForward((Integer) source);
			}
			else if ( sourceType == Long.class )
			{
				return LONG_TO_STRING_CONVERTER.convertForward((Long) source);
			}
			else if ( sourceType == Float.class )
			{
				return FLOAT_TO_STRING_CONVERTER.convertForward((Float) source);
			}
			else if ( sourceType == Double.class )
			{
				return DOUBLE_TO_STRING_CONVERTER.convertForward((Double) source);
			}
			else if ( sourceType == Boolean.class )
			{
				return BOOLEAN_TO_STRING_CONVERTER.convertForward((Boolean) source);
			}
			else if ( sourceType == Character.class )
			{
				return CHAR_TO_STRING_CONVERTER.convertForward((Character) source);
			}
			else if ( sourceType == BigInteger.class )
			{
				return BIGINTEGER_TO_STRING_CONVERTER.convertForward((BigInteger) source);
			}
			else if ( sourceType == BigDecimal.class )
			{
				return BIGDECIMAL_TO_STRING_CONVERTER.convertForward((BigDecimal) source);
			}
		}
		else if ( sourceType == String.class )
		{
			if ( targetType == Byte.class )
			{
				return BYTE_TO_STRING_CONVERTER.convertReverse((String) source);
			}
			else if ( targetType == Short.class )
			{
				return SHORT_TO_STRING_CONVERTER.convertReverse((String) source);
			}
			else if ( targetType == Integer.class )
			{
				return INT_TO_STRING_CONVERTER.convertReverse((String) source);
			}
			else if ( targetType == Long.class )
			{
				return LONG_TO_STRING_CONVERTER.convertReverse((String) source);
			}
			else if ( targetType == Float.class )
			{
				return FLOAT_TO_STRING_CONVERTER.convertReverse((String) source);
			}
			else if ( targetType == Double.class )
			{
				return DOUBLE_TO_STRING_CONVERTER.convertReverse((String) source);
			}
			else if ( targetType == Boolean.class )
			{
				return BOOLEAN_TO_STRING_CONVERTER.convertReverse((String) source);
			}
			else if ( targetType == Character.class )
			{
				return CHAR_TO_STRING_CONVERTER.convertReverse((String) source);
			}
			else if ( targetType == BigInteger.class )
			{
				return BIGINTEGER_TO_STRING_CONVERTER.convertReverse((String) source);
			}
			else if ( targetType == BigDecimal.class )
			{
				return BIGDECIMAL_TO_STRING_CONVERTER.convertReverse((String) source);
			}
		}
		else if ( sourceType == Integer.class && targetType == Boolean.class )
		{
			return INT_TO_BOOLEAN_CONVERTER.convertForward((Integer) source);
		}
		else if ( sourceType == Boolean.class && targetType == Integer.class )
		{
			return INT_TO_BOOLEAN_CONVERTER.convertReverse((Boolean) source);
		}
		return source;
	}
}
