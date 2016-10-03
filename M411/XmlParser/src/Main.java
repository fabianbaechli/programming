import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Main {
    public static void main (String[] args){
        try{
            File inputFile = new File("/Users/Fabian/Documents/GitHub/school/M411/XmlParser/src/input.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("Person");
            for (int i = 0; i < nList.getLength(); i++){
                Node nListNode = nList.item(i);
                System.out.println("Name of Node: " + nListNode.getNodeName());
                if (nListNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementOfNode = (Element) nListNode;
                    System.out.println("Name of Person: " + elementOfNode.getAttribute("Name"));
                    NodeList macNodes = ((Element) nListNode).getElementsByTagName("Mac");
                    System.out.println("MAC Address: " + macNodes.item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
