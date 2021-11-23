package com.ggomez.misjuegosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ggomez.misjuegosapp.db.DBUsuario;
import com.ggomez.misjuegosapp.models.Usuario;

public class LoginActivity extends AppCompatActivity {
    EditText editTextLoginEmail, editTextLoginClave;
    Button buttonLoginIniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextLoginEmail = findViewById(R.id.editTextLoginEmail);
        editTextLoginClave = findViewById(R.id.editTextLoginClave);
        buttonLoginIniciarSesion = findViewById(R.id.buttonLoginIniciarSesion);

        buttonLoginIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextLoginEmail.getText().toString();
                String clave = editTextLoginClave.getText().toString();
                DBUsuario dbUsuario = new DBUsuario(getApplicationContext());

                Usuario usuario = dbUsuario.iniciarSesion(email, clave);
                if(usuario != null) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    Toast.makeText(getApplicationContext(), "Bienvenido, " + usuario.getNombres(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Los datos ingresados no son válidos", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}