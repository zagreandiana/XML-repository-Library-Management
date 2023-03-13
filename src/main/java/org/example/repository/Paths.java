package org.example.repository;

import java.nio.file.Path;

public enum Paths {
    BOOKFILEPATH("./src/main/resources/Book"),
    CLIENTFILEPATH("./src/main/resources/Client"),
    TRANZACTIEFILEPATH("./src/main/resources/Tranzactie"),
    BOOKXMLPATH("./src/main/resources/book.xml"),
    CLIENTXMLPATH("./src/main/resources/client.xml"),

    TRANZACTIEXMLPATH("./src/main/resources/tranzactie.xml");

    public String path;

    Paths(String path){
        this.path = path;
    }

    public  String getPath(){
        return path;
    }

}
