package com.ggomez.misjuegosapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.ggomez.misjuegosapp.models.Plataforma;
import com.ggomez.misjuegosapp.models.Publicador;

import java.util.ArrayList;

public class DBPublicador extends DBHelper {
    Context context;

    public DBPublicador(@Nullable Context context) {
        super(context);

        this.context = context;
    }

    // -------------------------------
    public long insertarPublicador(Publicador publicador) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", publicador.getNombre());

        return db.insert(TABLA_PUBLICADOR, null, values);
    }

    public ArrayList<Publicador> obtenerPublicadores() {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Publicador> publicadores = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from " + TABLA_PUBLICADOR, null);
        if(cursor.moveToFirst()) {
            do {
                Publicador publicador = new Publicador(
                        cursor.getInt(0),
                        cursor.getString(1)
                );
                publicadores.add(publicador);
            } while (cursor.moveToNext());

            return publicadores;
        }

        return null;
    }

    public Publicador obtenerPublicadorPorId(int id_publicador) {
        DBHelper dbHelper = new DBCategoria(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from " + TABLA_PUBLICADOR + " where id_publicador = ?", new String[] {String.valueOf(id_publicador)});
        if(cursor.moveToFirst()) {
            Publicador publicador = new Publicador(
                    cursor.getInt(0),
                    cursor.getString(1)
            );

            return publicador;
        }

        return null;
    }

    // Para usar con el searchView
    public ArrayList<Publicador> buscarPublicador(String nombre) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Publicador> publicadores = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from " + TABLA_PUBLICADOR + " where nombre like ?", new String[] {'%' + nombre + '%'});

        if(cursor.moveToFirst()) {
            do {
                Publicador publicador = new Publicador(
                        cursor.getInt(0),
                        cursor.getString(1)
                );
                publicadores.add(publicador);
            } while (cursor.moveToNext());

            return publicadores;
        }

        return null;
    }

    // -------------------------------
    public int editarPublicador(int id_publicador, String nombre) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", nombre);

        return db.update(TABLA_PUBLICADOR, values, "id_publicador = ?", new String[] {String.valueOf(id_publicador)});
    }

    public int eliminarPublicador(int id_publicador) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        return db.delete(TABLA_PUBLICADOR, "id_publicador = ?", new String[] {String.valueOf(id_publicador)});
    }
}
