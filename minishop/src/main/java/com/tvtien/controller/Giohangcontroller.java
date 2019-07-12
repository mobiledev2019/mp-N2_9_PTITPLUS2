package com.tvtien.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException.Gone;

import com.tvtien.entity.ChiTietHoaDon;
import com.tvtien.entity.ChiTietHoaDonid;
import com.tvtien.entity.DanhMucSanPham;
import com.tvtien.entity.HoaDon;
import com.tvtien.entity.SanPham;
import com.tvtien.entity.giohang;
import com.tvtien.service.ChiTietHoaDonService;
import com.tvtien.service.HoaDonService;

@Controller
@RequestMapping("giohang/")
public class Giohangcontroller {
	
	@Autowired
	HoaDonService hoadonSevice;
	@Autowired
	ChiTietHoaDonService chitiethoadonservice;
	
	@GetMapping
	public String Default(ModelMap modelmap ,HttpSession httpSession){
		if(httpSession.getAttribute("user") != null){
			String username = (String)httpSession.getAttribute("user");
			String chucaidau = username.substring(0,1);
			modelmap.addAttribute("chudau",chucaidau);
		}
		
		if(null != httpSession.getAttribute("giohang")){
			List<giohang> listgiohang = (List<giohang>) httpSession.getAttribute("giohang");
			modelmap.addAttribute("soluong", listgiohang.size());
			modelmap.addAttribute("giohang",listgiohang);
		}
		
		return "GioHang";
	}
	
	@PostMapping
	public String ThemHoaDon(HttpSession httpSession , @RequestParam String tenKhacHang ,@RequestParam String sodt ,@RequestParam String diachigiaohang ,@RequestParam String hinhthucgiaohang,@RequestParam String ghichu){
		if(null != httpSession.getAttribute("giohang")){
			List<giohang> listgiohang = (List<giohang>) httpSession.getAttribute("giohang");
			
			HoaDon hoadon = new HoaDon();
			hoadon.setTenKhacHang(tenKhacHang);
			hoadon.setSodt(sodt);
			hoadon.setDiachigiaohang(diachigiaohang);
			hoadon.setHinhthucgiaohang(hinhthucgiaohang);
			hoadon.setGhichu(ghichu);
			
			int idHoaDon = hoadonSevice.ThemHoaDon(hoadon);
			if(idHoaDon > 0){
				Set<ChiTietHoaDon> listChiTietHoaDon = new HashSet<ChiTietHoaDon>();
				for(giohang gioHang : listgiohang){
					ChiTietHoaDonid chitiethoadonID = new ChiTietHoaDonid();
					chitiethoadonID.setIdChiTietSanPham(gioHang.getMachitiet());
					chitiethoadonID.setIdHoaDon(hoadon.getIdHoaDon());
					
					ChiTietHoaDon chitiethoadon = new ChiTietHoaDon();
					chitiethoadon.setChiTietHoaDonid(chitiethoadonID);
					chitiethoadon.setGiatien(gioHang.getGiatien().toString());
					chitiethoadon.setSoluong(gioHang.getSoluong());
					chitiethoadonservice.themchitiethoadon(chitiethoadon);
				}
			}
			else{
				System.out.println("khong thanh cong !!!");
			}
		
		}
		
		return "GioHang";
	}
}
