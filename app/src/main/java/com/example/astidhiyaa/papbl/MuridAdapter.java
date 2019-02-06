package com.example.astidhiyaa.papbl;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import com.example.astidhiyaa.papbl.dbHelper.MuridHelper;


public class MuridAdapter extends RecyclerView.Adapter<MuridAdapter.CustomViewHolder> {
    private LayoutInflater mInflater;
    private ArrayList<MuridModel> murid;
    private Context context;
    private MuridHelper muridHelper;


    public MuridAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        muridHelper = new MuridHelper(context);
    }
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.item_murid, viewGroup, false);
        CustomViewHolder vh = new CustomViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {
        final String nama = murid.get(position).getNama();
        final String no_induk = murid.get(position).getNo_induk();
        holder.nama_murid.setText(nama);
        holder.no_induk_murid.setText(no_induk);

        holder.btnupdate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,tambah_murid.class);
                intent.putExtra("data",murid.get(position));
                ((Activity)context).startActivity(intent);
            }
        });
        holder.btndelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteitem(murid.get(position).getId());
                murid.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    private void deleteitem(int id) {
        muridHelper.open();
        muridHelper.delete(id);
        muridHelper.close();

        Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();

    }

    public int getItemCount() {
        return murid.size();
    }

    public void addItem(ArrayList<MuridModel> mData) {
        this.murid = mData;
        notifyDataSetChanged();
    }
    public void searchItem(ArrayList<MuridModel> mData) {
        this.murid = mData;
        notifyDataSetChanged();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        private TextView nama_murid, no_induk_murid;
        private ImageView btnupdate, btndelete;
        private CardView cv;

        public CustomViewHolder(View itemView) {
            super(itemView);

            nama_murid = (TextView) itemView.findViewById(R.id.text_nama_murid);
            no_induk_murid = (TextView) itemView.findViewById(R.id.text_no_induk_murid);
            btnupdate = (ImageView) itemView.findViewById(R.id.btn_update);
            btndelete = (ImageView) itemView.findViewById(R.id.btn_delete);
            cv = (CardView) itemView.findViewById(R.id.cv);

        }

    }


}
