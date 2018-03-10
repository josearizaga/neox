package com.neox.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neox.inventory.model.material.MaterialView;
import com.neox.inventory.util.HibernateUtil;

public class MaterialViewService {
	
	public static List<MaterialView> getAll() {
		List<MaterialView> list = new ArrayList<MaterialView>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from MaterialView order by material asc");
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
	
	public static List<MaterialView> getList() {
		List<MaterialView> list = new ArrayList<MaterialView>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query q = session.createQuery("from MaterialView where active = 1 order by material asc");
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
