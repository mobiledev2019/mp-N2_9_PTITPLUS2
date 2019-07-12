package com.tvtien.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvtien.dao.ChiTietHoaDonDAO;
import com.tvtien.daoimp.ChiTietHoaDonImp;
import com.tvtien.entity.ChiTietHoaDon;

@Service
public class ChiTietHoaDonService implements ChiTietHoaDonImp{
	@Autowired
	ChiTietHoaDonDAO chitiethoahonDAO;

	public boolean themchitiethoadon(ChiTietHoaDon chiTietHoaDon) {
		
		return chitiethoahonDAO.themchitiethoadon(chiTietHoaDon);
	}

}
