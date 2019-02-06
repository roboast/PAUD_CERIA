package com.example.astidhiyaa.papbl;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.astidhiyaa.papbl.dbHelper.DatabaseHelper;
import com.example.astidhiyaa.papbl.dbHelper.MuridHelper;

public class tambah_murid extends AppCompatActivity {
    private TextInputEditText et_nama, et_noinduk;
    private Button btn_post;
    private Context context;

    private MuridHelper muridHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tambah_murid);
        muridHelper = new MuridHelper(this);

        et_nama = findViewById(R.id.text_nama_murid);
        et_noinduk = findViewById(R.id.text_no_induk_murid);
        btn_post = findViewById(R.id.btnSubmit);

        final MuridModel muridModel = (MuridModel) getIntent().getSerializableExtra("data");



        if (muridModel != null) {
           et_nama.setText(muridModel.getNama());
           et_noinduk.setText(muridModel.getNo_induk());

           btn_post.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   muridModel.setNama(et_nama.getText().toString());
                   muridModel.setNo_induk(et_noinduk.getText().toString());
                   updateData(muridModel);
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
        muridHelper.open();
        MuridModel murid = new MuridModel(et_nama.getText().toString(), et_noinduk.getText().toString());
        muridHelper.insert(murid);
        muridHelper.close();

    }
    public void updateData(final MuridModel murid){
        muridHelper.open();
        MuridModel  mrd = new MuridModel(murid.getId(),et_nama.getText().toString(),et_noinduk.getText().toString());
        muridHelper.update(mrd);
        muridHelper.close();
    }
}
