package com.tvtien.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tvtien.entity.ChiTietSanPham;
import com.tvtien.entity.DanhMucSanPham;
import com.tvtien.entity.JSON_SanPham;
import com.tvtien.entity.MauSanPham;
import com.tvtien.entity.SanPham;
import com.tvtien.entity.SizeSanPham;
import com.tvtien.entity.giohang;
import com.tvtien.service.NhanVienService;
import com.tvtien.service.SanPhamService;

@Controller
@RequestMapping("api/")
@SessionAttributes({"user","giohang"})
public class ApiController {
	
	@Autowired
	NhanVienService nhanvienService;
	@Autowired
	SanPhamService sanphamservice;
	
	
	@GetMapping("kiemtradangnhap")
	@ResponseBody
	public String checkDangNhap(@RequestParam String username , @RequestParam String password , ModelMap modelmap){
		boolean kiemtra = nhanvienService.checkDangNhap(username, password);
		modelmap.addAttribute("user",username);
		
		return ""+kiemtra;
	}
	
	
	@GetMapping("capnhatgiohang")
	@ResponseBody
	public void capnhatgiohang(HttpSession httpSession, @RequestParam int soluong ,@RequestParam int masp ,@RequestParam int mamau ,@RequestParam int masize){
		if( null != httpSession.getAttribute("giohang")){
			List<giohang> listgiohangs = (List<giohang>) httpSession.getAttribute("giohang");
			int vitri  = kiemtrasanpham(masp, masize, mamau, httpSession);
			listgiohangs.get(vitri).setSoluong(soluong);
		}
	}
	
	@GetMapping("themgiohang")
	@ResponseBody
	public String ThemGioHang(@RequestParam int idChiTietSanPham, @RequestParam int idSanPham ,@RequestParam int idMaMauSanPham , @RequestParam int idSizeSanpham  , @RequestParam String tenSanPham ,  @RequestParam String tenMauSanPham ,  @RequestParam String tenSizeSanPham ,  @RequestParam String giatien , @RequestParam int soluong ,HttpSession httpSession ){
		if( null == httpSession.getAttribute("giohang")){
			List<giohang> giohangs = new ArrayList<giohang>();
			giohang gioHang = new giohang();
			gioHang.setMachitiet(idChiTietSanPham);
			gioHang.setIdSanPham(idSanPham);
			gioHang.setIdMaMauSanPham(idMaMauSanPham);
			gioHang.setIdSizeSanpham(idSizeSanpham);
			gioHang.setTenSanPham(tenSanPham);
			gioHang.setTenMauSanPham(tenMauSanPham);
			gioHang.setTenSizeSanPham(tenSizeSanPham);
			gioHang.setGiatien(giatien);
			gioHang.setSoluong(1);
			
			giohangs.add(gioHang);
			httpSession.setAttribute("giohang", giohangs);
			List<giohang> list = (List<giohang>) httpSession.getAttribute("giohang");
			System.out.println(list.size());
			
			return giohangs.size()+"";
			
		}else{
			List<giohang> giohangs = (List<giohang>) httpSession.getAttribute("giohang");
			int vitri = kiemtrasanpham(idSanPham, idSizeSanpham, idMaMauSanPham, httpSession);
			if(vitri == -1){
				
				giohang gioHang = new giohang();
				gioHang.setIdSanPham(idSanPham);
				gioHang.setIdMaMauSanPham(idMaMauSanPham);
				gioHang.setIdSizeSanpham(idSizeSanpham);
				gioHang.setTenSanPham(tenSanPham);
				gioHang.setTenMauSanPham(tenMauSanPham);
				gioHang.setTenSizeSanPham(tenSizeSanPham);
				gioHang.setGiatien(giatien);
				gioHang.setSoluong(1);
				
				giohangs.add(gioHang);
				
				System.out.println(giohangs.size());
			}else{
			
				int soluongcu = giohangs.get(vitri).getSoluong();
				giohangs.get(vitri).setSoluong(soluongcu +1 );
				System.out.println(giohangs.size());
			}
			return giohangs.size() +"";
			
		}
	
		
	}
	
	@GetMapping("xoagiohang")
	@ResponseBody
	public void xoagiohang(HttpSession httpSession ,@RequestParam int masp ,@RequestParam int mamau ,@RequestParam int masize){
		if( null != httpSession.getAttribute("giohang")){
			List<giohang> listgiohangs = (List<giohang>) httpSession.getAttribute("giohang");
			int vitri  = kiemtrasanpham(masp, masize, mamau, httpSession);
			listgiohangs.remove(vitri);
		}
	}
	
