package service;

import org.example.model.Tranzactie;
import org.example.Validari.TranzactieValidator;
import org.example.Validari.Validator;
import org.example.Validari.ValidatorException;
import org.example.service.TranzactieService;
import org.junit.jupiter.api.Test;
import org.example.repository.IRepository;
import org.example.repository.InMemoryRepository;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TranzactieServiceTes implements Validator<Tranzactie> {

    TranzactieValidator tranzactieValidator = new TranzactieValidator();

    IRepository<Long, Tranzactie> getTranzactieRepository(){
        InMemoryRepository<Long,Tranzactie> testRepository=new InMemoryRepository<>(tranzactieValidator);
        testRepository.save(new Tranzactie("11", "1111", 2, "2022-05-02 00:00"));
        return testRepository;
    }

    TranzactieValidator getTranzactieValidator(){
        TranzactieValidator tranzactieValidator=new TranzactieValidator();
        return tranzactieValidator;
    }

    TranzactieService getTranzactieService(){
        IRepository<Long,Tranzactie> tranzactieRepository=getTranzactieRepository();
        TranzactieValidator tranzactieValidator=getTranzactieValidator();
        TranzactieService tranzactieService=new TranzactieService(tranzactieRepository);
        return tranzactieService;
    }

//    @Test
//    void addTranzactie() {
//        TranzactieService tranzactieService = getTranzactieService();
//        tranzactieService.addTranzactie(new Tranzactie("11", "1111", 2, "2022-05-02 00:00"));
//        Set<Tranzactie> set=tranzactieService.getAllT();
//        assertEquals(1,set.size());
//    }

    @Test
    void getAllT() {
        TranzactieService tranzactieService = getTranzactieService();
        Set <Tranzactie> set = tranzactieService.getAllT();
        assertFalse(set.isEmpty());
    }

//    @Test
//    void deleteTranzactie() {
//        TranzactieService tranzactieService = getTranzactieService();
//        tranzactieService.addTranzactie(new Tranzactie("11", "1111", 2, "2022-05-02 00:00"));
//        tranzactieService.addTranzactie(new Tranzactie("22", "3333", 1, "2022-05-01 00:00"));
//        tranzactieService.deleteTranzactie("1");
//        Set<Tranzactie> set=tranzactieService.getAllT();
//        assertEquals(1,set.size());
//    }

    @Override
    public void validate(Tranzactie entity) throws ValidatorException {

    }
}