package com.tvtien.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import com.tvtien.daoimp.SizeSanPhamimp;
import com.tvtien.entity.SizeSanPham;

@Repository
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS)
public class SizeSanPhamDAO implements SizeSanPhamimp {
	@Autowired
	SessionFactory sessionFactory ;
	
	@Transactional
	public List<SizeSanPham> LaydanhsachSize() {
		Session session = sessionFactory.getCurrentSession();
		String query = "from sizesanpham ";
		List<SizeSanPham> listsize = session.createQuery(query).getResultList();
		return listsize;
	}
}
