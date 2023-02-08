package com.example.juegosinsprites_carlosprez.Clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class PuntuacionesDAO extends SQLiteOpenHelper {
    private static final int DB_Version = 2;
    private static final String nameDB = "Puntuaciones.db";

    public PuntuacionesDAO(Context context) {
        super(context, nameDB, null, DB_Version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE \"Puntuacion\" (\n" +
                "\t\"Nombre\"\tTEXT NOT NULL,\n" +
                "\t\"Puntuación\"\tINTEGER\n" +
                ")");

        db.execSQL("INSERT INTO Puntuacion(Nombre, Puntuación) \n" +
                "VALUES(\"Carlos\", 0), \n" +
                "(\"Luis\",1),\n" +
                "(\"Pablo\",2),\n" +
                "(\"Marta\",1)");
    }

    public long insertarPuntuación(Puntuacion p){
        long id = 0;
        try{
            SQLiteDatabase bbdd = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put("Nombre", p.getNombre());
            values.put("Puntuación",p.getCaidas());

            id = bbdd.insert("Puntuacion",null,values);
        }
        catch (Exception e){

        }
        return id;
    }

    public ArrayList<Puntuacion> devolverPuntuaciones(){
        SQLiteDatabase bbdd = getWritableDatabase();
        ArrayList<Puntuacion> listaPuntuacion = new ArrayList<>();
        Cursor c = bbdd.rawQuery("SELECT * from Puntuacion order by Puntuación",null);

        c.moveToFirst();
        while(c.isAfterLast()==false){
            Puntuacion p = new Puntuacion(c.getString(0), c.getInt(1));
            listaPuntuacion.add(p);
            c.moveToNext();
        }
        c.close();
        return listaPuntuacion;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
