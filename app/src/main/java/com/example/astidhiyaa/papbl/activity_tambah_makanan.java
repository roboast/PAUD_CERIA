package com.example.astidhiyaa.papbl;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.astidhiyaa.papbl.dbHelper.DatabaseHelper;
import com.example.astidhiyaa.papbl.dbHelper.MakananHelper;



public class activity_tambah_makanan extends AppCompatActivity {
    private TextInputEditText et_nama_makanan, et_umur;
    private Button btn_post;
    Context context;

    private MakananHelper makananHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_makanan);
        makananHelper = new MakananHelper(this);

        et_nama_makanan = findViewById(R.id.text_nama_makanan);
        et_umur = findViewById(R.id.text_umur);
        btn_post = findViewById(R.id.btnSubmit);
        final MakananModel makananModel = (MakananModel) getIntent().getSerializableExtra("data");

        if (makananModel != null){
            et_nama_makanan.setText(makananModel.getNama_makanan());
            et_umur.setText(makananModel.getUmur());

            btn_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    makananModel.setNama_makanan(et_nama_makanan.getText().toString());
                    makananModel.setUmur(et_umur.getText().toString());
                    updateData(makananModel);
                }
            });
        }

        else{
            btn_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    insertData();

                }
            });
        }
    }
    public void insertData() {
        makananHelper.open();
        MakananModel makananModel = new MakananModel(et_nama_makanan.getText().toString(), et_umur.getText().toString());
        makananHelper.insert(makananModel);
        makananHelper.close();

    }
    public void updateData(final MakananModel makanan){
        makananHelper.open();
        MakananModel  mkn = new MakananModel(makanan.getId(),et_nama_makanan.getText().toString(),et_umur.getText().toString());
        makananHelper.update(mkn);
        makananHelper.close();

    }
}
