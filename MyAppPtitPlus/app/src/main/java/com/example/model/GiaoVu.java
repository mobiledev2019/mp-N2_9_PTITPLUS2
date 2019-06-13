package com.example.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.adapter.NewsGiaoVuAdapter;
import com.example.model_item.NewsGiaoVu;
import com.example.model_item.danhmuc;
import com.example.model_item.soluongtin;
import com.example.myappptitplus.MainActivity;
import com.example.myappptitplus.NewsDetailActivity;
import com.example.myappptitplus.NewsGVChiTietActivity;
import com.example.myappptitplus.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class GiaoVu extends Fragment {

    Activity mContext;
    Spinner sp_choice;
    danhmuc selectdanhmuc;
    ArrayAdapter<danhmuc> danhmucArrayAdapter;
    ListView lv_newGiaoVu;
    String url_giaovu ="http://portal.ptit.edu.vn/giaovu/category/thong-bao/";
    String url_ttkt = "http://portal.ptit.edu.vn/ttkt/";
    ArrayList<NewsGiaoVu> arrayListNewsGiaoVu =  new ArrayList<>();
    NewsGiaoVu selsectNews = null;
    NewsGiaoVuAdapter newsGiaoVuAdapter;
    private String context_giaovu ="";
    private String context2_giaovu ="";
    private String context3_giaovu ="";
    private String context4_giaovu ="";


    //ten trang thai luu
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myvView = inflater.inflate(R.layout.activity_giao_vu,container ,false);
        lv_newGiaoVu = myvView.findViewById(R.id.lv_newGiaovu);

        initPreferences();
        return myvView;
    }
    // khoi tao gia tri de luu du lieu data vao
    private void initPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        editor = sharedPreferences.edit();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (Activity)context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int x = soluongtin.soluonghienthi;
        if(x != 0){
            gettingiaovu(x);
            gettinttkt(x);
            addvents();
        }
        else{
            gettingiaovu(3);
            gettinttkt(3);
            addvents();
        }

    }

    private void gettingiaovu(final int x) {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_giaovu, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String time = "";
                String title = "";
                String context = "";
                String url = "";

                int i = 1;
                int j = 1;
                Document document = Jsoup.parse(response);
                if(document != null){
                    Elements elements = document.select("div[class = post-desc-wrapper]");
                    for (Element element:elements){
                        if(j <= x){
                            Element element1time = element.getElementsByTag("span").get(5);
                            Element element1title = element.getElementsByTag("h2").first();
                            Element element1context = element.getElementsByTag("div").get(8);
                            Element element1url = element.getElementsByTag("a").get(4);

                            if(element1time != null){
                                time = element1time.text();
                            }
                            if(element1title != null){
                                title = element1title.text();
                            }
                            if(element1url != null){
                                url = element1url.attr("href");
                                if (url.equals("http://portal.ptit.edu.vn/giaovu/category/thong-bao/thong-bao-tu-ho%cc%a3c-vie%cc%a3n/") == true){
                                    url = "http://portal.ptit.edu.vn/ttkt/bang-diem-thi-lan-2-hk-1-nam-hoc-2018-2019/";
                                }
                                System.out.println(url);
                            }
                            if (element1context != null){
                                context = element1context.text();
                            }
                            arrayListNewsGiaoVu.add(new NewsGiaoVu(time,title,context,url,"Tin Giáo Vụ"));
                            if(i == 1){
                                // luu gia tri ban tin dau tien vao trong list de xu ly ben sevice
                                editor.putString("DATAGiaoVu", title);
                                editor.commit();
                            }
                            i++;
                            j++;
                            System.out.println("giaovu :"+j);
                            System.out.println("gia tri s :"+x);
                        }
                    }
                    newsGiaoVuAdapter = new NewsGiaoVuAdapter(arrayListNewsGiaoVu,mContext);
                    lv_newGiaoVu.setAdapter(newsGiaoVuAdapter);
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

    private void gettinttkt(final int x) {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_ttkt, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String time = "";
                String title = "";
                String context = "";
                String url = "";
                int i = 1;
                int j = 1;
                Document document = Jsoup.parse(response);
                if(document != null){
                    Elements elements = document.select("div[class = post-desc-wrapper]");
                    for (Element element:elements){
                        if(j <= x){
                            Element element1time = element.getElementsByTag("span").get(5);
                            Element element1title = element.getElementsByTag("h2").first();
                            Element element1context = element.getElementsByTag("div").get(8);
                            Element element1url = element.getElementsByTag("a").get(4);

                            if(element1time != null){
                                time = element1time.text();
                            }
                            if(element1title != null){
                                title = element1title.text();
                            }
                            if(element1url != null){
                                url = element1url.attr("href");
                                System.out.println(url);
                            }
                            if (element1context != null){
                                context = element1context.text();
                            }
                            arrayListNewsGiaoVu.add(new NewsGiaoVu(time,title,context,url,"Trung Tâm Khảo Thí"));
                            if(i == 1){
                                // luu gia tri ban tin dau tien vao trong list de xu ly ben sevice
                                editor.putString("DATAttkt", title);
                                editor.commit();
                            }
                            i++;
                            j++;
                            System.out.println("ttkt  :"+j);
                        }
                    }
                    newsGiaoVuAdapter = new NewsGiaoVuAdapter(arrayListNewsGiaoVu,mContext);
                    lv_newGiaoVu.setAdapter(newsGiaoVuAdapter);
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


    private void addvents() {
        lv_newGiaoVu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selsectNews = (NewsGiaoVu) newsGiaoVuAdapter.getItem(position);
                System.out.println(selsectNews.url);
                hienthitinchitiet();
            }
        });

    }

    private void hienthitinchitiet() {
        try {
            // khoi tao mot man hinh moi
            final Intent intent = new Intent(mContext, NewsGVChiTietActivity.class);
            // lay du lieu
            RequestQueue requestQueue = Volley.newRequestQueue(mContext);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, selsectNews.url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Document document = Jsoup.parse(response);
                    if(document != null){
                        Log.e("CALLAPI", "onResponse: " + document.toString());
                        Elements elements = document.select("div[class=post-wrapper-content]");
                        for (Element element : elements){
//                            Element element1context = element.getElementsByTag("p").first();
//                            Element element1context2 = element.getElementsByTag("p").get(2);
//                            Element element1context3 = element.getElementsByTag("p").get(3);

                            try {
                                Element element1context = element.getElementsByTag("p").first();
                                Element element1context2 = element.getElementsByTag("p").get(2);
                                Element element1context3 = element.getElementsByTag("p").get(3);
                                Element element1context4 = element.getElementsByTag("p").get(4);
                                if(element1context != null){
                                    context_giaovu = element1context.text();
                                }
                                if(element1context2 != null){
                                    context2_giaovu = element1context2.text();
                                }
                                if(element1context3 != null){
                                    context3_giaovu = element1context3.text();
                                }
                                if (element1context4 != null){
                                    context4_giaovu = element1context4.text();
                                }
                                selsectNews.context=(context_giaovu+"\n"+context2_giaovu+"\n"+context3_giaovu+"\n"+context4_giaovu+"\n");
                            }catch (Exception e){
                                e.printStackTrace();
                                selsectNews.context = "Nhà trường thông báo cho sinh viên về "+selsectNews.title+"\ntruy cập website để biết thêm chi tiết \n"+ selsectNews.url+"\n\n";
                            }
                            // gui du lieu sang ben bang intent
                            intent.putExtra("title",selsectNews.title);
                            intent.putExtra("context",selsectNews.context);
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
        }catch (Exception e){
            Toast.makeText(mContext,"Không Thê Mở Tin",Toast.LENGTH_SHORT).show();
            System.out.println("khong the mo man hinh moi");
        }

    }
}
