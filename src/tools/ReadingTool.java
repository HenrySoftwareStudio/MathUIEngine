package tools;

import java.io.File;
import java.util.ArrayList;

public class ReadingTool
{
	private final File workingOn;
	public ReadingTool(final File filePath)
	{
		workingOn=filePath;
		System.out.println(workingOn);
	}
	
	public ArrayList<FunctionProp> read()
	{
		return null; //TODO: work on this
	}
	
	public File getWorkingOn()
	{
		return workingOn;
	}

}
