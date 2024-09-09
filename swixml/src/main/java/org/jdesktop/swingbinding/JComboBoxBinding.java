package org.jdesktop.swingbinding;

import static org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ;
import static org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Property;
import org.jdesktop.beansbinding.PropertyStateEvent;
import org.jdesktop.beansbinding.PropertyStateListener;
import org.jdesktop.swingbinding.impl.AbstractColumnBinding;
import org.jdesktop.swingbinding.impl.ListBindingManager;

/**
 * Binds a {@code List} of objects to act as the items of a {@code JComboBox}.
 * 
 * <p>Each object in the source {@code List} is an item in the {@code JComboBox}.
 * Instances of {@code JComboBoxBinding} are obtained by calling one of the
 * {@code createJComboBoxBinding} methods in the {@code SwingBindings} class.</p>
 * 
 * <p>Here is an example of creating a binding from a {@code List} of
 * {@code Country} objects to a {@code JComboBox}:</p>
 * 
 * <pre><code>
 *    // create the country list
 *    List&lt;Country&gt; countries = createCountryList();
 *
 *    // create the binding from List to JComboBox
 *    JComboBoxBinding cb = SwingBindings.createJComboBoxBinding(READ, countries, jComboBox);
 *
 *    // realize the binding
 *    cb.bind();
 * </code></pre>
 * 
 * <p>If the {@code List} is an instance of {@code ObservableList}, then changes to
 * the {@code List} contents (such as adding, removing or replacing an object)
 * are reflected in the {@code JComboBox}. <b>Important:</b> Changing the
 * contents of a non-observable {@code List} while it is participating in a
 * {@code JComboBoxBinding} is unsupported, resulting in undefined behavior and
 * possible exceptions.</p>
 * 
 * <p>{@code JComboBoxBinding} requires extra
 * clarification on the operation of the {@code refresh} and {@code save}
 * methods and the meaning of the update strategy. The target property of a
 * {@code JComboBoxBinding} is not the target {@code JComboBox} property
 * provided in the constructor, but rather a private synthetic property
 * representing the {@code List} of objects to show in the target
 * {@code JComboBox}. This synthetic property is readable/writable only when
 * the {@code JComboBoxBinding} is bound and the target {@code JComboBox}
 * property is readable with a {@code non-null} value.</p>
 * 
 * <p>It is this private synthetic property on which the {@code refresh} and
 * {@code save} methods operate; meaning that these methods simply cause syncing
 * between the value of the source {@code List} property and the value of the
 * synthetic target property (representing the {@code List} to be shown in the
 * target {@code JComboBox}). These methods do not, therefore, have anything to
 * do with refreshing <i>values</i> in the {@code JComboBox}. Likewise, the
 * update strategy, which simply controls when {@code refresh} and {@code save}
 * are automatically called, also has nothing to do with refreshing
 * <i>values</i> in the {@code JComboBox}.</p>
 * 
 * <p><b>Note:</b> At the current time, the {@code READ_WRITE} update strategy is
 * not useful for {@code JComboBoxBinding}. To prevent unwanted confusion,
 * {@code READ_WRITE} is translated to {@code READ} by
 * {@code JComboBoxBinding's} constructor.</p>
 * 
 * <p>{@code JComboBoxBinding} works by installing a custom model on the target
 * {@code JComboBox}, as appropriate, to represent the source {@code List}. The
 * model is installed on a target {@code JComboBox} with the first successful
 * call to {@code refresh} with that {@code JComboBox} as the target. Subsequent
 * calls to {@code refresh} update the elements in this already-installed model.
 * The model is uninstalled from a target {@code JComboBox} when either the
 * {@code JComboBoxBinding} is unbound or when the target {@code JComboBox}
 * property changes to no longer represent that {@code JComboBox}. Note: When
 * the model is uninstalled from a {@code JComboBox}, it is replaced with a
 * {@code DefaultComboBoxModel}, in order to leave the {@code JComboBox}
 * functional.</p>
 * 
 * <p>Some of the above is easier to understand with an example. Let's consider a
 * {@code JComboBoxBinding} ({@code binding}), with update strategy
 * {@code READ}, between a property representing a {@code List} ({@code listP})
 * and a property representing a {@code JComboBox} ({@code jComboBoxP}).
 * {@code listP} and {@code jComboBoxP} both start off readable, referring to a
 * {@code non-null} {@code List} and {@code non-null} {@code JComboBox}
 * respectively. Let's look at what happens for each of a sequence of events:</p>
 * 
 * <table border=1>
 * <caption><b>Sequence of Events</b></caption>
 * <tr>
 * <th>Sequence</th>
 * <th>Event</th>
 * <th>Result</th>
 * </tr>
 * <tr >
 * <td >1</td>
 * <td>explicit call to {@code binding.bind()}</td>
 * <td>- synthetic target property becomes readable/writable <br>
 * - {@code refresh()} is called <br>
 * - model is installed on target {@code JComboBox}, representing list of
 * objects</td>
 * </tr>
 * <tr >
 * <td >2</td>
 * <td>{@code listP} changes to a new {@code List}</td>
 * <td>- {@code refresh()} is called <br>
 * - model is updated with new list of objects</td>
 * </tr>
 * <tr >
 * <td >3</td>
 * <td>{@code jComboBoxP} changes to a new {@code JComboBox}</td>
 * <td>- model is uninstalled from old {@code JComboBox}</td>
 * </tr>
 * <tr >
 * <td >4</td>
 * <td>explicit call to {@code binding.refresh()}</td>
 * <td>- model is installed on target {@code JComboBox}, representing list of
 * objects</td>
 * </tr>
 * <tr >
 * <td >5</td>
 * <td>{@code listP} changes to a new {@code List}</td>
 * <td>- {@code refresh()} is called <br>
 * - model is updated with new list of objects</td>
 * </tr>
 * <tr >
 * <td >6</td>
 * <td>explicit call to {@code binding.unbind()}</td>
 * <td>- model is uninstalled from target {@code JComboBox}</td>
 * </tr>
 * </table>
 * 
 * <p>Notice that in <a href="#STEP3">step 3</a>, when the
 * value of the {@code JComboBox} property changed, the new {@code JComboBox}
 * did not automatically get the model with the elements applied to it. A change
 * to the target value should not cause an {@code AutoBinding} to sync the
 * target from the source. Step 4 forces a sync by explicitly calling
 * {@code refresh}. Alternatively, it could be caused by any other action that
 * results in a {@code refresh} (for example, the source property changing
 * value, or an explicit call to {@code unbind} followed by {@code bind}).</p>
 * 
 * <p>In addition to binding the items of a {@code JComboBox}, it is possible to
 * bind to the selected item of a {@code JComboBox}. See the list of
 * <a href="package-summary.html#SWING_PROPS"> interesting swing properties</a>
 * in the package summary for more details.</p>
 *
 * @author Shannon Hickey
 *             
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 *
 * @apiNote {@code <E>}  the type of elements in the source {@code List}
 * @apiNote {@code <SS>} the type of source object (on which the source property resolves to {@code List})
 * @apiNote {@code <TS>} the type of target object (on which the target property resolves to {@code JComboBox})
 */
