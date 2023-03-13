package org.example.Validari;

import org.example.Exceptie.Exceptie;
import org.example.model.Tranzactie;

public class TranzactieValidator implements Validator<Tranzactie>{
    @Override
    public void validate(Tranzactie entity) throws ValidatorException {
        String errors = "";
//        if (tranzactie.getId().isEmpty()) {
//            errors += "Id tranzactiei nu poate fi nul. \n";
//        }
        if (entity.getId_book().isEmpty()) {
            errors += "Id-ul cartii nu poate fi nul. \n";
        }
        if (entity.getId_client().isEmpty()) {
            errors += "Id-ul clientului nu poate fi nul.\n";
        }
        if (entity.getNrBucati()<1) {
            errors += "Nu s-a realizat nici o tranzactie\n";
        }
        if (entity.getDataSiOra().isEmpty()) {
            errors += "Data si ora nu pot fi nule\n";
        }

        if (!errors.isEmpty()) {
            throw new Exceptie(errors);
        }
    }
}