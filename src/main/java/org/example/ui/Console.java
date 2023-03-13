package org.example.ui;
import org.example.Exceptie.Exceptie;
import org.example.Validari.ValidatorException;
import org.example.model.Book;
import org.example.model.Client;
import org.example.model.Tranzactie;
import org.xml.sax.SAXException;
import org.example.service.BookService;
import org.example.service.ClientService;
import org.example.service.TranzactieService;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.*;

public class Console {

    private BookService bookService;
    private ClientService clientService;
    private TranzactieService tranzactieService;




    Scanner scanner = new Scanner(System.in);

    public Console(BookService bookService, ClientService clientService, TranzactieService tranzactieService) {
        this.bookService = bookService;
        this.clientService = clientService;
        this.tranzactieService = tranzactieService;



    }

    public void runConsole() throws ParseException, ParserConfigurationException, IOException, TransformerException, SAXException {
        while (true) {
            this.printMeniu();

            String Option = scanner.next();
//            String target = scanner.next();
            if (Option.equals("x") ) {
                break;
            }
            switch (Option) {
                case "1":
                    this.subMenu1();
                    String target = scanner.next();
                    switch (target) {
                        case "1":
                            handleAddBooks();
                            break;
                        case "2":
                            handleAddClients();
                            break;
                        case "3":
                            handleAddTranzactions();
                            break;
                    }
                    break;
                case "2": subMenu2();
                    String target2 = scanner.next();
                    switch (target2) {
                        case "1":
                            handlePrintAllBooks();
                            break;
                        case "2":
                            handlePrintAllClients();
                            break;
                        case "3":
                            handlePrintAllTranzactions();
                            break;
                    }
                    break;
                case "3":
                    this.subMenu3();
                    String target3 = scanner.next();
                    switch (target3) {
                        case "1":
                            handleFilterBooks();
                            break;
                        case "2":
                            handleFilterClients();
                            break;
                        case "3":
                            handleFilterTranzactions();
                            break;
                    }
                    break;
                case "4":
                    this.subMenu4();
                    String target4 = scanner.next();
                    switch (target4) {
                        case "1":
                            handleDeleteBook();
                            break;
                        case "2":
                            handleDeleteClients();
                            break;
                        case "3":
                            handleDeleteTranzactions();
                            break;
                        case "4":
                            handleUpdateBook();
                            break;
                    }
                    break;
                case "5":
                    this.subMenu5();
                    String target5 = scanner.next();
                    switch (target5) {
                        case "1":
                            handleCartiScumpite();
                            break;
                        case "2":
                            handleTheYoungestClient();
                            break;
                        case "3":
                            handleYC();
                            break;
                        case "4":
                            handleMostExpensiveBook();
                            break;
                    }
                    break;
//                case "6":
//                    this.subMenu6();
//                    String target6 = scanner.next();
//                    switch (target6) {
//                        case "1":
//                            handleBookXml();
//                            break;
//                        case "2":
//                            handleClientXml();
//                            break;
//                        case "3":
//                            handleTranzactieXml();
//                            break;
//                    }
//                    break;


                default:
                    System.out.println("Invalid option");
            }
            break;
        }

    }

//    private  void handleClientXml() throws ParserConfigurationException, IOException, TransformerException, SAXException{
//        clientXmlRepo.xmlClient();
//    }
//    private void handleBookXml() throws ParserConfigurationException, IOException, TransformerException, SAXException {
//        bookXmlRepo.xmlBook();
//
//    }
//
//    private void handleTranzactieXml() throws ParserConfigurationException, IOException, TransformerException, SAXException {
//        tranzactieXmlRepo.xmlTranzactie();
//
//    }
    private void handleMostExpensiveBook() {
        bookService.mostExpensiveBook();

    }
    private void handleYC(){
        Client client2 = clientService.youngClinet2();
        System.out.println(client2);
    }
    private void handleTheYoungestClient(){

        Client clientT = clientService.youngClient();
        System.out.println("Cel mai tanar client este:" + clientT );
    }



