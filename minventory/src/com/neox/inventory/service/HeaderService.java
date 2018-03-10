package com.neox.inventory.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.neox.inventory.model.material.Header;
import com.neox.inventory.util.HibernateUtil;

public class HeaderService {
	
	public static boolean save(Header header) {
		boolean value = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			
			session.save(header);
			
			value = (header.getId() != 0);
			
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
