package com.example.astidhiyaa.papbl;

import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;

import com.example.astidhiyaa.papbl.dbHelper.DatabaseHelper;
import com.example.astidhiyaa.papbl.dbHelper.MakananHelper;



import java.util.ArrayList;

public class activity_list_makanan extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MakananAdapter makananAdapter;
    private MakananHelper makananHelper;
    private FloatingActionButton btn_tambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_makanan);

        makananHelper = new MakananHelper(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_makanan);
        makananHelper = new MakananHelper(this);
        makananAdapter = new MakananAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        btn_tambah = findViewById(R.id.btn_tambah);


        getAllData();


       btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tambah = new Intent(activity_list_makanan.this,activity_tambah_makanan.class);
                startActivity(tambah);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchMakan(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchMakan(s);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void searchMakan(String keyword) {
        makananHelper.open();
        ArrayList<MakananModel> makananModels = makananHelper.search(keyword);
        makananHelper.close();
        makananAdapter.searchItem(makananModels);
        recyclerView.setAdapter(makananAdapter);
    }

    private void getAllData() {
        makananHelper.open();
        ArrayList<MakananModel> makananModels = makananHelper.getAllData();
        makananHelper.close();
        makananAdapter.addItem(makananModels);
        recyclerView.setAdapter(makananAdapter);

    }
}
