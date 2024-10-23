package org.swixml.examples.task;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URI;
import java.net.URL;

import org.jdesktop.application.Application;
import org.jdesktop.application.Task;

public class NetworkTimeRetrieverTask extends Task<String, Void>
{
	public NetworkTimeRetrieverTask(Application app)
	{
		super(app);
	}

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
}
