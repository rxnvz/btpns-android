package com.example.android_adapter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android_adapter.R;
import com.example.android_adapter.model.Nasabah;

import java.util.List;

public class NasabahAdapter extends BaseAdapter {
    Context context;
    private List<Nasabah> list;

    public NasabahAdapter(Context context, List<Nasabah> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressWarnings("deprecation")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(R.layout.item_nasabah, null);
        }

        Nasabah nb = list.get(position);
        TextView namaTV = (TextView) convertView.findViewById(R.id.namaTV);
        TextView alamatTV = (TextView) convertView.findViewById(R.id.alamatTV);
        TextView saldoTV = (TextView) convertView.findViewById(R.id.saldoTV);

        namaTV.setText(nb.getNama());
        alamatTV.setText(nb.getAlamat());
        saldoTV.setText(nb.getSaldo().toString());

        return convertView;
    }
}
