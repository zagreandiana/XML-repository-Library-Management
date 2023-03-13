package org.example.model;


import java.util.Comparator;
import java.util.Objects;

public class Book extends BaseEntity<Long> {


    private String titlu;
    private String autor;
    private float pret;
    private String editura;


    public Book( String titlu, String autor, float pret, String editura){

        this.titlu = titlu;
        this.autor = autor;
        this.pret = pret;
        this.editura = editura;

    }

    public Book() {

    }


    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    public String getEditura() {
        return editura;
    }

    public void setEditura(String editura) {
        this.editura = editura;
    }

    public static Comparator<Book> getBComparator() {
        return BComparator;
    }

    public static void setBComparator(Comparator<Book> BComparator) {
        Book.BComparator = BComparator;
    }

    public static Comparator<Book> BComparator = new Comparator<Book>() {
        public int compare(Book b1, Book b2){

           float pret1=b1.getPret();
           float pret2=b2.getPret();
           return (int) (pret2-pret1);
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return Float.compare(book.getPret(), getPret()) == 0 && Objects.equals(getTitlu(), book.getTitlu()) && Objects.equals(getAutor(), book.getAutor()) && Objects.equals(getEditura(), book.getEditura());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitlu(), getAutor(), getPret(), getEditura());
    }

    @Override
    public String toString() {
        return "Book{" +
                "titlu='" + titlu + '\'' +
                ", autor='" + autor + '\'' +
                ", pret=" + pret +
                ", editura='" + editura + '\'' +
                '}';
    }
}
