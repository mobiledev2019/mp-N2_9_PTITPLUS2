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
import android.widget.Button;
import android.widget.EditText;

import com.example.adapter.XeBusAdapter;
import com.example.myappptitplus.LichThiActivity;
import com.example.myappptitplus.R;

public class LichThi  extends Fragment {
    EditText edt_mssv ;
    Button btn_lichthi;
    Activity mContext;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myvView = inflater.inflate(R.layout.activity_lichthi,container ,false);
        edt_mssv = myvView.findViewById(R.id.edt_mssv);
        btn_lichthi = myvView.findViewById(R.id.btn_xemlichthi);
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
        btn_lichthi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edt_mssv.getText().toString();
                if(s.equals("") == false){
                    Intent intent = new Intent(mContext, LichThiActivity.class);
                    intent.putExtra("mssv",s);
                    startActivity(intent);
                }else{
                    edt_mssv.setText("không được để trống !");
                }
            }
        });
    }
}
