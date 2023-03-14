package org.example.service;

import org.example.model.Client;
import org.example.Validari.ValidatorException;
import org.xml.sax.SAXException;
import org.example.repository.IRepository;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ClientService {
    private IRepository<Long, Client> repository;

    public ClientService(IRepository<Long, Client> repository) {
        this.repository = repository;

    }
    public void addClient(Client client) throws ValidatorException, ParserConfigurationException, IOException, TransformerException, SAXException {
        repository.save(client);
    }

    public Client youngClinet2() {
        Set<Client> clients = (Set<Client>) repository.findAll();

        Set<Client> orderedClients = clients.stream().sorted(Comparator.comparing(Client::getAnulNasterii)).collect(Collectors.toCollection(LinkedHashSet::new));

        Iterator c = orderedClients.iterator();
        Client last = (Client) c.next();
        while (c.hasNext()) {
            last = (Client) c.next();
        }
        return last;
    }

    public  Client  youngClient() {
        Set<Client> clients = (Set<Client>) repository.findAll();

        List<Client> client2 = new ArrayList<>();
        for (Client client : clients) {
            client2.add(client);
        }
        Collections.sort(client2, new Comparator<Client>() {
            @Override
            public int compare(Client o1, Client o2) {
                return (-o1.getAnulNasterii()) - (-o2.getAnulNasterii());
            }
        });

        Client clientT = client2.get(0);
//        System.out.println("Cel mai tanar client este:" + clientT);

        return clientT;
    }




    public Set<Client> getAllC() {
        Iterable<Client> clients = repository.findAll();
        return StreamSupport.stream(clients.spliterator(),false).collect(Collectors.toSet());
    }

    public Set<Client> filterClientsByName(String input) {
        Iterable<Client> clients = repository.findAll();
        Set<Client> filterClients = new HashSet<>();
        clients.forEach(filterClients::add);
        filterClients.removeIf(client -> !client.getNume().contains(input));
        return filterClients;
    }

    public void deleteClient(String id) {
        this.repository.delete(Long.valueOf(id));


    }
    public void updateClient(String cnp, String nume,String prenume, String adresa, int anulNasterii) {
        Client client = new Client(cnp, nume, prenume, adresa, anulNasterii);
        this.repository.update(client);

    }
}
