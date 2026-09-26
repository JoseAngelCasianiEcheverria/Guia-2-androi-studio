package com.example.notasdocente.dao;

public class CodigoSQLBackUp {
    public static final String CREAR_BASE_DATOS = "CREATE DATABASE IF NOT EXISTS notasdocentev1;";

    public static final String CREAR_TABLA_UNIVERSIDADES = 
        "CREATE TABLE IF NOT EXISTS Universidades (" +
        "id TEXT PRIMARY KEY, " +
        "nombre TEXT NOT NULL, " +
        "www TEXT" +
        ");";


    // Insertar de datos de prueba
    public static final String INSERTAR_UNIVERSIDADES_PRUEBA = 
        "INSERT OR IGNORE INTO Universidades (id, nombre, www) VALUES " +
        "('U001', 'Unicolombo', 'www.unicolombo.edu.co'), ";

}