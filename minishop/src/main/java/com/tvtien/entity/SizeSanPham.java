package com.tvtien.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "sizesanpham")
public class SizeSanPham {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int idSizeSanPham;
	
	public int getIdSizeSanPham() {
		return idSizeSanPham;
	}
	public void setIdSizeSanPham(int idSizeSanPham) {
		this.idSizeSanPham = idSizeSanPham;
	}
	public String getTenSizeSanPham() {
		return tenSizeSanPham;
	}
	public void setTenSizeSanPham(String tenSizeSanPham) {
		this.tenSizeSanPham = tenSizeSanPham;
	}
	String tenSizeSanPham;
}
