package com.example.model_item;

import java.io.Serializable;
import java.util.ArrayList;

public class danhmuc  implements Serializable {
    private String madm , tendm;

    public danhmuc(String madm, String tendm) {
        this.madm = madm;
        this.tendm = tendm;
    }
    public String getMadm() {
        return madm;
    }

    public void setMadm(String madm) {
        this.madm = madm;
    }

    public String getTendm() {
        return tendm;
    }

    public void setTendm(String tendm) {
        this.tendm = tendm;
    }


    @Override
    public String toString() {
        return this.tendm;
    }
}
