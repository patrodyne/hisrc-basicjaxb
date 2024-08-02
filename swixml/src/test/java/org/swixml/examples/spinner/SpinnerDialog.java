package org.swixml.examples.spinner;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;

import org.jdesktop.application.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implement a SpinnerDialog ny extending {@link JDialog}.
 */
public class SpinnerDialog extends JDialog
{
	private static final long serialVersionUID = 20240501L;

	private static final Logger logger = LoggerFactory.getLogger(SpinnerDialog.class);

	private Calendar calendar = Calendar.getInstance();
	public Calendar getCalendar() { return calendar; }
	public void setCalendar(Calendar calendar) { this.calendar = calendar; }

	private List<String> months = Arrays.asList(createMonthStrings());
	public List<String> getMonths() { return months; }
	public void setMonths(List<String> months) { this.months = months; }

	private Date dateValue = new Date();
	public Date getDateValue() { return dateValue; }
	public void setDateValue(Date date)
	{
		logger.info("setDate " + date);
		this.dateValue = date;
		firePropertyChange("dateValue", null, null);
	}

	private int numberValue = 5;
	public int getNumberValue() { return numberValue; }
	public void setNumberValue(int value) { this.numberValue = value; }
	
	private String stringValue;;
	public String getStringValue() { return stringValue; }
	public void setStringValue(String value) { this.stringValue = value; }
	
	private JSpinner spinnerDate;
	public JSpinner getSpinnerDate()
	{
		return spinnerDate;
	}
	public void setSpinnerDate(JSpinner spinnerDate)
	{
		this.spinnerDate = spinnerDate;
	}

	private SpinnerModel spinnerDateModel = null;
	/**
	 * Model returned from ${spinnerDateModel} evaluation.
	 *
	 * @return A <code>SpinnerDateModel</code> that represents a sequence
     *         of dates between <code>start</code> and <code>end</code>.
	 */
	public SpinnerModel getSpinnerDateModel()
	{
		if ( spinnerDateModel == null )
		{
			Date initDate = getCalendar().getTime();
			getCalendar().add(Calendar.YEAR, -100);
			
			Date earliestDate = getCalendar().getTime();
			getCalendar().add(Calendar.YEAR, 200);
			
			Date latestDate = getCalendar().getTime();
			
			// ignored for user input
			spinnerDateModel =
				new SpinnerDateModel(initDate, earliestDate, latestDate, Calendar.YEAR);

		}
		return spinnerDateModel;
	}
	public void setSpinnerDateModel(SpinnerModel spinnerDateModel)
	{
		this.spinnerDateModel = spinnerDateModel;
	}
	
	private JSpinner spinnerList;
	public JSpinner getSpinnerList()
	{
		return spinnerList;
	}
	public void setSpinnerList(JSpinner spinnerList)
	{
		this.spinnerList = spinnerList;
	}

	private SpinnerModel spinnerListModel = null;
	/**
	 * Model returned from ${spinnerListModel} evaluation
	 *
	 * @return A <code>SpinnerModel</code> whose sequence of
     *         values is defined by the specified <code>List</code>.
	 */
	public SpinnerModel getSpinnerListModel()
	{
		if ( spinnerListModel == null )
			spinnerListModel = new SpinnerListModel(getMonths());
		return spinnerListModel;
	}
	public void setSpinnerListModel(SpinnerModel spinnerListModel)
	{
		this.spinnerListModel = spinnerListModel;
	}

	/**
	 * DateFormatSymbols returns an extra, empty value at the end of the array
	 * of months. Remove it.
	 */
	private String[] createMonthStrings()
	{
		String[] months = new java.text.DateFormatSymbols().getMonths();
		int lastIndex = months.length - 1;
		if ( months[lastIndex] == null || months[lastIndex].length() <= 0 )
		{ 
			// last item empty
			String[] monthStrings = new String[lastIndex];
			System.arraycopy(months, 0, monthStrings, 0, lastIndex);
			return monthStrings;
		}
		else
		{ 
			// last item not empty
			return months;
		}
	}
	
	/**
	 * Default constructor to initialize the spinnerDate lis.
	 */
	public SpinnerDialog()
	{
		setStringValue(getMonths().get(3));
	}

	@Action
	public void submit()
	{
		try
		{
			getSpinnerDate().commitEdit();
			logger.info("value is valid");
		}
		catch (ParseException ex)
		{
			logger.error(ex.getMessage());
		}
	}

	@Action
	public void test()
	{
		getCalendar().setTime(getDateValue());
		getCalendar().set(Calendar.HOUR, 16);
		getCalendar().set(Calendar.MINUTE, 01);
		Date dt = getCalendar().getTime();
		setDateValue(dt);
	}

	@Action
	public void close()
	{
		dispose();
	}
}
