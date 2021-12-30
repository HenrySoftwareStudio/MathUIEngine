package tools;

public class SettingProperty {
	private final String name;
	private String value;
	
	public SettingProperty() {
		this(null, null);
	}
	
	public SettingProperty(String Name, String InitValue) {
		this.name = Name;
		this.value = InitValue;
	}
	
	public String getName() {
		return name;
	}
	
	public String getValue() {
		return value;
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
