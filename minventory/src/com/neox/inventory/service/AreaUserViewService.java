package com.neox.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neox.inventory.model.area.AreaUserView;
import com.neox.inventory.util.HibernateUtil;

public class AreaUserViewService {
	
	public static List<AreaUserView> getListByArea(Integer idArea) {
		List<AreaUserView> list = new ArrayList<AreaUserView>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<AreaUserView> q = session.createQuery("from AreaUserView where idArea = :idArea and name is not null order by name asc");
			q.setParameter("idArea", idArea);
			list = q.list();
			
			tx.commit();
		} catch(HibernateException e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
}
