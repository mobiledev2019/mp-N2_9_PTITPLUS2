package com.tvtien.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "chitietsanpham")
public class ChiTietSanPham {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int idChiTietSanPham;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idSanPham")
	SanPham sanpham ;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idSizeSanPham")
	SizeSanPham sizesanpham;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idMauSanPham")
	MauSanPham mausanpham;
	
	
	public ChiTietSanPham() {
		super();
	}
	public int getIdChiTietSanPham() {
		return idChiTietSanPham;
	}
	public void setIdChiTietSanPham(int idChiTietSanPham) {
		this.idChiTietSanPham = idChiTietSanPham;
	}
	public SanPham getSanpham() {
		return sanpham;
	}
	public void setSanpham(SanPham sanpham) {
		this.sanpham = sanpham;
	}
	public SizeSanPham getSizesanpham() {
		return sizesanpham;
	}
	public void setSizesanpham(SizeSanPham sizesanpham) {
		this.sizesanpham = sizesanpham;
	}
	public MauSanPham getMausanpham() {
		return mausanpham;
	}
	public void setMausanpham(MauSanPham mausanpham) {
		this.mausanpham = mausanpham;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public String getNgaynhap() {
		return ngaynhap;
	}
	public void setNgaynhap(String ngaynhap) {
		this.ngaynhap = ngaynhap;
	}
	int soluong ;
	String ngaynhap;
}
