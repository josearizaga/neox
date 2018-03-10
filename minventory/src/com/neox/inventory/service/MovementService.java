package com.neox.inventory.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neox.inventory.model.material.Movement;
import com.neox.inventory.util.HibernateUtil;

public class MovementService {
	
	public static Movement byName(String name) {
		Movement movement = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			Query<Movement> q = session.createQuery("from Movement where upper(movement) = :movement and active = true ");
			q.setParameter("movement", name);
			if(q.list().size() > 0) {
				movement = q.getResultList().get(0);
			}
			
			tx.commit();
		} catch(HibernateException e) {
			if(tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return movement;
	}
	
}
