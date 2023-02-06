package org.jvnet.basicjaxb.plugin;

/**
 * A dummy application.
 */
public class Dummy
{
	/**
	 * List arguments.
	 * 
	 * @param args The CLI arguments.
	 */
	public static void main(String[] args)
	{
		println("Dummy arguments:");
		if ( args.length > 0 )
		{
			for ( int index = 0; index < args.length; ++index )
				println(index+": "+args[index]);
		}
		else
			println("none");
	}

	private static void println(String str)
	{
		System.out.println(str);
	}
}
