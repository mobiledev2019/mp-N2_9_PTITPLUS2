package com.tvtien.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "sanpham")

public class SanPham {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int idSanPham ;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idDanhMuc")
	DanhMucSanPham danhmuc ;
	
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idSanPham")
	Set<ChiTietSanPham> chitiets;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name ="chitietkhuyenmai",
	joinColumns={@JoinColumn(name = "idSanPham", referencedColumnName="idSanPham")},
	inverseJoinColumns={@JoinColumn(name = "idKhuyenMai",referencedColumnName="idKhuyenMai")}
	)
	
	Set<KhuyenMai> danhsachkm;
	
	String tenSanPham;
	String giatien;
	String mota;
	String hinhSanPham;
	String gianhcho;
	
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
	public SanPham() {
		super();
	}
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
	
}
