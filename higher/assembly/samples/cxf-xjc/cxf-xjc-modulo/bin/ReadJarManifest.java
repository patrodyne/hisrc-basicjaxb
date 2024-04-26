import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * Read JAR Manifest from a JAR file or a LOG file.
 *
 * Usage: 
 *     javac ReadJarManifest.java
 *     java ReadJarManifest [jarfile | stdin]
 *     
 * Example:
 *     java ReadJarManifest <build.log
 */
public class ReadJarManifest
{
	private static final int CONFLICT_LEVEL = 88;

	public static void main(String[] args) throws IOException
	{
		if ( args.length > 0 )
		{
			try ( JarFile jar = new JarFile(args[0]) )
			{
			    Manifest mf = jar.getManifest();
				String classPath = mf.getMainAttributes().getValue("Class-Path");
				reportConflicts(classPath);
			}
		}
		else
		{
			try ( InputStreamReader isr = new InputStreamReader(System.in) )
			{
				StringBuilder sb = new StringBuilder();
				LineNumberReader lnr = new LineNumberReader(isr);
				boolean inClassPath = false;
				String line = null;
				while ( (line = lnr.readLine()) != null )
				{
					if ( line.startsWith("Class-Path:") )
					{
						inClassPath = true;
						sb.append(line.stripTrailing());
					}
					else if ( inClassPath )
					{
						if ( !line.startsWith(" ") )
						{
							inClassPath = false;
							reportConflicts(sb.toString());
							sb = new StringBuilder();
						}
						else
							sb.append(line.replaceFirst(" ", "").stripTrailing());
					}
				}
				reportConflicts(sb.toString());
			}
		}
	}

	private static void reportConflicts(String classPath)
	{
	    if ( (classPath != null) && !classPath.isBlank() )
		{
			println("\nClasspath Report: START");
	    	String[] dependencies = classPath.split(" ");
	        for (String dependency : dependencies)
	            println(dependency);
	        reportConflicts(dependencies);
			println("Classpath Report: FINISH");
	    }
	}

	private static void reportConflicts(String[] dependencies)
	{
		for ( int ix=0; ix < dependencies.length; ++ix )
		{
			for ( int jx=ix+1; jx < dependencies.length; ++jx )
			{
				String dx = dependencies[ix];
				String dj = dependencies[jx];
				if ( dx.equals(dj) )
				{
					println("Duplicate: ");
					println("  " + dependencies[ix]);
				}
				else
				{
					int pct = conflictPercentage(dx, dj);
					if ( pct > CONFLICT_LEVEL )
					{
						println("Conflict%: " + pct);
						println("  " + dependencies[ix]);
						println("  " + dependencies[jx]);
						break;
					}
				}
			}
		}
		println("Note: Conflicts are estimated. Please review dependency list in detail.");
	}

	private static int conflictPercentage(String d1, String d2)
	{
		String da = d1;
		String db = d2;
		if ( d1.length() > d2.length() )
		{
			da = d2;
			db = d1;
		}
		char[] ca = da.toCharArray();
		char[] cb = db.toCharArray();
		int lena = ca.length;
		int lenb = cb.length;
		int leadCount = 0;
		for ( int cx=0; cx < lena; ++cx )
		{
			if ( ca[cx] == cb[cx] )
				++leadCount;
			else
				break;
		}
		int tailCount = 0;
		for ( int cx=lena; cx > leadCount ; --cx )
		{
			if ( ca[cx-1] == cb[cx-1] )
				++tailCount;
		}
		return lenb > 0 ? ( 100 * ( leadCount + tailCount) ) / lenb : 100;
	}

	private static void println(String str)
	{
		System.out.println(str);
	}
}
