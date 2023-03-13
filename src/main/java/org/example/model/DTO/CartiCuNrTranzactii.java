package org.example.model.DTO;

import org.example.model.Book;

public class CartiCuNrTranzactii {

    private Book book;
    private int nrTranzactii;

    public CartiCuNrTranzactii(Book book, int nrTranzactii){
        this.book = book;
        this.nrTranzactii = nrTranzactii;
    }

    public Book getBook() {
        return book;
    }

    public int getNrTranzactii() {
        return nrTranzactii;
    }

    @Override
    public String toString() {
        return "CartiCuNrTranzactii{" +
                "book=" + book +
                ", nrTranzactii=" + nrTranzactii +
                '}';
    }
}
