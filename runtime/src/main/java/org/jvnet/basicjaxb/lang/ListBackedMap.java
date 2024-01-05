package org.jvnet.basicjaxb.lang;

import static java.util.stream.Collectors.toMap;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * A {@link Map} "view" backed by a {@link List} of key value objects where
 * changes to the map are reflected in the underlying list.
 * 
 * <p><b>Example:</b></p>
 * 
 * <pre>{@code
 * class CustomField 
 * {
 *     String key;
 *     String value;
 * }
 *
 * List<CustomField> list = getList();
 * ListBackedMap<CustomField, String, String> map =
 *     new ListBackedMap<>
 *     (
 *         list,
 *         (key, value) -> new CustomField(key, value),
 *         CustomField::getKey,
 *         CustomField::getValue
 *     );
 * }</pre>
 * 
 * @param <T> The type of elements in the backing list.
 * @param <K> The type of keys maintained by this map.
 * @param <V> The type of mapped values.
 * 
 * <p>Reference: <a href="https://stackoverflow.com/questions/43803670/">how-to-generate-a-map-backed-by-a-list</a></p>
 */
public class ListBackedMap<T, K, V> extends AbstractMap<K, V>
{
	private final List<T> list;
	private final BiFunction<K, V, T> keyValueToElement;
	private final Function<T, K> elementToKey;
	private final Function<T, V> elementToValue;

	/**
	 * Construct with a backing {@link List} 
	 * 
	 * @param list The backing {@link List} instance.
	 * @param keyValueToElement The {@link BiFunction} to provide the list elements.
	 * @param elementToKey The {@link Function} to provide the map's key.
	 * @param elementToValue The {@link Function} to provide the map's value.
	 */
	public ListBackedMap(List<T> list, BiFunction<K, V, T> keyValueToElement,
		Function<T, K> elementToKey, Function<T, V> elementToValue)
	{
		this.list = list;
		this.keyValueToElement = keyValueToElement;
		this.elementToKey = elementToKey;
		this.elementToValue = elementToValue;
	}

	/**
	 * Collect a {@link Set} of map {@link Entry} from the backing {@link List}.
	 */
	@Override
	public Set<Entry<K, V>> entrySet()
	{
		return list.stream().collect(toMap(elementToKey, elementToValue)).entrySet();
	}

	/**
	 * Put a key-value pair into the backing {@link List}. Any matching previous
	 * key-value pair is replaced.
	 */
	@Override
	public V put(K key, V value)
	{
		V previousValue = remove(key);
		list.add(keyValueToElement.apply(key, value));
		return previousValue;
	}

	/**
	 * Get the backing {@link List}.
	 * 
	 * @return The backing {@link List} for the {@link Map}.
	 */
	public List<T> getList()
	{
		return list;
	}
}
