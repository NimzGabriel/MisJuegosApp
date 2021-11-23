package com.ggomez.misjuegosapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.ggomez.misjuegosapp.adapters.JuegoAdapter;
import com.ggomez.misjuegosapp.db.DBCategoria;
import com.ggomez.misjuegosapp.db.DBJuego;
import com.ggomez.misjuegosapp.db.DBPublicador;
import com.ggomez.misjuegosapp.models.Categoria;
import com.ggomez.misjuegosapp.models.Juego;
import com.ggomez.misjuegosapp.models.Publicador;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MisJuegosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MisJuegosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MisJuegosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MisJuegosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MisJuegosFragment newInstance(String param1, String param2) {
        MisJuegosFragment fragment = new MisJuegosFragment();
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

    SearchView searchJuegos;
    JuegoAdapter adapter;
    RecyclerView recyclerMisJuegos;
    FloatingActionButton fabAgregarJuego;
    Spinner spinnerFiltroCategoria, spinnerFiltroPublicador;
    Button buttonFiltrar, buttonLimpiarFiltro;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mis_juegos, container, false);

        recyclerMisJuegos = view.findViewById(R.id.recyclerMisJuegos);
        recyclerMisJuegos.setLayoutManager(new LinearLayoutManager(getContext()));
        DBJuego dbJuego = new DBJuego(getContext());
        ArrayList<Juego> juegos = dbJuego.obtenerJuegos();

        if(juegos != null) {
            adapter = new JuegoAdapter(juegos);
            recyclerMisJuegos.setAdapter(adapter);
        }

        fabAgregarJuego = view.findViewById(R.id.fabAgregarJuego);
        fabAgregarJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), AgregarJuegoActivity.class));
            }
        });

        searchJuegos = view.findViewById(R.id.searchJuegos);
        searchJuegos.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<Juego> juegosSearch = dbJuego.buscarJuego(s);
                if(juegosSearch != null) {
                    adapter = new JuegoAdapter(juegosSearch);
                    recyclerMisJuegos.setAdapter(adapter);
                }

                return true;
            }
        });

        buttonFiltrar = view.findViewById(R.id.buttonFiltrar);
        buttonLimpiarFiltro = view.findViewById(R.id.buttonLimpiarFiltro);
        spinnerFiltroCategoria = view.findViewById(R.id.spinnerFiltroCategoria);
        spinnerFiltroPublicador = view.findViewById(R.id.spinnerFiltroPublicador);
        cargarCategorias();
        cargarPublicadores();

        spinnerFiltroCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(spinnerFiltroPublicador.getSelectedItem().toString().equals("Seleccione")) {
                    Categoria categoria = (Categoria) adapterView.getSelectedItem();
                    ArrayList<Juego> filtrarJuegosPorCategoria = dbJuego.filtrarJuegoPorCategoria(categoria.getId_categoria());
                    buttonFiltrar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(filtrarJuegosPorCategoria != null) {
                                adapter = new JuegoAdapter(filtrarJuegosPorCategoria);
                                recyclerMisJuegos.setAdapter(adapter);
                            } else {
                                Toast.makeText(getContext(), "No se encontraron resultados", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Filtro por publicador
        spinnerFiltroPublicador.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(spinnerFiltroCategoria.getSelectedItem().toString().equals("Seleccione")) {
                    Publicador publicador = (Publicador) adapterView.getSelectedItem();
                    ArrayList<Juego> filtrarJuegosPorPublicador = dbJuego.filtrarJuegoPorPublicador(publicador.getId_publicador());
                    buttonFiltrar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(filtrarJuegosPorPublicador != null) {
                                adapter = new JuegoAdapter(filtrarJuegosPorPublicador);
                                recyclerMisJuegos.setAdapter(adapter);
                            } else {
                                Toast.makeText(getContext(), "No se encontraron resultados", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        buttonLimpiarFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinnerFiltroCategoria.setSelection(0);
                spinnerFiltroPublicador.setSelection(0);
                if(juegos != null) {
                    adapter = new JuegoAdapter(juegos);
                    recyclerMisJuegos.setAdapter(adapter);
                }
            }
        });

        // Filtro por categoria y publicador
        buttonFiltrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Categoria categoria =(Categoria) spinnerFiltroCategoria.getSelectedItem();
                Publicador publicador = (Publicador) spinnerFiltroPublicador.getSelectedItem();
                ArrayList<Juego> juegosCategoriaYPublicador = dbJuego.filtrarPorCategoriaYPublicador(categoria.getId_categoria(), publicador.getId_publicador());
                if(juegosCategoriaYPublicador != null) {
                    adapter = new JuegoAdapter(juegosCategoriaYPublicador);
                    recyclerMisJuegos.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), "No se encontraron resultados", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }

    public void cargarCategorias() {
        DBCategoria dbCategoria = new DBCategoria(getContext());
        ArrayList<Categoria> categorias = dbCategoria.obtenerCategorias();
        categorias.add(0, new Categoria("Seleccione"));

        if(categorias != null) {
            ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(
                    getContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    categorias
            );
            spinnerFiltroCategoria.setAdapter(adapter);

        }
    }

    public void cargarPublicadores() {
        DBPublicador dbPublicador = new DBPublicador(getContext());
        ArrayList<Publicador> publicadores = dbPublicador.obtenerPublicadores();
        publicadores.add(0, new Publicador("Seleccione"));

        if(publicadores != null) {
            ArrayAdapter<Publicador> adapter = new ArrayAdapter<>(
                    getContext(),
                    android.R.layout.simple_spinner_dropdown_item,
                    publicadores
            );
            spinnerFiltroPublicador.setAdapter(adapter);
        }
    }
}