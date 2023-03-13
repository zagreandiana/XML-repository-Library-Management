package org.example.service;
import org.example.Validari.BookValidator;
import org.example.Validari.ValidatorException;
import org.example.model.Book;
import org.xml.sax.SAXException;
import org.example.repository.IRepository;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BookService {
    private IRepository<Long, Book> repository;

    private BookValidator bookValidator;



    public BookService(IRepository<Long, Book> repository) {
//        this.bookIRepository = bookIRepository;
//        this.bookValidator = bookValidator;
        this.repository = repository;
//        this.bookFileRepository = bookFileRepository;

    }

//    public void addBook( String titlu, String autor, float pret, String editura) {
//        Book book = new Book(titlu, autor, pret, editura);
//        this.bookValidator.validateB(book);
//        this.bookIRepository.create(book);
//    }
    public void addBook(Book book) throws ValidatorException, ParserConfigurationException, IOException, TransformerException, SAXException {
        repository.save(book);
    }

    public void deleteBook(String id) {
        this.repository.delete(Long.valueOf(id));


    }

    public Set<Book> getAllB() {
//        BookFileRepository();
        Iterable<Book> books = repository.findAll();
        return StreamSupport.stream(books.spliterator(),false).collect(Collectors.toSet());
    }


    public Set<Book> filterBooksByTitle(String b) {
        Iterable<Book> books = repository.findAll();

        Set<Book> filteredBooks= new HashSet<>();
        books.forEach(filteredBooks::add);
        filteredBooks.removeIf(book -> !book.getTitlu().contains(b));
        return filteredBooks;
    }


    public void updateBook(String titlu,String autor, float pret, String editura) {
        Book book = new Book( titlu, autor, pret, editura);
        this.repository.update(book);

    }
    public  Book  mostExpensiveBook() {
        Set<Book> books = (Set<Book>) repository.findAll();

        List<Book> book2 = new ArrayList<>();
        for (Book book : books) {
            book2.add(book);
        }
        Collections.sort(book2, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return (int) ((-o1.getPret()) - (-o2.getPret()));
            }
        });

        Book bookB = book2.get(0);
        System.out.println("Most expensive book is" + bookB);

        return bookB;
    }

    public void deleteBook(Long id) {
        this.repository.delete(id);
    }

//    public String searchBook(String input) {
//        Pattern stringPattern = Pattern.compile(input);
//        String gasit = "";
//        for (Book book : bookIRepository.read()) {
//            Matcher similaritate = stringPattern.matcher(book.toString());
//            if (similaritate.find()) {
//                gasit += book.toString() + "\n";
//            }
//        }
//        return gasit;
//    }
//
//    public String searchBook(String input) {
//        return this.bookIRepository.search(input);
//    }


    public Set<Book> getListaCartiScumpite(float pragValoare, float procentaj) {
//        Map<Book, Float> mapCartiScumpite = new HashMap<>();
        float noulPret = 1F;
        float pretNeschimbat = 1F;
        List<Book> listaFinala = new ArrayList<>();
        for (Book book : this.getAllB()) {
            if (book.getPret() > pragValoare) {
                noulPret = book.getPret() + (book.getPret() * procentaj) / 100;

//                mapCartiScumpite.put(book, noulPret);
                book.setPret(noulPret);
            } else {
                pretNeschimbat = book.getPret();
//q!                mapCartiScumpite.put(book, pretNeschimbat);
                book.setPret(pretNeschimbat);
            }

        }
        return getAllB();
    }
}

