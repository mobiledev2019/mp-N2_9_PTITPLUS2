package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.model_item.NewsHome;
import com.example.myappptitplus.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsHomeAdapter extends BaseAdapter {
    ArrayList<NewsHome> arraylistNewsHome ;
    Context context ;

    public NewsHomeAdapter(ArrayList<NewsHome> arraylistNewsHome, Context context) {
        this.arraylistNewsHome = arraylistNewsHome;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arraylistNewsHome.size();
    }

    @Override
    public Object getItem(int position) {
        return arraylistNewsHome.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_new_home,null);
        TextView txt_newhome = convertView.findViewById(R.id.txt_newhome);
        ImageView img_newhome = convertView.findViewById(R.id.img_newhome);

        NewsHome newsHome = (NewsHome) getItem(position);
        txt_newhome.setText(newsHome.name);

        Picasso.with(context).load(newsHome.image).into(img_newhome);
        return convertView;
    }
}
