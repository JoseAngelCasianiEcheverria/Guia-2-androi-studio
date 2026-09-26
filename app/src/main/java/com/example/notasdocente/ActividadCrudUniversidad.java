package com.example.notasdocente;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.notasdocente.dao.ConexionBasedatos;
import com.example.notasdocente.dao.DAOUniversidad;
import com.example.notasdocente.entidades.Universidad;

import java.util.List;

public class ActividadCrudUniversidad extends AppCompatActivity {
    
    private EditText campoId, campoNombre, campoWww;
    private DAOUniversidad dao;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad_crud_universidad);
        
        campoId = findViewById(R.id.campoId);
        campoNombre = findViewById(R.id.campoNombre);
        campoWww = findViewById(R.id.campoWww);
        
        ConexionBasedatos conexion = new ConexionBasedatos(this);
        dao = new DAOUniversidad(conexion);
    }
    
    // Evento para agregar universidad
    public void agregarUniversidad(View view) {
        String id = campoId.getText().toString();
        String nombre = campoNombre.getText().toString();
        String www = campoWww.getText().toString();
        
        if (id.isEmpty() || nombre.isEmpty()) {
            Toast.makeText(this, "ID y Nombre son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }
        
        Universidad u = new Universidad();
        u.setId(id);
        u.setNombre(nombre);
        u.setWww(www);
        
        try {
            dao.agregarUniversidad(u);
            Toast.makeText(this, "Universidad agregada con éxito", Toast.LENGTH_LONG).show();
            limpiarCampos(view);
        } catch (Exception e) {
            Toast.makeText(this, "ERROR: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    
    // Evento para buscar universidad por ID
    public void buscarUniversidad(View view) {
        String id = campoId.getText().toString();
        
        if (id.isEmpty()) {
            Toast.makeText(this, "Ingrese un ID para buscar", Toast.LENGTH_SHORT).show();
            return;
        }
        
        try {
            Universidad u = dao.buscarUniversidad(id);
            campoNombre.setText(u.getNombre());
            campoWww.setText(u.getWww());
            Toast.makeText(this, "Universidad encontrada", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "ERROR: " + e.getMessage(), Toast.LENGTH_LONG).show();
            limpiarCampos(view);
        }
    }
    
    // Evento para modificar universidad
    public void modificarUniversidad(View view) {
        String id = campoId.getText().toString();
        String nombre = campoNombre.getText().toString();
        String www = campoWww.getText().toString();
        
        if (id.isEmpty() || nombre.isEmpty()) {
            Toast.makeText(this, "ID y Nombre son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }
        
        Universidad u = new Universidad();
        u.setId(id);
        u.setNombre(nombre);
        u.setWww(www);
        
        try {
            dao.editarUniversidad(u);
            Toast.makeText(this, "Universidad modificada con éxito", Toast.LENGTH_LONG).show();
            limpiarCampos(view);
        } catch (Exception e) {
            Toast.makeText(this, "ERROR: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    
    // Evento para eliminar universidad
    public void eliminarUniversidad(View view) {
        String id = campoId.getText().toString();
        
        if (id.isEmpty()) {
            Toast.makeText(this, "Ingrese un ID para eliminar", Toast.LENGTH_SHORT).show();
            return;
        }
        
        try {
            dao.borrarUniversidad(id);
            Toast.makeText(this, "Universidad eliminada con éxito", Toast.LENGTH_LONG).show();
            limpiarCampos(view);
        } catch (Exception e) {
            Toast.makeText(this, "ERROR: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    
    // Evento para listar todas las universidades
    public void listarUniversidades(View view) {
        try {
            List<Universidad> lista = dao.listarUniversidades();
            
            if (lista.isEmpty()) {
                Toast.makeText(this, "No hay universidades registradas", Toast.LENGTH_SHORT).show();
                return;
            }
            
            StringBuilder sb = new StringBuilder();
            sb.append("Total: ").append(lista.size()).append("\n\n");
            for (Universidad u : lista) {
                sb.append("ID: ").append(u.getId()).append("\n");
                sb.append("Nombre: ").append(u.getNombre()).append("\n");
                sb.append("Web: ").append(u.getWww()).append("\n");
                sb.append("------------------------\n");
            }
            
            new AlertDialog.Builder(this)
                .setTitle("Lista de Universidades")
                .setMessage(sb.toString())
                .setPositiveButton("Cerrar", null)
                .show();
                
        } catch (Exception e) {
            Toast.makeText(this, "ERROR: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    
    // Evento para limpiar campos
    public void limpiarCampos(View view) {
        campoId.setText("");
        campoNombre.setText("");
        campoWww.setText("");
        campoId.requestFocus();
    }
    
    // Evento para regresar a la actividad principal
    public void regresar(View view) {
        finish();
    }
}