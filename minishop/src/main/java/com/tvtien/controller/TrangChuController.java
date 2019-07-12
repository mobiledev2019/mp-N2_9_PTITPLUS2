package com.tvtien.controller;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.tvtien.entity.DanhMucSanPham;
import com.tvtien.entity.SanPham;
import com.tvtien.entity.giohang;
import com.tvtien.service.DanhMucSevice;
import com.tvtien.service.SanPhamService;


@Controller
@RequestMapping("/")
public class TrangChuController {
	
	@Autowired
	SanPhamService sanphamSevice;
	@Autowired
	DanhMucSevice danhmucSevice;
	
	@GetMapping
	@Transactional  
	public String Default(ModelMap modelMap ,HttpSession httpSession){
		if(httpSession.getAttribute("user") != null){
			String username = (String)httpSession.getAttribute("user");
			String chucaidau = username.substring(0,1);
			modelMap.addAttribute("chudau",chucaidau);
		}
		
		List<DanhMucSanPham> listdanhmuc = danhmucSevice.Laydanhmuc();
		modelMap.addAttribute("listdanhmuc",listdanhmuc);
		
		if(null != httpSession.getAttribute("giohang")){
			List<giohang> listgiohang = (List<giohang>) httpSession.getAttribute("giohang");
			modelMap.addAttribute("soluong", listgiohang.size());
		}
		
		List<SanPham> listsanpham = sanphamSevice.danhsachsanphamLimit(-1);
		modelMap.addAttribute("listsanpham", listsanpham);
		return "trangchu";
	}
	
	@PostMapping
	@Transactional
	public String ThemNhanVien(@RequestParam String tenNhanVien , @RequestParam int tuoiNhanVien){
		
		return "trangchu";
	}
	
}
