package org.jdesktop.beansbinding;

import java.util.StringTokenizer;
import java.util.ArrayList;

/**
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 * 
 * @author Shannon Hickey
 * @author Scott Violet
 */
abstract class PropertyPath
{
	private PropertyPath()
	{
	}

	public abstract int length();

	// throws ArrayIndexOutBoundsException if not valid
	public abstract String get(int index);

	public String getLast()
	{
		return get(length() - 1);
	}

	@Override
	public abstract String toString();

	public static PropertyPath createPropertyPath(String path)
	{
		if ( path == null )
		{
			throw new IllegalArgumentException("path must be non-null");
		}
		StringTokenizer tokenizer = new StringTokenizer(path, ".");
		ArrayList<String> list = new ArrayList<String>();
		while (tokenizer.hasMoreTokens())
		{
			list.add(tokenizer.nextToken());
		}
		int size = list.size();
		if ( size == 0 )
		{
			throw new IllegalArgumentException("path must be non-empty");
		}
		else if ( list.size() == 1 )
		{
			return new SinglePropertyPath(list.get(0));
		}
		else
		{
			String[] multi = new String[list.size()];
			return new MultiPropertyPath(list.toArray(multi));
		}
	}

	@Override
	public boolean equals(Object o)
	{
		if ( o == this )
		{
			return true;
		}
		if ( o instanceof PropertyPath )
		{
			PropertyPath oPath = (PropertyPath) o;
			int length = length();
			if ( length != oPath.length() )
			{
				return false;
			}
			for ( int i = 0; i < length; i++ )
			{
				if ( !get(i).equals(oPath.get(i)) )
				{
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		int result = 17;
		int length = length();
		for ( int i = 0; i < length; i++ )
		{
			result = 37 * result + get(i).hashCode();
		}
		return result;
	}

	static final class MultiPropertyPath
		extends
		PropertyPath
	{
		private final String[] path;

		public MultiPropertyPath(String[] path)
		{
			this.path = path;
			for ( int i = 0; i < path.length; i++ )
			{
				path[i] = path[i].intern();
			}
			assert (path.length > 0);
		}

		@Override
		public int length()
		{
			return path.length;
		}

		@Override
		public String get(int index)
		{
			return path[index];
		}

		@Override
		public String toString()
		{
			StringBuilder builder = new StringBuilder();
			builder.append(path[0]);
			for ( int i = 1; i < path.length; i++ )
			{
				builder.append('.');
				builder.append(path[i]);
			}
			return builder.toString();
		}
	}

	static final class SinglePropertyPath
		extends
		PropertyPath
	{
		private final String path;

		public SinglePropertyPath(String path)
		{
			this.path = path.intern();
		}

		@Override
		public int length()
		{
			return 1;
		}

		@Override
		public String get(int index)
		{
			if ( index == 0 )
			{
				return path;
			}
			throw new ArrayIndexOutOfBoundsException();
		}

		@Override
		public String getLast()
		{
			return path;
		}

		@Override
		public String toString()
		{
			return path;
		}
	}
}
