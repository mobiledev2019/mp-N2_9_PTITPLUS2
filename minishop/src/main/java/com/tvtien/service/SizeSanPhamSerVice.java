package com.tvtien.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvtien.dao.SizeSanPhamDAO;
import com.tvtien.daoimp.SizeSanPhamimp;
import com.tvtien.entity.SizeSanPham;

@Service
public class SizeSanPhamSerVice implements SizeSanPhamimp {
	@Autowired
	SizeSanPhamDAO sizeDAO;
	public List<SizeSanPham> LaydanhsachSize() {
		return sizeDAO.LaydanhsachSize();
	}

}
