package org.example.service;
import org.example.Validari.ValidatorException;
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

    public Set<Tranzactie> filterTranzactieByNrPieces(int i) {
        Iterable<Tranzactie> tranzactions = repository.findAll();

        Set<Tranzactie> filterTranzactions = new HashSet<>();
        tranzactions.forEach(filterTranzactions::add);
        filterTranzactions.removeIf(tranzactie ->tranzactie.getNrBucati() != i);
        return filterTranzactions;
    }

    public void deleteTranzactie(String id) {
        this.repository.delete(Long.valueOf(id));


    }

    //version 1
//        Set<Student> filteredStudents = StreamSupport.stream(students.spliterator(), false)
//                .filter(student -> student.getName().contains(s)).collect(Collectors.toSet());

    //    private InMemoryRepository inMemoryRepository;
//
//    private IRepository<Book> bookIRepository;
//    private IRepository<Client> clientIRepository;
//    private IRepository<Tranzactie> tranzactieIRepository;
//    private TranzactieValidator tranzactieValidator;
//
// //   private CartiCuNrTranzactii cartiCuNrTranzactii;
//
//    Scanner scanner = new Scanner(System.in);
//
//
//    public TranzactieService(IRepository<Tranzactie> tranzactieIRepository, IRepository<Book> bookIRepository, IRepository<Client> clientIRepository, TranzactieValidator tranzactieValidator) {
//        this.tranzactieIRepository = tranzactieIRepository;
//        this.bookIRepository = bookIRepository;
//        this.clientIRepository = clientIRepository;
//        this.tranzactieValidator = tranzactieValidator;
// //       this.cartiCuNrTranzactii = cartiCuNrTranzactii;
//
//    }
//
//    public void addTranzactie(String id, String id_book, String id_client, int nrBucati, String dataSiOra) {
//        Tranzactie tranzactie = new Tranzactie(id, id_book, id_client, nrBucati, dataSiOra);
//        this.tranzactieValidator.validateT(tranzactie);
//        this.tranzactieIRepository.create(tranzactie);
//    }
//
//    public List<Tranzactie> getAllT() {
//
//        return this.tranzactieIRepository.read();
//    }
//
//    public void updateTranzactie(String id, String id_book, String id_client, int nrBucati, String dataSiOra) {
//        Tranzactie tranzactie = new Tranzactie(id, id_book, id_client, nrBucati, dataSiOra);
//        this.tranzactieIRepository.update(tranzactie);
//
//    }
//
//    public void deleteTranzactie(String id) {
//        this.tranzactieIRepository.delete(id);
//    }
//
//    public String searchTranzactie(String input){
//        Pattern stringPattern = Pattern.compile(input);
//        String gasit = "";
//        for (Tranzactie tranzactie : tranzactieIRepository.read()) {
//            Matcher similaritate = stringPattern.matcher(tranzactie.toString());
//            if(similaritate.find()) {
//                gasit += tranzactie.toString() + "\n";
//            }
//        }
//        return gasit;
//    }
//
////    public String searchTranzactie(String input) {
////        return this.tranzactieIRepository.search(input);
////    }
//
//    public List<CartiCuNrTranzactii> getCartiOrdonateDescDupaNrTranzactii() {
//   //    CartiCuNrTranzactii cartiCuNrTranzactii1 = new CartiCuNrTranzactii();
//        Map<String, Integer> mapCartiTranzactii = new HashMap<>();
//        for (Tranzactie tranzactie : this.getAllT()) {
//            String idBook = tranzactie.getId_book();
//            if (mapCartiTranzactii.containsKey(idBook)) {
//                mapCartiTranzactii.put(idBook, mapCartiTranzactii.get(idBook) + 1);
//            } else {
//                mapCartiTranzactii.put(idBook, 1);
//            }
//        }
//
//        List<CartiCuNrTranzactii> result = new ArrayList<>();
//        for (String idBook : mapCartiTranzactii.keySet()) {
//            result.add(new CartiCuNrTranzactii(this.bookIRepository.read(idBook), mapCartiTranzactii.get(idBook)));
//        }
//
//        result.sort(new Comparator<CartiCuNrTranzactii>() {
//            @Override
//            public int compare(CartiCuNrTranzactii o1, CartiCuNrTranzactii o2) {
//                return -Integer.compare(o1.getNrTranzactii(), o2.getNrTranzactii());
//            }
//        });
//
//        return result;
//
//
//    }
//
//
    public List<Tranzactie> getListaTranzactiiPeInterval(String inceputInterval,String sfarsitInterval ) throws ParseException {
        List<Tranzactie> getListaTranzactiiPeInterval = new ArrayList<>();

//        System.out.println("Introduceti data la care incepe intervalul format yyyy-MM-dd HH:mm: ");
//        String inceputInterval = this.scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime DeLa = LocalDateTime.parse(inceputInterval, formatter);

//        System.out.println("Introduceti data la care se incheie intervalul yyyy-MM-dd HH:mm: ");
//        String sfarsitInterval = this.scanner.nextLine();

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
