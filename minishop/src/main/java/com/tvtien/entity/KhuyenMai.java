package com.tvtien.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name = "khuyenmai")
public class KhuyenMai {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int idKhuyenMai;
	String tenKhuyenMai;
	String thoigianbatdau;
	String thoigianketthuc;
	String mota;
	int giagiam;
	String hinhKhuyenMai;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name ="chitietkhuyenmai",
	joinColumns={@JoinColumn(name = "idKhuyeMai", referencedColumnName="idKhuyenMai")},
	inverseJoinColumns={@JoinColumn(name = "idSanPham",referencedColumnName="idSanPham")}
	)
	
	Set<SanPham> danhsachsp ;

	public int getIdKhuyenMai() {
		return idKhuyenMai;
	}

	public void setIdKhuyenMai(int idKhuyenMai) {
		this.idKhuyenMai = idKhuyenMai;
	}

	public String getTenKhuyenMai() {
		return tenKhuyenMai;
	}

	public void setTenKhuyenMai(String tenKhuyenMai) {
		this.tenKhuyenMai = tenKhuyenMai;
	}

	public String getThoigianbatdau() {
		return thoigianbatdau;
	}

	public void setThoigianbatdau(String thoigianbatdau) {
		this.thoigianbatdau = thoigianbatdau;
	}

	public String getThoigianketthuc() {
		return thoigianketthuc;
	}

	public void setThoigianketthuc(String thoigianketthuc) {
		this.thoigianketthuc = thoigianketthuc;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public int getGiagiam() {
		return giagiam;
	}

	public void setGiagiam(int giagiam) {
		this.giagiam = giagiam;
	}

	public String getHinhKhuyenMai() {
		return hinhKhuyenMai;
	}

	public void setHinhKhuyenMai(String hinhKhuyenMai) {
		this.hinhKhuyenMai = hinhKhuyenMai;
	}

	public Set<SanPham> getDanhsachsp() {
		return danhsachsp;
	}

	public void setDanhsachsp(Set<SanPham> danhsachsp) {
		this.danhsachsp = danhsachsp;
	}
	
	
}
