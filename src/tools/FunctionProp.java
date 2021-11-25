package tools;

import java.io.Serializable;

public class FunctionProp implements Serializable
{
	public enum AnsType
	{
		FULL("f"),
		PARTIAL("p");

		AnsType(String string){}
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2515774817797271903L;
	private final String name;
	private final String launchArg;
	private final String toolTipMessage;
	private final AnsType type;
	private final int LookupKey;
	private final int inputFieldCount;
	public FunctionProp(final String Name, final String LaunchArg, final String TooltipMessage, final int InputFieldCount, final AnsType Type)
	{
		name = Name;
		launchArg = LaunchArg;
		toolTipMessage = TooltipMessage;
		inputFieldCount = InputFieldCount;
		type = Type;
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
	
	public AnsType getType()
	{
		return type;
	}
	
	public static AnsType makeType(final String code)
	{
		if (code.equalsIgnoreCase("Full"))
		{
			return AnsType.FULL;
		}
		else if (code.equalsIgnoreCase("Partial")) 
		{
			return AnsType.PARTIAL;
		}
		else
		{
			throw new IllegalArgumentException(code + " has no corresponding ENUM Code");
		}
	}
}
