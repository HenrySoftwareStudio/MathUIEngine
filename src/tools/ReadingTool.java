package tools;

import java.io.File;

public class ReadingTool
{
	private final File workingOn;
	public ReadingTool(final File filePath)
	{
		workingOn=filePath;
		System.out.println(workingOn);
	}
	
	public FunctionProp read()
	{
		return null; //TODO: work on this
	}
	
	public File getWorkingOn()
	{
		return workingOn;
	}

}
