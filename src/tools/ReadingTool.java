package tools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import tools.FunctionProp.AnsType;

public class ReadingTool
{
	private final File workingOn;
	public ReadingTool(final File filePath)
	{
		workingOn = filePath;
	}
	
	public ArrayList<FunctionProp> read() throws ParserConfigurationException, SAXException, IOException
	{
		ArrayList<FunctionProp> ret = new ArrayList<>(1);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(workingOn);
        doc.getDocumentElement().normalize();
	    dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        NodeList MainNodeList = doc.getElementsByTagName("function");
        for (int temp = 0; temp < MainNodeList.getLength(); temp++) 
        {
        	Node node = MainNodeList.item(temp);
        	if (node.getNodeType() == Node.ELEMENT_NODE) 
	        {
        		Element idvFunction = (Element) node;
        		String name = idvFunction.getAttribute("name");
        		String launchArgs = idvFunction.getAttribute("launchArgs");
				String tooltip = idvFunction.getAttribute("tooltip");
				int valueCount = Integer.parseInt(idvFunction.getAttribute("valuesCount"));
				AnsType type = FunctionProp.makeType(idvFunction.getAttribute("answerType"));
				ret.add(new FunctionProp(name, launchArgs, tooltip, valueCount, type));    		
	        }
        }
		return ret;
	}
	
	public File getWorkingOn()
	{
		return workingOn;
	}

}
