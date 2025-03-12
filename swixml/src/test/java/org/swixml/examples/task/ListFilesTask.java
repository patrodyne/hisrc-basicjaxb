package org.swixml.examples.task;

import static org.jdesktop.application.Application.showErrorDialog;
import static org.jdesktop.application.Application.showInformationDialog;
import static org.jdesktop.application.Application.showWarningDialog;
import static org.swixml.examples.task.BackgroundTaskExample.WINDOW;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jdesktop.application.Application;
import org.jdesktop.application.Task;

/**
 *
 * @author Sorrentino
 */
public class ListFilesTask extends Task<Void, File>
{
	private static final String DIALOG_TITLE = ListFilesTask.class.getSimpleName();

	private final File root;
	private final int bufferSize;
	private final List<File> buffer;

	public ListFilesTask(Application app, File root)
	{
		super(app);
		this.root = root;
		bufferSize = 10;
		buffer = new ArrayList<File>(bufferSize);
	}

	private void expand(File file)
	{
		sleep(200l);
		if ( !isCancelled() )
		{
			setMessage(file.toString());
			
			if ( file.isDirectory() )
			{
				for ( File f : file.listFiles() )
				{
					if ( isCancelled() )
						break;
					else
						expand(f);
				}
			}
			else
			{
				buffer.add(file);
				if ( buffer.size() >= bufferSize )
				{
					File bufferFiles[] = new File[buffer.size()];
					publish(buffer.toArray(bufferFiles));
					buffer.clear();
				}
			}
		}
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
	public Void doInBackground()
	{
		expand(root);
		if ( !isCancelled() )
		{
			File bufferFiles[] = new File[buffer.size()];
			publish(bufferFiles);
		}
		return null;
	}

	/** {@inheritDoc} */
	@Override
	protected void failed(Throwable cause)
	{
		showErrorDialog(WINDOW, cause);
	}

	/** {@inheritDoc} */
	@Override
	protected void succeeded(Void result)
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
	
	private void sleep(long ms)
	{
		try
		{
			Thread.sleep(ms);
		}
		catch (InterruptedException e)
		{
			// TODO log condition
			return;
		}
	}
}
