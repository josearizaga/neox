package com.neox.inventory.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neox.inventory.model.area.AreaMaterial;
import com.neox.inventory.util.HibernateUtil;

public class AreaMaterialService {
	
	public static AreaMaterial getByIds(Integer idArea, Integer idMaterial) {
		AreaMaterial value = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query<AreaMaterial> q = session.createQuery("from AreaMaterial where idArea = :idArea and idMaterial = :idMaterial");
			q.setParameter("idArea", idArea);
			q.setParameter("idMaterial", idMaterial);
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
