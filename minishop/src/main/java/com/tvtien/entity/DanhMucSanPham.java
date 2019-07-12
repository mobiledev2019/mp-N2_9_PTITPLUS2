package com.tvtien.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name = "danhmucsanpham")
public class DanhMucSanPham {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int idDanhMuc ;
	String tenDanhMuc;
	String hinhDanhMuc;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "idDanhMuc")
	Set<SanPham> sanphams ;
	
	public Set<SanPham> getSanphams() {
		return sanphams;
	}
	public void setSanphams(Set<SanPham> sanphams) {
		this.sanphams = sanphams;
	}

	
	public DanhMucSanPham() {
		super();
	}
	public int getIdDanhMuc() {
		return idDanhMuc;
	}
	public void setIdDanhMuc(int idDanhMuc) {
		this.idDanhMuc = idDanhMuc;
	}
	public String getTenDanhMuc() {
		return tenDanhMuc;
	}
	public void setTenDanhMuc(String tenDanhMuc) {
		this.tenDanhMuc = tenDanhMuc;
	}
	public String getHinhDanhMuc() {
		return hinhDanhMuc;
	}
	public void setHinhDanhMuc(String hinhDanhMuc) {
		this.hinhDanhMuc = hinhDanhMuc;
	}
	
	
}
