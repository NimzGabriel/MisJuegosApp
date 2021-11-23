package com.ggomez.misjuegosapp.models;

import java.io.Serializable;

public class Juego implements Serializable {
    private int id_juego;
    private String nombre;
    private String descripcion;
    private String restriccion;
    private int anio;
    private float calificacion;
    Categoria categoria;
    Plataforma plataforma;
    Publicador publicador;

    public Juego(int id_juego, String nombre, String descripcion, String restriccion, int anio, float calificacion, Categoria categoria, Plataforma plataforma, Publicador publicador) {
        this.id_juego = id_juego;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.restriccion = restriccion;
        this.anio = anio;
        this.calificacion = calificacion;
        this.categoria = categoria;
        this.plataforma = plataforma;
        this.publicador = publicador;
    }

    public Juego(String nombre, String descripcion, String restriccion, int anio, float calificacion, Categoria categoria, Plataforma plataforma, Publicador publicador) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.restriccion = restriccion;
        this.anio = anio;
        this.calificacion = calificacion;
        this.categoria = categoria;
        this.plataforma = plataforma;
        this.publicador = publicador;
    }

    public Juego() {
    }

    public int getId_juego() {
        return id_juego;
    }

    public void setId_juego(int id_juego) {
        this.id_juego = id_juego;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRestriccion() {
        return restriccion;
    }

    public void setRestriccion(String restriccion) {
        this.restriccion = restriccion;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

    public Publicador getPublicador() {
        return publicador;
    }

    public void setPublicador(Publicador publicador) {
        this.publicador = publicador;
    }
}
