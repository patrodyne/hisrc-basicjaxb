package org.jvnet.basicjaxb.lang;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.xml.sax.Locator;

public class StringUtils
{
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	public static final String EMPTY = "";
	public static final String[] EMPTY_STRING_ARRAY = new String[0];
	public static final String NULL = "null";
	public static final String UNKNOWN = "unknown";
	public static final int DEFAULT_MAX_ID_SIZE = 20;

	/**
	 * <p>Convert a value to its string representation or "null".</p>
	 * 
	 * <p><b>NOTE:</b> Stringification of collections, etc. may be time consuming!</p>
	 * 
	 * @param value The value to be stringified.
	 * 
	 * @return The stringification.
	 */
	public static String valueToString(Object value)
	{
		return (value != null) ? value.toString() : NULL;
	}
	
	public static boolean isEmpty(final CharSequence cs)
	{
		return cs == null || cs.length() == 0;
	}
	
    public static boolean isBlank(final CharSequence cs) {
        final int strLen = length(cs);
        if (strLen == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }
	
    public static int length(CharSequence cs)
	{
		return cs == null ? 0 : cs.length();
	}

	public static String[] split(String str, char separatorChar)
	{
		return splitWorker(str, separatorChar, false);
	}

	@SuppressWarnings("unchecked")
	private static String[] splitWorker(String str, char separatorChar, boolean preserveAllTokens)
	{
		// Performance tuned for 2.0 (JDK1.4)
		if (str == null)
		{
			return null;
		}
		int len = str.length();
		if (len == 0)
		{
			return EMPTY_STRING_ARRAY;
		}
		@SuppressWarnings("rawtypes")
		List list = new ArrayList();
		int i = 0, start = 0;
		boolean match = false;
		boolean lastMatch = false;
		while (i < len)
		{
			if (str.charAt(i) == separatorChar)
			{
				if (match || preserveAllTokens)
				{
					list.add(str.substring(start, i));
					match = false;
					lastMatch = true;
				}
				start = ++i;
				continue;
			}
			lastMatch = false;
			match = true;
			i++;
		}
		if (match || (preserveAllTokens && lastMatch))
		{
			list.add(str.substring(start, i));
		}
		return (String[]) list.toArray(new String[list.size()]);
	}

	public static String join(@SuppressWarnings("rawtypes") Iterator iterator, String separator)
	{
		// handle null, zero and one elements before building a buffer
		if (iterator == null)
		{
			return null;
		}
		if (!iterator.hasNext())
		{
			return EMPTY;
		}
		Object first = iterator.next();
		if (!iterator.hasNext())
		{
			return first == null ? "" : first.toString();
		}
		// two or more elements
		StringBuilder buf = new StringBuilder(256); // Java default is 16,
		// probably too small
		if (first != null)
		{
			buf.append(first);
		}
		while (iterator.hasNext())
		{
			buf.append(separator);
			Object obj = iterator.next();
			if (obj != null)
			{
				buf.append(obj);
			}
		}
		return buf.toString();
	}
	
	public static String clipId(String id, int maxIdSize)
	{
		String clipId = "";
		if ( id != null )
		{
			if ( id.length() <= maxIdSize )
				clipId = id;
			else
			{
				if ( maxIdSize >= 3)
					clipId = "..." + id.substring(id.length()-maxIdSize+3);
				else
					clipId = id.substring(id.length()-maxIdSize);
			}
		}
		return clipId;
	}
	
	public static String toLocation(Locator locator)
	{
		return toLocation(locator, DEFAULT_MAX_ID_SIZE);
	}
	
	public static String toLocation(Locator locator, int maxIdSize)
	{
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if ( locator != null )
        {
            final String pub = clipId(locator.getPublicId(), maxIdSize);
            final String sys = clipId(locator.getSystemId(), maxIdSize);
            final int row = locator.getLineNumber();
            final int col = locator.getColumnNumber();
            
            sb.append(isEmpty(pub) ? "" : " " + pub);
            sb.append(isEmpty(sys) ? "" : " " + sys);
            if ( row > 0 )
            {
                sb.append("{" + row);
                sb.append((col > 0) ? "," + col : "");
                sb.append("}");
            }
        }
        else
        	sb.append(" " + UNKNOWN);
        sb.append(" ]");
        return sb.toString();
	}
	
	public static String trim(String text, String trim)
	{
		String result = text;
		if ( (text != null) && (trim != null) )
		{
			result = text.trim();
			for ( char ch : trim.toCharArray() )
			{
				if ( result.charAt(0) == ch )
					result = result.substring(1);
				int lastIndex = result.lastIndexOf(ch);
				if ( lastIndex >= 0 )
				{
					if ( result.charAt(lastIndex) == ch )
						result = result.substring(0, lastIndex);					
				}
			}
		}
		return result;
	}
}
