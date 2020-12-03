package com.example.android_butterknife.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_butterknife.R;
import com.example.android_butterknife.model.Nasabah;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NasabahAdapter extends RecyclerView.Adapter<NasabahAdapter.NasabahViewHolder> {
    Context context;
    ArrayList<Nasabah> nbs;

    public NasabahAdapter(Context context, ArrayList<Nasabah> nbs) {
        this.context = context;
        this.nbs = nbs;
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

        holder.nbLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), nbs.get(position).getNama(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return nbs.size();
    }

    public class NasabahViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.namaTV) TextView namaTV;
        @BindView(R.id.alamatTV) TextView alamatTV;
        @BindView(R.id.emailTV) TextView emailTV;
        @BindView(R.id.nbIV) ImageView nbIV;
        @BindView(R.id.nbLL) LinearLayout nbLL;

        public NasabahViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
