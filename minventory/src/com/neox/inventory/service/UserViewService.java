package com.neox.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neox.inventory.model.area.AreaUserView;
import com.neox.inventory.model.user.UserView;
import com.neox.inventory.util.HibernateUtil;

public class UserViewService {
	
	public static List<UserView> getListByIdArea(Integer idArea,boolean in) {
		List<UserView> list = new ArrayList<UserView>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<UserView> q = session.createQuery("from UserView where id "+((!in)?"not":"")+" in (select idUser from AreaUserView where idArea = :idArea) and active = true order by name asc");
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
