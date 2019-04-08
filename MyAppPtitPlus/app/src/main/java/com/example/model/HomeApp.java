package com.example.model;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.adapter.NewsHomeAdapter;
import com.example.model_item.NewsHome;
import com.example.model_item.class_home;
import com.example.myappptitplus.MainActivity;
import com.example.myappptitplus.NewsDetailActivity;
import com.example.myappptitplus.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class HomeApp extends Fragment {
    NewsHomeAdapter newsHomeAdapter;
    ListView lv_newhome ;
    String url_home = "http://portal.ptit.edu.vn/category/tin-tuc/" ;
    ArrayList<NewsHome> arraylistNewshome = new ArrayList<>();
    NewsHome selectNew = null;
    Activity mContext;
    private String con = "";
    private String context ="";
    private String context2 ="";
    private String context3 ="";
    private String context4 ="";
    private String context5 ="";
    private String context6 ="";
    private String context7 ="";
    String[] classDiv = class_home.classDivHome;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myvView = inflater.inflate(R.layout.activity_home,container ,false);
        lv_newhome = myvView.findViewById(R.id.lv_newhome);
        return myvView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Loading !");
        progressDialog.setCancelable(true);
        progressDialog.show();
        super.onViewCreated(view, savedInstanceState);
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_home, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String name = "";
                String image = "";
                String url = "";
                Document document = Jsoup.parse(response);
                if(document != null){
                    Log.e("CALLAPI", "onResponse: " + document.toString());
                    for(int i = 0 ;i < classDiv.length ;i++){
                        Elements elements = document.select(classDiv[i]);
                        for (Element element : elements){
                            Element element1context = element.getElementsByTag("h2").first();
                            Element element1image = element.getElementsByTag("img").first();
                            Element element1url = element.getElementsByTag("a").first();
                            if(element1context != null){
                                name = element1context.text();
                            }
                            if(element1image != null){
                                image = element1image.attr("src");
                            }
                            if(element1image != null){
                                url = element1url.attr("href");
                            }
                            arraylistNewshome.add(new NewsHome(name, image, url,null));
                            progressDialog.dismiss();
                            System.out.println(arraylistNewshome);
                        }
                        newsHomeAdapter = new NewsHomeAdapter(arraylistNewshome,mContext);
                        lv_newhome.setAdapter(newsHomeAdapter);

                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("CALLAPI", "onErrorResponse: " + error.getMessage());
            }
        });
        requestQueue.add(stringRequest);
        requestQueue.start();

        addevents();
    }

    private void addevents() {
        lv_newhome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectNew = (NewsHome) newsHomeAdapter.getItem(position);
                System.out.println(selectNew.url);
                hienthichitiettin();
            }
        });
    }

    private void hienthichitiettin() {
        // khoi tao mot man hinh moi
        final Intent intent = new Intent(mContext, NewsDetailActivity.class);
        // lay du lieu
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, selectNew.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Document document = Jsoup.parse(response);
                if(document != null){
                    Log.e("CALLAPI", "onResponse: " + document.toString());
                        Elements elements = document.select("div[class=post-wrapper-content]");
                        for (Element element : elements){
                            Element element1context = element.getElementsByTag("strong").first();
                            Element element1context2 = element.getElementsByTag("p").get(2);
                            Element element1context3 = element.getElementsByTag("p").get(3);
//
//
                            if(element1context != null){
                                context = element1context.text();
                            }
                            if(element1context2 != null){
                                context2 = element1context2.text();
                            }
                            if(element1context3 != null){
                                context3 = element1context3.text();
                            }
                            selectNew.context=(context+context2+context3+context4+context5+context6+context7);
                            // gui du lieu sang ben bang intent
                            intent.putExtra("newsimage",selectNew.image);
                            intent.putExtra("contxt",selectNew.context);
                            startActivity(intent);
                            // goi ham animatiom
                        }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("CALLAPI", "onErrorResponse: " + error.getMessage());
            }
        });
        //thuc hien lenh
        requestQueue.add(stringRequest);
        requestQueue.start();

    }

}
