package com.ggomez.misjuegosapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.ggomez.misjuegosapp.models.Categoria;

import java.util.ArrayList;

public class DBCategoria extends DBHelper {
    Context context;

    public DBCategoria(@Nullable Context context) {
        super(context);

        this.context = context;
    }

    // ------------------------------

    public long insertarCategoria(Categoria categoria) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", categoria.getNombre());

        return db.insert(TABLA_CATEGORIA, null, values);
    }

    public ArrayList<Categoria> obtenerCategorias() {
        DBHelper dbHelper = new DBCategoria(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Categoria> categorias = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from " + TABLA_CATEGORIA, null);
        if(cursor.moveToFirst()) {
            do {
                Categoria categoria = new Categoria(
                  cursor.getInt(0),
                  cursor.getString(1)
                );
                categorias.add(categoria);

            } while (cursor.moveToNext());

            return categorias;
        }

        return null;
    }

    // searchView
    public ArrayList<Categoria> buscarCategoria(String nombre) {
        DBHelper dbHelper = new DBCategoria(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Categoria> categorias = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from " + TABLA_CATEGORIA + " where nombre like ?", new String[] {'%' + nombre + '%'});
        if(cursor.moveToFirst()) {
            do {
                Categoria categoria = new Categoria(
                        cursor.getInt(0),
                        cursor.getString(1)
                );
                categorias.add(categoria);

            } while (cursor.moveToNext());

            return categorias;
        }

        return null;
    }

    public Categoria obtenerCategoriaPorId(int id_categoria) {
        DBHelper dbHelper = new DBCategoria(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from " + TABLA_CATEGORIA + " where id_categoria = ?", new String[] {String.valueOf(id_categoria)});
        if(cursor.moveToFirst()) {
            Categoria categoria = new Categoria(
                    cursor.getInt(0),
                    cursor.getString(1)
            );

            return categoria;
        }

        return null;
    }

    // ------------------------------

    public int actualizarCategoria(int id_categoria, String nombre) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", nombre);

        return db.update(TABLA_CATEGORIA, values, "id_categoria = ?", new String[] {String.valueOf(id_categoria)});
    }

    public int eliminarCategoria(int id_categoria) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        return db.delete(TABLA_CATEGORIA, "id_categoria = ?", new String[] {String.valueOf(id_categoria)});
    }

}
