package com.tvtien.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tvtien.entity.DanhMucSanPham;
import com.tvtien.entity.SanPham;
import com.tvtien.service.DanhMucSevice;
import com.tvtien.service.SanPhamService;

@Controller
@RequestMapping("sanpham/")
public class SanPhamController {
	
	@Autowired
	SanPhamService sanphamSevice;
	@Autowired
	DanhMucSevice danhmucSevice;
	
	@GetMapping("{id}/{tendanhmuc}")
	public String Default(ModelMap modelMap ,HttpSession httpSession ,@PathVariable int id ,@PathVariable String tendanhmuc){
		if(httpSession.getAttribute("user") != null){
			String username = (String)httpSession.getAttribute("user");
			String chucaidau = username.substring(0,1);
			modelMap.addAttribute("chudau",chucaidau);
		}
		
		List<DanhMucSanPham> listdanhmuc = danhmucSevice.Laydanhmuc();
		modelMap.addAttribute("listdanhmuc",listdanhmuc);
		
		List<SanPham> listsanpham = sanphamSevice.LaydanhsachsanphamtheomaDanhmuc(id);
		
		modelMap.addAttribute("listsanpham",listsanpham);
		modelMap.addAttribute("tendanhmuc",tendanhmuc);
		return "sanpham";
	}
}
