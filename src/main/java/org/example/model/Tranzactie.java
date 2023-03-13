package org.example.model;

public class Tranzactie extends BaseEntity<Long> {

    private String id_book;
    private String id_client;
    private int nrBucati;

    private String dataSiOra;


    public Tranzactie(String id_book, String id_client, int nrBucati, String dataSiOra) {

        this.id_book = id_book;
        this.id_client = id_client;
        this.nrBucati = nrBucati;
        this.dataSiOra = dataSiOra;


    }

    public Tranzactie() {

    }


    public void setId_book(String id_book) {
        this.id_book = id_book;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    public void setNrBucati(int nrBucati) {
        this.nrBucati = nrBucati;
    }

    public void setDataSiOra(String dataSiOra) {
        this.dataSiOra = dataSiOra;
    }

    public String getId_book() {
        return id_book;
    }

    public String getId_client() {
        return id_client;
    }

    public int getNrBucati() {
        return nrBucati;
    }

    public String getDataSiOra() {
        return dataSiOra;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Tranzactie that)) return false;
//        return getNrBucati() == that.getNrBucati() && Objects.equals(getId_book(), that.getId_book()) && Objects.equals(getId_client(), that.getId_client()) && Objects.equals(getDataSiOra(), that.getDataSiOra());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getId_book(), getId_client(), getNrBucati(), getDataSiOra());
//    }

    @Override
    public String toString() {
        return "Tranzactie{" +
                "id_book='" + id_book + '\'' +
                ", id_client='" + id_client + '\'' +
                ", nrBucati=" + nrBucati +
                ", dataSiOra='" + dataSiOra + '\'' +
                '}';
    }
}


