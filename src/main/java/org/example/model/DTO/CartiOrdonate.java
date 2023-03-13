package org.example.model.DTO;

import org.example.model.Book;

public class CartiOrdonate {

    private Book book;
    public float pret;

    public CartiOrdonate(Book book, float pret) {
        this.pret = this.pret;
        this.book = book;
    }

    @Override
    public String toString() {
        return "CartiOrdonate{" +
                "book=" + book +
                ", pret=" + pret +
                '}';
    }
}