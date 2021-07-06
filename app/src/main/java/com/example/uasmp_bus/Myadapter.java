package com.example.uasmp_bus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<Myadapter.myviewholder>
{

    private ArrayList<dataBis> bisArrayList;
    Context context;

    public Myadapter(ArrayList<dataBis> bisArrayList, Context context) {
        this.bisArrayList = bisArrayList;
        this.context=context;
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bus,parent,false);
        return new myviewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        final dataBis temp=bisArrayList.get(position);

        holder.bis_nama.setText(bisArrayList.get(position).getBis_nama());
        holder.bis_fasilitas.setText(bisArrayList.get(position).getBis_fasilitas());
        holder.bis_harga.setText(bisArrayList.get(position).getBis_harga());
        holder.bis_waktuber.setText(bisArrayList.get(position).getBis_waktuber());
        holder.bis_waktuti.setText(bisArrayList.get(position).getBis_waktuti());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mAdapterCallback.onRowMakananAdapterClicked(getAdapterPosition());
                Intent intent=new Intent(context,Main3Activity.class);
//                intent.putExtra("DESTINATION", des_ed.getText().toString());
//                intent.putExtra("FROM", from_ed.getText().toString());
//                intent.putExtra("DATE", date_ed.getText().toString());
//                intent.putExtra("NAMA", name_ed.getText().toString());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bisArrayList.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {

        TextView bis_nama, bis_harga, bis_fasilitas, bis_waktuber, bis_waktuti,name_ed ,from_ed,des_ed,date_ed;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            bis_nama = itemView.findViewById(R.id.nama_bis);
            bis_harga = itemView.findViewById(R.id.harga_bis);
            bis_fasilitas = itemView.findViewById(R.id.fasilitas_bis);
            bis_waktuber = itemView.findViewById(R.id.waktu_bisber);
            bis_waktuti = itemView.findViewById(R.id.waktu_bisti);
             name_ed = itemView.findViewById(R.id.nameview);
             from_ed = itemView.findViewById(R.id.fromview);
             des_ed = itemView.findViewById(R.id.desview);
             date_ed = itemView.findViewById(R.id.dateview);
        }

    }
}