package com.example.myappptitplus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ATTT_Activity extends AppCompatActivity {
    TextView txttitleNote ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attt_);
        txttitleNote = findViewById(R.id.txt_titleNote);
        Intent intent = getIntent();
        String s = intent.getStringExtra("title");
        txttitleNote.setText(s);
    }
}
