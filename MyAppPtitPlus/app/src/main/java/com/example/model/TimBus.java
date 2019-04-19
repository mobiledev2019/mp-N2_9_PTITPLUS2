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
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.adapter.XeBusAdapter;
import com.example.model_item.xebus;
import com.example.myappptitplus.BusActivity;
import com.example.myappptitplus.R;

public class TimBus  extends Fragment {
    ListView lv_danhsachbus ;
    XeBusAdapter xeBusAdapter;
    Activity mContext;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myvView = inflater.inflate(R.layout.activity_bus,container ,false);
        lv_danhsachbus =  myvView.findViewById(R.id.lv_danhsachbus);
        xeBusAdapter = new XeBusAdapter(mContext,R.layout.item_bus);
        fakeData();
        lv_danhsachbus.setAdapter(xeBusAdapter);
        return myvView;
    }

    private void fakeData() {
        xeBusAdapter.add(new xebus("BX Gia Lâm - BX Yên Nghĩa","01",21.048635f,105.878638f,20.950385f,105.747881f));
        xeBusAdapter.add(new xebus("Bác Cổ - BX Yên Nghĩa","02",21.024904f,105.860314f,20.950385f,105.747881f));
        xeBusAdapter.add(new xebus("Trần Khánh Dư - Thiên Đường Bảo Sơn","19",21.016175f,105.862408f,20.999072f,105.733572f));
        xeBusAdapter.add(new xebus("BX Giáp Bát - BX Yên Nghĩa","21",20.980435f,105.841444f,20.950385f,105.747881f));
        xeBusAdapter.add(new xebus("BX Giáp Bát - KĐT Dương Nội","22",20.980435f,105.841444f,20.973121f,105.757270f));
        xeBusAdapter.add(new xebus("BX Nam Thăng Long - BX Yên Nghĩa","27",21.069453f,105.785970f,20.950385f,105.747881f));
        xeBusAdapter.add(new xebus("Xuân Đỉnh - BX Yên Nghĩa","33",21.071435f,105.790615f,20.950385f,105.747881f));
        xeBusAdapter.add(new xebus("CV Nghĩa Đô - Tứ Hiệp","39",21.040768f,105.796753f,20.945532f,105.859493f));
        xeBusAdapter.add(new xebus("BX Mỹ Đình - Tế Tiêu","78",21.028730f,105.778279f,20.685780f,105.738468f));
        xeBusAdapter.add(new xebus("Cầu Giấy - BX Yên Nghĩa","85",20.957376f,105.737568f,20.950385f,105.747881f));
        xeBusAdapter.add(new xebus("BX Mỹ Đình - Hương Sơn","103",21.028730f,105.778279f,20.613665f,105.787780f));

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (Activity)context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv_danhsachbus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                xebus xb = (xebus) xeBusAdapter.getItem(position);
                Intent intent = new Intent(mContext,BusActivity.class);
                intent.putExtra("xb",xb);
                startActivity(intent);
            }
        });
    }
}
