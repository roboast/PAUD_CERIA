package com.example.astidhiyaa.papbl;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.example.astidhiyaa.papbl.dbHelper.DatabaseHelper;
import com.example.astidhiyaa.papbl.dbHelper.MuridHelper;

import java.util.ArrayList;

public class list_murid extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MuridAdapter muridAdapter;
    private MuridHelper muridHelper;
    private FloatingActionButton btn_tambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list_murid);

        muridHelper = new MuridHelper(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        muridHelper = new MuridHelper(this);
        muridAdapter = new MuridAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        btn_tambah = findViewById(R.id.btn_tambah);

        getAllData();

        btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tambah = new Intent(list_murid.this,tambah_murid.class);
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
                searchNama(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchNama(s);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void searchNama(String keyword) {
        muridHelper.open();
        ArrayList<MuridModel> muridModels = muridHelper.search(keyword);
        muridHelper.close();
        muridAdapter.searchItem(muridModels);
        recyclerView.setAdapter(muridAdapter);
    }

    private void getAllData() {
        muridHelper.open();
        ArrayList<MuridModel> mahasiswaModels = muridHelper.getAllData();
        muridHelper.close();
        muridAdapter.addItem(mahasiswaModels);
        recyclerView.setAdapter(muridAdapter);

    }

}
