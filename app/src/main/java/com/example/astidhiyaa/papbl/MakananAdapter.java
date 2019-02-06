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
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import com.example.astidhiyaa.papbl.dbHelper.MakananHelper;


public class MakananAdapter extends RecyclerView.Adapter<MakananAdapter.CustomViewHolder> {


    private LayoutInflater mInflater;
    private ArrayList<MakananModel> makananModels;

    private Context context;
    private MakananHelper makananHelper;


    public MakananAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        makananHelper = new MakananHelper(context);

    }
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.item_makanan, viewGroup, false);
        CustomViewHolder vh = new CustomViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {

        final String nama_makanan = makananModels.get(position).getNama_makanan();
        final String umur = makananModels.get(position).getUmur();
        holder.nama_makanan.setText(nama_makanan);
        holder.umur.setText(umur);

        holder.btnupdate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,activity_tambah_makanan.class);
                intent.putExtra("data",makananModels.get(position));
                ((Activity)context).startActivity(intent);

            }
        });
        holder.btndelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteitem(makananModels.get(position).getId());
                makananModels.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    private void deleteitem(int id) {
        makananHelper.open();
        makananHelper.delete(id);
        makananHelper.close();
        Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return makananModels.size();
    }

    public void addItem(ArrayList<MakananModel> mData) {
        this.makananModels = mData;
        notifyDataSetChanged();
    }

    public void searchItem(ArrayList<MakananModel> mData) {
        this.makananModels = mData;
        notifyDataSetChanged();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        private TextView nama_makanan, umur;
        private ImageView btnupdate, btndelete;
        private CardView cv;

        public CustomViewHolder(View itemView) {
            super(itemView);

            nama_makanan = (TextView) itemView.findViewById(R.id.text_nama_makanan);
            umur = (TextView) itemView.findViewById(R.id.text_umur);
            btnupdate = (ImageView) itemView.findViewById(R.id.btn_update);
            btndelete = (ImageView) itemView.findViewById(R.id.btn_delete);
            cv = (CardView) itemView.findViewById(R.id.cv_makanan);
        }
    }
}
