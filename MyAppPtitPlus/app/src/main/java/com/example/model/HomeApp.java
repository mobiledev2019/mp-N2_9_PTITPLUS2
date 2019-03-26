package com.example.model;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.myappptitplus.R;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class HomeApp extends Fragment {


    NewsHomeAdapter newsHomeAdapter;
    ListView lv_newhome ;
    ImageView img_new_chitiet;
    TextView txt_new_chitiet;
    String url_home = "http://portal.ptit.edu.vn/category/tin-tuc/" ;
    ArrayList<NewsHome> arraylistNewshome = new ArrayList<>();
    NewsHome selectNew = null;
    Activity mContext;
    Context contextimg;

    String[] classDiv = {"div[class=post-item isotope-item clearfix author-buihieu post-9056 post type-post status-publish format-standard has-post-thumbnail hentry category-tin-tuc]",
            "div[class=post-item isotope-item clearfix author-buihieu post-9048 post type-post status-publish format-standard has-post-thumbnail hentry category-tin-tuc]",
            "div[class=post-item isotope-item clearfix author-buihieu post-9063 post type-post status-publish format-standard has-post-thumbnail hentry category-tin-tuc]",
            "div[class=post-item isotope-item clearfix author-buihieu post-9018 post type-post status-publish format-standard has-post-thumbnail hentry category-tin-tuc]",
            "div[class=post-item isotope-item clearfix author-buihieu post-8944 post type-post status-publish format-standard has-post-thumbnail hentry category-tin-tuc]",
            "div[class=post-item isotope-item clearfix author-buihieu post-9080 post type-post status-publish format-standard has-post-thumbnail hentry category-tin-tuc]",
            "div[class=post-item isotope-item clearfix author-buihieu post-8844 post type-post status-publish format-standard has-post-thumbnail hentry category-tin-tuc]",
            "div[class=post-item isotope-item clearfix author-buihieu post-8790 post type-post status-publish format-standard has-post-thumbnail hentry category-tin-tuc]",
            "div[class=post-item isotope-item clearfix author-buihieu post-8776 post type-post status-publish format-standard has-post-thumbnail hentry category-tin-tuc]",
            "div[class=post-item isotope-item clearfix author-buihieu post-8730 post type-post status-publish format-standard has-post-thumbnail hentry category-tin-tuc]",
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myvView = inflater.inflate(R.layout.activity_home,container ,false);
        getActivity();
        lv_newhome = myvView.findViewById(R.id.lv_newhome);

        return myvView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
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
                            arraylistNewshome.add(new NewsHome(name, image, url));
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
        final Dialog dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.new_home_chitiet);
        txt_new_chitiet = dialog.findViewById(R.id.txt_new_chitiet);
        img_new_chitiet = dialog.findViewById(R.id.img_anh_chitiet);
        // lay du lieu
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, selectNew.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String context ="";
                String context2 ="";
                String context3 ="";
                String context4 ="";
                String context5 ="";
                String context6 ="";
                String context7 ="";
                Document document = Jsoup.parse(response);
                if(document != null){
                    Log.e("CALLAPI", "onResponse: " + document.toString());
                        Elements elements = document.select("div[class=post-wrapper-content]");
                        for (Element element : elements){
                            Element element1context = element.getElementsByTag("strong").first();
                            Element element1context2 = element.getElementsByTag("p").get(2);
                            Element element1context3 = element.getElementsByTag("p").get(3);
//                            Element element1context4 = null;
//                            Element element1context5 = null;
//                            Element element1context6 = null;
//                            Element element1context7 = null;
//                            if(element.getElementsByTag("p").get(4) != null){
//                                 element1context4 = element.getElementsByTag("p").get(4); }
//                            if(element.getElementsByTag("p").get(5) != null){
//                             element1context5 = element.getElementsByTag("p").get(5);}
//                            if(element.getElementsByTag("p").get(6) != null){
//                                 element1context6 = element.getElementsByTag("p").get(6);}
//                            if(element.getElementsByTag("p").get(7) != null){
//                                element1context7 = element.getElementsByTag("p").get(7);}
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
//                            if(element1context4 != null){
//                                context4 = element1context4.text();
//                            }
//                            if(element1context5 != null){
//                                context5 = element1context5.text();
//                            }
//                            if(element1context6 != null){
//                                context6 = element1context6.text();
//                            }
//                            if(element1context7 != null){
//                                context7 = element1context7.text();
//                            }
                            System.out.println(context);
                            System.out.println(context2);
                            Picasso.with(contextimg).load(selectNew.image).into(img_new_chitiet);
                            txt_new_chitiet.setText(context+context2+context3+context4+context5+context6+context7);
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
        dialog.show();
    }

}
