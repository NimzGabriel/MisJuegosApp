package com.ggomez.misjuegosapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.ggomez.misjuegosapp.EditarCategoriaActivity;
import com.ggomez.misjuegosapp.EditarJuegoActivity;
import com.ggomez.misjuegosapp.MainActivity;
import com.ggomez.misjuegosapp.R;
import com.ggomez.misjuegosapp.db.DBJuego;
import com.ggomez.misjuegosapp.models.Juego;

import java.util.ArrayList;
import java.util.Collection;

public class JuegoAdapter extends RecyclerView.Adapter<JuegoAdapter.ViewHolder> {
    ArrayList<Juego> juegos;

    public JuegoAdapter(ArrayList<Juego> juegos) {
        this.juegos = juegos;
    }

    @NonNull
    @Override
    public JuegoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_juego, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JuegoAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.cargarJuegos(juegos.get(position));
        Context context = holder.itemView.getContext();

        holder.imageButtonEditarJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Juego juego = juegos.get(position);
                Intent intent = new Intent(context, EditarJuegoActivity.class);

                intent.putExtra("juego", juego);
                context.startActivity(intent);
            }
        });

        // -----------------
        holder.imageButtonEliminarJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                alerta.setTitle("Eliminar juego");
                alerta.setMessage("¿Seguro que quieres eliminar este juego?");
                alerta.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Juego juego = juegos.get(position);
                        DBJuego dbJuego = new DBJuego(context);
                        int eliminar = dbJuego.eliminarJuego(juego.getId_juego());

                        if(eliminar > 0) {
                            Toast.makeText(context.getApplicationContext(), "Se ha eliminado el juego", Toast.LENGTH_SHORT).show();
                        }
                        context.startActivity(new Intent(context, MainActivity.class));
                    }
                });

                alerta.setNegativeButton("Cancelar", null);
                alerta.create();
                alerta.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return juegos.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombreJuego, textViewDescripcionJuego,
                textViewRestriccionJuego, textViewAnioJuego,
                textViewCalifJuego, textViewCategoriaJuego,
                textViewPlataformaJuego, textViewPublicadorJuego;
        ImageView imageViewJuego;
        ImageButton imageButtonEditarJuego, imageButtonEliminarJuego;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNombreJuego = itemView.findViewById(R.id.textViewNombreJuego);
            textViewDescripcionJuego = itemView.findViewById(R.id.textViewDescripcionJuego);
            textViewRestriccionJuego = itemView.findViewById(R.id.textViewRestriccionJuego);

            textViewAnioJuego = itemView.findViewById(R.id.textViewAnioJuego);
            textViewCalifJuego = itemView.findViewById(R.id.textViewCalifJuego);

            textViewCategoriaJuego = itemView.findViewById(R.id.textViewCategoriaJuego);
            textViewPlataformaJuego = itemView.findViewById(R.id.textViewPlataformaJuego);
            textViewPublicadorJuego = itemView.findViewById(R.id.textViewPublicadorJuego);

            imageViewJuego = itemView.findViewById(R.id.imageViewJuego);
            imageButtonEditarJuego = itemView.findViewById(R.id.imageButtonEditarJuego);
            imageButtonEliminarJuego = itemView.findViewById(R.id.imageButtonEliminarJuego);
        }

        @SuppressLint("SetTextI18n")
        public void cargarJuegos(Juego juego) {
            textViewNombreJuego.setText(juego.getNombre());
            textViewDescripcionJuego.setText(juego.getDescripcion());

            textViewRestriccionJuego.setText("Restricción: " + juego.getRestriccion());

            textViewAnioJuego.setText(String.valueOf("Año: " + juego.getAnio()));
            textViewCalifJuego.setText(String.valueOf("Estrellas: " + juego.getCalificacion()));

            textViewCategoriaJuego.setText("Genero: " + juego.getCategoria().getNombre());
            textViewPlataformaJuego.setText("Plataforma: " + juego.getPlataforma().getNombre());
            textViewPublicadorJuego.setText("Publicador: " + juego.getPublicador().getNombre());

            imageViewJuego.setImageResource(R.drawable.juegos_img);
        }
    }
}
