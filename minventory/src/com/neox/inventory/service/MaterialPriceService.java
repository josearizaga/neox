package com.neox.inventory.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neox.inventory.model.material.MaterialPrice;
import com.neox.inventory.util.HibernateUtil;

public class MaterialPriceService {
	
	public static MaterialPrice byIdMaterial(Integer idMaterial) {
		MaterialPrice material = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			//Query<Status> q = session.createQuery("from Status where upper(status) = upper(:status) and active = true ");
			Query<MaterialPrice> q = session.createQuery("from MaterialPrice where idMaterial = :idMaterial"); 
			q.setParameter("idMaterial", idMaterial);
			material = q.uniqueResult();
			
			tx.commit();
		} catch(HibernateException e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return material;
	}
	
	public static MaterialPrice byIdl(Integer id) {
		MaterialPrice material = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			//Query<Status> q = session.createQuery("from Status where upper(status) = upper(:status) and active = true ");
			Query<MaterialPrice> q = session.createQuery("from MaterialPrice where id = :id"); 
			q.setParameter("id", id);
			material = q.uniqueResult();
			
			tx.commit();
		} catch(HibernateException e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return material;
	}
	
}
