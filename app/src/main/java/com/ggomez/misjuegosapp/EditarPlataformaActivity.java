package com.ggomez.misjuegosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ggomez.misjuegosapp.db.DBPlataforma;
import com.ggomez.misjuegosapp.models.Plataforma;

public class EditarPlataformaActivity extends AppCompatActivity {
    EditText editarPlataformaNombre;
    Button buttonEditarPlataforma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_plataforma);

        editarPlataformaNombre = findViewById(R.id.editarPlataformaNombre);
        buttonEditarPlataforma = findViewById(R.id.buttonEditarPlataforma);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Plataforma plataforma = (Plataforma) bundle.get("plataforma");
        editarPlataformaNombre.setText(plataforma.getNombre());

        buttonEditarPlataforma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = editarPlataformaNombre.getText().toString();
                DBPlataforma dbPlataforma = new DBPlataforma(getApplicationContext());

                int actualizar = dbPlataforma.actualizarPlataforma(plataforma.getId_plataforma(), nombre);
                if(actualizar > 0) {
                    Toast.makeText(getApplicationContext(), "Se ha actualizado la plataforma", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}