package com.example.astidhiyaa.papbl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView btn_murid, btn_makanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_murid = findViewById(R.id.btn_murid);
        btn_makanan = findViewById(R.id.btn_makanan);

        btn_murid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent murid = new Intent(MainActivity.this,list_murid.class);
                startActivity(murid);
            }
        });

        btn_makanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mkn = new Intent(MainActivity.this,activity_list_makanan.class);
                startActivity(mkn);
            }
        });

    }
}
