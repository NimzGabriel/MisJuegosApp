package com.ggomez.misjuegosapp;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.ggomez.misjuegosapp.adapters.PublicadorAdapter;
import com.ggomez.misjuegosapp.db.DBPlataforma;
import com.ggomez.misjuegosapp.db.DBPublicador;
import com.ggomez.misjuegosapp.models.Publicador;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PublicadoresFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PublicadoresFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PublicadoresFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PublicadoresFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PublicadoresFragment newInstance(String param1, String param2) {
        PublicadoresFragment fragment = new PublicadoresFragment();
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

    PublicadorAdapter adapter;
    RecyclerView recyclerPublicadores;
    FloatingActionButton fabAgregarPublicador;
    SearchView searchPublicador;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_publicadores, container, false);

        recyclerPublicadores = view.findViewById(R.id.recyclerPublicadores);
        recyclerPublicadores.setLayoutManager(new LinearLayoutManager(getContext()));

        DBPublicador dbPublicador = new DBPublicador(getContext());
        ArrayList<Publicador> publicadores = dbPublicador.obtenerPublicadores();

        if(publicadores != null) {
            adapter = new PublicadorAdapter(publicadores);
            recyclerPublicadores.setAdapter(adapter);
        }

        fabAgregarPublicador = view.findViewById(R.id.fabAgregarPublicador);
        fabAgregarPublicador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), AgregarPublicadorActivity.class));
            }
        });

        // SearchView
        searchPublicador = view.findViewById(R.id.searchPublicador);
        searchPublicador.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<Publicador> publicadoresSearch = dbPublicador.buscarPublicador(s);
                if(publicadoresSearch != null) {
                    adapter = new PublicadorAdapter(publicadoresSearch);
                    recyclerPublicadores.setAdapter(adapter);
                }

                return true;
            }
        });

        return view;
    }
}