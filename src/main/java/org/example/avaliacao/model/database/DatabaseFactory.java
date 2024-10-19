package org.example.avaliacao.model.database;

public class DatabaseFactory {

    public static Database getDatabase(String nome) {
        if(nome.equals("postgreesql")) {
            return new DatabasePostgreeSQL();
        } else {
            return new DatabaseMySQL();
        }
    }

}
