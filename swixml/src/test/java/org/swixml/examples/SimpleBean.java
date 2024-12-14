package org.swixml.examples;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

/**
 * An example of a simple Java Bean with properties comprised
 * of a field, accessor and mutator, per JavaBeans (1.01).
 * Additionally, it uses Jakarta Validation (JSR 303) to
 * provide field size information.
 * 
 * <p>See {@link SimpleBeanBeanInfo} to provide explicit
 * information about the methods, properties, events, and other
 * features of this bean.</p>
 * 
 * @author sorrentino
 */
public class SimpleBean
{
	@Size(min = 12, max = 254)
	private String name;
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	@Min(0)
	@Max(120)
	private int age;
	public int getAge() { return age; }
	public void setAge(int age) { this.age = age; }
	
	@Size(min = 20, max = 254)
	private String field3;
	public final String getField3() { return field3; }
	public final void setField3(String field3) { this.field3 = field3; }
	
	@Size(min = 20, max = 254)
	private String field4;
	public final String getField4() { return field4; }
	public final void setField4(String field4) { this.field4 = field4; }
	
	public SimpleBean(String name, int age)
	{
		setName(name);
		setAge(age);
		setField3("<value field3>");
		setField4("<value field4>");
	}

	@Override
	public String toString()
	{
		return new StringBuilder()
			.append("name=").append(getName())
			.append(" age=").append(getAge())
			.toString();
	}

}
