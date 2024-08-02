package org.swixml.examples.task;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JProgressBar;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.Task;

public class BackgroundTaskDialog extends JDialog
{
	private static final long serialVersionUID = 20240701L;
	public class NetworkTimeRetriever extends NetworkTimeRetrieverTask
	{
		public NetworkTimeRetriever(Application app)
		{
			super(app);
		}

		@Override
		protected void succeeded(Date time)
		{
			setTime(time.toString());
		}
	}

	JProgressBar progressBar;

	@Override
	public void addNotify()
	{
		super.addNotify();
	}

	private String time;
	public final String getTime() { return time; }
	public final void setTime(String time)
	{
		this.time = time;
		firePropertyChange("time", null, null);
	}

	private String file;
	public final String getFile() { return file; }
	public final void setFile(String file)
	{
		this.file = file;
		firePropertyChange("file", null, null);
	}

	@Action
	public Task<?, ?> retrieveTime()
	{
		Task<Date, Void> task = new NetworkTimeRetriever(Application.getInstance());
		return task;
	}

	@Action
	public Task<?, ?> scanDir()
	{
		Task<Void, File> task = new ListFilesTask(Application.getInstance(), new File(System.getProperty("user.home")));
		task.addPropertyChangeListener(new PropertyChangeListener()
		{
			@Override
			public void propertyChange(PropertyChangeEvent evt)
			{
				progressBar.setValue(1);
				progressBar.setString((null != evt.getNewValue()) ? evt.getNewValue().toString() : "");
				setFile(String.format("[%2$s]", evt.getPropertyName(), evt.getNewValue()));
			}
		});
		progressBar.setIndeterminate(true);
		return task;
	}
}
