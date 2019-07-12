package com.tvtien.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import com.tvtien.daoimp.HoaDonimp;
import com.tvtien.entity.HoaDon;

@Repository
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS)
public class HoaDonDAO implements HoaDonimp {
	@Autowired
	SessionFactory sessionFactory ;
	
	@Transactional
	public int ThemHoaDon(HoaDon hoadon) {
		Session session = sessionFactory.getCurrentSession();
		int id =  (Integer) session.save(hoadon);
		if(0 != id ){
			return id;
		}else{
			return 0;
		}
		
	}

}
