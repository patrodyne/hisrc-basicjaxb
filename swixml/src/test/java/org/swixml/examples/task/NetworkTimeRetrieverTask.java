package org.swixml.examples.task;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.jdesktop.application.Application;
import org.jdesktop.application.Task;

public class NetworkTimeRetrieverTask extends Task<Date, Void>
{
	public NetworkTimeRetrieverTask(Application app)
	{
		super(app);
	}

	@Override
	protected Date doInBackground()
		throws Exception
	{
		URL nistServer = new URI("https://time.nist.gov:13").toURL();
		InputStream is = nistServer.openStream();
		int ch = is.read();
		StringBuffer dateInput = new StringBuffer();
		
		while (ch != -1)
		{
			dateInput.append((char) ch);
			ch = is.read();
		}
		
		String strDate = dateInput.substring(7, 24);
		DateFormat dateFormat = DateFormat.getDateTimeInstance();
		SimpleDateFormat sdf = (SimpleDateFormat) dateFormat;
		sdf.applyPattern("yy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT-00:00"));
		Date now = dateFormat.parse(strDate);
		return now;
	}
}
