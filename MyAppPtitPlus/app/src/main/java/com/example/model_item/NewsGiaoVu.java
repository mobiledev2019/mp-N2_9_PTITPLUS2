package com.example.model_item;

public class NewsGiaoVu {
    public String url;
    public String time ;
    public String title;
    public String context;
    public String tingiaovu;

    public NewsGiaoVu(String time, String title, String context ,String url , String tingiaovu) {
        this.time = time;
        this.title = title;
        this.context = context;
        this.url = url;
        this.tingiaovu = tingiaovu;
    }
}
