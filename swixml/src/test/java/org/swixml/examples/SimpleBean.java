package org.swixml.examples;

import jakarta.validation.constraints.Size;

/**
 *
 * @author sorrentino
 */
public class SimpleBean
{
	@Size(max = 12)
	private String name;
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	@Size(max = 5)
	private int age;
	public int getAge() { return age; }
	public void setAge(int age) { this.age = age; }
	
	@Size(max = 20)
	private String field3;
	public final String getField3() { return field3; }
	public final void setField3(String field3) { this.field3 = field3; }
	
	@Size(max = 20)
	private String field4;
	public final String getField4() { return field4; }
	public final void setField4(String field4) { this.field4 = field4; }
	
	public SimpleBean(String name, int age)
	{
		setName(name);
		setAge(age);
		setField3("<value field3>");
		setField4("<value field4>");
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString()
	{
		return new StringBuilder()
			.append("name=").append(name)
			.append(" age=").append(age)
			.toString();
	}

}
