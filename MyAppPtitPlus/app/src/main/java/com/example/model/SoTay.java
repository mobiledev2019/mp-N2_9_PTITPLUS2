package com.example.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.myappptitplus.ATTT_Activity;
import com.example.myappptitplus.R;

public class SoTay extends Fragment {
    Button btn_attt , btn_cntt , btn_kt , btn_qtkd , btn_dpt;
    Context context;
    Activity mContext;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myvView = inflater.inflate(R.layout.note,container ,false);

 //       Animation animation =  AnimationUtils.loadAnimation( context,R.anim.translate_list);
        btn_attt = myvView.findViewById(R.id.btn_attt);
//        btn_attt.startAnimation(animation);
        btn_cntt = myvView.findViewById(R.id.btn_cntt);
 //       btn_cntt.startAnimation(animation);
        btn_dpt = myvView.findViewById(R.id.btn_dpt);
 //       btn_dpt.startAnimation(animation);
        btn_kt = myvView.findViewById(R.id.btn_kt);
 //       btn_kt.startAnimation(animation);
        btn_qtkd = myvView.findViewById(R.id.btn_qtkd);
 //       btn_qtkd.startAnimation(animation);

        return myvView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (Activity)context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_attt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ATTT_Activity.class);
                intent.putExtra("title","Ngành An Toàn Thông Tin");
                startActivity(intent);
            }
        });

        btn_cntt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ATTT_Activity.class);
                intent.putExtra("title","Ngành Công Nghệ Thông Tin");
                startActivity(intent);
            }
        });

        btn_qtkd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ATTT_Activity.class);
                intent.putExtra("title","Ngành Quản Trị Kinh Doanh");
                startActivity(intent);
            }
        });

        btn_kt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ATTT_Activity.class);
                intent.putExtra("title","Ngành Kinh Tế");
                startActivity(intent);
            }
        });

        btn_dpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ATTT_Activity.class);
                intent.putExtra("title","Ngành Đa Phương Tiện");
                startActivity(intent);
            }
        });
    }
}
