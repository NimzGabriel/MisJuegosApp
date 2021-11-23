package com.ggomez.misjuegosapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NOMBRE = "db_mis_juegos";
    public static final int DB_VERSION = 2;
    public static final String TABLA_USUARIO = "usuario";
    public static final String TABLA_CATEGORIA = "categoria";
    public static final String TABLA_PLATAFORMA = "plataforma";
    public static final String TABLA_PUBLICADOR = "publicador";
    public static final String TABLA_JUEGO = "juego";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NOMBRE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLA_USUARIO + "(id_usuario integer primary key autoincrement, nombres text not null, apellidos text not null, email text not null, clave text not null)");

        db.execSQL("create table " + TABLA_CATEGORIA + "(id_categoria integer primary key autoincrement, nombre text not null)");

        db.execSQL("create table " + TABLA_PLATAFORMA + "(id_plataforma integer primary key autoincrement, nombre text not null)");

        db.execSQL("create table " + TABLA_PUBLICADOR + "(id_publicador integer primary key autoincrement, nombre text not null)");

        db.execSQL("create table " + TABLA_JUEGO + "(id_juego integer primary key autoincrement, nombre text not null, descripcion text not null, restriccion text not null, anio integer not null, calificacion real not null, id_categoria integer not null, id_plataforma integer not null, id_publicador integer not null, foreign key (id_categoria) references categoria(id_categoria), foreign key (id_plataforma) references plataforma(id_plataforma), foreign key (id_publicador) references publicador(id_publicador))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + TABLA_USUARIO);
        db.execSQL("drop table if exists " + TABLA_CATEGORIA);
        db.execSQL("drop table if exists " + TABLA_PLATAFORMA);
        db.execSQL("drop table if exists " + TABLA_PUBLICADOR);
        db.execSQL("drop table if exists " + TABLA_JUEGO);

        onCreate(db);
    }
}
