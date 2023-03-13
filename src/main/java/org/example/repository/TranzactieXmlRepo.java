package org.example.repository;
import org.example.model.Tranzactie;
import org.example.Validari.Validator;
import org.example.Validari.ValidatorException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TranzactieXmlRepo extends InMemoryRepository<Long, Tranzactie> {

    private String Tranzactie;



    public TranzactieXmlRepo(Validator<Tranzactie> validator, String Tranzactie) {
        super(validator);
        this.Tranzactie = Tranzactie;

        loadData();
    }



    private void loadData()  {
        String TranzactiePath = org.example.repository.Paths.TRANZACTIEXMLPATH.getPath();
        Path path = Paths.get(TranzactiePath);

        try {
            List<Tranzactie> allTranzactions = new ArrayList<>();

            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document document = docBuilder.parse("./src/main/resources/tranzactie.xml");
            Element tranzactieListElement = document.getDocumentElement();

            NodeList nodeList = tranzactieListElement.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {

                Node tranzactieNode = nodeList.item(i);
                if (!(tranzactieNode instanceof Element)) {
                    continue;
                }

                Tranzactie tranzactie = createTranzactie(tranzactieNode);
                allTranzactions.add(tranzactie);
                super.save(tranzactie);
            }
        }catch (ParserConfigurationException | IOException | SAXException ex){
            ex.printStackTrace();
        }

    }

    private static Tranzactie createTranzactie(Node tranzactieNode) {


        Element tranzactieElement = (Element) tranzactieNode;

        String id = tranzactieElement.getAttribute("id");
        Tranzactie tranzactie1 = new Tranzactie();
        tranzactie1.setId(Long.valueOf(id));

        String id_book = getTextContent(tranzactieElement, "id_book");
        tranzactie1.setId_book(id_book);

        String id_client = getTextContent(tranzactieElement, "id_client");
        tranzactie1.setId_client(id_client);

        int nrBucati = Integer.parseInt(getTextContent(tranzactieElement, "nrBucati"));
        tranzactie1.setNrBucati(nrBucati);

        String dataSiOra = getTextContent(tranzactieElement, "dataSiOra");
        tranzactie1.setDataSiOra(dataSiOra);

        return tranzactie1;
    }

    private static String getTextContent( Element tranzactieElement, String tagName) {
        NodeList nodeList = tranzactieElement.getElementsByTagName(tagName);
        Node node = nodeList.item(0);
        return node.getTextContent();

    }

    @Override
    public Optional<Tranzactie> save(Tranzactie entity) throws ValidatorException{
        try {
            Optional<Tranzactie> optional = super.save(entity);
            if (optional.isPresent()) {
                return optional;
            }
            saveTranzactionToXmlFile(entity);
        } catch (ValidatorException ex){
            ex.printStackTrace();
        }
        return Optional.empty();

    }

    private static void saveTranzactionToXmlFile(Tranzactie tranzactie) {
       try {
           DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
           DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
           String Tranzactie = org.example.repository.Paths.TRANZACTIEXMLPATH.getPath();
           Document document = docBuilder.parse(Tranzactie);
           Element tranzactieListElement = document.getDocumentElement();
           Node newTranzactieNode = createNodeFromTranzaction(document, tranzactie);
           tranzactieListElement.appendChild(newTranzactieNode);
           Transformer transformer = TransformerFactory.newInstance().newTransformer();
           transformer.transform(new DOMSource(tranzactieListElement), new StreamResult(new FileOutputStream("./src/main/resources/tranzactie.xml")));
       } catch (IOException  | ParserConfigurationException | SAXException | TransformerException ex){
           ex.printStackTrace();
       }
    }

    private static Node createNodeFromTranzaction(Document document, Tranzactie tranzactie) {
        Element tranzactieElement = document.createElement("tranzactie");
        tranzactieElement.setAttribute("id", String.valueOf(tranzactie.getId()));
        createAndAppendElement(document, "id_book", tranzactie.getId_book(), tranzactieElement);
        createAndAppendElement(document, "id_client", tranzactie.getId_client(), tranzactieElement);
        createAndAppendElement(document, "nrBucati", String.valueOf(tranzactie.getNrBucati()), tranzactieElement);
        createAndAppendElement(document, "dataSiOra", tranzactie.getDataSiOra(), tranzactieElement);
        return tranzactieElement;
    }

    private static void createAndAppendElement(Document document, String title, String textContent, Element tranzactieElement) {
        Element newElement = document.createElement(title);
        newElement.setTextContent(textContent);
        tranzactieElement.appendChild(newElement);
    }

}