public final class JComboBoxBinding<E, SS, TS> extends AutoBinding<SS, List<E>, TS, List<?>>
{
	private Property<TS, ? extends JComboBox<E>> comboP;
	private ElementsProperty<TS> elementsP;
	private Handler handler = new Handler();
	private JComboBox<E> combo;
	private BindingComboBoxModel model;

	/**
	 * Constructs an instance of {@code JComboBoxBinding}.
	 *
	 * @param strategy the update strategy
	 * @param sourceObject the source object
	 * @param sourceListProperty a property on the source object that resolves
	 *            to the {@code List} of elements
	 * @param targetObject the target object
	 * @param targetJComboBoxProperty a property on the target object that
	 *            resolves to a {@code JComboBox}
	 * @param name a name for the {@code JComboBoxBinding}
	 * @throws IllegalArgumentException if the source property or target
	 *             property is {@code null}
	 */
	@SuppressWarnings("unchecked")
	protected JComboBoxBinding(UpdateStrategy strategy,
		SS sourceObject, Property<SS, List<E>> sourceListProperty,
		TS targetObject, Property<TS, ? extends JComboBox<?>> targetJComboBoxProperty,
		String name)
	{
		super(strategy == READ_WRITE ? READ : strategy,
		    sourceObject, sourceListProperty,
		    targetObject, new ElementsProperty<TS>(),
		    name);
		
		if ( targetJComboBoxProperty == null )
		{
			throw new IllegalArgumentException("target JComboBox property can't be null");
		}
		
		comboP = (Property<TS, ? extends JComboBox<E>>) targetJComboBoxProperty;
		elementsP = (ElementsProperty<TS>) getTargetProperty();
	}

	@Override
	protected void bindImpl()
	{
		elementsP.setAccessible(isComboAccessible());
		comboP.addPropertyStateListener(getTargetObject(), handler);
		elementsP.addPropertyStateListener(null, handler);
		super.bindImpl();
	}

	@Override
	protected void unbindImpl()
	{
		elementsP.removePropertyStateListener(null, handler);
		comboP.removePropertyStateListener(getTargetObject(), handler);
		elementsP.setAccessible(false);
		cleanupForLast();
		super.unbindImpl();
	}

	private boolean isComboAccessible()
	{
		return comboP.isReadable(getTargetObject()) && comboP.getValue(getTargetObject()) != null;
	}

	private boolean isComboAccessible(Object value)
	{
		return value != null && value != PropertyStateEvent.UNREADABLE;
	}

	private void cleanupForLast()
	{
		if ( combo == null )
		{
			return;
		}
		combo.setSelectedItem(null);
		combo.setModel(new DefaultComboBoxModel<E>());
		model.updateElements(null, combo.isEditable());
		combo = null;
		model = null;
	}

