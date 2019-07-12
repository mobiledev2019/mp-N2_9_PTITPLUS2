package com.tvtien.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "mausanpham")
public class MauSanPham {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int idMauSanPham;
	String tenMauSanPham;
	
	
	public int getIdMauSanPham() {
		return idMauSanPham;
	}
	public void setIdMauSanPham(int idMauSanPham) {
		this.idMauSanPham = idMauSanPham;
	}
	public String getTenMauSanPham() {
		return tenMauSanPham;
	}
	public void setTenMauSanPham(String tenMauSanPham) {
		this.tenMauSanPham = tenMauSanPham;
	}
}
