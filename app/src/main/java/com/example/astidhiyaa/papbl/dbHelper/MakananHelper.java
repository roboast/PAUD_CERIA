package com.example.astidhiyaa.papbl.dbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.astidhiyaa.papbl.MakananModel;
import com.example.astidhiyaa.papbl.MuridModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.example.astidhiyaa.papbl.dbHelper.DatabaseContract.TABLE_MAKANAN;
import static com.example.astidhiyaa.papbl.dbHelper.DatabaseContract.MakananColumns.NAMA_MAKANAN;
import static com.example.astidhiyaa.papbl.dbHelper.DatabaseContract.MakananColumns.UMUR;

public class MakananHelper {

    private Context ctx;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public MakananHelper(Context ctx) {
        this.ctx = ctx;
    }

    public MakananHelper open() throws SQLException {
        dbHelper = new DatabaseHelper(ctx);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public ArrayList<MakananModel> getAllData() {
        Cursor crsr = db.query(TABLE_MAKANAN, null, null, null, null, null, _ID + " DESC", null);
        crsr.moveToFirst();
        ArrayList<MakananModel> arrayList = new ArrayList<>();
        MakananModel makananModel;

        if (crsr.getCount() > 0){
            do {
                makananModel = new MakananModel();
                makananModel.setId(crsr.getInt(crsr.getColumnIndexOrThrow(_ID)));
                makananModel.setNama_makanan(crsr.getString(crsr.getColumnIndexOrThrow(NAMA_MAKANAN)));
                makananModel.setUmur(crsr.getString(crsr.getColumnIndexOrThrow(UMUR)));
                arrayList.add(makananModel);
                crsr.moveToNext();

            }while (!crsr.isAfterLast());
        }
        crsr.close();
        return arrayList;
    }

    public ArrayList<MakananModel> search(String keyword) {
        ArrayList<MakananModel> makananModels = null;
        try {

            Cursor cursor = db.rawQuery("select * from " + TABLE_MAKANAN + " where " + NAMA_MAKANAN + " like ?", new String[] { "%" + keyword + "%" });
            if (cursor.moveToFirst()) {
                makananModels = new ArrayList<MakananModel>();
                do {
                    MakananModel makan = new MakananModel();
                    makan.setId(cursor.getInt(0));
                    makan.setNama_makanan(cursor.getString(1));
                    makan.setUmur(cursor.getString(2));
                    makananModels.add(makan);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            makananModels = null;
        }
        return makananModels;
    }

    public long insert(MakananModel makananModel){
        ContentValues intVal = new ContentValues();
        intVal.put(NAMA_MAKANAN,makananModel.getNama_makanan());
        intVal.put(UMUR,makananModel.getUmur());
        return db.insert(TABLE_MAKANAN, null, intVal);
    }

    public int update(MakananModel makananModel){
        ContentValues args = new ContentValues();
        args.put(NAMA_MAKANAN, makananModel.getNama_makanan());
        args.put(UMUR,makananModel.getUmur());
        return db.update(TABLE_MAKANAN, args, _ID + " = '" + makananModel.getId()+"'",null);
    }

    public int delete (int id){
        return db.delete(TABLE_MAKANAN,_ID+" = '"+ id +"'",null);
    }
}
