package org.swixml.examples.task;

import static org.jdesktop.application.Application.showErrorDialog;
import static org.jdesktop.application.Application.showInformationDialog;
import static org.jdesktop.application.Application.showWarningDialog;
import static org.swixml.examples.task.BackgroundTaskExample.WINDOW;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URI;
import java.net.URL;

import org.jdesktop.application.Application;
import org.jdesktop.application.Task;

/**
 * Retrieve the current datetime from <a href="http://worldtimeapi.org">WorldTimeAPI</a>.
 * 
 * <p>WorldTimeAPI is a simple web service which returns the current local time for a
 * given timezone as either plain-text or JSON.</p>
 * 
 * <p>{@code datetime: 2025-03-12T14:22:29.109268-04:00}</p>
 */
public class NetworkTimeRetrieverTask extends Task<String, Void>
{
	private static final String DIALOG_TITLE = ListFilesTask.class.getSimpleName();

	/**
	 * Construct with an {@link Application} instance.
	 * 
	 * @param app The The jdesktop class for Swing applications.
	 */
	public NetworkTimeRetrieverTask(Application app)
	{
		super(app);
	}

    /**
     * Computes a result in a background thread, or throws an exception if unable to do so.
     *
     * <p><b>Notes:</b></p>
     * <ul>
     * <li>Create a new instance for each run, stop and discard it when done. </li>
     * <li>This method is executed in a background thread.</li>
     * </ul>
     *
     * @return The result. Use @{link javax.swing.SwingWorker.get()} to access it.
     * 
     * @throws Exception if unable to compute a result.
     */
	@Override
	protected String doInBackground()
		throws Exception
	{
		String line = null;
		URL wtServer = new URI("http://worldtimeapi.org/api/ip.txt").toURL();
		try ( InputStream is = wtServer.openStream() )
		{
			InputStreamReader isr = new InputStreamReader(is);
			LineNumberReader lnr = new LineNumberReader(isr);
			while ( (line = lnr.readLine()) != null )
			{
				// datetime: 2024-10-11T16:22:54.150754-04:00
				if ( line.startsWith("datetime: ") )
				{
					line = line.substring(10);
					break;
				}
			}
		}
		return line;
	}

	/** {@inheritDoc} */
	@Override
	protected void failed(Throwable cause)
	{
		showErrorDialog(WINDOW, cause);
	}
	
	/** {@inheritDoc} */
	@Override
	protected void succeeded(String result)
	{
		showInformationDialog(WINDOW, "Succeeded", DIALOG_TITLE);
	}
	
	/** {@inheritDoc} */
	@Override
	protected void cancelled()
	{
		showWarningDialog(WINDOW, "Cancelled", DIALOG_TITLE);
	}


	/** {@inheritDoc} */
	@Override
	protected void interrupted(InterruptedException cause)
	{
		showWarningDialog(WINDOW, "Interrupted", DIALOG_TITLE);
	}

	/** {@inheritDoc} */
	@Override
	protected void finished()
	{
		showInformationDialog(WINDOW, "Finished", DIALOG_TITLE);
	}
}
