package com.example.myappptitplus;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.model_item.NewsHome;
import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity {
    private ImageView img_new ;
    private TextView txt_context;
    Activity contextimg ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Tin Chi Tiết");
        addcontrols();

    }
    // lua chon bam quay tro lai
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

    private void addcontrols() {
        img_new = findViewById(R.id.img_detail_new_home);
        txt_context = findViewById(R.id.txt_news_detail_home);
        Intent intent = getIntent();
//        lay du lieu bang intent
        String url = intent.getStringExtra("newsimage");
        String noidung = intent.getStringExtra("contxt");
        Picasso.with(contextimg).load(url).into(img_new);
        txt_context.setText(noidung);
    }
}
