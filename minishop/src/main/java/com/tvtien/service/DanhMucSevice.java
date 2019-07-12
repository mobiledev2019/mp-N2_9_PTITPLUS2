package com.tvtien.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvtien.dao.DanhMucDAO;
import com.tvtien.daoimp.DanhmucImp;
import com.tvtien.entity.DanhMucSanPham;

@Service
public class DanhMucSevice implements DanhmucImp {

	@Autowired
	DanhMucDAO danhmucDAO;
	public List<DanhMucSanPham> Laydanhmuc() {
		return danhmucDAO.Laydanhmuc();
	}

}
