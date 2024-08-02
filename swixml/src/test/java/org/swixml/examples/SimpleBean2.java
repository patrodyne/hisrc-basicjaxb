package org.swixml.examples;

public class SimpleBean2
{
	private int field1;
	private boolean field2;
	private String field3;
	private String field4;
	private static int numOfInstance = 0;

	public SimpleBean2()
	{
		this.field1 = ++numOfInstance;
		field2 = false;
		field3 = String.format("<value%d field3>", numOfInstance);
		field4 = String.format("<value%d field4>", numOfInstance);
	}

	@Override
	public String toString()
	{
		return new StringBuilder().append("field1=").append(field1).append(" field2=").append(field2).append(" field3=")
			.append(field3).append(" field4=").append(field4).toString();
	}

	public int getField1()
	{
		return field1;
	}

	public void setField1(int value)
	{
		this.field1 = value;
	}

	public boolean isField2()
	{
		return field2;
	}

	public void setField2(boolean value)
	{
		this.field2 = value;
	}

	public final String getField3()
	{
		return field3;
	}

	public final void setField3(String field3)
	{
		this.field3 = field3;
	}

	public final String getField4()
	{
		return field4;
	}

	public final void setField4(String field4)
	{
		this.field4 = field4;
	}
}
