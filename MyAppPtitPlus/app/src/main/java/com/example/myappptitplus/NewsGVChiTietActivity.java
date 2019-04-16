package com.example.myappptitplus;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class NewsGVChiTietActivity extends AppCompatActivity {
    TextView txt_title , txt_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_gvchi_tiet);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Tin Chi Tiết");
        txt_title = findViewById(R.id.txt_title_chitiet_GV);
        txt_content = findViewById(R.id.txt_context_chitiet_GV);
        Intent intent = getIntent();

        txt_title.setText(intent.getStringExtra("title"));
        txt_content.setText(intent.getStringExtra("context")+"Học viện Công nghệ Bưu chính Viễn thông là một tổ chức Nghiên cứu - Giáo dục Đào tạo có thương hiệu, uy tín với thế mạnh về Nghiên cứu và đào tạo Đại học, Sau Đại học trong lĩnh vực Công nghệ Thông tin và Truyền thông. Học viện là cơ sở đào tạo công lập trực thuộc Bộ Thông tin và Truyền thông");
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
