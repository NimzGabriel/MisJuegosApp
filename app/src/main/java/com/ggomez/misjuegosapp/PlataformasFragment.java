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

import com.ggomez.misjuegosapp.adapters.PlataformaAdapter;
import com.ggomez.misjuegosapp.db.DBPlataforma;
import com.ggomez.misjuegosapp.models.Plataforma;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlataformasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlataformasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PlataformasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlataformasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlataformasFragment newInstance(String param1, String param2) {
        PlataformasFragment fragment = new PlataformasFragment();
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

    SearchView searchPlataforma;
    PlataformaAdapter plataformaAdapter;
    RecyclerView recyclerPlataformas;
    FloatingActionButton fabAgregarPlataforma;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plataformas, container, false);

        recyclerPlataformas = view.findViewById(R.id.recyclerPlataformas);
        recyclerPlataformas.setLayoutManager(new LinearLayoutManager(getContext()));
        DBPlataforma dbPlataforma = new DBPlataforma(getContext());
        ArrayList<Plataforma> plataformas = dbPlataforma.obtenerPlataformas();

        if(plataformas != null) {
            plataformaAdapter = new PlataformaAdapter(plataformas);
            recyclerPlataformas.setAdapter(plataformaAdapter);
        }

        fabAgregarPlataforma = view.findViewById(R.id.fabAgregarPlataforma);
        fabAgregarPlataforma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), AgregarPlataformaActivity.class));
            }
        });

        searchPlataforma = view.findViewById(R.id.searchPlataforma);
        searchPlataforma.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<Plataforma> plataformasSearch = dbPlataforma.buscarPlataforma(s);
                if(plataformasSearch != null) {
                    plataformaAdapter = new PlataformaAdapter(plataformasSearch);
                    recyclerPlataformas.setAdapter(plataformaAdapter);
                }

                return true;
            }
        });

        return view;
    }


}