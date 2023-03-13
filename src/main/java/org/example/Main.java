package org.example;


import org.example.Validari.BookValidator;
import org.example.Validari.ClientValidator;
import org.example.Validari.TranzactieValidator;
import org.example.Validari.Validator;
import org.example.model.Book;
import org.example.model.Client;
import org.example.model.Tranzactie;
import org.example.repository.*;
import org.xml.sax.SAXException;
import org.example.service.BookService;
import org.example.service.ClientService;
import org.example.service.TranzactieService;
import org.example.ui.Console;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException, ParserConfigurationException, IOException, TransformerException, SAXException {


        //in file repo
//        Validator<Book> bookValidator = new BookValidator();
//        IRepository<Long, Book> bookRepository = new BookFileRepository(bookValidator, "./src/main/resources/Book");
//        BookService bookService = new BookService(bookRepository);
//
//
//        Validator<Client> clientValidator = new ClientValidator();
//        IRepository<Long, Client> clientRepository = new ClientFileRepository(clientValidator, "./src/main/resources/Client");
//        ClientService clientService = new ClientService(clientRepository);
//
//        Validator<Tranzactie> tranzactieValidator = new TranzactieValidator();
//        IRepository<Long, Tranzactie> tranzactieRepository = new TranzactieFileRepository(tranzactieValidator, "./src/main/resources/Tranzactie");
//        TranzactieService tranzactieService = new TranzactieService(tranzactieRepository);


        //xml repo
        Validator<Book> bookValidator = new BookValidator();
        IRepository<Long, Book> bookRepository = new BookXmlRepo(bookValidator, Paths.BOOKXMLPATH.getPath());
        BookService bookService = new BookService(bookRepository);


        Validator<Client> clientValidator = new ClientValidator();
        IRepository<Long, Client> clientRepository = new ClientXmlRepo(clientValidator, Paths.CLIENTXMLPATH.getPath());
        ClientService clientService = new ClientService(clientRepository);

        Validator<Tranzactie> tranzactieValidator = new TranzactieValidator();
        IRepository<Long, Tranzactie> tranzactieRepository = new TranzactieXmlRepo(tranzactieValidator, Paths.TRANZACTIEXMLPATH.getPath());
        TranzactieService tranzactieService = new TranzactieService(tranzactieRepository);



        Console console = new Console(bookService, clientService, tranzactieService);
        try{
            console.runConsole();

        }catch (RuntimeException e){
            e.printStackTrace();
            System.out.println("Unknown error");

        }






        //In Memory Repo
//        Validator<Book> bookValidator = new BookValidator();
//        IRepository<Long, Book> bookRepository = new InMemoryRepository<>(bookValidator);
//        BookService bookService = new BookService(bookRepository);
////        BookFileRepository bookFileRepository = new BookFileRepository(bookV)
//
//
//        Validator<Client> clientValidator = new ClientValidator();
//        IRepository<Long, Client> clientRepository = new InMemoryRepository<>(clientValidator);
//        ClientService clientService = new ClientService(clientRepository);
//
//        Validator<Tranzactie> tranzactieValidator = new TranzactieValidator();
//        IRepository<Long, Tranzactie> tranzactieRepository = new InMemoryRepository<>(tranzactieValidator);
//        TranzactieService tranzactieService = new TranzactieService(tranzactieRepository);
//
//        Console console = new Console(bookService, clientService, tranzactieService);
//        console.runConsole();


//        IRepository<BookFileRepository> bookIRepository = new InMemoryRepository<>();
//        BookValidator bookValidator = new BookValidator();
//        BookService bookService= new BookService(bookIRepository, bookValidator);
//
//        IRepository<ClientFileRepository> clientIRepository = new InMemoryRepository<>();
//        ClientValidator clientValidator = new ClientValidator();
//        ClientService clientService= new ClientService(clientIRepository, clientValidator);
//
//        IRepository<Tranzactie> tranzactieIRepository = new InMemoryRepository<>();
//        TranzactieValidator tranzactieValidator = new TranzactieValidator();
//        TranzactieService tranzactieService= new TranzactieService(tranzactieIRepository, bookIRepository,clientIRepository, tranzactieValidator);
//
//
//        Console console=new Console(bookService, clientService, tranzactieService);
//
//        console.runMenu();

//        System.out.println("bye");
    }
}