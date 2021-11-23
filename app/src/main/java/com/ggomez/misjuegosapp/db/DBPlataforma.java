package com.ggomez.misjuegosapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.ggomez.misjuegosapp.models.Categoria;
import com.ggomez.misjuegosapp.models.Plataforma;

import java.util.ArrayList;

public class DBPlataforma extends DBCategoria{
    Context context;

    public DBPlataforma(@Nullable Context context) {
        super(context);

        this.context = context;
    }

    // ---------------------------

    public long insertarPlataforma(Plataforma plataforma) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", plataforma.getNombre());

        return db.insert(TABLA_PLATAFORMA, null, values);
    }

    public ArrayList<Plataforma> obtenerPlataformas() {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Plataforma> plataformas = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from " + TABLA_PLATAFORMA, null);
        if (cursor.moveToFirst()) {
            do {
                Plataforma plataforma = new Plataforma(
                        cursor.getInt(0),
                        cursor.getString(1)
                );
                plataformas.add(plataforma);
            } while (cursor.moveToNext());

            return plataformas;
        }

        return null;
    }

    // searchView
    public ArrayList<Plataforma> buscarPlataforma(String nombre) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Plataforma> plataformas = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from " + TABLA_PLATAFORMA + " where nombre like ?", new String[] {'%' + nombre + '%'});
        if (cursor.moveToFirst()) {
            do {
                Plataforma plataforma = new Plataforma(
                        cursor.getInt(0),
                        cursor.getString(1)
                );
                plataformas.add(plataforma);
            } while (cursor.moveToNext());

            return plataformas;
        }

        return null;
    }

    public Plataforma obtenerPlataformaPorId(int id_plataforma) {
        DBHelper dbHelper = new DBCategoria(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from " + TABLA_PLATAFORMA + " where id_plataforma = ?", new String[] {String.valueOf(id_plataforma)});
        if(cursor.moveToFirst()) {
            Plataforma plataforma = new Plataforma(
                    cursor.getInt(0),
                    cursor.getString(1)
            );

            return plataforma;
        }

        return null;
    }

    // ---------------------------

    public int actualizarPlataforma(int id_plataforma, String nombre) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", nombre);

        return db.update(TABLA_PLATAFORMA, values, "id_plataforma = ?", new String[] {String.valueOf(id_plataforma)});
    }

    public int eliminarPlataforma(int id_plataforma) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        return db.delete(TABLA_PLATAFORMA, "id_plataforma = ?", new String[] {String.valueOf(id_plataforma)});
    }

}
