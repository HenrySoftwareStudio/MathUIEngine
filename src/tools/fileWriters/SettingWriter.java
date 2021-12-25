package tools.fileWriters;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
	    	System.out.println(target.getAbsolutePath());
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        Document doc = db.parse(target);
	        Element ele = doc.createElement("Setting");
	        ele.setAttribute("ID", Setting.getName());
	        ele.setAttribute("Value", Setting.getValue());
	        Transformer tfr = TransformerFactory.newInstance().newTransformer();
            tfr.setOutputProperty(OutputKeys.INDENT, "yes");
            tfr.setOutputProperty(OutputKeys.METHOD, "xml");
            tfr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tfr.transform(new DOMSource(doc) , new StreamResult(target));
	        
	      } catch (Exception e) {//Do Nothing
	      }
	}
	
	public File getTarget() {
		return target;
	}
	
	public void setTarget(File target) {
		this.target = target;
	}
}