	private int kiemtrasanpham(int masp , int masize , int mamau ,HttpSession httpSession){
		List<giohang> listgiohang = (List<giohang>) httpSession.getAttribute("giohang");
		for(int i = 0 ;i < listgiohang.size() ;i++){
			if(listgiohang.get(i).getIdSanPham() == masp && listgiohang.get(i).getIdMaMauSanPham() == mamau && listgiohang.get(i).getIdSizeSanpham() == masize){
				return i;
			}
		}
		return -1;
	}
	
	@GetMapping("laysoluonggiohang")
	@ResponseBody
	public String Laysoluonghang(HttpSession httpSession){
		if(null != httpSession.getAttribute("giohang")){
			List<giohang> listgiohang = (List<giohang>) httpSession.getAttribute("giohang");
			return listgiohang.size()+"";
			
		}
		return "";
	}
	
	@GetMapping("laysanphamlimit")
	@ResponseBody
	public String laysanphamlimit(@RequestParam int spbatdau){
		String html = "";
		 List<SanPham> listsanpham = sanphamservice.danhsachsanphamLimit(spbatdau);
		 for(SanPham sanPham : listsanpham){
			 html += "<tr>";
			 html += "<td><div class = 'checkbox' ><label><input class ='checkboxsanpham' type ='checkbox' value ='"+sanPham.getIdSanPham()+"'></label></div>	</td>";
			 html += "<td class ='tensp' data-masp = '"+ sanPham.getIdSanPham() +"'>"+sanPham.getTenSanPham()+"</td>";
			 html += "<td class ='giatien' data-giasp = '"+ sanPham.getGiatien() +"'>"+sanPham.getGiatien()+"</td>";
			 html += "<td class ='giotinh' data-sex = '"+ sanPham.getGianhcho() +"'>"+sanPham.getGianhcho()+"</td>";
			 html += "<td class ='capnhatsanpham btn btn-info' data-id = '"+ sanPham.getIdSanPham() +"'>Sửa</td>";
			 html += "</tr>";
		 }
		 
		 return html;
		
	}
	
	@GetMapping("xoasanpham")
	@ResponseBody
	public String xoasanphamthemmasanpham(@RequestParam int masanpham){
		sanphamservice.xoasanphamthemmasp(masanpham);
		return "true";
	}
	
	@Autowired
	ServletContext context;
	
