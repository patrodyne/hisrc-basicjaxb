package org.jvnet.basicjaxb.lang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jvnet.basicjaxb.locator.ObjectLocator;

public class JAXBMergeCollectionsStrategy extends JAXBMergeStrategy
{
	private static final JAXBMergeCollectionsStrategy INSTANCE = new JAXBMergeCollectionsStrategy();

	public static JAXBMergeCollectionsStrategy getInstance()
	{
		return INSTANCE;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object mergeInternal(ObjectLocator lhsLocator, ObjectLocator rhsLocator,
		@SuppressWarnings("rawtypes") Collection lhsCollection,
		@SuppressWarnings("rawtypes") Collection rhsCollection)
	{
		if (lhsCollection instanceof List && rhsCollection instanceof List)
		{
			final List<Object> list = new ArrayList<Object>(lhsCollection.size() + rhsCollection.size());
			list.addAll(lhsCollection);
			list.addAll(rhsCollection);
			return observe("BHS", lhsLocator, list);
		}
		else if (lhsCollection instanceof Set && rhsCollection instanceof Set)
		{
			final Set<Object> set = new HashSet<Object>(lhsCollection.size() + rhsCollection.size());
			set.addAll(lhsCollection);
			set.addAll(rhsCollection);
			return observe("BHS", lhsLocator, set);
		}
		else
			return super.mergeInternal(lhsLocator, rhsLocator, lhsCollection, rhsCollection);
	}
}