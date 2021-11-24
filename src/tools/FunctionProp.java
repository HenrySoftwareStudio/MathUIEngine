package tools;

import java.io.Serializable;

public class FunctionProp implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2515774817797271903L;
	private final String name;
	private final String[] launchArg;
	private final String toolTipMessage;
	private final int LookupKey;
	public FunctionProp(final String Name, final String[] LaunchArg, final String TooltipMessage)
	{
		name=Name;
		launchArg=LaunchArg;
		toolTipMessage=TooltipMessage;
		LookupKey=hashCode();
	}
	
	public String[] getLaunchArg()
	{
		return launchArg;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getToolTipMessage()
	{
		return toolTipMessage;
	}
	
	public int getLookupKey()
	{
		return LookupKey;
	}
}
