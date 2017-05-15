package com.web.file;

import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.file.util.XMLDocLoader;
import com.web.param.DBInfoContainer;
import com.web.param.WebConfigContainer;

public class WebConfigLoader {

	private WebConfigContainer webConfig = new WebConfigContainer();
	private Document doc;

	public WebConfigContainer loadWebConfig(String filename) {

		doc = XMLDocLoader.loadXML(filename);
		readDBInfo();
		readOrignEnvs();
		readDestEnvs();
		readHomeDir();
		readImptCtrlDir();
		return webConfig;
	}
	
	private NodeList getNodeList( String expr ) {
		
		XPath xPath = XPathFactory.newInstance().newXPath();

		NodeList nodeList = null;

		try {
			nodeList = (NodeList) xPath.compile(expr).evaluate(doc,
					XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nodeList;
	}
	

	private void readDBInfo() {
		
		NodeList nodeList = getNodeList("/web-config/login/database");
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			
			DBInfoContainer dbInfo = new DBInfoContainer();
			
			
			Node dbNode = nodeList.item(i);
			NamedNodeMap dbAttrs = dbNode.getAttributes();
			Node hostAttr = dbAttrs.getNamedItem("host");
			dbInfo.setHost(hostAttr.getNodeValue());
			
			Node portAttr = dbAttrs.getNamedItem("port");
			dbInfo.setPort(portAttr.getNodeValue());
			
			Node svcAttr = dbAttrs.getNamedItem("service");
			dbInfo.setService(svcAttr.getNodeValue());
			
			webConfig.addDBInfo(dbInfo);
		}
		

	}

	private void readOrignEnvs() {
		NodeList nodeList = getNodeList("/web-config/orign-envs/env-id");
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node dbNode = nodeList.item(i);
			webConfig.addOrignEnv(dbNode.getTextContent());
			//System.out.println("Orign " + dbNode.getTextContent());
		};
	}

	private void readDestEnvs() {
		NodeList nodeList = getNodeList("/web-config/dest-envs/env-id");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node dbNode = nodeList.item(i);
			webConfig.addDestEnv(dbNode.getTextContent());
			//System.out.println("Dest " + dbNode.getTextContent());
		}
	}
	
	private void readHomeDir() {
	  NodeList nodeList = getNodeList("/web-config/home-dir");
	  Node homeDirNode = nodeList.item(0);
	  webConfig.setHomeDir(homeDirNode.getTextContent());
	}

	private void readImptCtrlDir () {
	  NodeList nodeList = getNodeList("/web-config/impt-ctrl");
	  Node imptCtrlNode = nodeList.item(0);
	  webConfig.setImptCtrlDir(imptCtrlNode.getTextContent());
	}
	
}