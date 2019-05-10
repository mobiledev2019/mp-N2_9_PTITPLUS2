package com.example.model_item;

import java.io.Serializable;

public class Lichthi implements Serializable {
    String ngayThi ;
    String tenMonThi;
    String thoiGian;
    String phongThi;

    public Lichthi() {
    }

    public Lichthi(String ngayThi, String tenMonThi, String thoiGian, String phongThi) {
        this.ngayThi = ngayThi;
        this.tenMonThi = tenMonThi;
        this.thoiGian = thoiGian;
        this.phongThi = phongThi;
    }

    public String getNgayThi() {
        return ngayThi;
    }

    public void setNgayThi(String ngayThi) {
        this.ngayThi = ngayThi;
    }

    public String getTenMonThi() {
        return tenMonThi;
    }

    public void setTenMonThi(String tenMonThi) {
        this.tenMonThi = tenMonThi;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getPhongThi() {
        return phongThi;
    }

    public void setPhongThi(String phongThi) {
        this.phongThi = phongThi;
    }
}
