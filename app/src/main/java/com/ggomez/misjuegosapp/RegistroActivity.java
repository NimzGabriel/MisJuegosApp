package com.ggomez.misjuegosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ggomez.misjuegosapp.db.DBUsuario;
import com.ggomez.misjuegosapp.models.Usuario;

public class RegistroActivity extends AppCompatActivity {
    EditText editTextRegistroNombres, editTextRegistroApellidos,
            editTextRegistroEmail, editTextRegistroClave;
    Button buttonRegistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        editTextRegistroNombres = findViewById(R.id.editTextRegistroNombres);
        editTextRegistroApellidos = findViewById(R.id.editTextRegistroApellidos);
        editTextRegistroEmail = findViewById(R.id.editTextRegistroEmail);
        editTextRegistroClave = findViewById(R.id.editTextRegistroClave);
        buttonRegistrarse = findViewById(R.id.buttonRegistrarse);

        buttonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombres = editTextRegistroNombres.getText().toString();
                String apellidos = editTextRegistroApellidos.getText().toString();
                String email = editTextRegistroEmail.getText().toString();
                String clave = editTextRegistroClave.getText().toString();
                Usuario usuario = new Usuario(nombres, apellidos, email, clave);

                // Registrar
                DBUsuario dbUsuario = new DBUsuario(getApplicationContext());
                long insertar = dbUsuario.registrarUsuario(usuario);

                if(insertar > 0) {
                    Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}