package com.ggomez.misjuegosapp.models;

import java.io.Serializable;

public class Publicador implements Serializable {
    private int id_publicador;
    private String nombre;

    public Publicador(int id_publicador, String nombre) {
        this.id_publicador = id_publicador;
        this.nombre = nombre;
    }

    public Publicador(String nombre) {
        this.nombre = nombre;
    }

    public Publicador() {
    }

    public int getId_publicador() {
        return id_publicador;
    }

    public void setId_publicador(int id_publicador) {
        this.id_publicador = id_publicador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.getNombre();
    }
}
