package org.swixml.tests;

/**
 * A POJO for unit tests.
 */
public class User
{
	private String name = null;
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
    public static String sayHelloWorld()
    {
        return sayHello("World");
    }

    public static String sayHello(String argument)
    {
        return "Hello, " + argument;
    }
    
    public String shout(String name)
    {
    	setName(name);
    	return getName().toUpperCase();
    }
}
