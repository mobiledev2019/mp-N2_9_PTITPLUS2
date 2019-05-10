package com.example.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.model.LichThi;
import com.example.model_item.Lichthi;
import com.example.myappptitplus.R;

import java.util.ArrayList;

public class LichthiAdapter extends BaseAdapter {
    Context context;
    ArrayList<Lichthi> arrayListLichthi;

    public LichthiAdapter(ArrayList<Lichthi> arrayListNewsGiaoVu, Context context) {
        this.arrayListLichthi = arrayListNewsGiaoVu;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListLichthi.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListLichthi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_lichthi,null);

        TextView txt_ngaythi = convertView.findViewById(R.id.txt_ngaythi);
        TextView txt_monthi = convertView.findViewById(R.id.txt_monthi);
        TextView txt_timethi =  convertView.findViewById(R.id.txt_timethi);
        TextView txt_phongthi = convertView.findViewById(R.id.txt_phongthi);

        Lichthi lich = (Lichthi) getItem(position);
        txt_ngaythi.setText(lich.getNgayThi());
        txt_monthi.setText(lich.getTenMonThi());
        txt_timethi.setText(lich.getThoiGian());
        txt_phongthi.setText(lich.getPhongThi());

        return convertView;
    }
}