	private class Handler implements PropertyStateListener
	{
		@SuppressWarnings("unchecked")
		@Override
		public void propertyStateChanged(PropertyStateEvent pse)
		{
			if ( !pse.getValueChanged() )
			{
				return;
			}
			if ( pse.getSourceProperty() == comboP )
			{
				cleanupForLast();
				boolean wasAccessible = isComboAccessible(pse.getOldValue());
				boolean isAccessible = isComboAccessible(pse.getNewValue());
				if ( wasAccessible != isAccessible )
				{
					elementsP.setAccessible(isAccessible);
				}
				else if ( elementsP.isAccessible() )
				{
					elementsP.setValueAndIgnore(null, null);
				}
			}
			else
			{
				if ( ((ElementsProperty<?>.ElementsPropertyStateEvent) pse).shouldIgnore() )
				{
					return;
				}
				if ( combo == null )
				{
					combo = comboP.getValue(getTargetObject());
					combo.setSelectedItem(null);
					model = new BindingComboBoxModel();
					combo.setModel(model);
				}
				model.updateElements((List<E>) pse.getNewValue(), combo.isEditable());
			}
		}
	}

	private final class BindingComboBoxModel
		extends ListBindingManager<SS, List<E>, TS, List<?>>
		implements ComboBoxModel<E>
	{
		private final List<ListDataListener> listeners;
		private Object selectedItem = null;
		private int selectedModelIndex = -1;

		public BindingComboBoxModel()
		{
			listeners = new CopyOnWriteArrayList<ListDataListener>();
		}

		public void updateElements(List<?> elements, boolean isEditable)
		{
			setElements(elements, false);
			if ( !isEditable || selectedModelIndex != -1 )
			{
				selectedItem = null;
				selectedModelIndex = -1;
			}
			if ( size() <= 0 )
			{
				if ( selectedModelIndex != -1 )
				{
					selectedModelIndex = -1;
					selectedItem = null;
				}
			}
			else
			{
				if ( selectedItem == null )
				{
					selectedModelIndex = 0;
					selectedItem = getElementAt(selectedModelIndex);
				}
			}
			allChanged();
		}

		@SuppressWarnings("unchecked")
		@Override
		protected AbstractColumnBinding<SS, List<E>, TS, List<?>>[] getColBindings()
		{
			return new AbstractColumnBinding[0];
		}

		@Override
		public Object getSelectedItem()
		{
			return selectedItem;
		}

		@Override
		public void setSelectedItem(Object item)
		{
			// This is what DefaultComboBoxModel does (yes, yuck!)
			if ( (selectedItem != null && !selectedItem.equals(item)) || selectedItem == null && item != null )
			{
				selectedItem = item;
				contentsChanged(-1, -1);
				selectedModelIndex = -1;
				if ( item != null )
				{
					int size = size();
					for ( int i = 0; i < size; i++ )
					{
						if ( item.equals(getElementAt(i)) )
						{
							selectedModelIndex = i;
							break;
						}
					}
				}
			}
		}

		@Override
		protected void allChanged()
		{
			contentsChanged(0, size());
		}

		@Override
		protected void valueChanged(int row, int column)
		{
			// we're not expecting any value changes since we don't have any
			// detail bindings for JComboBox
		}

		@Override
		protected void added(int index, int length)
		{
			assert length > 0; // enforced by ListBindingManager
			ListDataEvent e = new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, index, index + length - 1);
			int size = listeners.size();
			for ( int i = size - 1; i >= 0; i-- )
			{
				listeners.get(i).intervalAdded(e);
			}
			if ( size() == length && selectedItem == null )
			{
				setSelectedItem(getElementAt(0));
			}
		}

		@Override
		protected void removed(int index, int length)
		{
			assert length > 0; // enforced by ListBindingManager
			ListDataEvent e = new ListDataEvent(this, ListDataEvent.INTERVAL_REMOVED, index, index + length - 1);
			int size = listeners.size();
			for ( int i = size - 1; i >= 0; i-- )
			{
				listeners.get(i).intervalRemoved(e);
			}
			if ( selectedModelIndex >= index && selectedModelIndex < index + length )
			{
				if ( size() == 0 )
				{
					setSelectedItem(null);
				}
				else
				{
					setSelectedItem(getElementAt(Math.max(index - 1, 0)));
				}
			}
		}

		@Override
		protected void changed(int row)
		{
			contentsChanged(row, row);
		}

		private void contentsChanged(int row0, int row1)
		{
			ListDataEvent e = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, row0, row1);
			int size = listeners.size();
			for ( int i = size - 1; i >= 0; i-- )
			{
				listeners.get(i).contentsChanged(e);
			}
		}

		@SuppressWarnings("unchecked")
		@Override
		public E getElementAt(int index)
		{
			return (E) getElement(index);
		}

		@Override
		public void addListDataListener(ListDataListener l)
		{
			listeners.add(l);
		}

		@Override
		public void removeListDataListener(ListDataListener l)
		{
			listeners.remove(l);
		}

		@Override
		public int getSize()
		{
			return size();
		}
	}
}
