package com.example.notasdocente;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class ActividadPrincipal extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Ir directamente al CRUD de Universidades
        Intent intent = new Intent(this, ActividadCrudUniversidad.class);
        startActivity(intent);
        finish();
    }
}