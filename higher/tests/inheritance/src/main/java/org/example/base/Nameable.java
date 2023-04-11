package org.example.base;

/**
 * An interface to implement name accessors.
 */
public interface Nameable
{
	/**
	 * Access the first name.
	 * @return The first name.
	 */
	public String getFirstName();
	
	/**
	 * Access the last name.
	 * @return The last name.
	 */
	public String getLastName();
	
	/**
	 * Access the full name.
	 * @return The full name.
	 */
	default public String getFullName()
	{
        return getFirstName() + " " + getLastName();
    }
}
