package model.Validari;

import org.example.Exceptie.Exceptie;
import org.example.model.Client;
import org.example.Validari.ClientValidator;
import org.example.Validari.Validator;
import org.example.Validari.ValidatorException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientValidatorTest implements Validator<Client> {

    @Override
    public void validate(Client entity) throws ValidatorException {

    }

    @Test
    void validate() {

        ClientValidator clientValidator = new ClientValidator();

        Client c1 = new Client("19876543456678", "", "Octavian", "Str. Plopilor", 1997);
        try {
            clientValidator.validate(c1);
            fail();
        } catch ( Exceptie e){
            assertEquals("Numele clientului nu poate fi gol\n", e.getMessage());
        }

        Client c2 = new Client("19876543456678", "Marinescu", "", "Str. Plopilor", 1997);
        try {
            clientValidator.validate(c2);
            fail();
        } catch ( Exception e){
            assertEquals("Prenumele clientului nu poate fi gol\n", e.getMessage());
        }

        Client c3 = new Client("19876543456678", "Marinescu", "Octavian", "Str. Plopilor", 2023);
        try {
            clientValidator.validate(c3);
            fail();
        } catch ( Exceptie e){
            assertEquals("Anul nasterii nu poate fi gol\n", e.getMessage());
        }

        Client c4 = new Client("", "Marinescu", "Octavian", "Str. Plopilor", 1997);
        try {
            clientValidator.validate(c4);
            fail();
        } catch ( Exceptie e){
            assertEquals("CNP-ul clientului este o necesitate\n", e.getMessage());
        }

        Client c5 = new Client("19876543456678", "Marinescu", "Octavian", "", 1997);
        try {
            clientValidator.validate(c5);
            fail();
        } catch ( Exceptie e){
            assertEquals("Campul adresei nu poate fi gol\n", e.getMessage());
        }

    }


}