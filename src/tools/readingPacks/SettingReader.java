package tools.readingPacks;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import tools.SettingProperty;

public class SettingReader extends ReadingTool<SettingProperty> {
	private String propertyID;
	
	public SettingReader(File filePath, String PropertyID) {
		super(filePath);
		if (PropertyID != null) {
			this.propertyID = PropertyID;
		} else {
			throw new IllegalArgumentException("PropertyID Argument Can not be null");
		}
	}

	@Override
	public SettingProperty read() throws Exception {
		if(workingOn.canRead()) {
			try {
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.parse(workingOn);
				NodeList listOfSettings = doc.getElementsByTagName("Setting");
				for (int i = 0; i < listOfSettings.getLength(); i++) {
					Node lookingAt = listOfSettings.item(i);
					if (lookingAt.getAttributes().getNamedItem("ID").getNodeValue().equals(propertyID)) {
						return new SettingProperty(propertyID, lookingAt.getAttributes().getNamedItem("Value").getNodeValue());
					}
				}
				
			} catch (Exception e) {//Do nothing
				e.printStackTrace();
			}
		} else {
			workingOn.createNewFile();
		}
		return new SettingProperty(propertyID, null);
	}
	
	public String getPropertyID() {
		return propertyID;
	}
}
