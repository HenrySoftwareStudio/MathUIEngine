package tools.readingPacks;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import tools.ChangableString;

public class UITextReadingTool extends ReadingTool<ArrayList<Map<String, ChangableString>>> {
	private ArrayList<Map<String, ChangableString>> result;
	private String[] nodesToRead;
	
	public UITextReadingTool(File filePath, String[] NodesToRead) {
		super(filePath);
		nodesToRead = NodesToRead;
	}

	@Override
	public ArrayList<Map<String, ChangableString>> read() throws Exception {
		ArrayList<Map<String, ChangableString>> ToReturn = new ArrayList<>(0);
		ArrayList<String> nodesReading = new ArrayList<>(Arrays.asList(nodesToRead));
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    Document doc = builder.parse(workingOn);
	    NodeList nodeList = doc.getElementsByTagName("obj");
		for (int i = 0; i < nodeList.getLength(); i++) {
		}
		return ToReturn;
	}
	
	private Map<String, ChangableString> process(String objKey, Node NodeItem) {
		Map<String, ChangableString> ToReturn = new HashMap<String, ChangableString>();
		
		return ToReturn;
	}
	
	public ArrayList<Map<String, ChangableString>> getResult() {
		return result;
	}
}
