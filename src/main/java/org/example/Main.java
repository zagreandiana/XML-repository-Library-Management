package org.example;


import org.example.Validari.BookValidator;
import org.example.Validari.ClientValidator;
import org.example.Validari.TranzactieValidator;
import org.example.Validari.Validator;
import org.example.model.Book;
import org.example.model.Client;
import org.example.model.Tranzactie;
import org.example.repository.*;
import org.example.service.BookService;
import org.example.service.ClientService;
import org.example.service.TranzactieService;
import org.example.ui.Console;


public class Main {
    public static void main(String[] args) {


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
    }
}