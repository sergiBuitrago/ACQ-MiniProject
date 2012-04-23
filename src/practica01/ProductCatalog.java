package practica01;

import java.util.Hashtable;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Classe ProductCatalog la qual representara el cataleg de productes del TPV
 * @author Nestor Torres
 */
public class ProductCatalog {
    private Hashtable productDescriptions=new Hashtable();

    /**
     * @param cup Aquest parametre ens serveix per identificar l'ID de la descripció de producte que volem obtenir
     * @return Retorna l'objecte del producte obtingut de la Hashtable de ProductCatalog
     */
    public ProductDescription getDescription(int cup){
        return (ProductDescription) productDescriptions.get(cup);
    }


    /**
     * Mètode readProducts que ens serveix per a llegir el cataleg de productes del fitxer XML catalegProductes.xml
     */
    public void readProducts(){
        System.out.print("Llegint cataleg de productes... ");
        try {
            File file = new File("catalegProductes.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeLst = doc.getElementsByTagName("ProductDescription");
            for (int s = 0; s < nodeLst.getLength(); s++) {
                Node fstNode = nodeLst.item(s);
                if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element fstElmnt = (Element) fstNode;
                    fstElmnt.setNodeValue("");
                    int cup_p;
                    String description_p;
                    float price_p;
                    int stock_p;
                    int threshold_p;

                    NodeList cupNode1 = fstElmnt.getElementsByTagName("Cup");
                    Element cupElem = (Element) cupNode1.item(0);
                    NodeList cupNode2 = cupElem.getChildNodes();
                    cup_p=Integer.parseInt(((Node) cupNode2.item(0)).getNodeValue());

                    NodeList descriptionNode1 = fstElmnt.getElementsByTagName("Description");
                    Element descriptionElem = (Element) descriptionNode1.item(0);
                    NodeList descriptionNode2 = descriptionElem.getChildNodes();
                    description_p=((Node) descriptionNode2.item(0)).getNodeValue();

                    NodeList priceNode1 = fstElmnt.getElementsByTagName("Price");
                    Element priceElem = (Element) priceNode1.item(0);
                    NodeList priceNode2 = priceElem.getChildNodes();
                    price_p=Float.valueOf(((Node) priceNode2.item(0)).getNodeValue());

                    NodeList stockNode1 = fstElmnt.getElementsByTagName("Stock");
                    Element stockElem = (Element) stockNode1.item(0);
                    NodeList stockNode2 = stockElem.getChildNodes();
                    stock_p=Integer.parseInt(((Node) stockNode2.item(0)).getNodeValue());

                    NodeList thresholdNode1 = fstElmnt.getElementsByTagName("Threshold");
                    Element thresholdElem = (Element) thresholdNode1.item(0);
                    NodeList thresholdNode2 = thresholdElem.getChildNodes();
                    threshold_p=Integer.parseInt(((Node) thresholdNode2.item(0)).getNodeValue());

                    //System.out.println("Es guardara en memoria: " + cup_p +" - " + description_p + " - " + price_p + " - " + stock_p + " - " + threshold_p);
                    ProductDescription pd=new ProductDescription(cup_p, description_p, price_p);
                    pd.setStock(stock_p);
                    pd.setThreshold(threshold_p);
                    productDescriptions.put(pd.getCup(), pd);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print("Llegit\n");
    }

    /**
     * Mètode accessor que ens serveix per saber quants productes tenim al cataleg de productes
     * @return Retorna un enter amb el tamany de la Hashtable amb la descripció dels productes
     */
    public int getQuantityProducts(){
        return productDescriptions.size();
    }


    /**
     * Mètode que ens serveix per a guardar els canvis en el fitxer XML una vegada finalitzada la compra
     */
    public void saveChanges(){
        System.out.print("Guardant cataleg de productes... ");
            try{
             String filepath = "catalegProductes.xml";
             DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
             DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
             Document doc = docBuilder.parse(filepath);
             ProductDescription pd=null;
                doc.getDocumentElement().normalize();
                NodeList nodeLst = doc.getElementsByTagName("ProductDescription");
                for (int s = 0; s < nodeLst.getLength(); s++) {
                    Node fstNode = nodeLst.item(s);
                    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element fstElmnt = (Element) fstNode;
                        NodeList cupNode1 = fstElmnt.getElementsByTagName("Cup");
                        Element cupElem = (Element) cupNode1.item(0);
                        NodeList cupNode2 = cupElem.getChildNodes();
                        pd=(ProductDescription)productDescriptions.get(Integer.parseInt(((Node) cupNode2.item(0)).getNodeValue()));
                        if(pd.getCup()==Integer.parseInt(((Node) cupNode2.item(0)).getNodeValue())){
                            NodeList stockNode1 = fstElmnt.getElementsByTagName("Stock");
                            Element stockElem = (Element) stockNode1.item(0);
                            NodeList stockNode2 = stockElem.getChildNodes();
                            ((Node) stockNode2.item(0)).setNodeValue(String.valueOf(pd.getStock()));
                        }
                    }
                }

         //Escriem al fitxer
         TransformerFactory transformerFactory = TransformerFactory.newInstance();
         Transformer transformer = transformerFactory.newTransformer();
         DOMSource source = new DOMSource(doc);
         StreamResult result =  new StreamResult(new File(filepath));
         transformer.transform(source, result);

       }catch(ParserConfigurationException pce){
             pce.printStackTrace();
       }catch(TransformerException tfe){
             tfe.printStackTrace();
       }catch(IOException ioe){
             ioe.printStackTrace();
       }catch(SAXException sae){
             sae.printStackTrace();
       }
       System.out.print("Guardat\n");
    }

    /**
     * Mètode que ens serveix per obtenir la Hashtable amb el cataleg de productes
     * @return Retorna l'objecte de tipus Hashtable amb el cataleg de productes
     */
    public Hashtable getProducts(){
        return productDescriptions;
    }
}
