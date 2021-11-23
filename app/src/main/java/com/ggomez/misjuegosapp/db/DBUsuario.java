package com.ggomez.misjuegosapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.ggomez.misjuegosapp.models.Usuario;

public class DBUsuario extends DBHelper {
    Context context;

    public DBUsuario(@Nullable Context context) {
        super(context);

        this.context = context;
    }

    // ----------------------
    public long registrarUsuario(Usuario usuario) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombres", usuario.getNombres());
        values.put("apellidos", usuario.getApellidos());
        values.put("email", usuario.getEmail());
        values.put("clave", usuario.getClave());

        return db.insert(TABLA_USUARIO, null, values);
    }

    public Usuario iniciarSesion(String email, String clave) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from " + TABLA_USUARIO + " where email = ? and clave = ?", new String[] {email, clave});

        if(cursor.moveToFirst()) {
            Usuario usuario = new Usuario(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
            );

            return usuario;
        }

        return null;
    }

}
