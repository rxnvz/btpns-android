package com.example.tugas_berita.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas_berita.R;
import com.example.tugas_berita.model.Berita;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder>{
    Context context;
    ArrayList<Berita> newsArr;

    public BeritaAdapter(Context context, ArrayList<Berita> newsArr) {
        this.context = context;
        this.newsArr = newsArr;
    }


    @NonNull
    @Override
    public BeritaAdapter.BeritaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false);
        return new BeritaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeritaAdapter.BeritaViewHolder holder, int position) {
        holder.judulTV.setText(newsArr.get(position).getTitle());
        holder.tagTV.setText(newsArr.get(position).getCategory());
        Picasso.get().load(newsArr.get(position).getUrl()).into(holder.urlImg);
    }

    @Override
    public int getItemCount() {
        return newsArr.size();
    }

    public class BeritaViewHolder extends RecyclerView.ViewHolder {
        TextView judulTV;
        TextView tagTV;
        ImageView urlImg;

        public BeritaViewHolder(@NonNull View itemView) {
            super(itemView);
            judulTV = itemView.findViewById(R.id.titleTV);
            tagTV = itemView.findViewById(R.id.categoryTV);
            urlImg = itemView.findViewById(R.id.imgTV);
        }
    }
}
