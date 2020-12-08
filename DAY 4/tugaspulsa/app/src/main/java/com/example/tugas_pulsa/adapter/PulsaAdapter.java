package com.example.tugas_pulsa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas_pulsa.R;
import com.example.tugas_pulsa.model.Product;
import com.example.tugas_pulsa.util.Util;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PulsaAdapter extends RecyclerView.Adapter<PulsaAdapter.PulsaViewHolder> {
    Context context;
    ArrayList<Product> products;
    TextView hargaTV;
    LinearLayout bayarPulsa;

    public PulsaAdapter(Context context, ArrayList<Product> products, TextView hargaTV, LinearLayout bayarPulsa) {
        this.context = context;
        this.products = products;
        this.hargaTV = hargaTV;
        this.bayarPulsa = bayarPulsa;
    }

    @NonNull
    @Override
    public PulsaAdapter.PulsaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pulsa, parent, false);
        return new PulsaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PulsaAdapter.PulsaViewHolder holder, int position) {
        holder.nominalPulsa.setText(String.valueOf(products.get(position).getNominal()));
        holder.pulsaCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.expand(bayarPulsa, 2);
                hargaTV.setText(String.valueOf(products.get(position).getPrice()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class PulsaViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.nominalPulsa) TextView nominalPulsa;
        @BindView(R.id.pulsaCV) CardView pulsaCV;

        public PulsaViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
