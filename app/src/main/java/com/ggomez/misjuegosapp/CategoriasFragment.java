package com.ggomez.misjuegosapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.ggomez.misjuegosapp.adapters.CategoriaAdapter;
import com.ggomez.misjuegosapp.db.DBCategoria;
import com.ggomez.misjuegosapp.models.Categoria;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoriasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoriasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CategoriasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoriasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoriasFragment newInstance(String param1, String param2) {
        CategoriasFragment fragment = new CategoriasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    CategoriaAdapter categoriaAdapter;
    SearchView searchCategoria;
    RecyclerView recyclerCategorias;
    FloatingActionButton fabAgregarCategoria;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate
        View view = inflater.inflate(R.layout.fragment_categorias, container, false);

        recyclerCategorias = view.findViewById(R.id.recyclerCategorias);
        recyclerCategorias.setLayoutManager(new LinearLayoutManager(getContext()));
        DBCategoria dbCategoria = new DBCategoria(getContext());
        ArrayList<Categoria> categorias = dbCategoria.obtenerCategorias();

        if(categorias != null) {
            categoriaAdapter = new CategoriaAdapter(categorias);
            recyclerCategorias.setAdapter(categoriaAdapter);
        }

        fabAgregarCategoria = view.findViewById(R.id.fabAgregarCategoria);
        fabAgregarCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), AgregarCategoriaActivity.class));
            }
        });

        searchCategoria = view.findViewById(R.id.searchCategoria);
        searchCategoria.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<Categoria> categoriasSearch = dbCategoria.buscarCategoria(s);
                if(categoriasSearch != null) {
                    categoriaAdapter = new CategoriaAdapter(categoriasSearch);
                    recyclerCategorias.setAdapter(categoriaAdapter);
                }

                return true;
            }
        });

        return view;
    }
}