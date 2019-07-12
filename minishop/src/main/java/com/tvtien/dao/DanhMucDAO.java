package com.tvtien.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import com.tvtien.daoimp.DanhmucImp;
import com.tvtien.entity.DanhMucSanPham;

@Repository
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS)
public class DanhMucDAO implements DanhmucImp {

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public List<DanhMucSanPham> Laydanhmuc() {
		Session session = sessionFactory.getCurrentSession();
		String query = "from danhmucsanpham";
		List<DanhMucSanPham> listDanhmuc = session.createQuery(query).getResultList();
		return listDanhmuc;
	}

}
