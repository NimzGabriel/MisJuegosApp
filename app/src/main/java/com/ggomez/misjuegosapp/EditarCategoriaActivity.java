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

public class EditarCategoriaActivity extends AppCompatActivity {
    EditText editarCategoriaNombre;
    Button buttonEditarCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_categoria);

        editarCategoriaNombre = findViewById(R.id.editarCategoriaNombre);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Categoria categoria = (Categoria) bundle.get("categoria");

        editarCategoriaNombre.setText(categoria.getNombre());

        buttonEditarCategoria = findViewById(R.id.buttonEditarCategoria);
        buttonEditarCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = editarCategoriaNombre.getText().toString();
                DBCategoria dbCategoria = new DBCategoria(getApplicationContext());
                int resultado = dbCategoria.actualizarCategoria(categoria.getId_categoria(), nombre);

                if(resultado > 0) {
                    Toast.makeText(getApplicationContext(), "Se ha actualizado la categoría", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}