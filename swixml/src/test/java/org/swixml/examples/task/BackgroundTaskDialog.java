package org.swixml.examples.task;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JProgressBar;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.Task;
import org.swixml.jsr.widgets.JTextFieldBind;

public class BackgroundTaskDialog extends JDialog
{
	private static final long serialVersionUID = 20240701L;

	private JTextFieldBind txtShowTime;
	public JTextFieldBind getTxtShowTime() { return txtShowTime; }
	public void setTxtShowTime(JTextFieldBind txtShowTime) { this.txtShowTime = txtShowTime; }

	private String time;
	public final String getTime() { return time; }
	public final void setTime(String time)
	{
		this.time = time;
		firePropertyChange("time", null, null);
	}

	private String file = "file";
	public final String getFile() { return file; }
	public final void setFile(String file)
	{
		this.file = file;
		firePropertyChange("file", null, null);
	}

	private JProgressBar progressBar;
	public JProgressBar getProgressBar() { return progressBar; }
	public void setProgressBar(JProgressBar progressBar) { this.progressBar = progressBar; }

	public class NetworkTimeRetriever extends NetworkTimeRetrieverTask
	{
		public NetworkTimeRetriever(Application app)
		{
			super(app);
		}

		@Override
		protected void succeeded(String time)
		{
			setTime(time.toString());
		}
	}
	
	@Override
	public void addNotify()
	{
		super.addNotify();
	}
	
	private Task<?, ?> retrieveTimeTask = null;
	public Task<?, ?> getRetrieveTimeTask() { return retrieveTimeTask; }
	public void setRetrieveTimeTask(Task<?, ?> retrieveTimeTask) { this.retrieveTimeTask = retrieveTimeTask; }
	
	@Action
	public Task<?, ?> retrieveTime()
	{
		if ( getRetrieveTimeTask() == null )
			setRetrieveTimeTask(new NetworkTimeRetriever(Application.getInstance()));
		return getRetrieveTimeTask();
	}

	private Task<?, ?> scanDirTask = null;
	public Task<?, ?> getScanDirTask() { return scanDirTask; }
	public void setScanDirTask(Task<?, ?> scanDirTask) { this.scanDirTask = scanDirTask; }
	
	@Action
	public Task<?, ?> scanDir()
	{
		if ( getScanDirTask() == null )
		{
			File userHome = new File(System.getProperty("user.home"));
			Task<Void, File> task = new ListFilesTask(Application.getInstance(), userHome);
			task.addPropertyChangeListener(new PropertyChangeListener()
			{
				@Override
				public void propertyChange(PropertyChangeEvent evt)
				{
					getProgressBar().setValue(1);
					getProgressBar().setString((null != evt.getNewValue()) ? evt.getNewValue().toString() : "");
					setFile(String.format("[%2$s]", evt.getPropertyName(), evt.getNewValue()));
				}
			});
			getProgressBar().setIndeterminate(true);
			setScanDirTask(task);
		}
		return getScanDirTask();
	}
}
