package com.example.android_crud.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_crud.R;
import com.example.android_crud.ViewActivity;
import com.example.android_crud.model.Nasabah;

import java.util.ArrayList;

public class NasabahAdapter extends RecyclerView.Adapter<NasabahAdapter.NasabahViewHolder> {
    Context context;
    ArrayList<Nasabah> nbs;

    public NasabahAdapter(Context context, ArrayList<Nasabah> nasabahs) {
        this.context = context;
        this.nbs = nasabahs;
    }

    @NonNull
    @Override
    public NasabahAdapter.NasabahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_nasabah, parent, false);
        return new NasabahViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NasabahAdapter.NasabahViewHolder holder, int position) {
        holder.namaTV.setText(nbs.get(position).getNama());
        holder.alamatTV.setText(nbs.get(position).getAlamat());
        holder.emailTV.setText(nbs.get(position).getEmail());

        holder.nasabahLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), nbs.get(position).getNama(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, ViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("mode", "edit");
                bundle.putString("id", nbs.get(position).getId().toString());
                bundle.putString("nama", nbs.get(position).getNama());
                bundle.putString("alamat", nbs.get(position).getAlamat());
                bundle.putString("email", nbs.get(position).getEmail());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return nbs.size();
    }

    public class NasabahViewHolder extends RecyclerView.ViewHolder {
        TextView namaTV;
        TextView alamatTV;
        TextView emailTV;
        ImageView nasabahIV;
        LinearLayout nasabahLL;

        public NasabahViewHolder(@NonNull View itemView) {
            super(itemView);

            namaTV = itemView.findViewById(R.id.namaTV);
            alamatTV = itemView.findViewById(R.id.alamatTV);
            emailTV = itemView.findViewById(R.id.emailTV);
            nasabahIV = itemView.findViewById(R.id.nasabahIV);
            nasabahLL = itemView.findViewById(R.id.nasabahLL);
        }
    }
}
