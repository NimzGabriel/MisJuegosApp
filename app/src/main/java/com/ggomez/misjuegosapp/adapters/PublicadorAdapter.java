package com.ggomez.misjuegosapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.ggomez.misjuegosapp.EditarPublicadorActivity;
import com.ggomez.misjuegosapp.MainActivity;
import com.ggomez.misjuegosapp.R;
import com.ggomez.misjuegosapp.db.DBPublicador;
import com.ggomez.misjuegosapp.models.Publicador;

import java.util.ArrayList;

public class PublicadorAdapter extends RecyclerView.Adapter<PublicadorAdapter.ViewHolder> {
    ArrayList<Publicador> publicadores;

    public PublicadorAdapter(ArrayList<Publicador> publicadores) {
        this.publicadores = publicadores;
    }

    @NonNull
    @Override
    public PublicadorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_publicador, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PublicadorAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.cargarPublicadores(publicadores.get(position));
        Context context = holder.itemView.getContext();

        // ----------------------
        holder.imageButtonEditarPublicador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Publicador publicador = publicadores.get(position);
                Intent intent = new Intent(context, EditarPublicadorActivity.class);
                intent.putExtra("publicador", publicador);
                context.startActivity(intent);
            }
        });

        // ----------------------
        holder.imageButtonEliminarPublicador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                alerta.setTitle("Eliminar publicador");
                alerta.setMessage("¿Seguro que quieres eliminar este publicador?");
                alerta.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Publicador publicador = publicadores.get(position);
                        DBPublicador dbPublicador = new DBPublicador(context);
                        
                        int eliminar = dbPublicador.eliminarPublicador(publicador.getId_publicador());
                        if(eliminar > 0) {
                            Toast.makeText(context.getApplicationContext(), "Se ha eliminado el publicador", Toast.LENGTH_SHORT).show();
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
        return publicadores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewPublicador;
        TextView textViewPublicador;
        ImageButton imageButtonEditarPublicador, imageButtonEliminarPublicador;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewPublicador = itemView.findViewById(R.id.imageViewPublicador);
            textViewPublicador = itemView.findViewById(R.id.textViewPublicador);
            imageButtonEditarPublicador = itemView.findViewById(R.id.imageButtonEditarPublicador);
            imageButtonEliminarPublicador = itemView.findViewById(R.id.imageButtonEliminarPublicador);
        }

        public void cargarPublicadores(Publicador publicador) {
            textViewPublicador.setText(publicador.getNombre());
            imageViewPublicador.setImageResource(R.drawable.distribuidores_img);
        }
    }
}
