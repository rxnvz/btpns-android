package com.example.berita_restapi.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.berita_restapi.R;
import com.example.berita_restapi.model.Berita;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder> {
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
        System.out.println("Isi judul di onBindViewHolder: " + newsArr.get(position).getJudul());
        holder.judulTV.setText(newsArr.get(position).getJudul());
        holder.tagTV.setText(newsArr.get(position).getTags());
//        holder.urlImg.setImageURI(Uri.parse(newsArr.get(position).getPhotoURL()));
//        Glide.with(holder.urlImg.getContext()).load(newsArr.get(position).getPhotoURL()).into(holder.urlImg);
        Picasso.get().load(newsArr.get(position).getPhotoURL()).into(holder.urlImg);
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
            tagTV = itemView.findViewById(R.id.tagsTV);
            urlImg = itemView.findViewById(R.id.imageTV);
        }
    }
}
