package org.example.repository;
import org.example.model.Book;
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

public class BookXmlRepo extends InMemoryRepository<Long, Book> {

    private String Book;


    public BookXmlRepo(Validator<Book> validator, String Book)  {
        super(validator);
        this.Book = Book;

        loadData();

    }


    private void loadData() {
        String BookPath = org.example.repository.Paths.BOOKXMLPATH.getPath();
        Path path = Paths.get(BookPath);
        try {

            List<Book> allBooks = new ArrayList<>();


            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
            Document document = docBuilder.parse("./src/main/resources/book.xml");
            Element bookStoreElement = document.getDocumentElement();

            NodeList nodeList = bookStoreElement.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {

                Node bookNode = nodeList.item(i);
                if (!(bookNode instanceof Element)) {
                    continue;
                }

                Book book = createBook(bookNode);

                allBooks.add(book);
                super.save(book);


            }
        } catch (ParserConfigurationException | IOException | SAXException ex){
            ex.printStackTrace();
        }


    }

    private static Book createBook(Node bookNode) {


        Element bookElement = (Element) bookNode;

        String id = bookElement.getAttribute("id");
        Book book1 = new Book();
        book1.setId(Long.valueOf(id));

        String titlu = getTextContent(bookElement, "titlu");
        book1.setTitlu(titlu);

        String autor = getTextContent(bookElement, "autor");
        book1.setAutor(autor);

        float pret = Float.parseFloat(getTextContent(bookElement, "pret"));
        book1.setPret(pret);

        String editura = getTextContent(bookElement, "editura");
        book1.setEditura(editura);

        return book1;
    }

    private static String getTextContent(Element bookElement, String tagName) {
        NodeList nodeList = bookElement.getElementsByTagName(tagName);
        Node node = nodeList.item(0);
        return node.getTextContent();

    }

    @Override
    public Optional<Book> save(Book entity) {
        try {
            Optional<Book> optional = super.save(entity);
            if (optional.isPresent()) {
                return optional;
            }
            saveBookToXmlFile(entity);

        } catch (ValidatorException ex){
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    private static void saveBookToXmlFile(Book book) {
      try {
          DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
          DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
          String Book = org.example.repository.Paths.BOOKXMLPATH.getPath();
          Document document = docBuilder.parse(Book);
          Element bookStoreElement = document.getDocumentElement();
          Node newBookNode = createNodeFromBook(document, book);
          bookStoreElement.appendChild(newBookNode);
          Transformer transformer = TransformerFactory.newInstance().newTransformer();
          transformer.transform(new DOMSource(bookStoreElement), new StreamResult(new FileOutputStream("./src/main/resources/book.xml")));
      }
      catch (IOException  | ParserConfigurationException | SAXException | TransformerException ex){
          ex.printStackTrace();
      }
    }

    private static Node createNodeFromBook(Document document, Book book) {
        Element bookElement = document.createElement("book");
        bookElement.setAttribute("id", String.valueOf(book.getId()));
        createAndAppendElement(document, "titlu", book.getTitlu(), bookElement);
        createAndAppendElement(document, "autor", book.getAutor(), bookElement);
        createAndAppendElement(document, "pret", String.valueOf(book.getPret()), bookElement);
        createAndAppendElement(document, "editura", book.getEditura(), bookElement);
        return bookElement;
    }

    private static void createAndAppendElement(Document document, String title, String textContent, Element bookElement) {
        Element newElement = document.createElement(title);
        newElement.setTextContent(textContent);
        bookElement.appendChild(newElement);
    }



//    private static void delete(Document document, String idBook) {
//        NodeList nodeList = document.getElementsByTagName("book");
//        for (int i = 0; i < nodeList.getLength(); i++) {
//            Element book = (Element)nodeList.item(i);
//            // <name>
//            Element id = (Element)book.getElementsByTagName("id").item(0);
//            String idBook2 = id.getTextContent();
//            if (idBook2.equals(idBook)) {
//                book.getParentNode().removeChild(book);
//
//            }
//        }
//    }



//    public void call() throws ParserConfigurationException, IOException, TransformerException, SAXException {
//        String BookPath = repository.Paths.BOOKXMLPATH.getPath();
//        Path path = Paths.get(BookPath);
//        model.Book book1= new Book();
//        String idBook = String.valueOf(book1.getId());
//
//
//        List<Book> allBooks = new ArrayList<>();
//
//        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
//        Document document = docBuilder.parse("./src/main/resources/book.xml");
//        Element bookStoreElement = document.getDocumentElement();
////        delete(document, String.valueOf(book.getId()));
//        NodeList nodeList = bookStoreElement.getChildNodes();
//        NodeList nodeList1 = document.getElementsByTagName("book");
//        for (int i = 0; i < nodeList1.getLength(); i++) {
//            Element book = (Element) nodeList1.item(i);
//            // <name>
//            Element id = (Element) book.getElementsByTagName("id").item(0);
//            String idBook2 = id.getTextContent();
//            if (idBook2.equals(idBook)) {
//                book.getParentNode().removeChild(book);
//            }
////            allBooks.remove(idBook);
//        }
//        }
    }








