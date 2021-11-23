package com.ggomez.misjuegosapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.ggomez.misjuegosapp.EditarPlataformaActivity;
import com.ggomez.misjuegosapp.MainActivity;
import com.ggomez.misjuegosapp.R;
import com.ggomez.misjuegosapp.db.DBPlataforma;
import com.ggomez.misjuegosapp.models.Plataforma;

import java.util.ArrayList;

public class PlataformaAdapter extends RecyclerView.Adapter<PlataformaAdapter.ViewHolder> {
    ArrayList<Plataforma> plataformas;

    public PlataformaAdapter(ArrayList<Plataforma> plataformas) {
        this.plataformas = plataformas;
    }

    @NonNull
    @Override
    public PlataformaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plataforma, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlataformaAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.cargarPlataformas(plataformas.get(position));
        Context context = holder.itemView.getContext();

        // Editar
        holder.imageButtonEditarPlataforma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Plataforma plataforma = plataformas.get(position);
                Intent intent = new Intent(context, EditarPlataformaActivity.class);

                intent.putExtra("plataforma", plataforma);
                context.startActivity(intent);
            }
        });

        // -----------------------
        holder.imageButtonEliminarPlataforma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                alerta.setTitle("Eliminar plataforma");
                alerta.setMessage("¿Seguro que quieres eliminar esta plataforma?");
                alerta.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Plataforma plataforma = plataformas.get(position);
                        DBPlataforma dbPlataforma = new DBPlataforma(context);
                        
                        int eliminar = dbPlataforma.eliminarPlataforma(plataforma.getId_plataforma());
                        if(eliminar > 0) {
                            Toast.makeText(context.getApplicationContext(), "Se ha eliminado la plataforma", Toast.LENGTH_SHORT).show();
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
        return plataformas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPlataforma;
        ImageView imageViewPlataforma;
        ImageButton imageButtonEditarPlataforma, imageButtonEliminarPlataforma;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewPlataforma = itemView.findViewById(R.id.textViewPlataforma);
            imageViewPlataforma = itemView.findViewById(R.id.imageViewPlataforma);
            imageButtonEditarPlataforma = itemView.findViewById(R.id.imageButtonEditarPlataforma);
            imageButtonEliminarPlataforma = itemView.findViewById(R.id.imageButtonEliminarPlataforma);
        }

        public void cargarPlataformas(Plataforma plataforma) {
            textViewPlataforma.setText(plataforma.getNombre());
            // cargar imagen plataformas
            imageViewPlataforma.setImageResource(R.drawable.plataforma_img);
        }
    }
}
