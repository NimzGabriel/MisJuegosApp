package com.ggomez.misjuegosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ggomez.misjuegosapp.db.DBPlataforma;
import com.ggomez.misjuegosapp.models.Plataforma;

public class AgregarPlataformaActivity extends AppCompatActivity {
    EditText editTextPlataformaNombre;
    Button buttonCrearPlataforma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_plataforma);

        editTextPlataformaNombre = findViewById(R.id.editTextPlataformaNombre);
        buttonCrearPlataforma = findViewById(R.id.buttonCrearPlataforma);

        buttonCrearPlataforma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = editTextPlataformaNombre.getText().toString();
                Plataforma plataforma = new Plataforma(nombre);
                DBPlataforma dbPlataforma = new DBPlataforma(getApplicationContext());

                long insertar = dbPlataforma.insertarPlataforma(plataforma);
                if(insertar > 0) {
                    Toast.makeText(getApplicationContext(), "Se ha insertado la plataforma", Toast.LENGTH_SHORT).show();
                    editTextPlataformaNombre.setText("");
                }
            }
        });
    }
}