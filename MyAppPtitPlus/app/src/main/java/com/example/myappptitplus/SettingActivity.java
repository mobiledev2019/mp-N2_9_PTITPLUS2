package com.example.myappptitplus;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.adapter.NewsGiaoVuAdapter;
import com.example.model_item.class_urlthongbao;
import com.example.model_item.danhmuc;
import com.example.model_item.soluongtin;

import static com.example.model_item.soluongtin.soluonghienthi;

public class SettingActivity extends AppCompatActivity {

    Button btn_submit_thongbao ;
    CheckBox chk_giaovu , chk_ttkt;
    Spinner sp_choice;
    ArrayAdapter<danhmuc> danhmucArrayAdapter;
    NewsGiaoVuAdapter newsGiaoVuAdapter;
    danhmuc selectdanhmuc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Cài Đặt");
        addcontrols();
        addevents();
    }

    private void addevents() {
        btn_submit_thongbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chk_giaovu.isChecked()){
                    class_urlthongbao.url_thongbao ="http://portal.ptit.edu.vn/giaovu/category/thong-bao/";
                    Toast.makeText(SettingActivity.this,"Thông Báo : Giáo Vụ" ,Toast.LENGTH_SHORT).show();
                }
                else if (chk_ttkt.isChecked()){
                    class_urlthongbao.url_thongbao ="http://portal.ptit.edu.vn/ttkt/";
                    Toast.makeText(SettingActivity.this,"Thông Báo : Trung Tâm Khảo Thí" ,Toast.LENGTH_SHORT).show();
                }
            }
        });

        sp_choice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectdanhmuc = danhmucArrayAdapter.getItem(position);
                int x = Integer.parseInt(selectdanhmuc.getTendm());
                System.out.println(x);
                soluongtin.soluonghienthi = x;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void addcontrols() {
        btn_submit_thongbao = findViewById(R.id.btn_submit_thongbao);
        chk_giaovu = findViewById(R.id.chk_giaovu);
        chk_ttkt = findViewById(R.id.chk_ttkt);
        sp_choice = findViewById(R.id.spin_choice);

        danhmucArrayAdapter = new ArrayAdapter<danhmuc>(
                this,
                android.R.layout.simple_list_item_1
        );
        danhmucArrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sp_choice.setAdapter(danhmucArrayAdapter);

        danhmucArrayAdapter.add(new danhmuc("0","0"));
        danhmucArrayAdapter.add(new danhmuc("1","1"));
        danhmucArrayAdapter.add(new danhmuc("2","2"));
        danhmucArrayAdapter.add(new danhmuc("4","3"));
        danhmucArrayAdapter.add(new danhmuc("5","4"));
        danhmucArrayAdapter.add(new danhmuc("6","5"));
        danhmucArrayAdapter.add(new danhmuc("7","6"));
        danhmucArrayAdapter.add(new danhmuc("8","7"));
        //khoi tao cac gia tri lưu data
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
}
