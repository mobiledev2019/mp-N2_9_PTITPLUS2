package com.tvtien.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tvtien.entity.DanhMucSanPham;
import com.tvtien.entity.MauSanPham;
import com.tvtien.entity.SanPham;
import com.tvtien.entity.SizeSanPham;
import com.tvtien.service.DanhMucSevice;
import com.tvtien.service.MauSanPhamService;
import com.tvtien.service.SanPhamService;
import com.tvtien.service.SizeSanPhamSerVice;

@Controller
@RequestMapping("/themsanpham")
public class ThemsanphamController {
	@Autowired
	SanPhamService sanphamservicel;
	@Autowired
	DanhMucSevice danhmucSevice ;
	@Autowired
	SizeSanPhamSerVice sizeService;
	@Autowired
	MauSanPhamService mauService;
	
	@GetMapping
	public String Default(ModelMap modelMap){
		List<SanPham> listsanpham = sanphamservicel.danhsachsanphamLimit(0);
		List<SanPham> listsanphamall = sanphamservicel.danhsachsanphamLimit(-1);
		List<DanhMucSanPham> listdanhmuc = danhmucSevice.Laydanhmuc();
		List<SizeSanPham> listsize = sizeService.LaydanhsachSize();
		List<MauSanPham> listmau = mauService.laydanhsachmau();
		
		double tongsopage = Math.ceil((double)listsanphamall.size()/5) ;
		
		modelMap.addAttribute("listsp",listsanpham);
		modelMap.addAttribute("listspall",listsanphamall);
		modelMap.addAttribute("tongsopage",tongsopage);
		modelMap.addAttribute("listdanhmuc",listdanhmuc);
		modelMap.addAttribute("listsize",listsize);
		modelMap.addAttribute("listmau",listmau);
		return "themsanpham";
	}
}
