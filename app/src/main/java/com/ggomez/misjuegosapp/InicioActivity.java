package com.ggomez.misjuegosapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class InicioActivity extends AppCompatActivity {
    Button buttonIrIniciarSesion, buttonIrRegistrarse;
    TextView textViewInvitado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        buttonIrIniciarSesion = findViewById(R.id.buttonIrIniciarSesion);
        buttonIrRegistrarse = findViewById(R.id.buttonIrRegistrarse);
        textViewInvitado = findViewById(R.id.textViewInvitado);

        buttonIrIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        buttonIrRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegistroActivity.class));
            }
        });

        textViewInvitado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Has entrado como invitado", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

}