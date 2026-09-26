package com.example.notasdocente.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.notasdocente.entidades.Universidad;

import java.util.ArrayList;
import java.util.List;

public class DAOUniversidad {
    
    private ConexionBasedatos conexion;
    
    public DAOUniversidad(ConexionBasedatos conexion) {
        this.conexion = conexion;
    }
    
    // Metodo para agregar una universidad
    public void agregarUniversidad(Universidad institucion) throws Exception {
        SQLiteDatabase bd = conexion.getWritableDatabase();
        ContentValues columnasValor = new ContentValues();
        columnasValor.put("id", institucion.getId());
        columnasValor.put("nombre", institucion.getNombre());
        columnasValor.put("www", institucion.getWww());
        
        long resultado = bd.insert("Universidades", null, columnasValor);
        bd.close();
        
        if (resultado == -1) {
            throw new Exception("Error al insertar la universidad");
        }
    }
    
    // Metodo para buscar una universidad por ID
    public Universidad buscarUniversidad(String id) throws Exception {
        SQLiteDatabase bd = conexion.getReadableDatabase();
        String[] parametros = { id };
        String[] columnas = { "id", "nombre", "www" };
        
        Cursor cursor = bd.query("Universidades", columnas, "id=?", parametros, null, null, null);
        
        Universidad institucion = null;
        if (cursor.moveToFirst()) {
            institucion = new Universidad();
            institucion.setId(cursor.getString(0));
            institucion.setNombre(cursor.getString(1));
            institucion.setWww(cursor.getString(2));
        }
        
        cursor.close();
        bd.close();
        
        if (institucion == null) {
            throw new Exception("Universidad no encontrada con ID: " + id);
        }
        
        return institucion;
    }
    
    // Metodo para listar todas las universidades
    public List<Universidad> listarUniversidades() throws Exception {
        List<Universidad> lista = new ArrayList<>();
        SQLiteDatabase bd = conexion.getReadableDatabase();
        String[] columnas = { "id", "nombre", "www" };
        
        Cursor cursor = bd.query("Universidades", columnas, null, null, null, null, "id ASC");
        
        if (cursor.moveToFirst()) {
            do {
                Universidad u = new Universidad();
                u.setId(cursor.getString(0));
                u.setNombre(cursor.getString(1));
                u.setWww(cursor.getString(2));
                lista.add(u);
            } while (cursor.moveToNext());
        }
        
        cursor.close();
        bd.close();
        
        return lista;
    }
    
    // Metodo para modificar una universidad existente
    public void editarUniversidad(Universidad institucion) throws Exception {
        SQLiteDatabase bd = conexion.getWritableDatabase();
        ContentValues columnasValor = new ContentValues();
        columnasValor.put("nombre", institucion.getNombre());
        columnasValor.put("www", institucion.getWww());
        String[] valoresWhere = { institucion.getId() };
        
        int filasAfectadas = bd.update("Universidades", columnasValor, "id=?", valoresWhere);
        bd.close();
        
        if (filasAfectadas == 0) {
            throw new Exception("No se encontró la universidad para modificar");
        }
    }
    
    // Metodo para eliminar una universidad por ID
    public void borrarUniversidad(String id) throws Exception {
        SQLiteDatabase bd = conexion.getWritableDatabase();
        String[] valoresWhere = { id };
        
        int filasAfectadas = bd.delete("Universidades", "id=?", valoresWhere);
        bd.close();
        
        if (filasAfectadas == 0) {
            throw new Exception("No se encontró la universidad para eliminar");
        }
    }
}