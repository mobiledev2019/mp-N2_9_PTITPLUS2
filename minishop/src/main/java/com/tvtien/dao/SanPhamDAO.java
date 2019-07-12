package com.tvtien.dao;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import com.tvtien.daoimp.sanPhamImp;
import com.tvtien.entity.ChiTietHoaDon;
import com.tvtien.entity.ChiTietHoaDonid;
import com.tvtien.entity.ChiTietSanPham;
import com.tvtien.entity.SanPham;

@Repository
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS)
public class SanPhamDAO implements sanPhamImp{
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public List<SanPham> danhsachsanphamLimit(int spbatdau) {
		Session session = sessionFactory.getCurrentSession();
		if(spbatdau < 0){
			String query = "from sanpham " ;
			List<SanPham> listsanpham = (List<SanPham>) session.createQuery(query).getResultList();
			return listsanpham;
		}
		else{
			List<SanPham> listsanpham = (List<SanPham>) session.createQuery("from sanpham").setFirstResult(spbatdau).setMaxResults(5).getResultList();
			return listsanpham;
		}
		
	}
	
	@Transactional
	public SanPham LayDanhSachSanPhamTheoMa(int masanpham) {
		Session session = sessionFactory.getCurrentSession();
		String query = "from sanpham sp where sp.idSanPham = "+masanpham ;
		try {
			SanPham sanpham =(SanPham) session.createQuery(query).getSingleResult();
			return sanpham;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Transactional
	public List<SanPham> LaydanhsachsanphamtheomaDanhmuc(int madanhmuc) {
		Session session = sessionFactory.getCurrentSession();
		String query = "from sanpham sp where sp.danhmuc.idDanhMuc ="+madanhmuc;
		List<SanPham> listsanpham = session.createQuery(query).getResultList();
		return listsanpham;
	}

	//xoa san pham theo ma
	@Transactional
	public boolean xoasanphamthemmasp(int masanpham) {
		Session session = sessionFactory.getCurrentSession();
		SanPham sanPham = session.get(SanPham.class, masanpham);
		
		Set<ChiTietSanPham> chitietsanpham = sanPham.getChitiets();
		for(ChiTietSanPham chitiet : chitietsanpham){
			session.createQuery("delete chitiethoadon WHERE idChiTietSanPham ="+chitiet.getIdChiTietSanPham()).executeUpdate();
			
		}
		session.createQuery("delete chitietsanpham WHERE idSanPham ="+masanpham).executeUpdate();
		session.createQuery("delete sanpham WHERE idSanPham ="+masanpham).executeUpdate();
		return false;
	}

	// them san pham
	
	@Transactional
	public boolean themsanpham(SanPham sanpham) {
		Session session = sessionFactory.getCurrentSession();
		int id = (Integer) session.save(sanpham);
		// TODO Auto-generated method stub
		return false;
	}
	
	
	//capnhat san pham
	@Transactional
	public boolean capnhatsanpham(SanPham sanpham) {
		Session session = sessionFactory.getCurrentSession();
		session.update(sanpham);
		return false;
	}
	
	
	
	

}
