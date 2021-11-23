package com.ggomez.misjuegosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ggomez.misjuegosapp.db.DBPublicador;
import com.ggomez.misjuegosapp.models.Publicador;

public class EditarPublicadorActivity extends AppCompatActivity {
    EditText editarPublicadorNombre;
    Button buttonEditarPublicador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_publicador);

        editarPublicadorNombre = findViewById(R.id.editarPublicadorNombre);
        buttonEditarPublicador = findViewById(R.id.buttonEditarPublicador);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Publicador publicador = (Publicador)bundle.get("publicador");
        editarPublicadorNombre.setText(publicador.getNombre());

        buttonEditarPublicador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = editarPublicadorNombre.getText().toString();
                DBPublicador dbPublicador = new DBPublicador(getApplicationContext());
                int editar = dbPublicador.editarPublicador(publicador.getId_publicador(), nombre);

                if(editar > 0) {
                    Toast.makeText(getApplicationContext(), "Se ha actualizado el publicador", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}