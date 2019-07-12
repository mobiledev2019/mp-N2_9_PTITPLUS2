package com.tvtien.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvtien.dao.MauSanPhamDAO;
import com.tvtien.daoimp.MauSanPhamimp;
import com.tvtien.entity.MauSanPham;

@Service
public class MauSanPhamService implements MauSanPhamimp {
	@Autowired
	MauSanPhamDAO mauDAO;
	public List<MauSanPham> laydanhsachmau() {
		// TODO Auto-generated method stub
		return mauDAO.laydanhsachmau();
	}

}
