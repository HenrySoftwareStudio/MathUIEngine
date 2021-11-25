package tools;

import java.io.Serializable;

public class FunctionProp implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2515774817797271903L;
	private final String name;
	private final String launchArg;
	private final String toolTipMessage;
	private final int LookupKey;
	private final int inputFieldCount;
	public FunctionProp(final String Name, final String LaunchArg, final String TooltipMessage, final int InputFieldCount)
	{
		name = Name;
		launchArg = LaunchArg;
		toolTipMessage = TooltipMessage;
		inputFieldCount = InputFieldCount;
		LookupKey = hashCode();
	}
	
	public String getLaunchArg()
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
	
	public int getInputFieldCount()
	{
		return inputFieldCount;
	}
}
