package com.tvtien.daoimp;

import java.util.List;

import com.tvtien.entity.SanPham;

public interface sanPhamImp {
	
	List<SanPham> danhsachsanphamLimit(int spbatdau);
	SanPham LayDanhSachSanPhamTheoMa(int masanpham);
	List<SanPham> LaydanhsachsanphamtheomaDanhmuc(int madanhmuc);
	boolean xoasanphamthemmasp(int masanpham);
	boolean themsanpham(SanPham sanpham);
	boolean capnhatsanpham(SanPham sanpham);
}
