package org.example.repository;

import org.example.model.BaseEntity;
import org.example.Validari.Validator;
import org.example.Validari.ValidatorException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class InMemoryRepository<ID, T extends BaseEntity<ID> > implements IRepository<ID,T> {

    private Map<ID, T> entities;

    private Validator<T> validator;

    public InMemoryRepository(Validator<T> validator) {
        this.validator = validator;
        entities = new HashMap<>();

    }

    @Override
    public Optional<T> findOne(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public Iterable<T> findAll() {
        Set<T> allEntities = entities.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toSet());
        return allEntities;
    }

    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        validator.validate(entity);
        return Optional.ofNullable(entities.putIfAbsent(entity.getId(), entity));
    }

    @Override
    public Optional<T> delete(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        return Optional.ofNullable(entities.remove(id));
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        validator.validate(entity);
        return Optional.ofNullable(entities.computeIfPresent(entity.getId(), (k, v) -> entity));
    }

//    public void create(TEntity entity) {
//        if(storage.containsKey(entity.getId())){
//          //  throw new RuntimeException("Exista deja o entitate cu id-ul " + entity.getId());
//        }
//        this.storage.put(entity.getId(), entity);
//    }
//
//    @Override
//    public List<TEntity> read() {
//        return new ArrayList<>(storage.values());
//    }
//
//    @Override
//    public TEntity read(String idEntity) {
//        return storage.get(idEntity);
//    }
//
//    @Override
//    public void update(TEntity entity) {
//        if (!storage.containsKey(entity.getId())) {
//            throw new RuntimeException("Nu exista nicio entitate cu id-ul: " + entity.getId());
//        }
//        this.storage.put(entity.getId(), entity);
//
//    }
//
//
//    @Override
//    public void delete(String idEntity) {
//        if (!storage.containsKey(idEntity)) {
//            throw new RuntimeException("Nu exista nicio entitate cu id-ul: " + idEntity);
//        }
//        this.storage.remove(idEntity);
//    }
//
//    public String search(String input){
//        Pattern stringPattern = Pattern.compile(input);
//        String gasit = "";
//        for (TEntity entityItem : storage.values()) {
//            Matcher similaritate = stringPattern.matcher(entityItem.toString());
//            if(similaritate.find()) {
//                gasit += entityItem.toString() + "\n";
//            }
//        }
//        return gasit;
//    }
}
