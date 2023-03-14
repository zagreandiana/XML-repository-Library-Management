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


    public BookService(IRepository<Long, Book> repository) {
        this.repository = repository;

    }


    public void addBook(Book book) throws ValidatorException, ParserConfigurationException, IOException, TransformerException, SAXException {
        repository.save(book);
    }

    public void deleteBook(String id) {
        this.repository.delete(Long.valueOf(id));


    }

    public Set<Book> getAllB() {
        Iterable<Book> books = repository.findAll();
        return StreamSupport.stream(books.spliterator(),false).collect(Collectors.toSet());
    }


    public Set<Book> filterBooksByTitle(String input) {
        Iterable<Book> books = repository.findAll();

        Set<Book> filteredBooks= new HashSet<>();
        books.forEach(filteredBooks::add);
        filteredBooks.removeIf(book -> !book.getTitlu().contains(input));
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

// the classic variant for increasingBooksPrice method

//    public Set<Book> getListaCartiScumpite(float pragValoare, float procentaj) {
//        float noulPret = 1F;
//        for (Book book : this.getAllB()) {
//            if (book.getPret() > pragValoare) {
//                noulPret = book.getPret() + (book.getPret() * procentaj) / 100;
//                book.setPret(noulPret);
//            }
//        }
//        return getAllB();
//    }


//   increasingBooksPrice method with stream

    public Set<Book> increasingBooksPrice(float pragValoare, float procentaj) {
        float procentajFactor = 1F + (procentaj / 100F);
        Set<Book> allBooks = getAllB().stream()
                .filter(book -> book.getPret() > pragValoare)
                .peek(book -> book.setPret(book.getPret() * procentajFactor))
                .collect(Collectors.toSet());
        return allBooks;
    }
}

