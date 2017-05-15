package com.file.tasks;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import com.control.MigrationTask;
import com.file.FileWriterFactory;
import com.infa.util.IntegSvcMap;
import com.param.containers.ParamContainer;

public class XMLIntegSvcUpdTask implements MigrationTask {

	private ParamContainer params;
	private String filename;
	private String destRepo;
	private FileWriterFactory displayLogWriter;
	private List<String> wfIntegSvcList;
	private Set<String> newWFSet;
	// private XPath xPath;
	private Document doc;

	private static final int TWO_TOKENS = 2;
	private static final int SECOND_TOKEN = 1;

	public XMLIntegSvcUpdTask(ParamContainer params) {
		this.params = params;
		this.filename = params.getCustFileParm().getDMXMLFile().toString();
		// this.integSvcMap = params.getIntegSvcMap();
		this.destRepo = params.getDestInfaParm().getInfaRepo();
		this.displayLogWriter = params.getCustFileParm().getDisplayLogWriter();
		this.newWFSet = params.getRunParm().getNewWorkflowSet();
		wfIntegSvcList = new ArrayList<String> ();
	}

	@Override
	public int execute() {
		printStart();
		loadXML();
		updateDoc();
		writeXML();
		printEnd();
		return 0;
	}

	@Override
	public void run() {
		execute();

	}

	@Override
	public void printStart() {
		try {
			displayLogWriter.append("Updating Integration Service in XML ...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void printEnd() {
		try {
			
			displayLogWriter.append("Integration Service updated in XML.");
			displayLogWriter.appendList(wfIntegSvcList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void loadXML() {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			docFactory.setValidating(false);
			docFactory.setNamespaceAware(true);
			docFactory.setFeature("http://xml.org/sax/features/namespaces",
					false);
			docFactory.setFeature("http://xml.org/sax/features/validation",
					false);
			docFactory
					.setFeature(
							"http://apache.org/xml/features/nonvalidating/load-dtd-grammar",
							false);
			docFactory
					.setFeature(
							"http://apache.org/xml/features/nonvalidating/load-external-dtd",
							false);
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.parse(filename);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateDoc() {
		XPath xPath = XPathFactory.newInstance().newXPath();
		String expression = "/POWERMART/REPOSITORY/FOLDER/WORKFLOW";

		NodeList nodeList = null;

		try {
			nodeList = (NodeList) xPath.compile(expression).evaluate(doc,
					XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < nodeList.getLength(); i++) {
			
			Node wfNode = nodeList.item(i);
			NamedNodeMap wfAttr = wfNode.getAttributes();
			Node wf = wfAttr.getNamedItem("NAME");
			String wfName = wf.getNodeValue();
		
			
			Node folderNode = wfNode.getParentNode();
			NamedNodeMap folderAttr = folderNode.getAttributes();
			Node folder = folderAttr.getNamedItem("NAME");
			String folderName = folder.getNodeValue();

			String shortID = folderName + "," + wfName;
			
			if (newWFSet.contains(shortID) ) {
				
				Node IntegSvc = wfAttr.getNamedItem("SERVERNAME");
				String origIntegSvcName = IntegSvc.getNodeValue();
				

				String destIntegSvcName = getDestIntegSvc(folderName,
						origIntegSvcName);

				///System.out.println("Res: " + destIntegSvcName);

				IntegSvc.setNodeValue(destIntegSvcName);
				wfIntegSvcList.add(shortID + " => " + destIntegSvcName);
			}
		}
	}

	private void writeXML() {

		try {
			Transformer trans = TransformerFactory.newInstance()
					.newTransformer();
			trans.setOutputProperty(OutputKeys.INDENT, "yes");

			DocumentType doctype = doc.getDoctype();
			if (doctype != null) {
				// trans.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC,
				// doctype.getPublicId());
				trans.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
						doctype.getSystemId());
			}

			doc.setXmlStandalone(true);
			DOMSource domSource = new DOMSource(doc);
			StreamResult streamResr = new StreamResult(new File(filename));
			trans.transform(domSource, streamResr);

		} catch (TransformerFactoryConfigurationError | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String getDestIntegSvc(String folder, String origIntegSvc) {

		// System.out.println("Folder: " + folder + " | service: " +
		// origIntegSvc + " | Dest Repo: " + destRepo);

		IntegSvcMap integSvcMap = params.getRunParm().getIntegSvcMap();

		if (folder.equals("DCR_FIX")) {
			String[] inteSvcTokens = origIntegSvc.split("_");

			if (inteSvcTokens.length == TWO_TOKENS) {
				String folderExt = folder + inteSvcTokens[SECOND_TOKEN];

				if (integSvcMap.containsKeys(folderExt, destRepo)) {
					return integSvcMap.get(folderExt, destRepo);
				}
			}
		}

		// System.out.println(params.getIntegSvcMap().get("MBCDW_DM",
		// "MBCDWRepRegT"));

		return integSvcMap.get(folder, destRepo);
	}

}