package com.example.myappptitplus;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.model_item.class_urlthongbao;

public class SettingActivity extends AppCompatActivity {

    Button btn_submit_thongbao ;
    CheckBox chk_giaovu , chk_ttkt;
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
    }

    private void addcontrols() {
        btn_submit_thongbao = findViewById(R.id.btn_submit_thongbao);
        chk_giaovu = findViewById(R.id.chk_giaovu);
        chk_ttkt = findViewById(R.id.chk_ttkt);
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
