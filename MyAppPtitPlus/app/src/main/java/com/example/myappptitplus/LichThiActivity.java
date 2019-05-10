package com.example.myappptitplus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.adapter.LichthiAdapter;
import com.example.model.LichThi;
import com.example.model_item.NewsGiaoVu;
import com.example.model_item.Lichthi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class LichThiActivity extends AppCompatActivity {
    TextView txt_mssv ;
    ListView lv_lichthi;
    LichthiAdapter lichthiAdapter;
    ArrayList<Lichthi> arrayListLichthi = new ArrayList<>();
    String url_lichthi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_thi);
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Lịch Thi");
        Intent intent = getIntent();
        String s = intent.getStringExtra("mssv");
        txt_mssv = findViewById(R.id.txt_mssv);
        lv_lichthi = findViewById(R.id.lv_lichthi);
        txt_mssv.setText("Lịch Thi Của Sinh Viên : "+s);

        url_lichthi = "http://qldt.ptit.edu.vn/Default.aspx?page=xemlichthi&id="+s;
        addevents();
    }


    private void addevents() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_lichthi, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String ngaythi = "";
                String monthi = "";
                String giobatdau = "";
                String hinhthuc = "";
                String thoigianthi = "";
                String phongthi = "";
                System.out.println("12344444444444444444444");
                Document document = Jsoup.parse(response);
                if(document != null){
                    System.out.println(url_lichthi);
                    Log.e("CALLAPI", "onResponse: " + document.toString());
                    Elements elements = document.select("tr[onmouseover = className ='rowOnmouseover-GridView ']");
                    for (Element element:elements){
                        System.out.println("toi la tien 123");
                        Element elementngaythi = element.getElementsByTag("span").get(5);
                        Element elementgiobatdau = element.getElementsByTag("span").get(6);
                        Element elementmonthi = element.getElementsByTag("span").get(2);
                        Element elementthoigian = element.getElementsByTag("span").get(7);
                        Element elementhinhthuc = element.getElementsByTag("span").get(9);
                        Element elementphongthi = element.getElementsByTag("span").get(8);

                        if(elementngaythi != null){
                            ngaythi = elementngaythi.text();
                        }
                        if(elementgiobatdau != null){
                            giobatdau = elementgiobatdau.text();
                        }
                        if(elementmonthi != null){
                            monthi = elementmonthi.text();
                        }
                        if(elementthoigian != null){
                            thoigianthi = elementthoigian.text();
                        }
                        if(elementhinhthuc != null){
                            hinhthuc = elementhinhthuc.text();
                        }
                        if(elementphongthi != null){
                            phongthi = elementphongthi.text();
                        }
                        String NgayThi = ngaythi +" "+giobatdau;
                        String Phut = thoigianthi +"("+hinhthuc+")";
                        System.out.println(NgayThi);
                        arrayListLichthi.add(new Lichthi(NgayThi,monthi,Phut,phongthi));
                        System.out.println(arrayListLichthi);

                    }
                    lichthiAdapter = new LichthiAdapter(arrayListLichthi,LichThiActivity.this);
                    lv_lichthi.setAdapter(lichthiAdapter);
////                    newsGiaoVuAdapter = new NewsGiaoVuAdapter(arrayListNewsGiaoVu,this);
////                    lv_newGiaoVu.setAdapter(newsGiaoVuAdapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
        requestQueue.start();
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
