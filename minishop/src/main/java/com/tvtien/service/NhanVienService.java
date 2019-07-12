package com.tvtien.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvtien.dao.NhanVienDAO;
import com.tvtien.daoimp.NhanVienImp;
import com.tvtien.entity.NhanVien;

@Service
public class NhanVienService implements NhanVienImp {
	
	@Autowired
	NhanVienDAO nhanvienDAO;

	public boolean checkDangNhap(String username, String password) {
		boolean kiemtra = nhanvienDAO.checkDangNhap(username, password);
		return kiemtra;
	}

	public boolean themNhanViem(NhanVien nhanvien) {
		boolean ktThem = nhanvienDAO.themNhanViem(nhanvien);
		return ktThem;
	}
	
	
}
