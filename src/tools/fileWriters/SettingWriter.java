package tools.fileWriters;

import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import tools.SettingProperty;

public class SettingWriter extends Writer<SettingProperty> implements Closeable {
	private File target;
	public SettingWriter(File Target) throws IOException {
		this.target = Target;
	}
	
	@Override
	public void close() throws IOException {
		target = null;
	}

	@Override
	public void write(SettingProperty Setting) {
	    try {
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        Document doc = db.parse(target);
	        
	        NodeList nodeList = doc.getElementsByTagName("Setting");
	        
	        Node neededNode = null;
	        
	        for (int i = 0; i < nodeList.getLength(); i++) {
				if (nodeList.item(i).getAttributes().getNamedItem("ID").getNodeValue().equals(Setting.getName())) {
					neededNode = nodeList.item(i);
				}
			}
	        
	        neededNode.getAttributes().getNamedItem("Value").setNodeValue(Setting.getValue());
	        
	        Transformer tfr = TransformerFactory.newInstance().newTransformer();
            tfr.setOutputProperty(OutputKeys.STANDALONE, "yes");
            tfr.setOutputProperty(OutputKeys.METHOD, "xml");
            tfr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tfr.transform(new DOMSource(doc) , new StreamResult(new FileWriter(target, false)));
            tfr.transform(new DOMSource(doc) , new StreamResult(System.out));
	      } catch (Exception e) {
	    	  e.printStackTrace();
	      }
	}
	
	public File getTarget() {
		return target;
	}
	
	public void setTarget(File target) {
		this.target = target;
	}
	
	@Override
	public String toString() {
		return super.toString() + " with " + target.getAbsolutePath() + " as Target";
	}
}
