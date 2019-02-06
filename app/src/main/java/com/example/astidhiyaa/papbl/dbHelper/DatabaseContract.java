package com.example.astidhiyaa.papbl.dbHelper;

import android.provider.BaseColumns;

public class DatabaseContract {

    static String TABLE_MURID = "table_murid";
    static String TABLE_MAKANAN = "table_makanan";

    static final class MuridColumns implements BaseColumns{
        static String NAMA = "nama";
        static String NO_INDUK = "no_induk";
    }
    static final class MakananColumns implements BaseColumns{
        static String NAMA_MAKANAN = "nama_makanan";
        static String UMUR = "umur";
    }

}
