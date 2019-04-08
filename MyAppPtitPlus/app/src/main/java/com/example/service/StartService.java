package com.example.service;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myappptitplus.MainActivity;
import com.example.myappptitplus.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class StartService extends Service {

    Activity mContext ;
    Context context;
    String url_giaovu ="http://portal.ptit.edu.vn/giaovu/category/thong-bao/";
    //ten trang thai luu
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    NotificationManager notificationManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // thuc hien lenh trong day
        xylySevice();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void xylySevice() {
        //Toast.makeText(this,"runing !!!", Toast.LENGTH_LONG).show();
        initPreferences();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_giaovu, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String time = "";
                String title = "";
                String context = "";
                String url = "";
                int i = 1;
                Document document = Jsoup.parse(response);
                    Elements elements = document.select("div[class = post-desc-wrapper]");
                    for (Element element:elements ){
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
                        String savedData = sharedPreferences.getString("DATA", "");
                        System.out.println("12344444"+savedData);
                        System.out.println(title);
                        if(savedData.equals(title) == false && i == 1){
                            hienthithongbao(title);
                        }
                        i++;
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

    private void initPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        notificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
    }

    private void hienthithongbao(String title) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        Notification n  = new Notification.Builder(this)
                .setContentTitle(title)
                .setContentText("Tin Giáo Vụ")
                .setSmallIcon(R.drawable.ic_home)
                .setContentIntent(pIntent)
                .setAutoCancel(true).build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        n.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, n);
    }

}
