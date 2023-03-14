package org.example.model;

public class Client extends BaseEntity<Long> {

    private String  cnp;
    private String  nume;
    private String  prenume;
    private String adresa;
    private int anulNasterii;



    public Client(String cnp, String nume,String prenume, String adresa, int anulNasterii){

        this.cnp=cnp;
        this.nume = nume;
        this.prenume = prenume;
        this.adresa = adresa;
        this.anulNasterii = anulNasterii;

    }

    public Client() {

    }


    public String getCnp() {return  cnp;}

    public void setCNP(String cnp) { this.cnp = cnp;}

    public String getNume() { return nume; }
    public String getPrenume() { return prenume; }

    public void setNume(String nume) { this.nume = nume; }
    public void setPrenume(String prenume) { this.prenume = prenume; }

    public String getAdresa() { return adresa; }

    public void setAdresa(String adresa) { this.adresa = adresa;}

    public int getAnulNasterii() {
        return anulNasterii;
    }

    public void setAnulNasterii(int anulNasterii){ this.anulNasterii = anulNasterii;}


    @Override
    public String toString() {
        return "Client{" +
                "cnp='" + cnp + '\'' +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", adresa=' " + adresa + '\'' +
                ", anulNasterii='" + anulNasterii + '\'' +
                '}';
    }
}
