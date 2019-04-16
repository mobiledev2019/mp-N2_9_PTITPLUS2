package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.model_item.NewsGiaoVu;
import com.example.myappptitplus.R;

import java.util.ArrayList;

public class NewsGiaoVuAdapter extends BaseAdapter {

    ArrayList<NewsGiaoVu> arrayListNewsGiaoVu ;
    Context context;

    public NewsGiaoVuAdapter(ArrayList<NewsGiaoVu> arrayListNewsGiaoVu, Context context) {
        this.arrayListNewsGiaoVu = arrayListNewsGiaoVu;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListNewsGiaoVu.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListNewsGiaoVu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_new_giaovu,null);
        TextView txt_time = convertView.findViewById(R.id.txt_time_giaovu);
        TextView txt_title = convertView.findViewById(R.id.txt_tilte_giaovu);
        TextView txt_context = convertView.findViewById(R.id.txt_context_giaovu);
        TextView txt_tingiaovu = convertView.findViewById(R.id.txt_tingiaovu);

        NewsGiaoVu newsGiaoVu = (NewsGiaoVu) getItem(position);
        txt_time.setText(newsGiaoVu.time);
        txt_title.setText(newsGiaoVu.title);
        txt_context.setText(newsGiaoVu.context);
        txt_tingiaovu.setText(newsGiaoVu.tingiaovu);

        Animation animation = AnimationUtils.loadAnimation(context,R.anim.translate_new);
        convertView.startAnimation(animation);
        return convertView;
    }
}
