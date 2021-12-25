package tools;

public class SettingProperty {
	private final String name;
	private final String DefaultValue;
	private String value;
	
	public SettingProperty() {
		this(null, null, null);
	}
	
	/**Note: This will set value to null, please be advised
	 * 
	 * @param Name
	 */
	public SettingProperty(String Name, String InitValue) {
		this(Name, InitValue, "");
	}
	
	public SettingProperty(String Name, String InitValue, String DefaultValue) {
		this.name = Name;
		this.value = InitValue;
		this.DefaultValue = DefaultValue;
	}
	
	public String getName() {
		return name;
	}
	
	public String getValue() {
		if (value != null) {
			System.out.println(value);
			return value;
		} else {
			return DefaultValue;
		}
		
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getSetting() {
		return String.format("<Setting ID=\"%s\" Value=\"%s\">", this.name, this.value);
	}
	
	@Override
	public String toString() {
		return "Setting@"+super.toString()+" Name: " + name + " Value: " + value;
	}

}