	@PostMapping("uploadfile")
	@ResponseBody
	public String uploadfile(MultipartHttpServletRequest request){
		
		String path_save_file = context.getRealPath("/resources/image/sanpham");
		Iterator<String> listNames = request.getFileNames();
		MultipartFile mpf = request.getFile(listNames.next());
		
		File file_save = new File(path_save_file + mpf.getOriginalFilename());
		try {
			mpf.transferTo(file_save);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "true";
	}
	
	@PostMapping("themsanpham")
	@ResponseBody
	public void themsanpham(@RequestParam String dataJson){
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonObject;
		try {
			
			SanPham sanpham = new SanPham();
			jsonObject = objectMapper.readTree(dataJson);
			
			DanhMucSanPham danhMucSanPham = new DanhMucSanPham();

			danhMucSanPham.setIdDanhMuc(jsonObject.get("danhmuc").asInt());
			
			JsonNode jsonChitiet = jsonObject.get("chitietsanpham");
			Set<ChiTietSanPham> listchitiet = new HashSet<ChiTietSanPham>();
			for (JsonNode objectChitiet : jsonChitiet) {
				ChiTietSanPham chitietsanpham = new ChiTietSanPham();
				MauSanPham mausanpham = new MauSanPham();
				mausanpham.setIdMauSanPham(objectChitiet.get("mausanpham").asInt());
				
				chitietsanpham.setMausanpham(mausanpham);
				
				SizeSanPham sizesanpham = new SizeSanPham();
				sizesanpham.setIdSizeSanPham(objectChitiet.get("sizesanpham").asInt());
				
				chitietsanpham.setSizesanpham(sizesanpham);
				
				chitietsanpham.setSoluong(objectChitiet.get("soluong").asInt());
				
				listchitiet.add(chitietsanpham);
			}
			
			String tensanpham = jsonObject.get("tenSanPham").asText();
			String giatien = jsonObject.get("giatien").asText();
			String mota = jsonObject.get("mota").asText();
			String hinhanh = jsonObject.get("hinhSanPham").asText();
			String gianhcho = jsonObject.get("gianhcho").asText();
			
			sanpham.setTenSanPham(tensanpham);
			sanpham.setGiatien(giatien);
			sanpham.setMota(mota);
			sanpham.setHinhSanPham(hinhanh);
			sanpham.setGianhcho(gianhcho);
			sanpham.setChitiets(listchitiet);
			sanpham.setDanhmuc(danhMucSanPham);
			
			System.out.println(sanpham.getDanhmuc());
			System.out.println(sanpham.getChitiets());
			sanphamservice.themsanpham(sanpham);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@PostMapping(path = "GetSanPhamByID" ,produces = "application/json; charset=utf-8")
	@ResponseBody
	public JSON_SanPham laydanhsachsanphamtheoID(@RequestParam int masanpham){
		JSON_SanPham json_sanpham = new JSON_SanPham();
		
		SanPham sanphams = new SanPham();
		sanphams = sanphamservice.LayDanhSachSanPhamTheoMa(masanpham);
		
		json_sanpham.setIdSanPham(sanphams.getIdSanPham());
		json_sanpham.setTenSanPham(sanphams.getTenSanPham());
		json_sanpham.setGiatien(sanphams.getGiatien());
		json_sanpham.setMota(sanphams.getMota());
		json_sanpham.setHinhSanPham(sanphams.getHinhSanPham());
		json_sanpham.setGianhcho(sanphams.getGianhcho());
		
		DanhMucSanPham danhMucSanPham = new DanhMucSanPham();
		danhMucSanPham.setIdDanhMuc(sanphams.getDanhmuc().getIdDanhMuc());
		danhMucSanPham.setTenDanhMuc(sanphams.getDanhmuc().getTenDanhMuc());
		
		Set<ChiTietSanPham> listchitiet = new HashSet<ChiTietSanPham>();
		for (ChiTietSanPham value : sanphams.getChitiets()) {
			ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
			
			chiTietSanPham.setIdChiTietSanPham(value.getIdChiTietSanPham());
			
			MauSanPham mausanpham = new MauSanPham();
			mausanpham.setIdMauSanPham(value.getMausanpham().getIdMauSanPham());
			mausanpham.setTenMauSanPham(value.getMausanpham().getTenMauSanPham());
			
			chiTietSanPham.setMausanpham(mausanpham);
			
			SizeSanPham sizesanpham = new SizeSanPham();
			sizesanpham.setIdSizeSanPham(value.getSizesanpham().getIdSizeSanPham());
			sizesanpham.setTenSizeSanPham(value.getSizesanpham().getTenSizeSanPham());
			
			chiTietSanPham.setSizesanpham(sizesanpham);
			chiTietSanPham.setSoluong(value.getSoluong());
			
			listchitiet.add(chiTietSanPham);
		}
		
		json_sanpham.setDanhmuc(danhMucSanPham);
		
		json_sanpham.setChitiets(listchitiet);
		
		return json_sanpham;
		
	}
	
	
	
	@PostMapping("capnhatsanpham")
	@ResponseBody
	public void capnhatsanpham(@RequestParam String dataJson){
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonObject;
		try {
			
			SanPham sanpham = new SanPham();
			jsonObject = objectMapper.readTree(dataJson);
			
			DanhMucSanPham danhMucSanPham = new DanhMucSanPham();
			danhMucSanPham.setIdDanhMuc(jsonObject.get("danhmuc").asInt());
			
			
			JsonNode jsonChitiet = jsonObject.get("chitietsanpham");
			Set<ChiTietSanPham> listchitiet = new HashSet<ChiTietSanPham>();
			for (JsonNode objectChitiet : jsonChitiet) {
				ChiTietSanPham chitietsanpham = new ChiTietSanPham();
				MauSanPham mausanpham = new MauSanPham();
				mausanpham.setIdMauSanPham(objectChitiet.get("mausanpham").asInt());
				
				chitietsanpham.setMausanpham(mausanpham);
				
				SizeSanPham sizesanpham = new SizeSanPham();
				sizesanpham.setIdSizeSanPham(objectChitiet.get("sizesanpham").asInt());
				
				chitietsanpham.setSizesanpham(sizesanpham);
				
				chitietsanpham.setSoluong(objectChitiet.get("soluong").asInt());
				
				listchitiet.add(chitietsanpham);
			}
			
			String tensanpham = jsonObject.get("tenSanPham").asText();
			String giatien = jsonObject.get("giatien").asText();
			String mota = jsonObject.get("mota").asText();
			String hinhanh = jsonObject.get("hinhSanPham").asText();
			String gianhcho = jsonObject.get("gianhcho").asText();
			int idsanpham = jsonObject.get("idsanpham").asInt();
			
			sanpham.setTenSanPham(tensanpham);
			sanpham.setGiatien(giatien);
			sanpham.setMota(mota);
			sanpham.setHinhSanPham(hinhanh);
			sanpham.setGianhcho(gianhcho);
			sanpham.setChitiets(listchitiet);
			sanpham.setDanhmuc(danhMucSanPham);
			sanpham.setIdSanPham(idsanpham);
			
			sanphamservice.capnhatsanpham(sanpham);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
}
