import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XML{
	
	/**To successfully use this application: Please change the XML file directory to one on your machine. */
	public static final String xmlFilePath = "C:\\Users\\naija.andry\\OneDrive\\~~Work~~\\Currency Converter\\src\\CurrencyDatabase.xml";
	
	public XML(){	
		
	}
	
	public void initializeFile() {
        try {
            DocumentBuilderFactory docFactory= DocumentBuilderFactory.newInstance(); 
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder(); 		  
            Document doc = docBuilder.newDocument();					  

            Element root = doc.createElement("Currencies");				
            doc.appendChild(root);										    

            root.appendChild(createCurrency(doc, "USD", "1.00" ));
            root.appendChild(createCurrency(doc, "CAD", "1.30472" ));
            root.appendChild(createCurrency(doc, "ISK", "126.354" ));
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();   
            Transformer transformer = transformerFactory.newTransformer();				
            DOMSource domSource = new DOMSource(doc);								
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));		

            transformer.transform(domSource, streamResult);								
 
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
    
    public Node createCurrency(Document doc, String name, String rate) {
        
        Element currency = doc.createElement("Currency");       
        currency.appendChild(createCurrencyElements(doc, currency, "name", name));
        currency.appendChild(createCurrencyElements(doc, currency, "rate", rate));
    	return currency;
    }
    
    public Node createCurrencyElements(Document doc,Element element, String name, String entry) {
    	
    	Element node = doc.createElement(name);
    	node.appendChild(doc.createTextNode(entry));
    	return node;
    }    
    
	public void printFile() {	
		try {
			File xmlfile = new File(xmlFilePath);
			DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder dB = dBF.newDocumentBuilder();
			Document doc = dB.parse(xmlfile);
	
			NodeList list = doc.getElementsByTagName("Currency");
			
			for(int i = 0; i <list.getLength(); i++) {
				
				Node node =  (Node) list.item(i);
				
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					
					Element element = (Element) node;
					System.out.println("Currency Name: " + element.getElementsByTagName("name").item(0).getTextContent()+ " Currency Rate: " + element.getElementsByTagName("rate").item(0).getTextContent()+ "\n");
				}
			}
			
		}catch (Exception e) {
			System.out.println("Sorry, the system has encountered an unexpected error at PrintFile.");
			System.exit(0);
		}
	}
	
	
	public void addEntry(String currency, String rate) {	
		try {
			
			File xmlfile = new File(xmlFilePath);
			
			DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder dB = dBF.newDocumentBuilder();
			Document doc = dB.parse(xmlfile);
			
			Node root = doc.getFirstChild();
			
			root.appendChild(createCurrency(doc, currency.toUpperCase(), rate ));
			
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(xmlFilePath));
			transformer.transform(source, result);	
			
		} catch (Exception e) {
			System.out.println("Sorry, the system has encountered an unexpected error at Delete Entry");
			System.exit(0);
		}
	}
	
	
	
	public void deleteEntry(String input) {
		try {
			
			File xmlfile = new File(xmlFilePath);
			
			DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder dB = dBF.newDocumentBuilder();
			Document doc = dB.parse(xmlfile);
			
			Node root = doc.getFirstChild();
			
			NodeList list = doc.getElementsByTagName("Currency");
			
			
			for(int i = 0; i <list.getLength(); i++) {
	
				Node node =  (Node) list.item(i);
				
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					
					Element element = (Element) node;
					
					if(element.getElementsByTagName("name").item(0).getTextContent().toLowerCase().equals(input.toLowerCase())) {
					root.removeChild(node);
					break;
					}
				}
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(xmlFilePath));
			transformer.transform(source, result);	
			
		} catch (Exception e) {
			System.out.println("Sorry, the system has encountered an unexpected error at Delete Entry");
			System.exit(0);
		}
	}
	
	public void modifyEntry(String input, String newValue) {
		try {
			File xmlfile = new File(xmlFilePath);
			
			DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder dB = dBF.newDocumentBuilder();
			Document doc = dB.parse(xmlfile);
			
			NodeList list = doc.getElementsByTagName("Currency");
			
			
			for(int i = 0; i <list.getLength(); i++) {
	
				Node node =  (Node) list.item(i);
				
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					
					Element element = (Element) node;
					
					if(element.getElementsByTagName("name").item(0).getTextContent().toLowerCase().equals(input.toLowerCase())) {
					element.getElementsByTagName("rate").item(0).setTextContent(newValue);
					break;
					}
				}
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(xmlFilePath));
			transformer.transform(source, result);	
			
		} catch (Exception e) {
			System.out.println("Sorry, the system has encountered an unexpected error at Delete Entry");
			System.exit(0);
		}
	}

	public boolean exists(String input) {
		boolean output = false;
		try {
			File xmlfile = new File(xmlFilePath);
			
			DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder dB = dBF.newDocumentBuilder();
			Document doc = dB.parse(xmlfile);

			NodeList list = doc.getElementsByTagName("Currency");

			for(int i = 0; i <list.getLength(); i++) {
				
				Node node =  (Node) list.item(i);
				
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					
					Element element = (Element) node;
					
					if(element.getElementsByTagName("name").item(0).getTextContent().toLowerCase().equals(input.toLowerCase())) {
					output = true;
					break;
					}
				}
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(xmlFilePath));
			transformer.transform(source, result);	
			
		}catch (Exception e) {
			System.out.println("Sorry, the system has encountered an unexpected error at Entry Exists");
			System.exit(0);
		}
	return output;
	}
	
	
	public String getRate(String input) {	
		String output = "";
		try {
			File xmlfile = new File(xmlFilePath);
			DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder dB = dBF.newDocumentBuilder();
			Document doc = dB.parse(xmlfile);
			
			NodeList list = doc.getElementsByTagName("Currency");
			
			for(int i = 0; i <list.getLength(); i++) {
				
				Node node =  (Node) list.item(i);
				
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					
					Element element = (Element) node;
					if(element.getElementsByTagName("name").item(0).getTextContent().toLowerCase().equals(input.toLowerCase())) {
						output = element.getElementsByTagName("rate").item(0).getTextContent();
						break;
						}
					}
				}
			
		}catch (Exception e) {
			System.out.println("Sorry, the system has encountered an unexpected error at getRate.");
			System.exit(0);
		}
	return output;
	}
}
