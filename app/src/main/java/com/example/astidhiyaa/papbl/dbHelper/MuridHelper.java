package com.example.astidhiyaa.papbl.dbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;


import com.example.astidhiyaa.papbl.MuridModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.astidhiyaa.papbl.dbHelper.DatabaseContract.TABLE_MURID;
import static com.example.astidhiyaa.papbl.dbHelper.DatabaseContract.MuridColumns.NAMA;
import static com.example.astidhiyaa.papbl.dbHelper.DatabaseContract.MuridColumns.NO_INDUK;

public class MuridHelper {

    private Context ctx;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public MuridHelper(Context ctx) {
        this.ctx = ctx;
    }

    public MuridHelper open() throws SQLException {
        dbHelper = new DatabaseHelper(ctx);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public ArrayList<MuridModel> getAllData() {
        Cursor crsr = db.query(TABLE_MURID, null, null, null, null, null, _ID + " DESC", null);
        crsr.moveToFirst();
        ArrayList<MuridModel> arrayList = new ArrayList<>();
        MuridModel muridModel;

        if (crsr.getCount() > 0){
            do {
                muridModel = new MuridModel();
                muridModel.setId(crsr.getInt(crsr.getColumnIndexOrThrow(_ID)));
                muridModel.setNama(crsr.getString(crsr.getColumnIndexOrThrow(NAMA)));
                muridModel.setNo_induk(crsr.getString(crsr.getColumnIndexOrThrow(NO_INDUK)));
                arrayList.add(muridModel);
                crsr.moveToNext();

            }while (!crsr.isAfterLast());
        }
        crsr.close();
        return arrayList;
    }
    public ArrayList<MuridModel> search(String keyword) {
        ArrayList<MuridModel> muridModels = null;
        try {

            Cursor cursor = db.rawQuery("select * from " + TABLE_MURID + " where " + NAMA + " like ?", new String[] { "%" + keyword + "%" });
            if (cursor.moveToFirst()) {
                muridModels = new ArrayList<MuridModel>();
                do {
                    MuridModel murid = new MuridModel();
                    murid.setId(cursor.getInt(0));
                    murid.setNama(cursor.getString(1));
                    murid.setNo_induk(cursor.getString(2));
                    muridModels.add(murid);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            muridModels = null;
        }
        return muridModels;
    }
    public long insert(MuridModel muridModel){
        ContentValues intVal = new ContentValues();
        intVal.put(NAMA,muridModel.getNama());
        intVal.put(NO_INDUK,muridModel.getNo_induk());
        return db.insert(TABLE_MURID, null, intVal);
    }

    public int update(MuridModel muridModel){
        ContentValues args = new ContentValues();
        args.put(NAMA, muridModel.getNama());
        args.put(NO_INDUK,muridModel.getNo_induk());
        return db.update(TABLE_MURID, args, _ID + " = '" + muridModel.getId()+"'",null);
    }

    public int delete (int id){
        return db.delete(TABLE_MURID,_ID+" = '"+ id +"'",null);
    }
}
