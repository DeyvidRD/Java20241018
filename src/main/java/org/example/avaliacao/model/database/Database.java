package org.example.avaliacao.model.database;

import java.sql.Connection;
public interface Database {

    public Connection conectar();

    public void desconectar();
}
