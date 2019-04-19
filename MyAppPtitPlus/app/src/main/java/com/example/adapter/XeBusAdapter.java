package com.example.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.model_item.xebus;
import com.example.myappptitplus.R;

public class XeBusAdapter extends ArrayAdapter {
    Activity context;
    int resource ;
    public XeBusAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        this.context = (Activity) context;
        this.resource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = context.getLayoutInflater().inflate(resource,null);

        TextView txt_idbus = view.findViewById(R.id.txt_idbus);
        TextView txt_namebus = view.findViewById(R.id.txt_namebus);

        xebus xb = (xebus) getItem(position);
        txt_idbus.setText(xb.getId());
        txt_namebus.setText(xb.getTen());

        return view;
    }
}
