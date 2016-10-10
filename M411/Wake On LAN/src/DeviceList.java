import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Iterator;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class DeviceList {

    public static int maxDevices = 50;
    public static String fileLocation = "/Users/Fabian/Documents/GitHub/fabian/M411/Wake On LAN/out/Konfiguration/config.xml";
    private HashMap<String, Device> deviceList = new HashMap<>();

    public DeviceList(){
        readFile();
    }

    public boolean addDevice(Device device){
        if(deviceList.containsKey(device.getName())){
            return false;
        } else {
            deviceList.put(device.getName(), device);
            String test = device.getName();
            System.out.println(test);
            return true;
        }
    }

    public void readFile (){
        String[] readArray = new String[maxDevices * 2];
        int arrayPoint = 0;
        try {
            File inputFile = new File(fileLocation);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("Person");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    readArray[arrayPoint] = eElement.getAttribute("Name");
                    String deviceKey = eElement.getAttribute("Name");
                    arrayPoint ++;
                    NodeList personList = eElement.getElementsByTagName("Mac");

                    for (int i = 0; i < personList.getLength(); i++) {

                        String deviceValue = personList.item(i).getTextContent();
                        if (Device.isMacValid(deviceValue)){
                            Device pDevice = new Device(deviceKey, deviceValue);
                            deviceList.put(deviceKey, pDevice);
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();

        }
    }

    public boolean safePerson(String namePerson, String macPerson){
        try {
            File inputFile = new File(fileLocation);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("Person");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if (eElement.getAttribute("Name").equals(namePerson) == true){					//IF Person already exists in file
                        return false;
                    }
                }
                else{
                    return false;
                }
            }
            newPerson(namePerson,macPerson,doc,doc.getDocumentElement());
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean deletePerson(String oldName, String oldMac){
        try {
            File inputFile = new File(fileLocation);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("Person");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    if (eElement.getAttribute("Name").equals(oldName) == true){					//IF Person already exists in file
                        eElement.getParentNode().removeChild(eElement);
                        try {
                            TransformerFactory transformerFactory = TransformerFactory.newInstance();
                            Transformer transformer = transformerFactory.newTransformer();
                            DOMSource source = new DOMSource(doc);
                            StreamResult result = new StreamResult(new File(fileLocation));
                            transformer.transform(source, result);
                            return true;
                        }
                        catch (TransformerException tfe) {
                            tfe.printStackTrace();
                            return false;
                        }
                    }
                }
                else{
                    return false;
                }
            }
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    public void newPerson(String personName, String macInput, Document doc, Element rootElement){
        Element person = doc.createElement("Person");
        rootElement.appendChild(person);
        person.setAttribute("Name", personName);

        Element macAddress = doc.createElement("Mac");
        macAddress.appendChild(doc.createTextNode(macInput));
        person.appendChild(macAddress);
        try{
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(fileLocation));
            transformer.transform(source, result);
        }
        catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    public String[] getNames(){
        Iterator<String> iterator = deviceList.keySet().iterator();
        String[] nameArray = new String[deviceList.size()];
        int count = 0;

        while (iterator.hasNext()) {
            String key = iterator.next();
            nameArray[count] = key;

            count++;
        }
        return nameArray;
    }

    public byte[] getMacAddress(String key){
        System.out.println(key);
        Device test = deviceList.get(key);
        if (test != null){
            String macAddress = test.getMac();
            System.out.println(macAddress);
            byte[] byteArray = new byte[6];
            if (Device.isMacValid(macAddress)){
                byteArray = Device.getMacArray(macAddress);
                return byteArray;
            }
            else{
                byteArray = null;
                return byteArray;
            }
        }
        else {
            return null;
        }
    }

    public String getMacAddressString(String key){
        System.out.println(key);
        Device test = deviceList.get(key);
        if (test != null){
            String macAddress = test.getMac();
            System.out.println(macAddress);

            if (Device.isMacValid(macAddress)){
                return macAddress;
            }
            else{
                return null;
            }
        }
        else {
            return null;
        }
    }
}
