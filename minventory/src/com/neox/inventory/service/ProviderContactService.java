package com.neox.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neox.inventory.model.provider.ProviderContact;
import com.neox.inventory.util.HibernateUtil;

public class ProviderContactService {
	
	public static ProviderContact byName(String name) {
		ProviderContact value = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<ProviderContact> q = session.createQuery("from ProviderContact where name = :name");
			q.setParameter("name", name);
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
	
	public static List<ProviderContact> getAll(Integer idProvider) {
		List<ProviderContact> list = new ArrayList<ProviderContact>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<ProviderContact> q = session.createQuery("from ProviderContact where idProvider = :idProvider order by name asc");
			q.setParameter("idProvider", idProvider);
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
	
	public static List<ProviderContact> getList(Integer idProvider) {
		List<ProviderContact> list = new ArrayList<ProviderContact>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<ProviderContact> q = session.createQuery("from ProviderContact where idProvider = :idProvider and active = 1 order by name asc");
			q.setParameter("idProvider", idProvider);
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
