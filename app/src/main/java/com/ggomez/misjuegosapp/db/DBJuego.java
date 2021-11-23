package com.ggomez.misjuegosapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.ggomez.misjuegosapp.models.Categoria;
import com.ggomez.misjuegosapp.models.Juego;
import com.ggomez.misjuegosapp.models.Plataforma;
import com.ggomez.misjuegosapp.models.Publicador;

import java.util.ArrayList;

public class DBJuego extends DBHelper {
    Context context;

    public DBJuego(@Nullable Context context) {
        super(context);

        this.context = context;
    }

    // ------------------------
    public long insertarJuego(Juego juego) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", juego.getNombre());
        values.put("descripcion", juego.getDescripcion());
        values.put("restriccion", juego.getRestriccion());
        values.put("anio", juego.getAnio());
        values.put("calificacion", juego.getCalificacion());

        values.put("id_categoria", juego.getCategoria().getId_categoria());
        values.put("id_plataforma", juego.getPlataforma().getId_plataforma());
        values.put("id_publicador", juego.getPublicador().getId_publicador());

        return db.insert(TABLA_JUEGO, null, values);
    }

    public ArrayList<Juego> obtenerJuegos() {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Juego> juegos = new ArrayList<>();

        DBCategoria dbCategoria = new DBCategoria(context);
        DBPlataforma dbPlataforma = new DBPlataforma(context);
        DBPublicador dbPublicador = new DBPublicador(context);

        Cursor cursor = db.rawQuery("select * from " + TABLA_JUEGO, null);
        if(cursor.moveToFirst()) {
            do {
                Categoria categoria = dbCategoria.obtenerCategoriaPorId(cursor.getInt(6));
                Plataforma plataforma = dbPlataforma.obtenerPlataformaPorId(cursor.getInt(7));
                Publicador publicador = dbPublicador.obtenerPublicadorPorId(cursor.getInt(8));

                Juego juego = new Juego(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getFloat(5),
                        categoria,
                        plataforma,
                        publicador

                );
                juegos.add(juego);

            } while (cursor.moveToNext());

            return juegos;
        }
        return null;
    }

    // searchView
    public ArrayList<Juego> buscarJuego(String nombre) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Juego> juegos = new ArrayList<>();

        DBCategoria dbCategoria = new DBCategoria(context);
        DBPlataforma dbPlataforma = new DBPlataforma(context);
        DBPublicador dbPublicador = new DBPublicador(context);

        Cursor cursor = db.rawQuery("select * from " + TABLA_JUEGO + " where nombre like ?", new String[] {'%' + nombre + '%'});
        if(cursor.moveToFirst()) {
            do {
                Categoria categoria = dbCategoria.obtenerCategoriaPorId(cursor.getInt(6));
                Plataforma plataforma = dbPlataforma.obtenerPlataformaPorId(cursor.getInt(7));
                Publicador publicador = dbPublicador.obtenerPublicadorPorId(cursor.getInt(8));

                Juego juego = new Juego(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getFloat(5),
                        categoria,
                        plataforma,
                        publicador

                );
                juegos.add(juego);

            } while (cursor.moveToNext());

            return juegos;
        }
        return null;
    }


    // Filtros
    public ArrayList<Juego> filtrarJuegoPorCategoria(int id_categoria) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Juego> juegos = new ArrayList<>();

        DBCategoria dbCategoria = new DBCategoria(context);
        DBPlataforma dbPlataforma = new DBPlataforma(context);
        DBPublicador dbPublicador = new DBPublicador(context);

        Cursor cursor = db.rawQuery("select * from " + TABLA_JUEGO + " where id_categoria = ?", new String[] {String.valueOf(id_categoria)});
        if(cursor.moveToFirst()) {
            do {
                Categoria categoria = dbCategoria.obtenerCategoriaPorId(cursor.getInt(6));
                Plataforma plataforma = dbPlataforma.obtenerPlataformaPorId(cursor.getInt(7));
                Publicador publicador = dbPublicador.obtenerPublicadorPorId(cursor.getInt(8));

                Juego juego = new Juego(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getFloat(5),
                        categoria,
                        plataforma,
                        publicador

                );
                juegos.add(juego);

            } while (cursor.moveToNext());

            return juegos;
        }
        return null;
    }

    public ArrayList<Juego> filtrarJuegoPorPublicador(int id_publicador) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Juego> juegos = new ArrayList<>();

        DBCategoria dbCategoria = new DBCategoria(context);
        DBPlataforma dbPlataforma = new DBPlataforma(context);
        DBPublicador dbPublicador = new DBPublicador(context);

        Cursor cursor = db.rawQuery("select * from " + TABLA_JUEGO + " where id_publicador = ?", new String[] {String.valueOf(id_publicador)});
        if(cursor.moveToFirst()) {
            do {
                Categoria categoria = dbCategoria.obtenerCategoriaPorId(cursor.getInt(6));
                Plataforma plataforma = dbPlataforma.obtenerPlataformaPorId(cursor.getInt(7));
                Publicador publicador = dbPublicador.obtenerPublicadorPorId(cursor.getInt(8));

                Juego juego = new Juego(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getFloat(5),
                        categoria,
                        plataforma,
                        publicador

                );
                juegos.add(juego);

            } while (cursor.moveToNext());

            return juegos;
        }
        return null;
    }

    public ArrayList<Juego> filtrarPorCategoriaYPublicador(int id_categoria, int id_publicador) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Juego> juegos = new ArrayList<>();

        DBCategoria dbCategoria = new DBCategoria(context);
        DBPlataforma dbPlataforma = new DBPlataforma(context);
        DBPublicador dbPublicador = new DBPublicador(context);

        Cursor cursor = db.rawQuery("select * from " + TABLA_JUEGO + " where id_categoria = ? and id_publicador = ?", new String[] {String.valueOf(id_categoria), String.valueOf(id_publicador)});
        if(cursor.moveToFirst()) {
            do {
                Categoria categoria = dbCategoria.obtenerCategoriaPorId(cursor.getInt(6));
                Plataforma plataforma = dbPlataforma.obtenerPlataformaPorId(cursor.getInt(7));
                Publicador publicador = dbPublicador.obtenerPublicadorPorId(cursor.getInt(8));

                Juego juego = new Juego(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getFloat(5),
                        categoria,
                        plataforma,
                        publicador

                );
                juegos.add(juego);

            } while (cursor.moveToNext());

            return juegos;
        }
        return null;
    }

    // ---------------------------
    public int actualizarJuego(int id_juego, String nombre, String descripcion, String restriccion, int anio, float calificacion, Categoria categoria, Plataforma plataforma, Publicador publicador) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("descripcion", descripcion);
        values.put("restriccion", restriccion);
        values.put("anio", anio);
        values.put("calificacion", calificacion);
        values.put("id_categoria", categoria.getId_categoria());
        values.put("id_plataforma", plataforma.getId_plataforma());
        values.put("id_publicador", publicador.getId_publicador());

        return db.update(TABLA_JUEGO, values, "id_juego = ?", new String[] {String.valueOf(id_juego)});
    }

    public int eliminarJuego(int id_juego) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        return db.delete(TABLA_JUEGO, "id_juego = ?", new String[] {String.valueOf(id_juego)});
    }

}
