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
        txt_content.setText(intent.getStringExtra("context"));
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
