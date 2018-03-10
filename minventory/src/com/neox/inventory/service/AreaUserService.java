package com.neox.inventory.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neox.inventory.model.area.Area;
import com.neox.inventory.model.area.AreaUser;
import com.neox.inventory.util.HibernateUtil;

public class AreaUserService {
	
	public static AreaUser byIds(Integer idArea, Integer idUser) {
		AreaUser value = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<AreaUser> q = session.createQuery("from AreaUser where idArea = :idArea and idUser = :idUser");
			q.setParameter("idArea", idArea);
			q.setParameter("idUser", idUser);
			value = q.uniqueResult();
			
			tx.commit();
		} catch(HibernateException e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return value;
	}
	
}
