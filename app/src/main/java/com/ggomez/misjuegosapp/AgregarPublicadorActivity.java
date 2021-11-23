package com.ggomez.misjuegosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ggomez.misjuegosapp.db.DBPublicador;
import com.ggomez.misjuegosapp.models.Publicador;

public class AgregarPublicadorActivity extends AppCompatActivity {
    EditText editTextPublicadorNombre;
    Button buttonCrearJuego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_publicador);

        editTextPublicadorNombre = findViewById(R.id.editTextPublicadorNombre);
        buttonCrearJuego = findViewById(R.id.buttonCrearJuego);

        buttonCrearJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = editTextPublicadorNombre.getText().toString();
                Publicador publicador = new Publicador(nombre);

                DBPublicador dbPublicador = new DBPublicador(getApplicationContext());
                long insertar = dbPublicador.insertarPublicador(publicador);
                if(insertar > 0) {
                    Toast.makeText(getApplicationContext(), "Se ha insertado el publicador", Toast.LENGTH_SHORT).show();
                    editTextPublicadorNombre.setText("");
                }
            }
        });
    }
}