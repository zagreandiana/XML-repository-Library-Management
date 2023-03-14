package org.example.service;
import org.example.Validari.ValidatorException;
import org.example.model.DTO.CartiCuNrTranzactii;
import org.example.model.Tranzactie;
import org.xml.sax.SAXException;
import org.example.repository.IRepository;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class TranzactieService {

    private IRepository<Long, Tranzactie> repository;

    public TranzactieService(IRepository<Long, Tranzactie> repository) {
        this.repository = repository;
    }
    public void addTranzactie(Tranzactie tranzactie) throws ValidatorException, ParserConfigurationException, IOException, TransformerException, SAXException {
        repository.save(tranzactie);
    }

    public Set<Tranzactie> getAllT() {
        Iterable<Tranzactie> tranzactions = repository.findAll();
        return StreamSupport.stream(tranzactions.spliterator(),false).collect(Collectors.toSet());
    }

    public Set<Tranzactie> filterTranzactieByNrPieces(int input) {
        Iterable<Tranzactie> tranzactions = repository.findAll();

        Set<Tranzactie> filterTranzactions = new HashSet<>();
        tranzactions.forEach(filterTranzactions::add);
        filterTranzactions.removeIf(tranzactie ->tranzactie.getNrBucati() != input);
        return filterTranzactions;
    }

    public void deleteTranzactie(String id) {
        this.repository.delete(Long.valueOf(id));

    }

    public void updateTranzactie( String id_book, String id_client, int nrBucati, String dataSiOra) {
        Tranzactie tranzactie = new Tranzactie(id_book, id_client, nrBucati, dataSiOra);
        this.repository.update(tranzactie);

    }



    public List<Tranzactie> getListaTranzactiiPeInterval(String inceputInterval,String sfarsitInterval ) throws ParseException {
        List<Tranzactie> getListaTranzactiiPeInterval = new ArrayList<>();


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime DeLa = LocalDateTime.parse(inceputInterval, formatter);

        LocalDateTime PanaLa = LocalDateTime.parse(sfarsitInterval, formatter);
        for (Tranzactie tranzactie : this.getAllT()) {
            LocalDateTime tranzactieDateTime = LocalDateTime.parse(tranzactie.getDataSiOra(), formatter);
            if (tranzactieDateTime.equals(DeLa) || tranzactieDateTime.isAfter(DeLa) && tranzactieDateTime.isBefore(PanaLa)) {
                getListaTranzactiiPeInterval.add(tranzactie);
            }

        }
        return getListaTranzactiiPeInterval;

    }


}
