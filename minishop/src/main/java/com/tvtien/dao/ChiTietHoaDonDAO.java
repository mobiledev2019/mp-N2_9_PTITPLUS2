package com.tvtien.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import com.tvtien.daoimp.ChiTietHoaDonImp;
import com.tvtien.entity.ChiTietHoaDon;
import com.tvtien.entity.ChiTietHoaDonid;

@Repository
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ChiTietHoaDonDAO implements ChiTietHoaDonImp {
	@Autowired
	SessionFactory sessionFactory ;
	
	@Transactional
	public boolean themchitiethoadon(ChiTietHoaDon chiTietHoaDon) {
		Session session = sessionFactory.getCurrentSession();
		ChiTietHoaDonid id = (ChiTietHoaDonid) session.save(chiTietHoaDon) ;
		if(null != id){
			return true ;
		}
		else{
			return false;
		}
	
	}

}
