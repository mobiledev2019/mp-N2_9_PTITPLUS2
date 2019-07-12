package com.tvtien.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvtien.dao.HoaDonDAO;
import com.tvtien.daoimp.HoaDonimp;
import com.tvtien.entity.HoaDon;

@Service
public class HoaDonService implements HoaDonimp {
	@Autowired
	HoaDonDAO hoahonDAO;

	public int ThemHoaDon(HoaDon hoadon) {
		// TODO Auto-generated method stub
		return hoahonDAO.ThemHoaDon(hoadon);
	}
	
}
