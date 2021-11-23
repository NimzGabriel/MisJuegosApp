package com.ggomez.misjuegosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ggomez.misjuegosapp.db.DBCategoria;
import com.ggomez.misjuegosapp.models.Categoria;

public class AgregarCategoriaActivity extends AppCompatActivity {
    EditText editTextCategoriaNombre;
    Button buttonCrearCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_categoria);

        editTextCategoriaNombre = findViewById(R.id.editTextCategoriaNombre);
        buttonCrearCategoria = findViewById(R.id.buttonCrearCategoria);
        buttonCrearCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = editTextCategoriaNombre.getText().toString();
                Categoria categoria = new Categoria(nombre);
                DBCategoria dbCategoria = new DBCategoria(getApplicationContext());
                long insertar = dbCategoria.insertarCategoria(categoria);

                if(insertar > 0) {
                    Toast.makeText(getApplicationContext(), "Se agrego una nueva categoría.", Toast.LENGTH_SHORT).show();
                    editTextCategoriaNombre.setText("");
                }
            }
        });
    }
}