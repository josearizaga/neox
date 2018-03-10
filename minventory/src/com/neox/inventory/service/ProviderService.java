package com.neox.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neox.inventory.model.provider.Provider;
import com.neox.inventory.util.HibernateUtil;

public class ProviderService {
	
	public static List<Provider> byIds(Integer idMaterial, Integer idLocation) {
		List<Provider> list = new ArrayList<Provider>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<Provider> q = session.createQuery("from Provider where id in (select i.idProvider from Inventory i where i.idMaterial = :idMaterial and i.idLocation = :idLocation) order by name asc");
			q.setParameter("idMaterial", idMaterial);
			q.setParameter("idLocation", idLocation);
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
	
	public static List<Provider> getAll() {
		List<Provider> list = new ArrayList<Provider>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<Provider> q = session.createQuery("from Provider order by name asc");
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
	
	public static List<Provider> getList() {
		List<Provider> list = new ArrayList<Provider>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<Provider> q = session.createQuery("from Provider where active = 1 order by name asc");
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
