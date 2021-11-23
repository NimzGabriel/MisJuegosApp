package com.ggomez.misjuegosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.ggomez.misjuegosapp.db.DBCategoria;
import com.ggomez.misjuegosapp.db.DBJuego;
import com.ggomez.misjuegosapp.db.DBPlataforma;
import com.ggomez.misjuegosapp.db.DBPublicador;
import com.ggomez.misjuegosapp.models.Categoria;
import com.ggomez.misjuegosapp.models.Juego;
import com.ggomez.misjuegosapp.models.Plataforma;
import com.ggomez.misjuegosapp.models.Publicador;

import java.util.ArrayList;

public class AgregarJuegoActivity extends AppCompatActivity {
    EditText editTextNombreJuego, editTextDescripcionJuego,
            editTextRestriccionJuego, editTextAnioJuego;
    Spinner spinnerCategoriaJuego, spinnerPlataformaJuego,
            spinnerPublicadorJuego;
    RatingBar ratingBarJuego;
    Button buttonCrearJuego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_juego);

        editTextNombreJuego = findViewById(R.id.editTextNombreJuego);
        editTextDescripcionJuego = findViewById(R.id.editTextDescripcionJuego);
        editTextRestriccionJuego = findViewById(R.id.editTextRestriccionJuego);
        editTextAnioJuego = findViewById(R.id.editTextAnioJuego);

        spinnerCategoriaJuego = findViewById(R.id.spinnerCategoriaJuego);
        spinnerPlataformaJuego = findViewById(R.id.spinnerPlataformaJuego);
        spinnerPublicadorJuego = findViewById(R.id.spinnerPublicadorJuego);
        cargarCategorias();
        cargarPlataformas();
        cargarPublicadores();

        ratingBarJuego = findViewById(R.id.ratingBarJuego);
        buttonCrearJuego = findViewById(R.id.buttonCrearJuego);

        buttonCrearJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = editTextNombreJuego.getText().toString();
                String descripcion = editTextDescripcionJuego.getText().toString();
                String restriccion = editTextRestriccionJuego.getText().toString();
                int anio = Integer.valueOf(editTextAnioJuego.getText().toString());
                float calif = ratingBarJuego.getRating();
                Categoria categoria = (Categoria) spinnerCategoriaJuego.getSelectedItem();
                Plataforma plataforma = (Plataforma) spinnerPlataformaJuego.getSelectedItem();
                Publicador publicador = (Publicador) spinnerPublicadorJuego.getSelectedItem();

                Juego juego = new Juego(nombre, descripcion, restriccion, anio, calif, categoria, plataforma, publicador);
                DBJuego dbJuego = new DBJuego(getApplicationContext());

                long insertar = dbJuego.insertarJuego(juego);
                if(insertar > 0) {
                    Toast.makeText(getApplicationContext(), "Se ha insertado el juego", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void cargarCategorias() {
        DBCategoria dbCategoria = new DBCategoria(getApplicationContext());
        ArrayList<Categoria> categorias = dbCategoria.obtenerCategorias();

        if(categorias != null) {
            ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(
                    getApplicationContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    categorias
            );
            spinnerCategoriaJuego.setAdapter(adapter);
        }
    }

    public void cargarPlataformas() {
        DBPlataforma dbPlataforma = new DBPlataforma(getApplicationContext());
        ArrayList<Plataforma> plataformas = dbPlataforma.obtenerPlataformas();

        if(plataformas != null) {
            ArrayAdapter<Plataforma> adapter = new ArrayAdapter<>(
                    getApplicationContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    plataformas
            );
            spinnerPlataformaJuego.setAdapter(adapter);
        }
    }

    public void cargarPublicadores() {
        DBPublicador dbPublicador = new DBPublicador(getApplicationContext());
        ArrayList<Publicador> publicadores = dbPublicador.obtenerPublicadores();

        if(publicadores != null) {
            ArrayAdapter<Publicador> adapter = new ArrayAdapter<>(
                    getApplicationContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    publicadores
            );
            spinnerPublicadorJuego.setAdapter(adapter);
        }
    }
}