package com.neox.inventory.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.neox.inventory.model.material.Category;
import com.neox.inventory.util.HibernateUtil;

public class ObjectService {
	
	public static boolean delete(Object ...objects) {
		boolean value = true;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			for(Object obj:objects) {
				if(obj != null) {
					session.delete(obj);
				}
			}
			
			tx.commit();
		} catch(HibernateException e) {
			if(tx != null) {
				tx.rollback();
			}
			//e.printStackTrace();
			value = false;
		} finally {
			session.close();
		}
		return value;
	}
	
//	public static boolean save(Object data) {
//		boolean value = true;
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		Transaction tx = null;
//		try {
//			tx = session.beginTransaction();
//			session.saveOrUpdate(data);
//			tx.commit();
//		} catch(Exception e) {
//			if(tx != null) {
//				tx.rollback();
//			}
//			e.printStackTrace();
//			value = false;
//		} finally {
//			session.close();
//		}
//		return value;
//	}
	
	public static boolean save(Object ...objects) {
		boolean value = true;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			/*session.save(outcome);
			session.save(material);*/
			for(Object obj:objects) {
				if(obj != null) {
					session.saveOrUpdate(obj);
				}
			}
			
			tx.commit();
		} catch(HibernateException e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
			value = false;
		} finally {
			session.close();
		}
		return value;
	}
	
}
