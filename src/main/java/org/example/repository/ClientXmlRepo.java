package org.example.repository;
import org.example.model.Client;
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

public class ClientXmlRepo extends InMemoryRepository<Long, Client> {

    private String client;


    public ClientXmlRepo(Validator<Client> validator, String client) {
        super(validator);
        this.client = client;

        loadData();
    }


    private void loadData() {
        String ClientPath = org.example.repository.Paths.CLIENTXMLPATH.getPath();
        Path path = Paths.get(ClientPath);
        try {

            List<Client> allClients = new ArrayList<>();

            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document document = docBuilder.parse("./src/main/resources/client.xml");
            Element clientListElement = document.getDocumentElement();

            NodeList nodeList = clientListElement.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {

                Node clientNode = nodeList.item(i);
                if (!(clientNode instanceof Element)) {
                    continue;
                }

                Client client = createClient(clientNode);
                allClients.add(client);
                super.save(client);
            }
        } catch (ParserConfigurationException | IOException | SAXException ex){
            ex.printStackTrace();
        }

    }

    private static Client createClient(Node clientNode) {


        Element clientElement = (Element) clientNode;

        String id = clientElement.getAttribute("id");
        Client client1 = new Client();
        client1.setId(Long.valueOf(id));

        String cnp = getTextContent(clientElement, "cnp");
        client1.setCNP(cnp);

        String nume = getTextContent(clientElement, "nume");
        client1.setNume(nume);

        String prenume = getTextContent(clientElement, "prenume");
        client1.setPrenume(prenume);

        String adresa = getTextContent(clientElement, "adresa");
        client1.setAdresa(adresa);

        int anulNasterii = Integer.parseInt(getTextContent(clientElement, "anulNasterii"));
        client1.setAnulNasterii(anulNasterii);

        return client1;
    }

    private static String getTextContent( Element clientElement, String tagName) {
        NodeList nodeList = clientElement.getElementsByTagName(tagName);
        Node node = nodeList.item(0);
        return node.getTextContent();

    }

    @Override
    public Optional<Client> save(Client entity) {
       try {
           Optional<Client> optional = super.save(entity);
           if (optional.isPresent()) {
               return optional;
           }
           saveClientToXmlFile(entity);
       } catch (ValidatorException ex){
           ex.printStackTrace();
       }
        return Optional.empty();
    }

    private static void saveClientToXmlFile(Client client) {
       try {
           DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
           DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
           String Client = org.example.repository.Paths.CLIENTXMLPATH.getPath();
           Document document = docBuilder.parse(Client);
           Element clientListElement = document.getDocumentElement();
           Node newClientNode = createNodeFromClient(document, client);
           clientListElement.appendChild(newClientNode);
           Transformer transformer = TransformerFactory.newInstance().newTransformer();
           transformer.transform(new DOMSource(clientListElement), new StreamResult(new FileOutputStream("./src/main/resources/client.xml")));
       } catch (IOException  | ParserConfigurationException | SAXException | TransformerException ex) {
           ex.printStackTrace();
       }
    }

    private static Node createNodeFromClient(Document document, Client client) {
        Element clientElement = document.createElement("client");
        clientElement.setAttribute("id", String.valueOf(client.getId()));
        createAndAppendElement(document, "cnp", client.getCnp(), clientElement);
        createAndAppendElement(document, "nume", client.getNume(), clientElement);
        createAndAppendElement(document, "prenume", client.getPrenume(), clientElement);
        createAndAppendElement(document, "adresa", client.getAdresa(), clientElement);
        createAndAppendElement(document, "anulNasterii", String.valueOf(client.getAnulNasterii()), clientElement);
        return clientElement;
    }

    private static void createAndAppendElement(Document document, String title, String textContent, Element clientElement) {
        Element newElement = document.createElement(title);
        newElement.setTextContent(textContent);
        clientElement.appendChild(newElement);
    }

}