    private void handleDeleteClients() {
        try {
            System.out.println("id client");
            String id = scanner.next();
            this.clientService.deleteClient(id);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleDeleteTranzactions() {
        try {
            System.out.println("id tranzactie");
            String id = scanner.next();
            this.tranzactieService.deleteTranzactie(id);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleDeleteBook() {
        try {
            System.out.println("id book");
            String id = scanner.next();
            this.bookService.deleteBook(id);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }


//
//    private void handleDeleteTranzactie() {
//        try {
//            System.out.println("Dati id-ul tranzactiei:");
//            String id = this.scanner.next();
//            this.tranzactieService.deleteTranzactie(id);
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//        }
//    }


    private void handleFilterBooks() {
        System.out.println("filtered books (name containing 'a'):");
        Set<Book> books = bookService.filterBooksByTitle("a");
        books.stream().forEach(System.out::println);
    }

    private void handleFilterClients() {
        System.out.println("filtered clients (name containing 'o'):");
        Set<Client> clients = clientService.filterClientsByName("o");
        clients.stream().forEach(System.out::println);
    }

    private void handleFilterTranzactions() {
        System.out.println("filtered tranzactions (nr of pieces 1):");
        Set<Tranzactie> tranzactions = tranzactieService.filterTranzactieByNrPieces(1);
        tranzactions.stream().forEach(System.out::println);
    }

    private void handlePrintAllBooks() {
        Set<Book> books = bookService.getAllB();
        books.stream().forEach(System.out::println);
    }

    private void handlePrintAllClients() {
        Set<Client> clients = clientService.getAllC();
        clients.stream().forEach(System.out::println);
    }

    private void handlePrintAllTranzactions() {
        Set<Tranzactie> tranzactions = tranzactieService.getAllT();
        tranzactions.stream().forEach(System.out::println);
    }

    private void handleAddBooks() {
        while (true) {
            Book book = readBook();
            if (book == null || book.getId() < 0) {
                break;
            }
            try {
                bookService.addBook(book);
            } catch (ValidatorException | ParserConfigurationException | IOException | TransformerException |
                     SAXException e) {
                e.printStackTrace();
            }
        }
    }


//        bookService.updateBook();

    private void handleUpdateBook() {
        try {
            System.out.println("Introduceti id-ul cartii: ");
            String id = this.scanner.next();

            System.out.println("Introduceti titlul cartii: ");
            String titlu = this.scanner.next();

            System.out.println("Introduceti autorul cartii: ");
            String autor = this.scanner.next();


            System.out.println("Introduceti pretul: ");
            float pret = Float.parseFloat(this.scanner.next());

            System.out.println("Introduceti editura: ");
            String editura = this.scanner.next();

            this.bookService.updateBook(titlu, autor, pret, editura);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }




    private void handleAddClients() {
        while (true) {
            Client client = readClient();
            if (client == null || client.getId() < 0) {
                break;
            }
            try {
                clientService.addClient(client);
            } catch (ValidatorException | ParserConfigurationException | IOException | TransformerException |
                     SAXException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleAddTranzactions() {
        while (true) {
            Tranzactie tranzactie = readTranzactie();
            if (tranzactie == null || tranzactie.getId() < 0) {
                break;
            }
            try {
                tranzactieService.addTranzactie(tranzactie);
            } catch (ValidatorException | ParserConfigurationException | IOException | TransformerException |
                     SAXException e) {
                e.printStackTrace();
            }
        }
    }

        private Client readClient() {
            System.out.println("Read client {id, cnp, nume, prenume, adresa, anulNasterii}");
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            try {
                Long id = Long.valueOf(bufferRead.readLine());// ...
                String cnp = bufferRead.readLine();
                String nume = bufferRead.readLine();
                String prenume = bufferRead.readLine();
                String adresa = bufferRead.readLine();
                int anulNasterii = Integer.parseInt(bufferRead.readLine());
                Client client = new Client(cnp, nume, prenume, adresa, anulNasterii);
                client.setId(id);
                return client;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;
        }

        private Book readBook() {
            System.out.println("Read book {id, titlu, autor, pret, editura}");
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            try {
                Long id = Long.valueOf(bufferRead.readLine());// ...
                String titlu = bufferRead.readLine();
                String autor = bufferRead.readLine();
                float pret = Float.parseFloat(bufferRead.readLine());
                String editura = bufferRead.readLine();// ...
                Book book = new Book(titlu, autor, pret, editura);
                book.setId(id);
                return book;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;
        }

        private Tranzactie readTranzactie() {
            System.out.println("Read tranzaction {id, id_book, id_client, nrBucati, dataSiOra}");
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            try {
                Long id = Long.valueOf(bufferRead.readLine());// ...
                String id_book = bufferRead.readLine();
                String id_client = bufferRead.readLine();
                int nrBucati = Integer.parseInt(bufferRead.readLine());
                String dataSiOra = bufferRead.readLine();// ...
                Tranzactie tranzactie = new Tranzactie(id_book, id_client, nrBucati, dataSiOra);
                tranzactie.setId(id);
                return tranzactie;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;
        }

        private void handleCartiScumpite() {
            try {
                System.out.println("Introduceti prajul de valoare peste care vor fi scumpite cartile: ");
                float pragValoare = Float.parseFloat(this.scanner.next());
                System.out.println("Introduceti procentajul cu care vor fi scumpite cartile %: ");
                float procentaj = Float.parseFloat(this.scanner.next());
                System.out.println(bookService.getListaCartiScumpite(pragValoare, procentaj));

            } catch (Exceptie e) {
                System.out.println(e.getMessage());
            }
        }



        private void printMeniu(){
            System.out.println(
                    "1-Add book/cliet/tranzaction\n"+
                            "2-Print book/client/tranzaction\n"+
                            "3-Filter book/client/tranzaction\n"+
                            "4-Delete book/client/tranzaction\n"+
                            "5-Raports\n"+
                            "6-Xml book/clients\n"+
                            "x-Exit"
            );
        }

        private void subMenu1(){
            System.out.println("1-Add book\n"+
                    "2-Add client\n"+
                    "3-Add tranzaction\n"+
                    "x-Exit");
        }

        private void subMenu2(){
            System.out.println("1-Show books\n"+
                    "2-Show clients\n"+
                    "3-Show tranzactions\n"+
                    "x-Exit");
        }

        private void subMenu3(){
            System.out.println("1-Filer books \n"+
                    "2-Filer clients\n"+
                    "3-Filer tranzactions\n"+
                    "x-Exit");
        }

        private void subMenu4(){
            System.out.println("1-Delete book\n"+
                    "2-Delete client\n"+
                    "3-Delete transaction\n"+
                    "4-Update book\n"+
                    "x-Exit");
        }

        private void subMenu5(){
            System.out.println("1-Increase price book\n"+
                    "2-Cel mai tanar client\n"+
                    "3-Cel mai tanar client Iterator\n"+
                    "4-Most expensive book\n"+
                    "x-Exit");
        }

        private void subMenu6(){
            System.out.println("1-Book XML" +"\n"+
                    "2-Client XML" +"\n"+
                    "3-Tranzactie XML" +"\n"+
                    "x-Exit");
        }

//    private void printMeniu(){
//        System.out.println("1-Add book\n"+
//                "2-Add client\n"+
//                "3-Add tranzaction\n"+
//                "4-Show books\n"+
//                "5-Show clients\n"+
//                "6-Show tranzactions\n"+
//                "7-Filer books \n"+
//                "8-Filer clients\n"+
//                "9-Filer tranzactions\n"+
//                "10-Delete book\n"+
//                "11-Delete client\n"+
//                "12-Delete transaction\n"+
//                "13-Update book\n"+
//                "14-Increase price book\n"+
//                "15-Cel mai tanar client\n"+
//                "16-Book XML" +"\n"+
//                "17-Client XML" +"\n"+
//                "x-Exit");
//    }





//    private BookService bookService;
//    private ClientService clientService;
//    private TranzactieService tranzactieService;
//    Scanner scanner = new Scanner(System.in);
//
//    public Console(BookService bookService, ClientService clientService, service.TranzactieService tranzactieService) {
//        this.bookService = bookService;
//        this.clientService = clientService;
//        this.tranzactieService = tranzactieService;
//    }
//
//    public void runMenu() throws ParseException {
//        while (true) {
//            this.printMenu();
//            String option = scanner.next();
//            if (option.equals("x")) {
//                break;
//            }
//            switch (option) {
//                case "1":
//                    afisareCarti();
//                    break;
//                case "2":
//                    handleAddBook();
//                    break;
//                case "3":
//                    handleUpdateBook();
//                    break;
//                case "4":
//                    handleDeleteBook();
//                    break;
//                case "5":
//                    handleSearchInBooks();
//                    break;
//                case "6":
//                    afisareClienti();
//                    break;
//                case "7":
//                    handleAddClient();
//                    break;
//                case "8":
//                    handleUpdateClient();
//                    break;
//                case "9":
//                    handleDeleteClient();
//                    break;
//                case "10":
//                    handleSearchInClients();
//                    break;
//                case "11":
//                    afisareTranzactii();
//                    break;
//                case "12":
//                    handleAddTranzactie();
//                    break;
//                case "13":
//                    handleUpdateTranzactie();
//                    break;
//                case "14":
//                    handleDeleteTranzactie();
//                    break;
//                case "15":
//                    handleSearchInTranzactii();
//                    break;
//                case "16":
//                    handleCartiScumpite();
//                    break;
//                case "17":
//                    handleCartiCuNrTranzactii();
//                    break;
//                case "18":
//                    handleAfisareTranzactiiPeInterval();
//                    break;
//
//            //    case "18":
//             //       handleCartiOrdonate();
//             //       break;
//
//                default:
//                    System.out.println("aceasta optiune nu este implementata");
//            }
//
//        }
//    }
//
//
//    private void handleAddBook() {
//        try {
//            System.out.println("Introduceti id-ul cartii: ");
//            String id = scanner.next();
//
//            System.out.println("Introduceti titlul cartii: ");
//            String titlu = scanner.next();
//
//            System.out.println("Introduceti autorii cartii: ");
//            ArrayList<String> autori = new ArrayList<>();
//            //   while(scanner.hasNext()){
//            //      autori.add(scanner.next());
//            while (true) {
//                String next = scanner.next();
//                if (next.equals("x")) {
//                    break;
//                }
//                autori.add(scanner.next());
//            }
//
//            System.out.println("Introduceti pretul: ");
//            float pret = Float.parseFloat(scanner.next());
//
//            System.out.println("Introduceti editura: ");
//            String editura = scanner.next();
//
//            this.bookService.addBook(id, titlu, autori, pret, editura);
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//    private void handleAddClient() {
//        try {
//            System.out.println("Introduceti CNP-ul dumneavoastra:  ");
//            String cnp = scanner.next();
//
//            System.out.println("Introduceti numele: ");
//            String numele = scanner.next();
//
//            System.out.println("Introduceti prenumele: ");
//            String prenumele = scanner.next();
//
//            System.out.println("Introduceti adresa dumneavoastra curenta: ");
//            ArrayList<String> adresa = new ArrayList<>();
//
//            //   while(scanner.hasNext()){
//            //      autori.add(scanner.next());
//
//            while (true) {
//                String next = scanner.next();
//                if (next.equals("x")) {
//                    break;
//                }
//                adresa.add(scanner.next());
//            }
//
//            System.out.println("Introduceti anul nasterii: ");
//            String anulNasterii = scanner.next();
//
//
//            this.clientService.addClient(cnp,numele,prenumele, adresa, anulNasterii);
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//    private void handleAddTranzactie() {
//        try {
//            System.out.println("Introduceti id tranzactiei:  ");
//            String id = scanner.next();
//
//            System.out.println("Introduceti id_book-ul: ");
//            String id_book = scanner.next();
//
//            System.out.println("Introduceti id_client-ului: ");
//            String id_client = scanner.next();
//
//            System.out.println("Introduceti nr de bucati: ");
//            int nrBucati = Integer.parseInt(scanner.next());
//
//            System.out.println("Introduceti data si ora tranzactiei in format yyyy-MM-dd HH:mm : ");
//            String dataSiOra = scanner.next();
//
//
//            this.tranzactieService.addTranzactie(id, id_book, id_client, nrBucati, dataSiOra);
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//
//    private void afisareCarti() {
//        for (Book book : this.bookService.getAllB()) {
//            System.out.println(book);
//        }
//    }
//    private void afisareClienti() {
//        for (Client client : this.clientService.getAllC()) {
//            System.out.println(client);
//        }
//    }
//    private void afisareTranzactii() {
//        for (Tranzactie tranzactie : this.tranzactieService.getAllT()) {
//            System.out.println(tranzactie);
//        }
//    }
//
//    private void handleUpdateBook() {
//        try {
//            System.out.println("Introduceti id-ul cartii: ");
//            String id = this.scanner.next();
//
//            System.out.println("Introduceti titlul cartii: ");
//            String titlu = this.scanner.next();
//
//            System.out.println("Introduceti autorii cartii = ");
//            ArrayList<String> autori = new ArrayList<>();
//            //   while(scanner.hasNext()){
//            //      autori.add(scanner.next());
//            while (true) {
//                String next = scanner.next();
//                if (next.equals("x")) {
//                    break;
//                }
//                autori.add(this.scanner.next());
//            }
//
//            System.out.println("Introduceti pretul: ");
//            float pret = Float.parseFloat(this.scanner.next());
//
//            System.out.println("Introduceti editura: ");
//            String editura = this.scanner.next();
//
//            this.bookService.updateBook(id, titlu, autori, pret, editura);
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    private void handleUpdateClient() {
//        try {
//            System.out.println("Introduceti CNP-ul dumneavoastra:  ");
//            String cnp = scanner.next();
//
//            System.out.println("Introduceti numele: ");
//            String numele = scanner.next();
//
//            System.out.println("Introduceti prenumele: ");
//            String prenumele = scanner.next();
//
//            System.out.println("Introduceti adresa dumneavoastra curenta: ");
//            ArrayList<String> adresa = new ArrayList<>();
//            //   while(scanner.hasNext()){
//            //      autori.add(scanner.next());
//            while (true) {
//                String next = scanner.next();
//                if (next.equals("x")) {
//                    break;
//                }
//                adresa.add(this.scanner.next());
//            }
//            System.out.println("Introduceti anul nasterii: ");
//            String anulNasterii = scanner.next();
//
//
//            this.clientService.updateClient(cnp,numele,prenumele, adresa, anulNasterii);
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    private void handleUpdateTranzactie() {
//        try {
//            System.out.println("Introduceti id tranzactiei:  ");
//            String id = scanner.next();
//
//            System.out.println("Introduceti id_book-ul: ");
//            String id_book = scanner.next();
//
//            System.out.println("Introduceti id_client-ului: ");
//            String id_client = scanner.next();
//
//            System.out.println("Introduceti nr de bucati: ");
//            int nrBucati = Integer.parseInt(scanner.next());
//
//            System.out.println("Introduceti data si ora tranzactiei in format yyyy-MM-dd HH:mm : ");
//            String dataSiOra = scanner.next();
//
//
//            this.tranzactieService.updateTranzactie(id, id_book, id_client, nrBucati, dataSiOra);
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//    private void handleDeleteBook() {
//        try {
//            System.out.println("Dati id-ul cartii:");
//            String id = this.scanner.next();
//            this.bookService.deleteBook(id);
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//    private void handleDeleteClient() {
//        try {
//            System.out.println("Dati cnp-ul clientului:");
//            String cnp = this.scanner.next();
//            this.clientService.deleteClient(cnp);
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//    private void handleDeleteTranzactie() {
//        try {
//            System.out.println("Dati id-ul tranzactiei:");
//            String id = this.scanner.next();
//            this.tranzactieService.deleteTranzactie(id);
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//
//    private void handleSearchInBooks() {
//        try {
//            System.out.println("Introduceti textul:");
//            String input = this.scanner.next();
//            this.bookService.searchBook(input);
//            System.out.println(bookService.searchBook(input));
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    private void handleSearchInClients() {
//        try {
//            System.out.println("Introduceti textul:");
//            String input = this.scanner.next();
//            this.clientService.searchClient(input);
//            System.out.println(clientService.searchClient(input));
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//    private void handleSearchInTranzactii() {
//        try {
//            System.out.println("Introduceti textul:");
//            String input = this.scanner.next();
//            this.tranzactieService.searchTranzactie(input);
//            System.out.println(tranzactieService.searchTranzactie(input));
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    private void handleCartiScumpite(){
//        try {
//            System.out.println("Introduceti prajul de valoare peste care vor fi scumpite cartile: ");
//            float pragValoare = Float.parseFloat(this.scanner.next());
//            System.out.println("Introduceti procentajul cu care vor fi scumpite cartile %: ");
//            float procentaj = Float.parseFloat(this.scanner.next());
////            for (MajorarePretCarti majorarePretCarti : this.bookService.getListaCartiScumpite(pragValoare, procentaj) {
////                   System.out.println(majorarePretCarti);
//            System.out.println(bookService.getListaCartiScumpite(pragValoare, procentaj));
//       //     }
//        } catch(RuntimeException e){
//            System.out.println(e.getMessage());
//        }
//
//
//    }
//
//
//    private void handleCartiCuNrTranzactii(){
//        for (CartiCuNrTranzactii cartiCuNrTranzactii : this.tranzactieService.getCartiOrdonateDescDupaNrTranzactii()){
//            System.out.println(cartiCuNrTranzactii);
//        }
//
//    }
//

    }
