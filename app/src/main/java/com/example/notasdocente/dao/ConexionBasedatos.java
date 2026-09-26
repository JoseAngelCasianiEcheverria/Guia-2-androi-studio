package com.example.notasdocente.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionBasedatos extends SQLiteOpenHelper {
    
    private static final String NOMBRE_BD = "notasdocentev1";
    private static final int VERSION_BD = 1;
    
    public ConexionBasedatos(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear solo tabla Universidades
        db.execSQL("CREATE TABLE Universidades (" +
                "id TEXT PRIMARY KEY, " +
                "nombre TEXT NOT NULL, " +
                "www TEXT" +
                ");");
        
        // Insertar dato de prueba único
        db.execSQL("INSERT OR IGNORE INTO Universidades (id, nombre, www) VALUES " +
                "('U001', 'Unicolombo', 'www.unicolombo.edu.co');");
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Universidades;");
        onCreate(db);
    }
    
    // Métodos auxiliares para operaciones CRUD genéricas
    public void insertar(String tabla, android.content.ContentValues valores) {
        SQLiteDatabase db = getWritableDatabase();
        db.insert(tabla, null, valores);
        db.close();
    }
    
    public void actualizar(String tabla, android.content.ContentValues valores, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = getWritableDatabase();
        db.update(tabla, valores, whereClause, whereArgs);
        db.close();
    }
    
    public void eliminar(String sqlDelete, String[] whereArgs) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sqlDelete, whereArgs);
        db.close();
    }
    
    public android.database.Cursor consultar(String sql, String[] selectionArgs) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, selectionArgs);
    }
}