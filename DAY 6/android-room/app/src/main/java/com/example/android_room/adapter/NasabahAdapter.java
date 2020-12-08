package com.example.android_room.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_room.R;
import com.example.android_room.model.Nasabah;

public class NasabahAdapter extends ListAdapter<Nasabah, NasabahAdapter.NasabahHolder> {
    private OnItemClickListener listener;

    public NasabahAdapter() {
        super(DIFF_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<Nasabah> DIFF_CALLBACK = new DiffUtil.ItemCallback<Nasabah>() {
        @Override
        public boolean areItemsTheSame(@NonNull Nasabah oldItem, @NonNull Nasabah newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Nasabah oldItem, @NonNull Nasabah newItem) {
            return oldItem.getNama().equals(newItem.getNama()) &&
                    oldItem.getAlamat().equals(newItem.getAlamat()) &&
                    oldItem.getEmail().equals(newItem.getEmail());
        }
    };

    @NonNull
    @Override
    public NasabahHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nasabah, parent, false);
        return new NasabahHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NasabahHolder holder, int position) {
        Nasabah currentNB = getItem(position);
        holder.namaTV.setText(currentNB.getNama());
        holder.alamatTV.setText(currentNB.getAlamat());
        holder.emailTV.setText(currentNB.getEmail());
    }

    public Nasabah getNasabahAt(int position) {
        return getItem(position);
    }

    class NasabahHolder extends RecyclerView.ViewHolder {
        private TextView namaTV;
        private TextView alamatTV;
        private TextView emailTV;

        public NasabahHolder(View itemView) {
            super(itemView);
            namaTV = itemView.findViewById(R.id.namaTV);
            alamatTV = itemView.findViewById(R.id.alamatTV);
            emailTV = itemView.findViewById(R.id.emailTV);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Log.d("isi position adapter: ", String.valueOf(position));
                    if (listener !=  null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Nasabah note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
