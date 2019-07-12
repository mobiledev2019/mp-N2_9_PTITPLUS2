package com.tvtien.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import com.tvtien.daoimp.NhanVienImp;
import com.tvtien.entity.NhanVien;

@Repository
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS)
public class NhanVienDAO implements NhanVienImp {
	@Autowired
	SessionFactory SessionFactory ;
	
	@Transactional
	public boolean checkDangNhap(String username, String password) {
		Session session = SessionFactory.getCurrentSession();
		
		try {
			NhanVien nhanvien = (NhanVien)session.createQuery("from nhanvien where tendangnhap='"+username+"' AND matkhau='"+password+"'").getSingleResult();
			if(nhanvien != null){
				return true;
			}
			else{
				return false;
			}
		} catch (Exception e) {
			return false;
		}

	}
	
	@Transactional
	public boolean themNhanViem(NhanVien nhanvien) {
		Session session = SessionFactory.getCurrentSession();
		int idNhanVien =  (Integer) session.save(nhanvien);
		System.out.println(idNhanVien+"");
		if(idNhanVien > 0){
			return true;
		}else{
			return false;
		}
	}
	
}
