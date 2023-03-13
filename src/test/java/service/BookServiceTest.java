package service;

import org.example.model.Book;
import org.example.Validari.BookValidator;
import org.example.Validari.Validator;
import org.example.Validari.ValidatorException;
import org.example.service.BookService;
import org.junit.jupiter.api.Test;
import org.example.repository.IRepository;
import org.example.repository.InMemoryRepository;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest implements Validator<Book> {

    BookValidator bookValidator = new BookValidator();



    IRepository<Long,Book> getBookRepository(){
        InMemoryRepository<Long,Book> testRepository=new InMemoryRepository<>(bookValidator);
        testRepository.save(new Book("Mara", "Ionel Teodorescu", 23, "Paralela45"));
        return testRepository;
    }

    BookValidator getBookValidator(){
        BookValidator bookValidator=new BookValidator();
        return bookValidator;
    }

    BookService getBookService(){
        IRepository<Long,Book> bookRepository=getBookRepository();
        BookValidator bookValidator=getBookValidator();
        BookService bookService=new BookService(bookRepository);
        return bookService;
    }

//    @Test
//    void addBook() {
//        BookService bookService = getBookService();
//        bookService.addBook(new Book("Mara", "Teodorescu", 45, "Paralela45"));
//        Set<Book> set=bookService.getAllB();
//        assertEquals(1,set.size());
//    }


//    @Test
//    void deleteBook() {
//        BookService bookService = getBookService();
//        bookService.addBook(new Book("Mara", "Teodorescu", 45, "Paralela45"));
//        bookService.addBook(new Book("Ion", "Liviu Rebreanu", 60, "Paralela45"));
//        bookService.deleteBook("1");
//        Set<Book> set=bookService.getAllB();
//        assertEquals(1,set.size());
//    }

    @Test
    void getAllB() {
        BookService bookService = getBookService();
        Set <Book> set = bookService.getAllB();
        assertFalse(set.isEmpty());
    }

    @Override
    public void validate(Book entity) throws ValidatorException {

    }

//    @Test
//    void updateBook() {
//        BookService bookService = getBookService();
//        bookService.updateBook("Mara", "Ionel Teodorescu", 45, "Paralela45");
//        Set<Book> set=bookService.getAllB();
//        Book book=set.contains(book.getTitlu("Mara"));
//        assertEquals("Mara",book.getTitlu());
//    }


}