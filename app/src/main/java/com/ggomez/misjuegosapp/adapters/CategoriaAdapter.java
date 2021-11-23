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

import com.ggomez.misjuegosapp.EditarCategoriaActivity;
import com.ggomez.misjuegosapp.MainActivity;
import com.ggomez.misjuegosapp.R;
import com.ggomez.misjuegosapp.db.DBCategoria;
import com.ggomez.misjuegosapp.models.Categoria;

import java.util.ArrayList;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.ViewHolder> {
    ArrayList<Categoria> categorias;

    public CategoriaAdapter(ArrayList<Categoria> categorias) {
        this.categorias = categorias;
    }

    @NonNull
    @Override
    public CategoriaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categoria, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.cargarCategorias(categorias.get(position));
        Context context = holder.itemView.getContext();

        holder.imageButtonEditarCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Categoria categoria = categorias.get(position);
                Intent intent = new Intent(context, EditarCategoriaActivity.class);

                intent.putExtra("categoria", categoria);
                context.startActivity(intent);
            }
        });

        holder.imageButtonEliminarCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                alerta.setTitle("Eliminar categoría");
                alerta.setMessage("¿Seguro que quieres eliminar esta categoría?");
                alerta.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Categoria categoria = categorias.get(position);
                        DBCategoria dbCategoria = new DBCategoria(context);
                        int eliminar = dbCategoria.eliminarCategoria(categoria.getId_categoria());

                        if (eliminar > 0) {
                            Toast.makeText(context, "Se ha eliminado la categoría", Toast.LENGTH_SHORT).show();

                            context.startActivity(new Intent(context, MainActivity.class));
                        }

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
        return categorias.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewCategoria;
        TextView textViewCategoria;
        ImageButton imageButtonEditarCategoria, imageButtonEliminarCategoria;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewCategoria = itemView.findViewById(R.id.imageViewCategoria);
            textViewCategoria = itemView.findViewById(R.id.textViewCategoria);
            imageButtonEditarCategoria = itemView.findViewById(R.id.imageButtonEditarCategoria);
            imageButtonEliminarCategoria = itemView.findViewById(R.id.imageButtonEliminarCategoria);
        }

        public void cargarCategorias(Categoria categoria) {
            imageViewCategoria.setImageResource(R.drawable.categoria_img);
            textViewCategoria.setText(categoria.getNombre());
        }
    }
}
