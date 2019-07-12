package com.tvtien.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import com.tvtien.daoimp.MauSanPhamimp;
import com.tvtien.entity.MauSanPham;
import com.tvtien.entity.SizeSanPham;

@Repository
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS)
public class MauSanPhamDAO implements MauSanPhamimp {
	@Autowired
	SessionFactory sessionFactory ;
	
	@Transactional
	public List<MauSanPham> laydanhsachmau() {
		Session session = sessionFactory.getCurrentSession();
		String query = "from mausanpham ";
		List<MauSanPham> listmau = session.createQuery(query).getResultList();
		return listmau;
	}

}
