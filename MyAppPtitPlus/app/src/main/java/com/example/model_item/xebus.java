package com.example.model_item;

import java.io.Serializable;

public class xebus implements Serializable {
    private String ten;
    private String id ;
    private float vidodi;
    private float kinhdodi;
    private float vidoden;
    private float kinhden;

    public xebus() {
    }

    public xebus(String ten,String id, float vidodi, float kinhdodi, float vidoden, float kinhden) {
        this.ten = ten;
        this.id = id;
        this.vidodi = vidodi;
        this.kinhdodi = kinhdodi;
        this.vidoden = vidoden;
        this.kinhden = kinhden;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public float getVidodi() {
        return vidodi;
    }

    public void setVidodi(float vidodi) {
        this.vidodi = vidodi;
    }

    public float getKinhdodi() {
        return kinhdodi;
    }

    public void setKinhdodi(float kinhdodi) {
        this.kinhdodi = kinhdodi;
    }

    public float getVidoden() {
        return vidoden;
    }

    public void setVidoden(float vidoden) {
        this.vidoden = vidoden;
    }

    public float getKinhden() {
        return kinhden;
    }

    public void setKinhden(float kinhden) {
        this.kinhden = kinhden;
    }
}
