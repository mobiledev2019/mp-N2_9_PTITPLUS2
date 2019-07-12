package com.tvtien.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tvtien.entity.NhanVien;
import com.tvtien.service.NhanVienService;

@Controller
@RequestMapping("dangnhap/")

public class DangNhapController {
	
	@Autowired
	NhanVienService nhanvienService;
	
	@GetMapping
	@Transactional
	public String Default(){
		return "dangnhap";
	}
	
	@PostMapping
	public String Dangky(@RequestParam String username , @RequestParam String password  , @RequestParam String password_again ,ModelMap modelmap ){
		boolean ktemail = validate(username);
		if(ktemail){
			if(password.equals(password_again)){
				NhanVien nhanvien = new NhanVien();
				nhanvien.setEmail(username);
				nhanvien.setTendangnhap(username);
				nhanvien.setMatkhau(password);
				
				boolean ktThem = nhanvienService.themNhanViem(nhanvien);
				if(ktThem = true){
					modelmap.addAttribute("ketqua"," thanh cong !!!");
				}else{
					modelmap.addAttribute("ketqua"," that bai !!!");
				}
			}else{
				modelmap.addAttribute("ketqua","vui long nhap lai mat khau !!!");
			}
		}
		else{
			modelmap.addAttribute("ketqua","vui long nhap dung dinh dang Email !!!");
		}
		return "dangnhap";
	}
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	public static boolean validate(String emailStr) {
		       Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
		       return matcher.find();
	}
}
