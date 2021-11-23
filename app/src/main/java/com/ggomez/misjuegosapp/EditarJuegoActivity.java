package com.ggomez.misjuegosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class EditarJuegoActivity extends AppCompatActivity {
    EditText editarNombreJuego, editarDescripcionJuego,
            editarRestriccionJuego, editarAnioJuego;
    Spinner spinnerEditCategoriaJuego, spinnerEditPlataformaJuego,
            spinnerEditPublicadorJuego;
    RatingBar ratingBarEditJuego;
    Button buttonEditarJuego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_juego);

        editarNombreJuego = findViewById(R.id.editarNombreJuego);
        editarDescripcionJuego = findViewById(R.id.editarDescripcionJuego);
        editarRestriccionJuego = findViewById(R.id.editarRestriccionJuego);
        editarAnioJuego = findViewById(R.id.editarAnioJuego);

        spinnerEditCategoriaJuego = findViewById(R.id.spinnerEditCategoriaJuego);
        spinnerEditPlataformaJuego = findViewById(R.id.spinnerEditPlataformaJuego);
        spinnerEditPublicadorJuego = findViewById(R.id.spinnerEditPublicadorJuego);
        cargarCategorias();
        cargarPlataformas();
        cargarPublicadores();

        ratingBarEditJuego = findViewById(R.id.ratingBarEditJuego);
        buttonEditarJuego = findViewById(R.id.buttonEditarJuego);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Juego juego = (Juego) bundle.get("juego");

        editarNombreJuego.setText(juego.getNombre());
        editarDescripcionJuego.setText(juego.getDescripcion());
        editarRestriccionJuego.setText(juego.getRestriccion());
        editarAnioJuego.setText(String.valueOf(juego.getAnio()));
        ratingBarEditJuego.setRating(juego.getCalificacion());

        buttonEditarJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = editarNombreJuego.getText().toString();
                String descripcion = editarDescripcionJuego.getText().toString();
                String restriccion = editarRestriccionJuego.getText().toString();
                int anio = Integer.parseInt(editarAnioJuego.getText().toString());
                float calif = ratingBarEditJuego.getRating();
                Categoria categoria = (Categoria) spinnerEditCategoriaJuego.getSelectedItem();
                Plataforma plataforma = (Plataforma) spinnerEditPlataformaJuego.getSelectedItem();
                Publicador publicador = (Publicador) spinnerEditPublicadorJuego.getSelectedItem();

                DBJuego dbJuego = new DBJuego(getApplicationContext());
                int actualizar = dbJuego.actualizarJuego(juego.getId_juego(), nombre, descripcion, restriccion, anio, calif, categoria, plataforma, publicador);
                if(actualizar > 0) {
                    Toast.makeText(getApplicationContext(), "Se ha actualizado el juego", Toast.LENGTH_SHORT).show();
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
            spinnerEditCategoriaJuego.setAdapter(adapter);

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
            spinnerEditPlataformaJuego.setAdapter(adapter);
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
            spinnerEditPublicadorJuego.setAdapter(adapter);
        }
    }
}