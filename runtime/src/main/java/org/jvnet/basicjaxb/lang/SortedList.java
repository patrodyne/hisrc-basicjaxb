package org.jvnet.basicjaxb.lang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * A {@link List} implementation that maintains natural ordering.
 *
 * <p>Extends {@link ArrayList} to meet JAXB's requirement for
 * {@code collectionType}.</p>
 *
 * @param <E> Generic list element that implements {@link Comparable}.
 */
public class SortedList<E extends Comparable<? super E>>
	extends ArrayList<E>
{
	/**
	 * Constructs an empty list with an initial capacity of ten.
	 */
	public SortedList()
	{
		super();
	}

	/**
	 * Constructs a list containing the elements of the specified collection.
     *
     * @param c The collection whose elements are to be placed into this list
	 */
	public SortedList(Collection<? extends E> c)
	{
		super(c);
	}

	/**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param  initialCapacity  The initial capacity of the list
	 */
	public SortedList(int initialCapacity)
	{
		super(initialCapacity);
	}

	// Crucial for JAXB: JAXB calls add(E e) during unmarshalling.
	@Override
	public boolean add(E e)
	{
		int index = Collections.binarySearch(this, e);
		if ( index < 0 )
		{
			index = -(index + 1);
		}
		super.add(index, e);
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c)
	{
		boolean modified = false;
		for ( E e : c )
		{
			if ( add(e) )
			{
				modified = true;
			}
		}
		return modified;
	}

	// Prevents breaking the sort order via indexed insertion
	@Override
	public void add(int index, E element)
	{
		add(element);
	}

	// Prevents breaking the sort order via set(index, element)
	@Override
	public E set(int index, E element)
	{
		E removed = remove(index);
		add(element);
		return removed;
	}
}
