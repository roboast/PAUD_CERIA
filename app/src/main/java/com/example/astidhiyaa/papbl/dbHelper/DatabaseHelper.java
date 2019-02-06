package com.example.astidhiyaa.papbl.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.example.astidhiyaa.papbl.dbHelper.DatabaseContract.TABLE_MURID;
import static com.example.astidhiyaa.papbl.dbHelper.DatabaseContract.MuridColumns.NAMA;
import static com.example.astidhiyaa.papbl.dbHelper.DatabaseContract.MuridColumns.NO_INDUK;

import static com.example.astidhiyaa.papbl.dbHelper.DatabaseContract.TABLE_MAKANAN;
import static com.example.astidhiyaa.papbl.dbHelper.DatabaseContract.MakananColumns.NAMA_MAKANAN;
import static com.example.astidhiyaa.papbl.dbHelper.DatabaseContract.MakananColumns.UMUR;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    public static String CREATE_TABLE_MURID = "create table " + TABLE_MURID +
            " (" + _ID + " integer primary key autoincrement, " +
            NAMA + " text not null, " +
            NO_INDUK + " text not null);";

    public static String CREATE_TABLE_MAKANAN = "create table " + TABLE_MAKANAN +
            " (" + _ID + " integer primary key autoincrement, " +
            NAMA_MAKANAN + " text not null, " +
            UMUR + " text not null);";


    private static String DATABASE_NAME = "dbpaud";

    public DatabaseHelper(Context ctx){
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL(CREATE_TABLE_MURID);
        db.execSQL(CREATE_TABLE_MAKANAN);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MURID);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAKANAN);
        onCreate(db);
    }

}
