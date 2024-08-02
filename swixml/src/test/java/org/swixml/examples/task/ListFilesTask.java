package org.swixml.examples.task;

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
		if ( isCancelled() )
		{
			return;
		}
		try
		{
			Thread.sleep(200);
		}
		catch (InterruptedException e)
		{
			// TODO log condition
			return;
		}
		setMessage(file.toString());
		if ( file.isDirectory() )
		{
			for ( File f : file.listFiles() )
			{
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
}
