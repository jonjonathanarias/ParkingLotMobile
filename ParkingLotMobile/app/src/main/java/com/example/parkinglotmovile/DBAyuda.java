package com.example.parkinglotmovile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBAyuda extends SQLiteOpenHelper {
    public DBAyuda(@Nullable Context context, @Nullable String name,
                   @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super(context, name,factory, version);

    }
    @Override
    public void onCreate(SQLiteDatabase db){
        //db.execSQL("create");
        db.execSQL("create table usuarios(id_usuario integer PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "nombreUsuario text NOT NULL, claveUsuario text NOT NULL)");
        db.execSQL("create table clientes(id_clientes integer PRIMARY KEY AUTOINCREMENT NOT NULL,"+
                "id_usuario integer ,nombre text NOT NULL, apellido text NOT NULL, telefono number NOT NULL, email text NOT NULL, FOREIGN KEY (id_usuario) REFERENCES usuarios)");
        db.execSQL("create table vehiculos(patente text PRIMARY KEY NOT NULL," +
                "id_cliente integer ,vehiculo text NOT NULL, modelo text NOT NULL, FOREIGN KEY(id_cliente) REFERENCES clientes)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("create table usuarios(id_usuario integer  PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "nombreusuario text NOT NULL,claveUsuario text NOT NULL)");
        db.execSQL("insert into usuarios(nombreUsuario,claveUsuario) values('admin','admin')");
    }
}
