package com.neox.inventory.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neox.inventory.model.material.MaterialProviderPrice;
import com.neox.inventory.util.HibernateUtil;

public class MaterialProviderPriceService {
	
	public static MaterialProviderPrice byIds(Integer idMaterial, Integer idProvider) {
		MaterialProviderPrice value = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			//Query<Status> q = session.createQuery("from Status where upper(status) = upper(:status) and active = true ");
			Query<MaterialProviderPrice> q = session.createQuery("from MaterialProviderPrice where idMaterial = :idMaterial and idProvider = :idProvider"); 
			q.setParameter("idMaterial", idMaterial);
			q.setParameter("idProvider", idProvider);
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
	
	public static MaterialProviderPrice byId(Integer id) {
		MaterialProviderPrice value = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			//Query<Status> q = session.createQuery("from Status where upper(status) = upper(:status) and active = true ");
			Query<MaterialProviderPrice> q = session.createQuery("from MaterialProviderPrice where id = :id"); 
			q.setParameter("id", id);
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
