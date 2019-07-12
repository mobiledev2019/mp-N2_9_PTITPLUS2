package com.tvtien.entity;

import java.util.Set;

public class JSON_SanPham {
	int idSanPham ;
	DanhMucSanPham danhmuc ;
	String tenSanPham;
	String giatien;
	String mota;
	String hinhSanPham;
	String gianhcho;
	Set<ChiTietSanPham> chitiets;
	Set<KhuyenMai> danhsachkm;
	
	
	public int getIdSanPham() {
		return idSanPham;
	}
	public void setIdSanPham(int idSanPham) {
		this.idSanPham = idSanPham;
	}
	public DanhMucSanPham getDanhmuc() {
		return danhmuc;
	}
	public void setDanhmuc(DanhMucSanPham danhmuc) {
		this.danhmuc = danhmuc;
	}
	public String getTenSanPham() {
		return tenSanPham;
	}
	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}
	public String getGiatien() {
		return giatien;
	}
	public void setGiatien(String giatien) {
		this.giatien = giatien;
	}
	public String getMota() {
		return mota;
	}
	public void setMota(String mota) {
		this.mota = mota;
	}
	public String getHinhSanPham() {
		return hinhSanPham;
	}
	public void setHinhSanPham(String hinhSanPham) {
		this.hinhSanPham = hinhSanPham;
	}
	public String getGianhcho() {
		return gianhcho;
	}
	public void setGianhcho(String gianhcho) {
		this.gianhcho = gianhcho;
	}
	public Set<ChiTietSanPham> getChitiets() {
		return chitiets;
	}
	public void setChitiets(Set<ChiTietSanPham> chitiets) {
		this.chitiets = chitiets;
	}
	public Set<KhuyenMai> getDanhsachkm() {
		return danhsachkm;
	}
	public void setDanhsachkm(Set<KhuyenMai> danhsachkm) {
		this.danhsachkm = danhsachkm;
	}
	
}
